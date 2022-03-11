@QACATERALL-3891 @UJStatement1 @UJStatement @AISUJ
Feature: UJ Statement Single Account

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   statements_1. GET /accounts/{AccountId}/statements

   UserJourney:"ReadStatementsDetail Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-3897 @id:QACATERALL-3891_1 @Prateek @4AStatementsUJ1
  Scenario: Token_1.43 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-3898 @id:QACATERALL-3891_2 @Prateek @4AStatementsUJ1
  Scenario: account-access-consents_1.UJ1-Statement : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-3899 @id:QACATERALL-3891_3 @Prateek @4AStatementsUJ1
  Scenario: Token_2.UJ1-Statement : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-3900 @id:QACATERALL-3891_4 @Prateek @4AStatementsUJ1
  Scenario: Token_3.UJ1-Statement : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-3901 @id:QACATERALL-3891_5 @Prateek @4AStatementsUJ1
  Scenario: statements_1.UJ1-Statement : Verify statements service for valid accountid
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information
    #Then I want to validate service Response and store the Statements for accounts  with Status code "200"
    Then I verify the cases against Banking portal for Ststements with response status code "200"

#  @QACATERALL-3902 @id:QACATERALL-3891_6 @Prateek @4AStatementsUJ1
#  Scenario: teardown
#    Then I want to close driver
