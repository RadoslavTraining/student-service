package com.monov.student.exception;

import com.monov.commons.exceptions.ItemNotFoundException;
import com.monov.student.response.StudentResponseHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StudentResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @Value("${exceptions.item-not-found.message}")
    private String itemNotFoundMessage;

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFoundException(ItemNotFoundException ex) {
        return StudentResponseHandler.generateErrorResponse(String.format("Student with id %d not found", ex.getId()),
                HttpStatus.NOT_FOUND);
    }

}
