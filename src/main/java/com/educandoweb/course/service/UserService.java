package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.User;
import com.educandoweb.course.repository.GenericRepository;
import com.educandoweb.course.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.educandoweb.course.domain.enums.OrderStatus.SHIPPED;
import static com.educandoweb.course.util.Constants.OPERATION_DELETE_FAIL;
import static com.educandoweb.course.util.Constants.OPERATION_END;
import static com.educandoweb.course.util.Constants.OPERATION_SAVE_FAIL;
import static com.educandoweb.course.util.Constants.OPERATION_START;
import static com.educandoweb.course.util.Constants.OPERATION_UPDATE_FAIL;

@Slf4j
@Service
public class UserService extends GenericService<User> {

    private static final Class<User> USER_CLASS = User.class;

    private UserRepository repository;

    public UserService(GenericRepository<User> repository, MessageSource messageSource) {
        super(repository, messageSource);
        this.repository = (UserRepository) repository;
    }

    public User saveUser(User request) {
        loggingOperation(OPERATION_START, USER_CLASS);

        isUsernameOrEmailExists(request.getNmUser(), request.getDsEmail(), OPERATION_SAVE_FAIL);

        if (request.getDsPassword().isBlank()) {
            loggingError(OPERATION_SAVE_FAIL, USER_CLASS);
            throw new RuntimeException("Your password can't be null or empty");
        }

        User response = create(request, USER_CLASS);

        loggingOperation(OPERATION_END, USER_CLASS);

        return response;
    }

    public User findUserById(Long id) {
        loggingOperation(OPERATION_START, USER_CLASS);

        User response = findById(id, USER_CLASS);

        loggingOperation(OPERATION_END, USER_CLASS);

        return response;
    }

    public List<User> findAllUsers() {
        loggingOperation(OPERATION_START, USER_CLASS);

        List<User> response = findAll(USER_CLASS);

        loggingOperation(OPERATION_END, USER_CLASS);

        return response;
    }

    public User updateUser(Long id, User request) {
        loggingOperation(OPERATION_START, USER_CLASS);

        User oldUser = findUserById(id);

        String username = request.getNmUser();
        String email = request.getDsEmail();

        boolean isNmUserChanged = !username.equals(oldUser.getNmUser());
        boolean isDsEmailChanged = !email.equals(oldUser.getDsEmail());

        if (isNmUserChanged || isDsEmailChanged) {
            isUsernameOrEmailExists(username, email, OPERATION_UPDATE_FAIL);
        }

        User response = update(id, request, USER_CLASS);

        loggingOperation(OPERATION_END, USER_CLASS);

        return response;
    }

    public void deleteUser(Long id) {
        loggingOperation(OPERATION_START, USER_CLASS);

        User response = findUserById(id);

        if (!response.getOrderList().isEmpty()) {
            response.getOrderList().stream()
                    .filter(order -> SHIPPED.equals(order.getOrderStatus()))
                    .findFirst()
                    .ifPresent(order -> {
                        loggingError(OPERATION_DELETE_FAIL, USER_CLASS);
                        throw new RuntimeException("An user with shipped order can't be deleted");
                    });
        }

        delete(id, USER_CLASS);

        loggingOperation(OPERATION_END, USER_CLASS);
    }

    private void isUsernameOrEmailExists(String user, String email, String errorMessage) {
        if (repository.existsByNmUserOrDsEmail(user, email)) {
            String message = getMessage(errorMessage);
            log.error(message);
            throw new RuntimeException(message);
        }
    }
}
