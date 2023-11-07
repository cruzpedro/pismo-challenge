# language: en

Feature: Account find feature

  Background:
    Given database is clean
    And execute sqlFile in database with name "account.sql"

  Scenario Outline: Find account
    When a GET request to resource "<resource>" is made
    Then it should return http status code <statusCode>
    Examples:
      | resource    | statusCode |
      | /accounts/1 | 200        |
      | /accounts/2 | 200        |
      | /accounts/3 | 404        |
      | /accounts/  | 404        |
