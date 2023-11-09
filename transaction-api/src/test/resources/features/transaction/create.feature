# language: en

Feature: Transaction creation feature

  Background:
    Given database is clean
    And execute sqlFile in database with name "account.sql"

  Scenario Outline: Create transaction
    Given create transaction request is build with new information:
      | account_id  | operation_type_id | amount   |
      | <accountId> | <operationTypeId> | <amount> |
    When a Post request to resource "/transactions" is made
    Then it should return http status code <statusCode>
    Examples:
      | accountId | operationTypeId | amount | statusCode |
      | 1         | 1               | 10     | 200        |
      | 1         | 4               | 20     | 200        |
      | 1         | 2               | 5      | 200        |
      | 1         | 3               | 100    | 500        |
      | 2         | 4               | 100    | 200        |
      |           | 1               | 100    | 400        |
      | 1         |                 | 100    | 400        |
      | 1         | 1               |        | 400        |
