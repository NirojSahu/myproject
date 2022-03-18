@AuthURLGen
Feature: UserJourney Accounts all Permissions Single Account

  Environments : 5A(Pre),6A(Prod)

  Token_1 -> Application Access Token
  accountaccess-consents_1 ->  Consent ID Generation
  Token_2 -> Authorization URL
  Token_3 -> Customer Access Token

  UserJourney:All Permissions
  ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources


  Scenario: Token_1.2 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"


  Scenario: account-access-consents_1.UJ1 : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"


  Scenario: Token_2.UJ1 : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
#    When I login to the portal with AuthURL
#    Then I get authorization code and store it for further processing
#
#  @QACATERALL-2262 @id:QACATERALL-2212_4 @Prateek
#  Scenario: Token_3.UJ1 : Verify  customer access consent token is generated successfully for valid code
#    Given I have the data available to create "POST" request for customer access token with jwt
#    When I want to make a call to customeraccesstoken_service to create token
#    Then I want to validate customer access token service Response and store the token with Status code "200"


  Scenario: teardown
    Then I want to close driver
