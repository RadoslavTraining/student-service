package com.monov.student.controller;

import com.monov.commons.dto.ItemIds;
import com.monov.commons.dto.StudentDTO;
import com.monov.student.entity.Student;
import com.monov.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDTO> findAllStudents() {
        log.info("Inside findAllStudents method of StudentController ");
        return studentService.findAllStudents();
    }

    @PostMapping
    public StudentDTO saveStudent(@RequestBody Student student) {
        log.info("Inside saveStudent method of StudentController ");
        return studentService.saveStudent(student);
    }

    @GetMapping("/{id}")
    public StudentDTO findStudentById(@PathVariable("id") Long studentId) {
        log.info("Inside findStudentById method of StudentController ");
        return studentService.findStudentById(studentId);
    }

    @PostMapping("/ids")
    public List<StudentDTO> findStudentsByIds(@RequestBody ItemIds studentIds) {
        return studentService.findStudentsByIds(studentIds);
    }

}
