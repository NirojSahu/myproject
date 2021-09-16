@QACATERALL-2203 @transactions @AIS @AIS5A
Feature: transactions

  Environments : 3A(Dev), 5A(Pre)
  Testcases to validate resource
  transactions_1. GET /accounts/{AccountId}/transactions


  @QACATERALL-2316 @id:QACATERALL-2203_1 @Prateek @4ATransactions
  Scenario: transactions_1.1 : Verify transaction service for valid Schema
    Given I have the data available to create "GET" request for transactions_service with Headers
    When I want to make a call to transactions_service to get transactions information
    Then I want to validate service Response for Valid Schema for transactions

  @QACATERALL-2317 @id:QACATERALL-2203_2 @Prateek @4ATransactions
  Scenario: transactions_1.2 : Verify transaction service for valid Response with transactions
    Given I have the data available to create "GET" request for transactions_service with Headers
    When I want to make a call to transactions_service to get transactions information
    Then I want to validate service Response and store the TransactionInfo with Status code "200"

  @QACATERALL-2318 @id:QACATERALL-2203_3 @Prateek @4ATransactions
  Scenario: transactions_1.3 : Verify transaction service for valid Response with invalid AccountID
    Given I have the data available to create "GET" request for transactions_service with Headers
    When I want to make a call to transactions_service to get transactions information
    Then I want to validate service Response and store the TransactionInfo with Status code "400"

  @QACATERALL-2319 @id:QACATERALL-2203_4 @Prateek @4ATransactions
  Scenario: transactions_1.4 : Verify transaction service for valid Response with invalid token
    Given I have the data available to create "GET" request for transactions_service with Headers
    When I want to make a call to transactions_service to get transactions information
    Then I want to validate service Response and store the TransactionInfo with Status code "401"
