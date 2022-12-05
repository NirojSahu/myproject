@QACATERALL-4702 @UJSchedulePayment2 @UJSchedulePayment
Feature: UJ SchedulePayment Multiple Accounts

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   sscheduledpayments_1. GET /accounts/{AccountId}/scheduled-payments

   UserJourney:"ReadScheduledPaymentsDetail Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-4723 @id:QACATERALL-4702_1 @Prateek @4ASchpayUJ2
  Scenario: Token_1.49 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-4724 @id:QACATERALL-4702_2 @Prateek @4ASchpayUJ2
  Scenario: account-access-consents_1.UJ2-Schpay : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-4725 @id:QACATERALL-4702_3 @Prateek @4ASchpayUJ2
  Scenario: Token_2.UJ2-Schpay : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-4712 @id:QACATERALL-4702_4 @Prateek @4ASchpayUJ2
  Scenario: Token_3.UJ2-Schpay : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-4736 @id:QACATERALL-4702_5 @Prateek @4ASchpayUJ2
  Scenario: scheduledpayments_1.UJ2-Schpay1 : Verify scheduled payments service for valid Schema
    Given I have the data available to create "GET" request for scheduled payments_service with Headers
    When I want to make a call to scheduled payments_service to get consentID status
    Then I want to validate scheduled payments service Response against banking portal and store Status code "200"

  @QACATERALL-4737 @id:QACATERALL-4702_6 @Prateek @4ASchpayUJ2
  Scenario: scheduledpayments_1.UJ2-Schpay2 : Verify scheduled payments service for valid Schema
    Given I have the data available to create "GET" request for scheduled payments_service with Headers
    When I want to make a call to scheduled payments_service to get consentID status
    Then I want to validate scheduled payments service Response against banking portal and store Status code "200"

#  @QACATERALL-4713
#  Scenario: teardown
#    Then I want to close driver
