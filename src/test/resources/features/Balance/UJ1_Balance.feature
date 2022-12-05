@QACATERALL-2220 @UJBalances1 @UJBalances @AISUJ
Feature: UJ balances Single Account

  Environments : 3A(Dev), 5A(Pre), 4A(UAT)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   balances_1 -> Testcases to validate resource GET /accounts/{AccountId}/balances

   UserJourney:"ReadBalances Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-2344 @id:QACATERALL-2220_1 @Prateek @4ABalUJ1
  Scenario: Token_1.11 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2345 @id:QACATERALL-2220_2 @Prateek @4ABalUJ1
  Scenario: account-access-consents_1.UJ1-Balances : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2346 @id:QACATERALL-2220_3 @Prateek @4ABalUJ1
  Scenario: Token_2.UJ1-Balances : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-2347 @id:QACATERALL-2220_4 @Prateek @4ABalUJ1
  Scenario: Token_3.UJ1-Balances : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-2348 @id:QACATERALL-2220_5 @Prateek @4ABalUJ1
  Scenario: balances_1.UJ1-Balances : Verify balances service for valid Account against
    Given I have the data available to create "GET" request for balances_service with Headers
    When I want to make a call to balances_service to get balance information
    #Then I want to validate service Response and store the BalanceInfo with Status code "200"
    Then I verify the cases against Banking portal for Balances with response status code "200"

#  @QACATERALL-2349
#  Scenario: teardown
#    Then I want to close driver
