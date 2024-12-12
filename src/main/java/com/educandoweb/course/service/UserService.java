package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.User;
import com.educandoweb.course.repository.GenericRepository;
import com.educandoweb.course.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.of;

@Slf4j
@Service
public class UserService extends GenericService<User> {

    @Autowired
    private UserRepository userRepository;

    public UserService(GenericRepository<User> repository) {
        super(repository);
    }

    public User saveUser(User user) {
        log.info("Saving user: {}", user);

        if (isUsernameOrEmailExists(user)) {
            return null;
        }

        return save(user);
    }

    public User findUserById(Long id) {
        return findById(id);
    }

    public List<User> findAllUsers() {
        return findAll();
    }

    public User updateUser(Long id, User user) {
        User oldUser = findUserById(id);

        boolean isNmUserChanged = !user.getNmUser().equals(oldUser.getNmUser());
        boolean isDsEmailChanged = !user.getDsEmail().equals(oldUser.getDsEmail());

        if (isNmUserChanged || isDsEmailChanged) {
            if (isUsernameOrEmailExists(user)) {
                return null;
            }
        }

        return update(id, user);
    }

    public void deleteUser(Long id) {
        if (isUserExists(id)) {
            delete(id);
        }
    }

    private boolean isUserExists(Long id) {
        return isExists(id);
    }

    private boolean isUsernameOrEmailExists(User user) {
        return userRepository.existsByNmUserOrDsEmail(user.getNmUser(), user.getDsEmail());
    }
}
