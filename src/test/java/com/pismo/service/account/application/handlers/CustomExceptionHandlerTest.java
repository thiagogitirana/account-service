package com.pismo.service.account.application.handlers;

import com.pismo.service.account.application.dto.ErrorMessageDTO;
import com.pismo.service.account.domain.exceptions.DuplicatedRecordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandler;

    @BeforeEach
    public void setUp() {
        customExceptionHandler = new CustomExceptionHandler();
    }

    @Test
    public void validationErros_ShouldReturnBadRequest() {

        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");

        ResponseEntity<ErrorMessageDTO> response = customExceptionHandler.validationErros(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid argument", response.getBody().message());
    }

    @Test
    public void shouldReturnConflictWhenThrowDuplicatedRecordException() {

        DuplicatedRecordException exception = new DuplicatedRecordException("Record already exists");

        ResponseEntity<ErrorMessageDTO> response = customExceptionHandler.validationDuplicatedErros(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Record already exists", response.getBody().message());
    }

    @Test
    public void shouldReturnNotFoundWhenThrowNotFoundException() {

        NotFoundException exception = new NotFoundException("Resource not found");

        ResponseEntity<ErrorMessageDTO> response = customExceptionHandler.validationNotFoundErros(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", response.getBody().message());
    }
}
