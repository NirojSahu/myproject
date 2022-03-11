@QACATERALL-4701 @UJSchedulePayment1 @UJSchedulePayment
Feature: UJ SchedulePayment Single Account

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   scheduledpayments_1. GET /accounts/{AccountId}/scheduled-payments

   UserJourney:"ReadScheduledPaymentsDetail Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-4720 @id:QACATERALL-4701_1 @Prateek @4ASchpayUJ1
  Scenario: Token_1.48 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-4721 @id:QACATERALL-4701_2 @Prateek @4ASchpayUJ1
  Scenario: account-access-consents_1.UJ1-Schpay : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-4722 @id:QACATERALL-4701_3 @Prateek @4ASchpayUJ1
  Scenario: Token_2.UJ1-Schpay : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-4710 @id:QACATERALL-4701_4 @Prateek @4ASchpayUJ1
  Scenario: Token_3.UJ1-Schpay : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-4735 @id:QACATERALL-4701_5 @Prateek @4ASchpayUJ1
  Scenario: scheduledpayments_1.UJ1-Schpay : Verify scheduled payments service for valid Schema
    Given I have the data available to create "GET" request for scheduled payments_service with Headers
    When I want to make a call to scheduled payments_service to get consentID status
    Then I want to validate scheduled payments service Response against banking portal and store Status code "200"

#  @QACATERALL-4711
#  Scenario: teardown
#    Then I want to close driver
