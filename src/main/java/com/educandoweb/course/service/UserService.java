package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.Order;
import com.educandoweb.course.domain.entity.User;
import com.educandoweb.course.repository.GenericRepository;
import com.educandoweb.course.repository.OrderRepository;
import com.educandoweb.course.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    private final UserRepository repository;
    private final OrderRepository orderRepository;

    public UserService(GenericRepository<User> repository, MessageSource messageSource, OrderRepository orderRepository) {
        super(repository, messageSource);
        this.repository = (UserRepository) repository;
        this.orderRepository = orderRepository;
    }

    public User saveUser(User request) {
        loggingOperation(OPERATION_START, USER_CLASS);

        validateUser(request, OPERATION_SAVE_FAIL);

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

        User user = findUserById(id);

        String username = request.getNmUser();
        String email = request.getDsEmail();

        boolean isNmUserChanged = !username.equals(user.getNmUser());
        boolean isDsEmailChanged = !email.equals(user.getDsEmail());

        if ((isNmUserChanged && isUsernameExists(username)) ||
                (isDsEmailChanged && isEmailExists(email))) {
            loggingError(OPERATION_UPDATE_FAIL, USER_CLASS);
            throw new RuntimeException("Username or email already exists");
        }

        updateUserData(user, request);

        User response = create(user, USER_CLASS);

        loggingOperation(OPERATION_END, USER_CLASS);

        return response;
    }

    public void deleteUser(Long id) {
        loggingOperation(OPERATION_START, USER_CLASS);

        User response = findUserById(id);

        boolean hasShippedOrders = orderRepository.existsByClientAndOrderStatus(response, SHIPPED);
        if (hasShippedOrders) {
            loggingError(OPERATION_DELETE_FAIL, USER_CLASS);
            throw new RuntimeException("An user with shipped order can't be deleted");
        }

        delete(id, USER_CLASS);

        loggingOperation(OPERATION_END, USER_CLASS);
    }

    private void validateUser(User user, String message) {
        boolean userExists = isUsernameOrEmailExists(user.getNmUser(), user.getDsEmail());

        if (userExists) {
            log.error(message);
            throw new RuntimeException(message);
        }
    }

    private boolean isUsernameOrEmailExists(String user, String email) {
        return repository.existsByNmUserOrDsEmail(user, email);
    }

    private boolean isUsernameExists(String username) {
        return repository.existsByNmUser(username);
    }

    private boolean isEmailExists(String email) {
        return repository.existsByDsEmail(email);
    }

    private void updateUserData(User user, User request) {
        user.setDsEmail(request.getDsEmail());
        user.setNmUser(request.getNmUser());
        user.setDsPassword(request.getDsPassword());
        user.setNrPhone(request.getNrPhone());
    }
}
