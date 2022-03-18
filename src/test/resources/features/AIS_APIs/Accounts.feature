@QACATERALL-2129 @accounts @AIS @AIS5A
Feature: accounts

  Environments : 3A(Dev), 5A(Pre)
  Testcases to validate resource
  accounts_1. GET /accounts
  accounts_2. GET /accounts/{AccountId}

  @QACATERALL-2193 @id:QACATERALL-2129_1 @Prateek @4AAccounts
  Scenario: accounts_1.1 : Verify accounts service for valid Schema
    Given I have the data available to create "GET" request for accounts_service with Headers
    When I want to make a call to accounts_service to get consentID status
    Then I want to validate accounts service Response for Valid Schema

  @QACATERALL-2194 @id:QACATERALL-2129_2 @Prateek @4AAccounts
  Scenario: accounts_1.2 : Verify accounts service for valid response single account
    Given I have the data available to create "GET" request for accounts_service with Headers
    When I want to make a call to accounts_service to get consentID status
    Then I want to validate accounts service Response and store with Status code "200"

  @QACATERALL-2195 @id:QACATERALL-2129_3 @Prateek @4AAccounts
  Scenario: accounts_1.3 : Verify accounts service for Invalid Token
    Given I have the data available to create "GET" request for accounts_service with Headers
    When I want to make a call to accounts_service to get consentID status
    Then I want to validate accounts service Response and store with Status code "401"

  @QACATERALL-2196 @id:QACATERALL-2129_4 @Prateek @4AAccounts
  Scenario: accounts_1.4 : Verify accounts service for valid response
    Given I have the data available to create "GET" request for accounts_service with Headers
    When I want to make a call to accounts_service to get consentID status
    Then I want to validate allaccounts service Response and store with Status code "200"

  @QACATERALL-2197 @id:QACATERALL-2129_5 @Prateek @4AAccounts
  Scenario: accounts_1.5 : Verify accounts service for valid response multiple accounts
    Given I have the data available to create "GET" request for accounts_service with Headers
    When I want to make a call to accounts_service to get consentID status
    Then I want to validate accounts service Response and store with Status code "200"

  @QACATERALL-2198 @id:QACATERALL-2129_6 @Prateek @4AAccounts
  Scenario: accounts_2.1 : Verify single account service for valid Schema
    Given I have the data available to create "GET" request for accounts_service with Headers
    When I want to make a call to specific accounts_service to get consentID status
    Then I want to validate specific accounts service Response for Valid Schema

  @QACATERALL-2199 @id:QACATERALL-2129_7 @Prateek @4AAccounts
  Scenario: accounts_2.2 : Verify single account service for valid Response
    Given I have the data available to create "GET" request for accounts_service with Headers
    When I want to make a call to specific accounts_service to get consentID status
    Then I want to validate accounts service Response and store with Status code "200"

  @QACATERALL-2200 @id:QACATERALL-2129_8 @Prateek @4AAccounts
  Scenario: accounts_2.3 : Verify single account service for Invalid Token
    Given I have the data available to create "GET" request for accounts_service with Headers
    When I want to make a call to specific accounts_service to get consentID status
    Then I want to validate accounts service Response and store with Status code "401"

  @QACATERALL-2192 @id:QACATERALL-2129_9 @Prateek @4AAccounts
  Scenario: accounts_2.4 : Verify single account service for Invalid AccountID
    Given I have the data available to create "GET" request for accounts_service with Headers
    When I want to make a call to specific accounts_service to get consentID status
    Then I want to validate accounts service Response and store with Status code "400"
