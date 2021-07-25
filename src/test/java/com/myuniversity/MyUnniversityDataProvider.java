package com.myuniversity;

import com.myuniversity.entities.Student;
import org.testng.annotations.DataProvider;

import java.util.UUID;

public class MyUnniversityDataProvider {

    @DataProvider(name = "saveStudent")
    public Object[][] saveStudent() {
        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setFirstName("Varun");
        student.setLastName("Deshpande");
        student.setClassName("3 A");
        student.setNationality("indian");

        Student student1 = new Student();
        student1.setId(UUID.randomUUID().toString());
        student1.setFirstName("Virendra");
        student1.setLastName("Deshpande");
        student1.setClassName("1 A");
        student1.setNationality("indian");

        Object[][] data = new Object[2][1];
        data[0][0] = student;
        data[1][0] = student1;

        return data;
    }
}
