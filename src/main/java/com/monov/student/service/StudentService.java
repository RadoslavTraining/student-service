package com.monov.student.service;

import com.monov.student.data.ItemIds;
import com.monov.student.entity.Student;
import com.monov.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        log.info("Inside saveStudent method in StudentService");
        return studentRepository.save(student);
    }

    public Student findStudentById(Long studentId) {
        log.info("Inside findStudentById method in StudentService");
        return studentRepository.findById(studentId).get();
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public List<Student> findStudentsByIds(ItemIds studentIds) {
        return studentRepository.findAllById(studentIds.getIds());
    }
}
