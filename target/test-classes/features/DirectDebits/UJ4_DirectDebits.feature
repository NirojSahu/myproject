@QACATERALL-2233 @UJDirectdebits4 @UJDirectdebits @AISUJ
Feature: UJ Directdebit without ReadDirectDebits Permissions

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   directdebits_1. -> /accounts/{AccountId}/direct-debits

   UserJourney:"With out ReadDirectDebits Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-2484 @id:QACATERALL-2233_1 @Prateek @4ADirDebUJ4
  Scenario: Token_1.36 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2485 @id:QACATERALL-2233_2 @Prateek @4ADirDebUJ4
  Scenario: account-access-consents_1.UJ4-Directdebits : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2486 @id:QACATERALL-2233_3 @Prateek @4ADirDebUJ4
  Scenario: Token_2.UJ4-Directdebits : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-2487 @id:QACATERALL-2233_4 @Prateek @4ADirDebUJ4
  Scenario: Token_3.UJ4-Directdebits : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-2488 @id:QACATERALL-2233_5 @Prateek @4ADirDebUJ4
  Scenario: directdebits_1.UJ4-Directdebits : Verify direct debits service for valid DD Response without permission
    Given I have the data available to create "GET" request for direct debits_service with Headers
    When I want to make a call to direct debits_service
   #Then I want to validate direct debits service Response and store the ConsentID with Status code "200"
    Then I verify the cases against Banking portal for DirectDebit with response status code "403"

#  @QACATERALL-2489
#  Scenario: teardown
#    Then I want to close driver
