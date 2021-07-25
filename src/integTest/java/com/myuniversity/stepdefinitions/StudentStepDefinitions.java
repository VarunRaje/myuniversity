package com.myuniversity.stepdefinitions;


import com.myuniversity.controller.StudentController;
import com.myuniversity.dao.StudentDao;
import com.myuniversity.entities.Student;
import com.myuniversity.repository.StudentRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;

import java.util.List;
import java.util.UUID;

/**
 * This class contains implementation of BBD feature file.
 */
@Slf4j
public class StudentStepDefinitions {
    private static final Logger LOG = LoggerFactory.getLogger(StudentStepDefinitions.class);

    public ResponseEntity<Student> studentResponse;
    public ResponseEntity<List<Student>> listStudent;
    @Autowired
    private StudentController studentController;

    @Autowired
    private StudentRepository studentRepository;

    @When("^user calls \"(.+)\" API$")
    public void whenUserCallsSaveAPI(String api){
        LOG.info("API called is {}", api);
        switch (api){
            case "GET":
                this.listStudent = this.studentController.getStudent();
                break;
            case "POST":
                Student student = new Student();
                student.setId(UUID.randomUUID().toString());
                student.setFirstName("Varun");
                student.setLastName("Deshpande");
                student.setClassName("3 A");
                student.setNationality("indian");

                this.studentResponse = this.studentController.saveStudent(student);
                break;
        }

    }

    @Then("^user receives status code of \"(.+)\"$")
    public void thenUserReceivesStatusCode(String status){
        LOG.info("Verifying status is {}", status);
        switch (status){
            case "201":
                Assert.assertEquals(this.studentResponse.getStatusCode(), HttpStatus.ACCEPTED);
                break;
            case "200":
                Assert.assertEquals(this.listStudent.getStatusCode(), HttpStatus.OK);
                break;
        }

    }

    @And("^student added successfully$")
    public void studentAddedSuccessfully(){
        LOG.info("Checking if user added Successfully");
        List<StudentDao> studentDaoList = this.studentRepository.findAll();
        Assert.assertEquals(studentDaoList.size(), 2);

    }

    @And("^student list returned$")
    public void studentListReturned(){
        LOG.info("Checking retrieved numbered of students");
        Assert.assertNotNull(this.listStudent.getBody());

    }

}
