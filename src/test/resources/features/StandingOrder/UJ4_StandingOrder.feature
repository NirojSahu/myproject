@QACATERALL-3849 @UJStandingOrder4 @UJStandingOrder @AISUJ
Feature: UJ SO without Permission

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   transactions_1. -> GET /accounts/{AccountId}/standing-orders

   UserJourney:"ReadStandingOrdersDetail without Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-3859 @id:QACATERALL-3849_1 @Prateek @4AStadOrdUJ4
  Scenario: Token_1.41 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-3860 @id:QACATERALL-3849_2 @Prateek @4AStadOrdUJ4
  Scenario: account-access-consents_1.UJ4-StandingOrder : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-3861 @id:QACATERALL-3849_3 @Prateek @4AStadOrdUJ4
  Scenario: Token_2.UJ4-StandingOrder : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-3862 @id:QACATERALL-3849_4 @Prateek @4AStadOrdUJ4
  Scenario: Token_3.UJ4-StandingOrder : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-3863 @id:QACATERALL-3849_5 @Prateek @4AStadOrdUJ4
  Scenario: standingorders_1.UJ4-StandingOrder : Verify standing orders service for valid response
    Given I have the data available to create "GET" request for standing orders_service with Headers
    When I want to make a call to standing orders_service to get consentID status
    #Then I want to validate Standing Orders service Response and store Status code "200"
    Then I verify the cases against Banking portal for StandingOrder with response status code "403"

#  @QACATERALL-3864 @id:QACATERALL-3849_6 @Prateek @4AStadOrdUJ4
#  Scenario: teardown
#    Then I want to close driver
