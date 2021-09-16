@QACATERALL-2208 @SchedulePayments @AIS @AIS5A
Feature: Scheduled Payments

  Environments : 3A(Dev), 5A(Pre)
  Testcases to validate resource
  scheduledpayments_1. GET /accounts/{AccountId}/scheduled-payments

  @QACATERALL-2290 @id:QACATERALL-2208_1 @Prateek @4ASchedulePayments
  Scenario: scheduledpayments_1.1 : Verify scheduled payments service for valid Schema
    Given I have the data available to create "GET" request for scheduled payments_service with Headers
    When I want to make a call to scheduled payments_service to get consentID status
    Then I want to validate scheduled payments service Response for Valid Schema

  @QACATERALL-2291 @id:QACATERALL-2208_2 @Prateek @4ASchedulePayments
  Scenario: scheduledpayments_1.2 : Verify scheduled payments service for valid Schema
    Given I have the data available to create "GET" request for scheduled payments_service with Headers
    When I want to make a call to scheduled payments_service to get consentID status
    Then I want to validate scheduled payments service Response and store Status code "200"

  @QACATERALL-2292 @id:QACATERALL-2208_3 @Prateek @4ASchedulePayments
  Scenario: scheduledpayments_1.3 : Verify scheduled payments service for Invalid Token
    Given I have the data available to create "GET" request for scheduled payments_service with Headers
    When I want to make a call to scheduled payments_service to get consentID status
    Then I want to validate scheduled payments service Response and store Status code "401"

  @QACATERALL-2293 @id:QACATERALL-2208_4 @Prateek @4ASchedulePayments
  Scenario: scheduledpayments_1.4 : Verify scheduled payments service for Invalid AccountID
    Given I have the data available to create "GET" request for scheduled payments_service with Headers
    When I want to make a call to scheduled payments_service to get consentID status
    Then I want to validate scheduled payments service Response and store Status code "400"

  @QACATERALL-9065 @id:QACATERALL-2208_5 @4ASchedulePayments
  Scenario: scheduledpayments_1.5 : Verify single scheduled payments service
    Given I have the data available to create "GET" request for scheduled payments_service with Headers
    When I want to make a call to scheduled payments_service to get consentID status
    Then I want to validate scheduled payments service Response and store Status code "200"

  @QACATERALL-9066 @id:QACATERALL-2208_6 @4ASchedulePayments
  Scenario: scheduledpayments_1.6 : Verify without scheduled payments service
    Given I have the data available to create "GET" request for scheduled payments_service with Headers
    When I want to make a call to scheduled payments_service to get consentID status
    Then I want to validate scheduled payments service Response and store Status code "200"

