@QACATERALL-2234 @UJDirectdebits5 @UJDirectdebits @AISUJ
Feature: UJ Directdebit with single DirectDebit only

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   directdebits_1. -> /accounts/{AccountId}/direct-debits

   UserJourney:"With ReadDirectDebits Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-2426 @id:QACATERALL-2234_1 @Prateek
  Scenario: Token_1.37 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2427 @id:QACATERALL-2234_2 @Prateek
  Scenario: account-access-consents_1.UJ5-Directdebits : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2428 @id:QACATERALL-2234_3 @Prateek
  Scenario: Token_2.UJ5-Directdebits : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-2429 @id:QACATERALL-2234_4 @Prateek
  Scenario: Token_3.UJ5-Directdebits : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-2430 @id:QACATERALL-2234_5 @Prateek
  Scenario: directdebits_1.UJ5-Directdebits : Verify direct debits service with single DD transaction
    Given I have the data available to create "GET" request for direct debits_service with Headers
    When I want to make a call to direct debits_service
   #Then I want to validate direct debits service Response and store the ConsentID with Status code "200"
    Then I verify the cases against Banking portal for DirectDebit with response status code "200"

#  @QACATERALL-2431
#  Scenario: teardown
#    Then I want to close driver
