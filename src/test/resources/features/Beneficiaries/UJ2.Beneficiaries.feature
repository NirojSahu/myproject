@QACATERALL-2226 @UJBeneficiaries2 @UJBeneficiaries @AISUJ
Feature: UJ Beneficiaries multiple Accounts

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   transactions_1. -> GET /accounts/{AccountId}/beneficiaries

   UserJourney:"ReadBeneficiariesDetail Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-2380 @id:QACATERALL-2226_1 @Prateek @4ABenUJ2
  Scenario: Token_1.29 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2381 @id:QACATERALL-2226_2 @Prateek @4ABenUJ2
  Scenario: account-access-consents_1.UJ2-Beneficiaries : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2382 @id:QACATERALL-2226_3 @Prateek @4ABenUJ2
  Scenario: Token_2.UJ2-Beneficiaries : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-2383 @id:QACATERALL-2226_4 @Prateek @4ABenUJ2
  Scenario: Token_3.UJ2-Beneficiaries : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-2384 @id:QACATERALL-2226_5 @Prateek @4ABenUJ2
  Scenario: beneficiaries_1.UJ2-Beneficiaries1 : Verify beneficiaries service for valid Response
    Given I have the data available to create "GET" request for beneficiaries_service with Headers
    When I want to make a call to beneficiaries_service to get consentID status
    #Then I want to validate beneficiaries service Response and store the ConsentID with Status code "200"
    Then I verify the cases against Banking portal for Beneficiaries with response status code "200"

  @QACATERALL-2385 @id:QACATERALL-2226_6 @Prateek @4ABenUJ2
  Scenario: beneficiaries_1.UJ2-Beneficiaries2 : Verify beneficiaries service for valid Response
    Given I have the data available to create "GET" request for beneficiaries_service with Headers
    When I want to make a call to beneficiaries_service to get consentID status
    #Then I want to validate beneficiaries service Response and store the ConsentID with Status code "200"
    Then I verify the cases against Banking portal for Beneficiaries with response status code "200"

  @QACATERALL-2386 @id:QACATERALL-2226_7 @Prateek @4ABenUJ2
  Scenario: beneficiaries_1.UJ2-Beneficiaries3 : Verify beneficiaries service for valid Response
    Given I have the data available to create "GET" request for beneficiaries_service with Headers
    When I want to make a call to beneficiaries_service to get consentID status
    #Then I want to validate beneficiaries service Response and store the ConsentID with Status code "200"
    Then I verify the cases against Banking portal for Beneficiaries with response status code "200"

#  @QACATERALL-2387
#  Scenario: teardown
#    Then I want to close driver
