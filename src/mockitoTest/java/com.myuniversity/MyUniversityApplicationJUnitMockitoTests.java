package com.myuniversity;

import static org.assertj.core.api.Assertions.assertThat;
import com.myuniversity.controller.StudentController;

import com.myuniversity.entities.Student;


import com.myuniversity.utils.StudentTransformationUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class MyUniversityApplicationJUnitMockitoTests {

    @InjectMocks
    StudentController studentController;
    @InjectMocks
    StudentTransformationUtils studentTransformationUtils;


    @Test
    public void testAddStudent()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(studentController.saveStudent(ArgumentMatchers.any(Student.class)))
                .thenReturn(new ResponseEntity(new Student(), HttpStatus.OK));

        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setFirstName("Varun");
        student.setLastName("Deshpande");
        student.setClassName("3 A");
        student.setNationality("indian");

        ResponseEntity<Student> responseEntity = studentController.saveStudent(student);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }
}
