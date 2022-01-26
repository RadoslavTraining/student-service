package com.monov.student.controller;

import com.monov.commons.dto.ItemIdsDTO;
import com.monov.student.StudentServiceApplication;
import com.monov.student.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static com.monov.commons.utils.StringUtils.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = StudentServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = { "spring.config.location = classpath:integration-test-application.yaml" })
@Transactional
public class StudentControllerIntTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void findAllStudents() throws Exception{
        mvc.perform(get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void saveStudent() throws Exception{
        Student student = new Student();
        student.setFirstName("Glenn");
        student.setLastName("Quagmire");
        mvc.perform(
                post("/students")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(student.getFirstName())));
    }

    @Test
    public void findStudentById() throws Exception{
        mvc.perform(get("/students/sampleID1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("sampleID1")));
    }

    @Test
    public void findStudentsByIds() throws Exception {
        ItemIdsDTO ids = new ItemIdsDTO(Arrays.asList("sampleID1","sampleID2"));
        mvc.perform(post("/students/ids")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ids)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}