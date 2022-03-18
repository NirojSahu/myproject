@QACATERALL-2207 @Products @AIS @AIS5A
Feature: Products

  Environments : 3A(Dev), 5A(Pre)
  Testcases to validate resource
  products_1. GET /accounts/{AccountId}/product

  @QACATERALL-2285 @id:QACATERALL-2207_1 @Prateek @4AProducts
  Scenario: products_1.1 : Verify products service for valid Schema
    Given I have the data available to create "GET" request for products_service with Headers
    When I want to make a call to products_service to get consentID status
    Then I want to validate products service Response for Valid Schema

  @QACATERALL-2286 @id:QACATERALL-2207_2 @Prateek @4AProducts
  Scenario: products_1.2 : Verify products service for valid response
    Given I have the data available to create "GET" request for products_service with Headers
    When I want to make a call to products_service to get consentID status
    Then I want to validate products service Response and store with Status code "200"

  @QACATERALL-2287 @id:QACATERALL-2207_3 @Prateek @4AProducts
  Scenario: products_1.3 : Verify products service for invalid token
    Given I have the data available to create "GET" request for products_service with Headers
    When I want to make a call to products_service to get consentID status
    Then I want to validate products service Response and store with Status code "401"

  @QACATERALL-2288 @id:QACATERALL-2207_4 @Prateek @4AProducts
  Scenario: products_1.4 : Verify products service for invalid accountid
    Given I have the data available to create "GET" request for products_service with Headers
    When I want to make a call to products_service to get consentID status
    Then I want to validate products service Response and store with Status code "400"


