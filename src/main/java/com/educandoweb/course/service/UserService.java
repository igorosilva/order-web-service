package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.User;
import com.educandoweb.course.repository.GenericRepository;
import com.educandoweb.course.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.educandoweb.course.util.Constants.ENTITY_EXISTS;
import static com.educandoweb.course.util.Constants.OPERATION_END;
import static com.educandoweb.course.util.Constants.OPERATION_SAVE_FAIL;
import static com.educandoweb.course.util.Constants.OPERATION_SEARCH_COMPLETE;
import static com.educandoweb.course.util.Constants.OPERATION_START;
import static com.educandoweb.course.util.Constants.OPERATION_UPDATE_FAIL;

@Slf4j
@Service
public class UserService extends GenericService<User> {

    private UserRepository repository;

    public UserService(GenericRepository<User> repository, MessageSource messageSource) {
        super(repository, messageSource);
        this.repository = (UserRepository) repository;
    }

    public User saveUser(User request) {
        loggingOperation(OPERATION_START, User.class);

        isUsernameOrEmailExists(request.getNmUser(), request.getDsEmail(), OPERATION_SAVE_FAIL);

        User response = save(request, User.class);

        loggingOperation(OPERATION_END, User.class);

        return response;
    }

    public User findUserById(Long id) {
        loggingOperation(OPERATION_START, User.class);

        User response = findById(id, User.class);

        loggingOperation(OPERATION_END, User.class);

        return response;
    }

    public List<User> findAllUsers() {
        loggingOperation(OPERATION_START, User.class);

        List<User> response = findAll(User.class);

        loggingOperation(OPERATION_END, User.class);

        return response;
    }

    public User updateUser(Long id, User request) {
        loggingOperation(OPERATION_START, User.class);

        User oldUser = findUserById(id);

        boolean isNmUserChanged = !request.getNmUser().equals(oldUser.getNmUser());
        boolean isDsEmailChanged = !request.getDsEmail().equals(oldUser.getDsEmail());

        if (isNmUserChanged || isDsEmailChanged) {
            isUsernameOrEmailExists(request.getNmUser(), request.getDsEmail(), OPERATION_UPDATE_FAIL);
        }

        User response = update(id, request, User.class);

        loggingOperation(OPERATION_END, User.class);

        return response;
    }

    public void deleteUser(Long id) {
        loggingOperation(OPERATION_START, User.class);

        delete(id, User.class);

        loggingOperation(OPERATION_END, User.class);
    }

    private void isUsernameOrEmailExists(String user, String email, String errorMessage) {
        if(repository.existsByNmUserOrDsEmail(user, email)) {
            String message = getMessage(errorMessage);
            log.error(message);
            throw new RuntimeException(message);
        }
    }
}
