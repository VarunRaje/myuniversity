
package com.myuniversity;


import com.myuniversity.controller.StudentController;
import com.myuniversity.dao.StudentDao;
import com.myuniversity.entities.Student;
import com.myuniversity.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class MyUniversityApplicationTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private StudentController studentController;

	@Autowired
	private StudentRepository studentRepository;


	@Test(dataProvider = "saveStudent", dataProviderClass = MyUnniversityDataProvider.class)
	void saveStudentTest(Student student) {

		ResponseEntity<Student> studentResponse = studentController.saveStudent(student);
		Assert.assertEquals(studentResponse.getStatusCode(), HttpStatus.ACCEPTED);
		Assert.assertNotNull(studentResponse.getBody());
		Optional<StudentDao> studentDao = studentRepository.findById(studentResponse.getBody().getId());
		Assert.assertTrue(studentDao.isPresent());
		Assert.assertEquals(studentDao.get().getFirstName(), student.getFirstName());

	}

	@Test(dependsOnMethods = "saveStudentTest")
	void AllStudentTest() {
		ResponseEntity<List<Student>> studentResponse = studentController.getStudent();
		Assert.assertEquals(studentResponse.getStatusCode(), HttpStatus.OK);
		Assert.assertNotNull(studentResponse.getBody());
		Assert.assertEquals(studentResponse.getBody().size(), 2);
	}


}
