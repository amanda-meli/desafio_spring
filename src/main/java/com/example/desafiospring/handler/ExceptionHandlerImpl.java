package com.example.desafiospring.handler;

import com.example.desafiospring.exception.BadRequestException;
import com.example.desafiospring.exception.ExceptionDetails;
import com.example.desafiospring.exception.MethodNotAllowedException;
import com.example.desafiospring.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(NotFoundException ex){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Não encontrado.")
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ExceptionDetails> handlerMethodNotAllowedEx(MethodNotAllowedException ex){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Método não implementado.")
                        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDetails> handlerBadRequestEx(BadRequestException ex){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Falha na requisição.")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
