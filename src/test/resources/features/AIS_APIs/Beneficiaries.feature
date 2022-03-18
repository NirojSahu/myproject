@QACATERALL-2205 @beneficiaries @AIS @AIS5A
Feature: beneficiaries

  Environments : 3A(Dev), 5A(Pre)
  Testcases to validate resource
  beneficiaries_1. GET /accounts/{AccountId}/beneficiaries

  @QACATERALL-2276 @id:QACATERALL-2205_1 @Prateek @4ABeneficiaries
  Scenario: beneficiaries_1.1 : Verify beneficiaries service for valid Schema
    Given I have the data available to create "GET" request for beneficiaries_service with Headers
    When I want to make a call to beneficiaries_service to get consentID status
    Then I want to validate beneficiaries service Response for Valid Schema

  @QACATERALL-2277 @id:QACATERALL-2205_2 @Prateek @4ABeneficiaries
  Scenario: beneficiaries_1.2 : Verify beneficiaries service for valid Response
    Given I have the data available to create "GET" request for beneficiaries_service with Headers
    When I want to make a call to beneficiaries_service to get consentID status
    Then I want to validate beneficiaries service Response and store the ConsentID with Status code "200"

  @QACATERALL-2278 @id:QACATERALL-2205_3 @Prateek @4ABeneficiaries
  Scenario: beneficiaries_1.3 : Verify beneficiaries service for invalid token
    Given I have the data available to create "GET" request for beneficiaries_service with Headers
    When I want to make a call to beneficiaries_service to get consentID status
    Then I want to validate beneficiaries service Response and store the ConsentID with Status code "401"

  @QACATERALL-2279 @id:QACATERALL-2205_4 @Prateek @4ABeneficiaries
  Scenario: beneficiaries_1.4 : Verify beneficiaries service for invalid accountId
    Given I have the data available to create "GET" request for beneficiaries_service with Headers
    When I want to make a call to beneficiaries_service to get consentID status
    Then I want to validate beneficiaries service Response and store the ConsentID with Status code "400"

  @QACATERALL-9063 @id:QACATERALL-2205_5 @Prateek @4ABeneficiaries
  Scenario: beneficiaries_1.5 : Verify No beneficiaries for valid Response
    Given I have the data available to create "GET" request for beneficiaries_service with Headers
    When I want to make a call to beneficiaries_service to get consentID status
    Then I want to validate beneficiaries service Response and store the ConsentID with Status code "200"




