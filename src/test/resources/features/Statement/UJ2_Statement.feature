@QACATERALL-3892 @UJStatement2 @UJStatement @AISUJ
Feature: UJ Statement Multiple Accounts

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   statements_1. GET /accounts/{AccountId}/statements

   UserJourney:"ReadStatementsDetail Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-3903 @id:QACATERALL-3892_1 @Prateek @4AStatementsUJ2
  Scenario: Token_1.44 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-3904 @id:QACATERALL-3892_2 @Prateek @4AStatementsUJ2
  Scenario: account-access-consents_1.UJ2-Statement : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-3905 @id:QACATERALL-3892_3 @Prateek @4AStatementsUJ2
  Scenario: Token_2.UJ2-Statement : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-3906 @id:QACATERALL-3892_4 @Prateek @4AStatementsUJ2
  Scenario: Token_3.UJ2-Statement : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-3907 @id:QACATERALL-3892_5 @Prateek @4AStatementsUJ2
  Scenario: statements_1.UJ2-Statement1 : Verify statements service for valid accountid
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information
    #Then I want to validate service Response and store the Statements for accounts  with Status code "200"
    Then I verify the cases against Banking portal for Ststements with response status code "200"

  @QACATERALL-3908 @id:QACATERALL-3892_6 @Prateek @4AStatementsUJ2
  Scenario: statements_1.UJ2-Statement2 : Verify statements service for valid accountid
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information
    #Then I want to validate service Response and store the Statements for accounts  with Status code "200"
    Then I verify the cases against Banking portal for Ststements with response status code "200"

  @QACATERALL-3909 @id:QACATERALL-3892_7 @Prateek @4AStatementsUJ2
  Scenario: statements_1.UJ2-Statement3 : Verify statements service for valid accountid
    Given I have the data available to create "GET" request for statements_service with Headers
    When I want to make a call to statements_service to get statement information
    #Then I want to validate service Response and store the Statements for accounts  with Status code "200"
    Then I verify the cases against Banking portal for Ststements with response status code "200"

#  @QACATERALL-3910 @id:QACATERALL-3892_8 @Prateek @4AStatementsUJ2
#  Scenario: teardown
#    Then I want to close driver
