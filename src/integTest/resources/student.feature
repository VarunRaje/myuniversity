Feature: Student CRUD operation

  Scenario: Save Student
    When user calls "POST" API
    Then user receives status code of "201"
    And student added successfully

  Scenario: Get Student
    When user calls "GET" API
    Then user receives status code of "200"
    And student list returned