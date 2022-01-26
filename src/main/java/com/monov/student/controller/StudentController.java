package com.monov.student.controller;

import com.monov.commons.dto.ItemIdsDTO;
import com.monov.commons.dto.StudentDTO;
import com.monov.student.entity.Student;
import com.monov.student.response.StudentResponseHandler;
import com.monov.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAllStudents() {
        log.info("Inside findAllStudents method of StudentController ");
        return StudentResponseHandler.generateListSuccessResponse(HttpStatus.OK,studentService.findAllStudents());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> saveStudent(@Valid  @RequestBody Student student) {
        log.info("Inside saveStudent method of StudentController ");
        return StudentResponseHandler.generateSuccessResponse(HttpStatus.OK,studentService.saveStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findStudentById(@PathVariable("id") String studentId) {
        log.info("Inside findStudentById method of StudentController ");
        return StudentResponseHandler.generateSuccessResponse(HttpStatus.OK, studentService.findStudentById(studentId));
    }

    @PostMapping("/ids")
    public ResponseEntity<List<StudentDTO>> findStudentsByIds(@RequestBody ItemIdsDTO studentIds) {
        return StudentResponseHandler.generateListSuccessResponse(HttpStatus.OK,
                studentService.findStudentsByIds(studentIds));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
