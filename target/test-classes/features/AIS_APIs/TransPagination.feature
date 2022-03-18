@QACATERALL-2203 @transactions @AIS5A
Feature: Transactions-Pagination

  Environments : 3A(Dev), 5A(Pre)
  Testcases to validate resource
  transactions_1. GET /accounts/{AccountId}/transactions


  @QACATERALL-9069 @id:QACATERALL-2203_5 @4ATransactions
  Scenario: transactions_1.5 : Verify transaction service for valid Response with transactions
    Given I have the data available to create "GET" request for transactions_service with Headers
    When  I want to make a call to transactions_service to get transactions information with limits
    Then I want to validate service Response and store the TransactionInfo with pagination with Status code "200"

  @QACATERALL-9070 @id:QACATERALL-2203_6 @4ATransactions
  Scenario: transactions_1.6 : Verify transaction service for valid Response with transactions
    Given I have the data available to create "GET" request for transactions_service with Headers
    When  I want to make a call to transactions_service to get transactions information with limits
    Then I want to validate service Response and store the TransactionInfo with pagination with Status code "200"
