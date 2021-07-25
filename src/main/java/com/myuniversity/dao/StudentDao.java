package com.myuniversity.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@ToString
@Entity
@Table(name = "student")
public class StudentDao {

    @Id
    String id;
    String firstName;
    String lastName;
    String className;
    String nationality;


}
