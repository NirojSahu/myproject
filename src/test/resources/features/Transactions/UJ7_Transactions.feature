@QACATERALL-2241 @UJTransactions7 @UJTransactions @AISUJ
Feature: UJ Transactions Single Account from date-todate

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   transactions_1. -> GET /accounts/{AccountId}/transactions?fromBookingDateTime=<>&amp;toBookingDateTime=<>

   UserJourney:"ReadTransactionsCredits,ReadTransactionsDebits,ReadTransactionsDetail Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-2470 @id:QACATERALL-2241_1 @Prateek @4ATransactionUJ7
  Scenario: Token_1.26 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2471 @id:QACATERALL-2241_2 @Prateek @4ATransactionUJ7
  Scenario: account-access-consents_1.UJ7-Transactions : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2472 @id:QACATERALL-2241_3 @Prateek @4ATransactionUJ7
  Scenario: Token_2.UJ7-Transactions : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-2473 @id:QACATERALL-2241_4 @Prateek @4ATransactionUJ7
  Scenario: Token_3.UJ7-Transactions : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-2474 @id:QACATERALL-2241_5 @Prateek @4ATransactionUJ7
  Scenario: transactions_1.UJ7-Transactions : Verify transaction service for valid Response with transactions
    Given I have the data available to create "GET" request for transactions_service with Headers
    When I want to make a call to transactions_service to get transactions information with limits
    #Then I want to validate service Response and store the TransactionInfo with Status code "200"
    Then I verify the cases against Banking portal for Transactions with response status code "200"

#  @QACATERALL-2475 @4ATransactionUJ7
#  Scenario: teardown
#    Then I want to close driver
