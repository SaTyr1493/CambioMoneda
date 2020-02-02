package com.bcp.api.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bcp.api.exception.ErrorCode;
import com.bcp.api.webdto.response.BaseWebResponse;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionRestController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BaseWebResponse> handleEntityNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseWebResponse.error(ErrorCode.TIPOCAMBIO_NO_ENCONTRADO));
    }
}
