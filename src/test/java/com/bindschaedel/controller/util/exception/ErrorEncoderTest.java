package com.bindschaedel.controller.util.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ErrorEncoderTest {

    private static final String MESSAGE = "msg";
    private static final String ERROR_MESSAGE = "err";
    private static final String PROPERTY_PATH_1 = "path1";
    private static final String PROPERTY_PATH_2 = "path2";

    private final ErrorEncoder errorEncoder = new ErrorEncoder();

    @Mock
    private TransactionSystemException transactionSystemExceptionMock;

    @Mock
    private NumberFormatException numberFormatException;

    @Test
    public void encodeTransactionSystemExceptionDerivedFromConstraintViolation() {
        Map<String, String> expected = new HashMap<>();
        expected.put(PROPERTY_PATH_1, ERROR_MESSAGE);
        expected.put(PROPERTY_PATH_2, ERROR_MESSAGE);
        Set<ConstraintViolation<?>> constraintViolations = new HashSet<>();
        ConstraintViolation<?> cv1 = mockCv(PROPERTY_PATH_1);
        ConstraintViolation<?> cv2 = mockCv(PROPERTY_PATH_2);
        constraintViolations.add(cv1);
        constraintViolations.add(cv2);
        ConstraintViolationException cve = new ConstraintViolationException(constraintViolations);
        when(transactionSystemExceptionMock.getOriginalException()).thenReturn(new Exception(cve));

        assertEncoding(expected, transactionSystemExceptionMock);
    }

    @Test
    public void encodeTransactionSystemExceptionNotDerivedFromConstraintViolation() {
        Map<String, String> expected = defaultErrorMap();
        when(transactionSystemExceptionMock.getLocalizedMessage()).thenReturn(MESSAGE);
        when(transactionSystemExceptionMock.getOriginalException()).thenReturn(null);

        assertEncoding(expected, transactionSystemExceptionMock);

        when(transactionSystemExceptionMock.getOriginalException()).thenReturn(new Exception());

        assertEncoding(expected, transactionSystemExceptionMock);
    }

    @Test
    public void encodeNumberFormatException() {
        when(numberFormatException.getMessage()).thenReturn(MESSAGE);

        assertThat(errorEncoder.encode(numberFormatException)).isEqualTo(defaultErrorMap());
    }

    private void assertEncoding(Map<String, String> expected, TransactionSystemException ex) {
        Map<String, String> encodedEx = errorEncoder.encode(ex);
        assertThat(encodedEx).isEqualTo(expected);
    }

    private static ConstraintViolation<?> mockCv(String propertyPath) {
        Path pathMock = mock(Path.class);
        when(pathMock.toString()).thenReturn(propertyPath);

        ConstraintViolation<?> mock = mock(ConstraintViolation.class);
        when(mock.getPropertyPath()).thenReturn(pathMock);
        when(mock.getMessage()).thenReturn(ERROR_MESSAGE);
        return mock;
    }

    private static Map<String, String> defaultErrorMap() {
        Map<String, String> m = new HashMap<>();
        m.put("error", MESSAGE);
        return m;
    }
}
