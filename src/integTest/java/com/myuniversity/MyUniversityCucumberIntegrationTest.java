package com.myuniversity;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/integTest/resources", extraGlue = "com.myuniversity.entities")
public class MyUniversityCucumberIntegrationTest {
}
