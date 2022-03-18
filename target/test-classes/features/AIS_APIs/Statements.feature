@QACATERALL-2210 @statements @AIS @AIS5A
Feature: statements

  Environments : 3A(Dev), 5A(Pre)
  Testcases to validate resource
  statements_1. GET /accounts/{AccountId}/statements
  statements_2. GET /accounts/{AccountId}/statements/{StatementId}
  Validation against Datasheet

  @QACATERALL-2299 @id:QACATERALL-2210_1 @Prateek @4AStatements
  Scenario: statements_1.1 : Verify statements service for valid Schema
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information
    Then I want to validate service Response for Valid Schema for statements with Accountid

  @QACATERALL-2300 @id:QACATERALL-2210_2 @Prateek @4AStatements
  Scenario: statements_1.2 : Verify statements service for valid accountid
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information
    Then I want to validate service Response and store the Statements for accounts  with Status code "200"

  @QACATERALL-2301 @id:QACATERALL-2210_3 @Prateek @4AStatements
  Scenario: statements_1.3 : Verify statements service for invalid accountid
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information
    Then I want to validate service Response and store the Statements for accounts  with Status code "400"

  @QACATERALL-2302 @id:QACATERALL-2210_4 @Prateek @4AStatements
  Scenario: statements_1.4 : Verify statements service for invalid token
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information
    Then I want to validate service Response and store the Statements for accounts  with Status code "401"

  @QACATERALL-2303 @id:QACATERALL-2210_5 @Prateek @4AStatements
  Scenario: statements_2.1 : Verify statements service for valid Schema with statementID
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information with AccountID and StatementID
    Then I want to validate service Response for Valid Schema for statements with Accountid

  @QACATERALL-2304 @id:QACATERALL-2210_6 @Prateek @4AStatements
  Scenario: statements_2.2 : Verify statements service for valid accountid and statementid
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information with AccountID and StatementID
    Then I want to validate service Response and store the Statements with StatementID for account with Status code "200"

  @QACATERALL-2305 @id:QACATERALL-2210_7 @Prateek @4AStatements
  Scenario: statements_2.3 : Verify statements service for valid accountid and invalid statementid
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information with AccountID and StatementID
    Then I want to validate service Response and store the Statements with StatementID for account with Status code "400"

  @QACATERALL-2306 @id:QACATERALL-2210_8 @Prateek @4AStatements
  Scenario: statements_2.4 : Verify statements service for invalid accountid and valid statementid
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information with AccountID and StatementID
    Then I want to validate service Response and store the Statements with StatementID for account with Status code "400"

  @QACATERALL-2307 @id:QACATERALL-2210_9 @Prateek @4AStatements
  Scenario: statements_2.5 : Verify statements service for invalid accountid and invalid statementid
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information with AccountID and StatementID
    Then I want to validate service Response and store the Statements with StatementID for account with Status code "400"

  @QACATERALL-2308 @id:QACATERALL-2210_10 @Prateek @4AStatements
  Scenario: statements_2.6 : Verify statements service for invalid token
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information with AccountID and StatementID
    Then I want to validate service Response and store the Statements with StatementID for account with Status code "401"
