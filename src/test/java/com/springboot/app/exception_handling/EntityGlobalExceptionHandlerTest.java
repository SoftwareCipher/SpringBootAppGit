package com.springboot.app.exception_handling;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntityGlobalExceptionHandlerTest {
    @Test
    void testHandlerException() {
        EntityGlobalExceptionHandler userGlobalExceptionHandler = new EntityGlobalExceptionHandler();
        ResponseEntity<EntityIncorrectData> actualHandlerException
                = userGlobalExceptionHandler
                .handlerException(new NoSuchEntityException("An error occurred"));
        assertTrue(actualHandlerException.hasBody());
        assertTrue(actualHandlerException.getHeaders().isEmpty());
        assertEquals(HttpStatus.NOT_FOUND, actualHandlerException.getStatusCode());
        assertEquals("An error occurred", actualHandlerException.getBody().getInfo());
    }
}

