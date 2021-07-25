package com.myuniversity.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.myuniversity.dao.StudentDao;
import com.myuniversity.entities.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provide a easiest way to transform A DAO object to entity and vice-versa
 *
 */
@Component
@Slf4j
public class StudentTransformationUtils {

    private ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
            .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.PUBLIC_ONLY)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);


    public List<Student> transformStudent(List<StudentDao> studentDaoList) {
        log.info("Transforming Student Entity");
        List<Student> studentList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(studentDaoList)) {
            studentDaoList.stream().forEach(studentDao -> {
                Student student = convertValue(studentDao, Student.class);
                studentList.add(student);

            });
        }
        return studentList;

    }

    public <T> T convertValue(Object json, Class<T> objectClass) {
        return mapper.convertValue(json, objectClass);
    }


}
