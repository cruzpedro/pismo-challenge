# language: en

Feature: Account creation feature

  Background:
    Given database is clean

  Scenario Outline: Create account
    Given create account request is build with new information:
      | document_number  |
      | <documentNumber> |
    When a Post request to resource "/accounts" is made
    Then it should return http status code <statusCode>
    Examples:
      | documentNumber | statusCode |
      | 12345678900    | 200        |
      |                | 400        |
