@QACATERALL-2204 @balances @AIS @AIS5A
Feature: balances

  Environments : 3A(Dev), 5A(Pre)
  Testcases to validate resource GET /accounts/{AccountId}/balances

  @QACATERALL-2269 @id:QACATERALL-2204_1 @Prateek @4ABalance
  Scenario: balances_1.1 : Verify balances service for valid Schema
    Given I have the data available to create "GET" request for balances_service with Headers
    When I want to make a call to balances_service to get balance information
    Then I want to validate service Response for Valid Schema for balances

  @QACATERALL-2270 @id:QACATERALL-2204_2 @Prateek @4ABalance
  Scenario: balances_1.2 : Verify balances service for valid Account
    Given I have the data available to create "GET" request for balances_service with Headers
    When I want to make a call to balances_service to get balance information
    Then I want to validate service Response and store the BalanceInfo with Status code "200"
    #Then I verify the cases against Banking portal

  @QACATERALL-2271 @id:QACATERALL-2204_3 @Prateek @4ABalance
  Scenario: balances_1.3 : Verify balances service for invalid Account
    Given I have the data available to create "GET" request for balances_service with Headers
    When I want to make a call to balances_service to get balance information
    Then I want to validate service Response and store the BalanceInfo with Status code "400"

  @QACATERALL-2272 @id:QACATERALL-2204_4 @Prateek @4ABalance
  Scenario: balances_1.4 : Verify balances service for invalid token
    Given I have the data available to create "GET" request for balances_service with Headers
    When I want to make a call to balances_service to get balance information
    Then I want to validate service Response and store the BalanceInfo with Status code "401"

  @QACATERALL-2273 @id:QACATERALL-2204_5 @Prateek @4ABalance
  Scenario: balances_1.5 : Verify balances service for valid Account
    Given I have the data available to create "GET" request for balances_service with Headers
    When I want to make a call to balances_service to get balance information
    Then I want to validate service Response and store the BalanceInfo with Status code "200"

  @QACATERALL-2274 @id:QACATERALL-2204_6 @Prateek @4ABalance
  Scenario: balances_1.6 : Verify balances service for valid Account
    Given I have the data available to create "GET" request for balances_service with Headers
    When I want to make a call to balances_service to get balance information
    Then I want to validate service Response and store the BalanceInfo with Status code "200"

  @QACATERALL-2275 @id:QACATERALL-2204_7 @Prateek @4ABalance
  Scenario: balances_1.7 : Verify balances service for valid Account
    Given I have the data available to create "GET" request for balances_service with Headers
    When I want to make a call to balances_service to get balance information
    Then I want to validate service Response and store the BalanceInfo with Status code "200"


