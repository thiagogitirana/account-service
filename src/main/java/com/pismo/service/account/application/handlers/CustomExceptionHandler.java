package com.pismo.service.account.application.handlers;

import com.pismo.service.account.application.dto.ErrorMessageDTO;
import com.pismo.service.account.domain.exceptions.DuplicatedRecordException;
import com.pismo.service.account.domain.exceptions.LimitException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorMessageDTO> validationErros(Exception exception) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessageDTO(exception.getMessage()));
    }

    @ExceptionHandler(value = {DuplicatedRecordException.class})
    public ResponseEntity<ErrorMessageDTO> validationDuplicatedErros(Exception exception) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessageDTO(exception.getMessage()));
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ErrorMessageDTO> validationNotFoundErros(Exception exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(exception.getMessage()));
    }

    @ExceptionHandler(value = {LimitException.class})
    public ResponseEntity<ErrorMessageDTO> validationLimitErros(Exception exception) {

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorMessageDTO(exception.getMessage()));
    }
}
