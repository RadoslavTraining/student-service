package com.monov.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monov.commons.dto.ItemIds;
import com.monov.student.StudentServiceApplication;
import com.monov.student.entity.Student;
import com.monov.student.service.StudentService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = StudentServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = { "spring.config.location = classpath:application-integrationtest.yaml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentControllerIntTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testAfindAllStudents() throws Exception{
        mvc.perform(get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testBsaveStudent() throws Exception{
        Student student = new Student();
        student.setFirstName("Glenn");
        student.setLastName("Quagmire");
        mvc.perform(
                post("/students")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(student)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)));
    }

    @Test
    public void testCfindStudentById() throws Exception{
        mvc.perform(get("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testDfindStudentsByIds() throws Exception {
        ItemIds ids = new ItemIds(Arrays.asList(1L,2L));
        mvc.perform(post("/students/ids")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ids)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}