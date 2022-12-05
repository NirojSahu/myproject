@QACATERALL-2213 @AuthURL @authURLMA
Feature: UserJourney Account all Permissions Multiple Account

  Environments : 3A(Dev), 5A(Pre)

  Token_1 -> Application Access Token
  accountaccess-consents_1 ->  Consent ID Generation
  Token_2 -> Authorization URL
  Token_3 -> Customer Access Token

   UserJourney:All Permissions
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-2264 @id:QACATERALL-2213_1 @Prateek
  Scenario: Token_1.9: Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2265 @id:QACATERALL-2213_2 @Prateek
  Scenario: account-access-consents_1.UJ2 : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2266 @id:QACATERALL-2213_3 @Prateek
  Scenario: Token_2.UJ2 : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-2267 @id:QACATERALL-2213_4 @Prateek
  Scenario: Token_3.UJ2 : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-3966 @id:QACATERALL-2213_5 @Prateek
  Scenario: Token_4.UJ2 : Verify  customer access consent token is generated successfully for valid code using Refresh token
    Given I have the data available to create "POST" request for customer access token with jwt for refreshtoken
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token generated post refresh token service and store the token with Status code "200"

  @QACATERALL-2268
    Scenario: teardown
    Then I want to close driver
