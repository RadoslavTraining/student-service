package com.monov.student.exception;

import com.monov.student.response.StudentResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StudentResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFoundException() {
        return StudentResponseHandler.generateErrorResponse("Student with this id not found", HttpStatus.NOT_FOUND);
    }



}
