package com.myuniversity.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Entity class for student object
 */
@Setter
@Getter
@Component
public class Student {

    @JsonProperty("id")
    @ApiModelProperty(notes = "Id of the Student",name="id",value="id")
    String id;

    @JsonProperty("firstName")
    @ApiModelProperty(notes = "FirstName of the Student",name="firstName",required=true,value="firstName")
    String firstName;

    @JsonProperty("lastName")
    @ApiModelProperty(notes = "Last Name of the Student",name="lastName",required=true,value="lastName")
    String lastName;

    @JsonProperty("className")
    @ApiModelProperty(notes = "Class of the Student",name="class",required=true,value="class")
    String className;

    @JsonProperty("nationality")
    @ApiModelProperty(notes = "Nationality of the Student",name="nationality",value="nationality")
    String nationality;



}
