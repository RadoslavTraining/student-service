package com.monov.student.response;

import com.monov.commons.dto.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class StudentResponseHandler {

    public static ResponseEntity<StudentDTO> generateSuccessResponse(HttpStatus status, StudentDTO response) {
        return new ResponseEntity<>(response,status);
    }

    public static ResponseEntity<List<StudentDTO>> generateListSuccessResponse(HttpStatus status,
                                                                              List<StudentDTO> response) {
        return new ResponseEntity<>(response,status);
    }

    public static ResponseEntity<String> generateErrorResponse(String message, HttpStatus status){
        return new ResponseEntity<>(message,status);
    }

}
