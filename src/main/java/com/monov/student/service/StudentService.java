package com.monov.student.service;

import com.monov.student.entity.Student;
import com.monov.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService {

    private static final String API_GATEWAY = "API-GATEWAY";
    private static final String COURSES_ENDPOINT = "courses";

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        log.info("Inside saveStudent method in StudentService");
        return studentRepository.save(student);
    }

    public Student findStudentById(Long studentId) {
        log.info("Inside findStudentById method in StudentService");
        return studentRepository.findByStudentId(studentId);
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public Student addCourseToStudent(Long studentId, Long courseId) {
        Student studentToEdit = findStudentById(studentId);
        studentToEdit.getCourseIds().add(courseId);
        studentRepository.save(studentToEdit);
        return  studentToEdit;
    }

    // To Do
    // Created by Radoslav Monov 14.01.2022
    // https://estafetducationsite.atlassian.net/browse/ED-11
    // Create endpoint to get all students that attend a certain course
    public List<Student> getStudentsByCourseId(Long courseId) {

        return null;
    }
}
