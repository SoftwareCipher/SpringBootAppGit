package com.springboot.app.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EntityIncorrectData> handlerException(
            NoSuchEntityException exception){
        EntityIncorrectData data = new EntityIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityIncorrectData> handlerException(Exception e){
        EntityIncorrectData data = new EntityIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
