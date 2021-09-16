@QACATERALL-2242 @UJTransactions8 @UJTransactions @AISUJ
Feature: UJ Transactions Multiple Account with no transaction

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   transactions_1. -> GET /accounts/{AccountId}/transactions

   UserJourney:"ReadTransactionsCredits,ReadTransactionsDebits,ReadTransactionsDetail Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-2476 @id:QACATERALL-2242_1 @Prateek @4ATransactionUJ8
  Scenario: Token_1.27 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2477 @id:QACATERALL-2242_2 @Prateek @4ATransactionUJ8
  Scenario: account-access-consents_1.UJ8-Transactions : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2478 @id:QACATERALL-2242_3 @Prateek @4ATransactionUJ8
  Scenario: Token_2.UJ8-Transactions : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-2479 @id:QACATERALL-2242_4 @Prateek @4ATransactionUJ8
  Scenario: Token_3.UJ8-Transactions : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-2480 @id:QACATERALL-2242_5 @Prateek @4ATransactionUJ8
  Scenario: transactions_1.UJ8-Transactions : Verify transaction service for valid Response with transactions
    Given I have the data available to create "GET" request for transactions_service with Headers
    When I want to make a call to transactions_service to get transactions information with limits
    #Then I want to validate service Response and store the TransactionInfo with Status code "200"
    Then I verify the cases against Banking portal for Transactions with response status code "200"

#  @QACATERALL-2481 @4ATransactionUJ8
#  Scenario: teardown
#    Then I want to close driver
