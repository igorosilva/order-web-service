package com.educandoweb.course.service;

import com.educandoweb.course.domain.entity.User;
import com.educandoweb.course.repository.GenericRepository;
import com.educandoweb.course.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Locale;
import java.util.Optional;

import static com.educandoweb.course.factory.UserFactory.createUser;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenericServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private MessageSource messageSource;

    private User user;

    private Class<User> userClass = User.class;

    @BeforeEach
    void setUp() {
        user = createUser();

        lenient().when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Message test");
    }

    void prepareWhen() {
        lenient().when(repository.save(any())).thenReturn(user);
        lenient().when(repository.findById(anyLong())).thenReturn(ofNullable(user));
    }

    void verifyCommonMethods(int times) {
        verify(messageSource, times(times)).getMessage(anyString(), any(), any(Locale.class));
    }

    void assertResult(Object expected, Object result, int times) {
        verifyCommonMethods(times);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void testCreate() {
        prepareWhen();

        var result = service.create(user, userClass);

        assertResult(user, result, 3);
    }

    @Test
    void testFindById_whenSuccess() {
        prepareWhen();

        var result = service.findById(user.getId(), userClass);

        assertResult(user, result, 3);
    }
}
