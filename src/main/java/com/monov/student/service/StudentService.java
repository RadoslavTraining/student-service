package com.monov.student.service;

import com.monov.student.dto.ItemIds;
import com.monov.student.dto.StudentDTO;
import com.monov.student.entity.Student;
import com.monov.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO saveStudent(Student student) {
        log.info("Inside saveStudent method in StudentService");
        return new StudentDTO(studentRepository.save(student));
    }

    public StudentDTO findStudentById(Long studentId) {
        log.info("Inside findStudentById method in StudentService");
        return new StudentDTO(studentRepository.findById(studentId).get());
    }

    public List<StudentDTO> findAllStudents(){
        return convertToStudentDTOs(studentRepository.findAll());
    }

    public List<StudentDTO> findStudentsByIds(ItemIds studentIds) {
        return convertToStudentDTOs(studentRepository.findAllById(studentIds.getIds()));
    }

    private List<StudentDTO> convertToStudentDTOs(List<Student> studentEntities) {
        return studentEntities.stream()
                .map(StudentDTO::new)
                .collect(Collectors.toList());
    }
}
