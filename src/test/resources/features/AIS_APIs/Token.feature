@QACATERALL-2211 @token @AIS5A
Feature: Token Generation

  Environments : 3A(Dev), 5A(Prod)
  Testcases to validate resource - /token
  Token_1. Application access token to provide header to account access consent
  Token_2. Auth URL Generation based on Scope
  Token_3. Clinet access token for accessing user Data thru resources

  @QACATERALL-2309 @id:QACATERALL-2211_1 @Prateek
  Scenario: Token_1.1 : Verify application access consent token is not generated successfully because of old JWT value
    Given I have the data available to create "POST" request for application access consent token
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "400"

  #################
  #Local UserJourney
  #################

  @QACATERALL-2310 @id:QACATERALL-2211_2 @Prateek @new4a
  Scenario: Token_1.2 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2311 @id:QACATERALL-2211_3 @Prateek
  Scenario: account-access-consents_1.2 : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2312 @id:QACATERALL-2211_4 @Prateek
  Scenario: Token_2.1 : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-2313 @id:QACATERALL-2211_5 @Prateek
  Scenario: Token_3.2 : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-3967 @id:QACATERALL-2211_7 @Prateek
  Scenario: Token_4.2 : Verify  customer access consent token is generated successfully for valid code using Refresh token
    Given I have the data available to create "POST" request for customer access token with jwt for refreshtoken
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token generated post refresh token service and store the token with Status code "200"

  @QACATERALL-2314 @id:QACATERALL-2211_6 @Prateek
  Scenario: Token_3.1 : Verify  customer access consent token is returns error for Invalid code
    Given I have the data available to create "POST" request for customer access token
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "400"

#  @QACATERALL-2315
#  Scenario: teardown
#    Then I want to close driver
