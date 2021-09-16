@QACATERALL-3846 @UJStandingOrder1 @UJStandingOrder @AISUJ
Feature: UJ SO Single Account

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   transactions_1. -> /accounts/{AccountId}/standing-orders

   UserJourney:"ReadStandingOrdersDetail Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-2499 @id:QACATERALL-3846_1 @Prateek @4AStadOrdUJ1
  Scenario: Token_1.38 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2500 @id:QACATERALL-3846_2 @Prateek @4AStadOrdUJ1
  Scenario: account-access-consents_1.UJ1-StandingOrder : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2501 @id:QACATERALL-3846_3 @Prateek @4AStadOrdUJ1
  Scenario: Token_2.UJ1-StandingOrder : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-2502 @id:QACATERALL-3846_4 @Prateek @4AStadOrdUJ1
  Scenario: Token_3.UJ1-StandingOrder : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-2503 @id:QACATERALL-3846_5 @Prateek @4AStadOrdUJ1
  Scenario: standingorders_1.UJ1-StandingOrder : Verify standing orders service for valid response
    Given I have the data available to create "GET" request for standing orders_service with Headers
    When I want to make a call to standing orders_service to get consentID status
    #Then I want to validate Standing Orders service Response and store Status code "200"
    Then I verify the cases against Banking portal for StandingOrder with response status code "200"

#  @QACATERALL-2504 @id:QACATERALL-3846_6 @Prateek @4AStadOrdUJ1
#  Scenario: teardown
#    Then I want to close driver
