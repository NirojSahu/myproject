@QACATERALL-2227 @UJBeneficiaries3 @UJBeneficiaries @AISUJ
Feature: UJ Beneficiaries multiple Account select single account

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   transactions_1. -> GET /accounts/{AccountId}/beneficiaries

   UserJourney:"ReadBeneficiariesDetail Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-2388 @id:QACATERALL-2227_1 @Prateek @4ABenUJ3
  Scenario: Token_1.30 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2389 @id:QACATERALL-2227_2 @Prateek @4ABenUJ3
  Scenario: account-access-consents_1.UJ3-Beneficiaries : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2390 @id:QACATERALL-2227_3 @Prateek @4ABenUJ3
  Scenario: Token_2.UJ3-Beneficiaries : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-2391 @id:QACATERALL-2227_4 @Prateek @4ABenUJ3
  Scenario: Token_3.UJ3-Beneficiaries : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-2392 @id:QACATERALL-2227_5 @Prateek @4ABenUJ3
  Scenario: beneficiaries_1.UJ3-Beneficiaries : Verify beneficiaries service for valid Response
    Given I have the data available to create "GET" request for beneficiaries_service with Headers
    When I want to make a call to beneficiaries_service to get consentID status
    #Then I want to validate beneficiaries service Response and store the ConsentID with Status code "200"
    Then I verify the cases against Banking portal for Beneficiaries with response status code "200"

#  @QACATERALL-2393
#  Scenario: teardown
#    Then I want to close driver
