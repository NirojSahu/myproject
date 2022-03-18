@QACATERALL-2206 @DirectDebits @AIS @AIS5A
Feature: direct debits

  Environments : 3A(Dev), 5A(Pre)
  Testcases to validate resource
  directdebits_1. GET /accounts/{AccountId}/direct-debits

  @QACATERALL-2280 @id:QACATERALL-2206_1 @Prateek @4ADirectDebits
  Scenario: directdebits_1.1 : Verify direct debits service for valid Schema
    Given I have the data available to create "GET" request for direct debits_service with Headers
    When I want to make a call to direct debits_service
    Then I want to validate direct debits service Response for Valid Schema

  @QACATERALL-2281 @id:QACATERALL-2206_2 @Prateek @4ADirectDebits
  Scenario: directdebits_1.2 : Verify direct debits service for valid DD Response
    Given I have the data available to create "GET" request for direct debits_service with Headers
    When I want to make a call to direct debits_service
    Then I want to validate direct debits service Response and store the ConsentID with Status code "200"

  @QACATERALL-2282 @id:QACATERALL-2206_3 @Prateek @4ADirectDebits
  Scenario: directdebits_1.3 : Verify direct debits service for Invalid AccountID
    Given I have the data available to create "GET" request for direct debits_service with Headers
    When I want to make a call to direct debits_service
    Then I want to validate direct debits service Response and store the ConsentID with Status code "400"

  @QACATERALL-2283 @id:QACATERALL-2206_4 @Prateek @4ADirectDebits
  Scenario: directdebits_1.4 : Verify direct debits service for Invalid Token
    Given I have the data available to create "GET" request for direct debits_service with Headers
    When I want to make a call to direct debits_service
    Then I want to validate direct debits service Response and store the ConsentID with Status code "401"

  @QACATERALL-2284 @id:QACATERALL-2206_5 @Prateek @4ADirectDebits
  Scenario: directdebits_1.5 : Verify direct debits service for single DD
    Given I have the data available to create "GET" request for direct debits_service with Headers
    When I want to make a call to direct debits_service
    Then I want to validate direct debits service Response and store the ConsentID with Status code "200"

  @QACATERALL-9064 @id:QACATERALL-2206_6 @Prateek @4ADirectDebits
  Scenario: directdebits_1.6 : Verify without direct debits service
    Given I have the data available to create "GET" request for direct debits_service with Headers
    When I want to make a call to direct debits_service
    Then I want to validate direct debits service Response and store the ConsentID with Status code "200"


