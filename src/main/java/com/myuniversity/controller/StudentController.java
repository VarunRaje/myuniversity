package com.myuniversity.controller;
import com.myuniversity.dao.StudentDao;
import com.myuniversity.entities.Student;
import com.myuniversity.repository.StudentRepository;
import com.myuniversity.utils.StudentTransformationUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
@Api(value = "Student Controller", description = "REST Apis related to Student Entity!!!!")
public class StudentController {

    @Autowired
    Student student;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentTransformationUtils studentTransformationUtils;



    /**
     * Get all Student
     * <p><b>200</b> - OK
     * <p><b>400</b> - Bad Request
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * <p><b>500</b> - Internal Server Error
     * @return ResponseEntity<List<Student>>
     */
    @GetMapping("/student")
    @ApiOperation(value = "Get list of Students in the System ", tags = "getAllStudents")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    public ResponseEntity<List<Student>> getStudent() {
        log.info("Fetching All Student");
        List<StudentDao> studentDaoList = studentRepository.findAll();
        List<Student> studentList = studentTransformationUtils.transformStudent(studentDaoList);
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    /**
     * Get Student by Id
     * <p><b>200</b> - OK
     * <p><b>400</b> - Bad Request
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * <p><b>500</b> - Internal Server Error
     * @pathParam - studentId
     * @return ResponseEntity<Student>
     */
    @GetMapping("/student/{id}")
    @ApiOperation(value = "Get specific Student in the System ", response = Student.class, tags = "getStudent")
    public ResponseEntity<Student> getStudentById(@PathVariable String id){
        log.info("Fetching student with id {}", id);
        Optional<StudentDao> studentDao = studentRepository.findById(id);
        if(studentDao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "student Not Found");
        }
        student = studentTransformationUtils.convertValue(studentDao.get(), Student.class);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    /**
     * Save Student
     * <p><b>201</b> - OK
     * <p><b>400</b> - Bad Request
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * <p><b>500</b> - Internal Server Error
     * @param - Student Student json
     * @return ResponseEntity<Student>
     */
    @PostMapping("/student")
    @ApiOperation(value = "Add Student in the System ", response = Student.class, tags = "saveStudent")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        StudentDao studentDao = studentTransformationUtils.convertValue(student, StudentDao.class);
        studentDao.setId(UUID.randomUUID().toString());
        studentRepository.save(studentDao);
        student.setId(studentDao.getId());
        log.info("Added Student {} with id {}", student.getFirstName() + " " + student.getLastName(), student.getId());
        return new ResponseEntity<>(student, HttpStatus.ACCEPTED);
    }


    /**
     * Update Student
     * <p><b>200</b> - OK
     * <p><b>400</b> - Bad Request
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * <p><b>500</b> - Internal Server Error
     * @param - Student Student json
     * @return ResponseEntity<Student>
     */
    @PutMapping("/student")
    @ApiOperation(value = "update specific Student in the System ", response = Student.class, tags = "updateStudent")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        Optional<StudentDao> studentDaoOptional = studentRepository.findById(student.getId());
        if(studentDaoOptional.isEmpty()){
            log.info("Student with id {} not found", student.getId());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "student Not Found");
        }
        StudentDao studentDao = studentDaoOptional.get();
        studentDao.setFirstName(student.getFirstName() != null ? student.getFirstName() : studentDao.getFirstName());
        studentDao.setLastName(student.getLastName() != null ? student.getLastName() : studentDao.getLastName());
        studentDao.setClassName(student.getClassName() != null ? student.getClassName() : studentDao.getClassName());
        studentDao.setNationality(student.getNationality() != null ? student.getNationality() : studentDao.getNationality());

        studentRepository.save(studentDao);
        return new ResponseEntity<>(studentTransformationUtils.convertValue(studentDao, Student.class), HttpStatus.ACCEPTED);
    }


    /**
     * Delete Student
     * <p><b>204</b> - OK
     * <p><b>400</b> - Bad Request
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * <p><b>500</b> - Internal Server Error
     * @param - Student Student json
     * @return ResponseEntity<Student>
     */
    @DeleteMapping("/student")
    @ApiOperation(value = "Delete specific Student in the System ", response = Student.class, tags = "deleteStudent")
    public ResponseEntity deleteStudent(@RequestBody Student student){
        studentRepository.deleteById(student.getId());
        log.info("student with id {} deleted successfully" , student.getId());
        return ResponseEntity.noContent().build();
    }

}
