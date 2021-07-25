package com.myuniversity.repository;

import com.myuniversity.dao.StudentDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Repository Class for Student
 * This support All CRUD operations on student
 */
@Repository
@Component
public interface StudentRepository extends JpaRepository<StudentDao, String> {
}
