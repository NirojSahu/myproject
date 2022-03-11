@QACATERALL-4704 @UJSchedulePayment4 @UJSchedulePayment
Feature: UJ SchedulePayment without Permission

  Environments : 3A(Dev), 5A(Pre)

   Token_1 -> Application Access Token
   accountaccess-consents_1 ->  Consent ID Generation
   Token_2 -> Authorization URL
   Token_3 -> Customer Access Token
   scheduledpayments_1. GET /accounts/{AccountId}/scheduled-payments

   UserJourney:"with out ReadScheduledPaymentsDetail Permissions"
   ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-4729 @id:QACATERALL-4704_1 @Prateek @4ASchpayUJ4
  Scenario: Token_1.51 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-4730 @id:QACATERALL-4704_2 @Prateek @4ASchpayUJ4
  Scenario: account-access-consents_1.UJ4-Schpay : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-4731  @id:QACATERALL-4704_3 @Prateek @4ASchpayUJ4
  Scenario: Token_2.UJ4-Schpay : Generating Auth URL with Scope_type Account
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-4716 @id:QACATERALL-4704_4 @Prateek @4ASchpayUJ4
  Scenario: Token_3.UJ4-Schpay : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-4739 @id:QACATERALL-4704_5 @Prateek @4ASchpayUJ4
  Scenario: scheduledpayments_1.UJ4-Schpay : Verify scheduled payments service for valid Schema
    Given I have the data available to create "GET" request for scheduled payments_service with Headers
    When I want to make a call to scheduled payments_service to get consentID status
    Then I want to validate scheduled payments service Response against banking portal and store Status code "403"

#  @QACATERALL-4717
#  Scenario: teardown
#    Then I want to close driver
