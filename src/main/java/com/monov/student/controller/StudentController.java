package com.monov.student.controller;

import com.monov.student.entity.Student;
import com.monov.student.service.StudentService;
import com.monov.student.data.Course;
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

    @GetMapping("/")
    public List<Student > findAllStudents() {
        log.info("Inside findAllStudents method of StudentController ");
        return studentService.findAllStudents();
    }

    @PostMapping("/")
    public Student saveStudent(@RequestBody Student student) {
        log.info("Inside saveStudent method of StudentController ");
        return studentService.saveStudent(student);
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable("id") Long studentId) {
        log.info("Inside findStudentById method of StudentController ");
        return studentService.findStudentById(studentId);
    }

    @GetMapping("/{id}/courses")
    public List<Course> getCoursesForStudent(@PathVariable("id") Long id) {
        log.info("Inside getCoursesForStudent method of StudentController ");
        return studentService.getCoursesForStudent(studentService.findStudentById(id));
    }

    @PostMapping("/{studentId}/{courseId}")
    public Student addCourseToStudent(@PathVariable("studentId") Long studentId,
                                      @PathVariable("courseId") Long courseId) {
        log.info("Inside addCourseToStudent method of StudentController ");
        return  studentService.addCourseToStudent(studentId,courseId);
    }

}
