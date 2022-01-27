package com.monov.student.service;

import com.monov.commons.dto.ItemIdsDTO;
import com.monov.commons.dto.StudentDTO;
import com.monov.commons.exceptions.ItemNotFoundException;
import com.monov.student.entity.Student;
import com.monov.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO saveStudent(Student student) {
        log.info("Inside saveStudent method in StudentService");
        return convertToStudentDTO(studentRepository.save(student));
    }

    public StudentDTO findStudentById(String studentId) {
        log.info("Inside findStudentById method in StudentService");
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()) {
            return convertToStudentDTO(student.get());
        }
        throw new ItemNotFoundException(studentId);
    }

    public List<StudentDTO> findAllStudents(){
        return convertToStudentDTOs(studentRepository.findAll());
    }

    public List<StudentDTO> findStudentsByIds(ItemIdsDTO studentIds) {
        return convertToStudentDTOs(studentRepository.findAllById(studentIds.getIds()));
    }

    private List<StudentDTO> convertToStudentDTOs(List<Student> studentEntities) {
        return studentEntities.stream()
                .map(this::convertToStudentDTO)
                .collect(Collectors.toList());
    }

    private StudentDTO convertToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());

        return studentDTO;
    }
}
