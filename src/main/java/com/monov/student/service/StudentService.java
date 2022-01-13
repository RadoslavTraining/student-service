package com.monov.student.service;

import com.monov.student.data.CourseIds;
import com.monov.student.entity.Student;
import com.monov.student.repository.StudentRepository;
import com.monov.student.data.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class StudentService {

    private static final String API_GATEWAY = "API-GATEWAY";
    private static final String COURSES_ENDPOINT = "courses";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

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

    public List<Course> getCoursesForStudent(Student student) {
        log.info("Inside getCoursesForStudent method in StudentService");
        CourseIds courseIds = new CourseIds(student.getCourseIds());
        List<Course> courses = Arrays.asList(Objects.requireNonNull(
                restTemplate.postForObject(String.format("http://%s/%s/ids",API_GATEWAY, COURSES_ENDPOINT), courseIds,
                        Course[].class)));
        return courses;
    }

    public Student addCourseToStudent(Long studentId, Long courseId) {
        Student studentToEdit = findStudentById(studentId);
        studentToEdit.getCourseIds().add(courseId);
        studentRepository.save(studentToEdit);
        return  studentToEdit;
    }

}
