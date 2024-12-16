package com.educandoweb.course.service;

import com.educandoweb.course.repository.GenericRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class GenericServiceTest<T> {

    @InjectMocks
    private GenericService<T> service;

    @Mock
    private GenericRepository<T> repository;

    @Mock
    private MessageSource messageSource;

    private T object;

    private Class<T> objectClass;

    @BeforeEach
    void setUp() {
        service = mock(GenericService.class, CALLS_REAL_METHODS);

        ReflectionTestUtils.setField(service, "repository", repository);
        ReflectionTestUtils.setField(service, "messageSource", messageSource);

        lenient().when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Message test");
    }

    void prepareWhen() {
        lenient().when(repository.save(any())).thenReturn(object);
    }

    @Test
    void testCreateObject() {
        prepareWhen();

        var result = service.create((T) object, objectClass);

        assertNotNull(result);
        assertEquals(object, result);
    }
}
