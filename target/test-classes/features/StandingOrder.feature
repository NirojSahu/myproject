@QACATERALL-2209 @standingOrders @AIS @AIS5A
Feature: Standing Orders

  Environments : 3A(Dev), 5A(Pre)
  Testcases to validate resource
  standingorders_1. GET /accounts/{AccountId}/standing-orders

  @QACATERALL-2295 @id:QACATERALL-2209_1 @Prateek @4AStandingOrders
  Scenario: standingorders_1.1 : Verify standing orders service for valid Schema
    Given I have the data available to create "GET" request for standing orders_service with Headers
    When I want to make a call to standing orders_service to get consentID status
    Then I want to validate standing orders service Response for Valid Schema

  @QACATERALL-2296 @id:QACATERALL-2209_2 @Prateek @4AStandingOrders
  Scenario: standingorders_1.2 : Verify standing orders service for valid response
    Given I have the data available to create "GET" request for standing orders_service with Headers
    When I want to make a call to standing orders_service to get consentID status
    Then I want to validate Standing Orders service Response and store Status code "200"

  @QACATERALL-2297 @id:QACATERALL-2209_3 @Prateek @4AStandingOrders
  Scenario: standingorders_1.3 : Verify standing orders service for invalid token
    Given I have the data available to create "GET" request for standing orders_service with Headers
    When I want to make a call to standing orders_service to get consentID status
    Then I want to validate Standing Orders service Response and store Status code "401"

  @QACATERALL-2298 @id:QACATERALL-2209_4 @Prateek @4AStandingOrders
  Scenario: standingorders_1.4 : Verify standing orders service for invalid account
    Given I have the data available to create "GET" request for standing orders_service with Headers
    When I want to make a call to standing orders_service to get consentID status
    Then I want to validate Standing Orders service Response and store Status code "400"

  @QACATERALL-9067 @id:QACATERALL-2209_5 @4AStandingOrders
  Scenario: standingorders_1.5 : Verify Single standing orders service for valid response
    Given I have the data available to create "GET" request for standing orders_service with Headers
    When I want to make a call to standing orders_service to get consentID status
    Then I want to validate Standing Orders service Response and store Status code "200"

  @QACATERALL-9068 @id:QACATERALL-2209_6 @4AStandingOrders
  Scenario: standingorders_1.6 : Verify no standing orders service for valid response
    Given I have the data available to create "GET" request for standing orders_service with Headers
    When I want to make a call to standing orders_service to get consentID status
    Then I want to validate Standing Orders service Response and store Status code "200"




