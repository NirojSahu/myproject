@QACATERALL-2202 @Accountconsent @AIS @AIS5A
Feature: account-access-consents

  Environments : 3A(Dev), 5A(Pre)
  Testcases to validate resource
  account-access-consents_1. POST/account-access-consents
  account-access-consents_2. GET /account-access-consents/{ConsentId}
  account-access-consents_3. DELETE /account-access-consents/{ConsentId}

  @QACATERALL-2243 @id:QACATERALL-2202_1 @Prateek
  Scenario: Token_1.2 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2244 @id:QACATERALL-2202_2 @Prateek
  Scenario: account-access-consents_1.1 : Verify account access consent service for valid Schema
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response for Valid Schema

  @QACATERALL-2245 @id:QACATERALL-2202_3 @Prateek @deleteaccountconsent
  Scenario: account-access-consents_1.2 : Verify account access consent service for All permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2246 @id:QACATERALL-2202_4 @Prateek @deleteaccountconsent
  Scenario: account-access-consents_1.3 : Verify account access consent service for specific permission
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2247 @id:QACATERALL-2202_5 @Prateek
  Scenario: account-access-consents_1.4 : Verify account access consent service for Invalid token
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "401"

  @QACATERALL-2248 @id:QACATERALL-2202_6 @Prateek
  Scenario: account-access-consents_1.5 : Verify account access consent service for invalid request parameter Permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "400"

  @QACATERALL-2249 @id:QACATERALL-2202_7 @Prateek
  Scenario: account-access-consents_1.6 : Verify account access consent service for invalid request parameter TransactionToDateTime
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "400"

  @QACATERALL-2250 @id:QACATERALL-2202_8 @Prateek
  Scenario: account-access-consents_1.7 : Verify account access consent service for empty parameter expirationdatetime
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "400"

  @QACATERALL-2243 @id:QACATERALL-2202_9 @Prateek
  Scenario: Token_1.2 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2251 @id:QACATERALL-2202_10 @Prateek
  Scenario: account-access-consents_2.1 : Verify account accessconsentconsent service for getting the status for valid ConsentID
    Given I have the data available to create "GET" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to get consentID status
    Then I want to validate service Response for Valid Schema

  @QACATERALL-2252 @id:QACATERALL-2202_11 @Prateek
  Scenario: account-access-consents_2.2 : Verify account accessconsentconsent service for getting the status for valid ConsentID
    Given I have the data available to create "GET" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to get consentID status
    Then I want to validate service Response and validate the ConsentID status as "AwaitingAuthorisation" with Status code "200"

  @QACATERALL-2253 @id:QACATERALL-2202_12 @Prateek
  Scenario: account-access-consents_2.3 : Verify account accessconsentconsent service for getting the status for valid ConsentID
    Given I have the data available to create "GET" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to get consentID status
    Then I want to validate service Response and validate the ConsentID status as "Authorised" with Status code "200"

  @QACATERALL-2254 @id:QACATERALL-2202_13 @Prateek
  Scenario: account-access-consents_2.4 : Verify account accessconsentconsent service for getting the status for Invalid ConsentID
    Given I have the data available to create "GET" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to get consentID status
    Then I want to validate service Response and validate the ConsentID status as "Authorised" with Status code "400"

  @QACATERALL-2255 @id:QACATERALL-2202_14 @Prateek
  Scenario: account-access-consents_2.5 : Verify account accessconsentconsent service for getting the status for Invalid token
    Given I have the data available to create "GET" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to get consentID status
    Then I want to validate service Response and validate the ConsentID status as "Authorised" with Status code "401"

  @QACATERALL-2243 @id:QACATERALL-2202_15 @Prateek @deleteaccountconsent
  Scenario: Token_1.2 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2256 @id:QACATERALL-2202_16 @Prateek @deleteaccountconsent
  Scenario: account-access-consents_3.1 : Verify  accessconsentconsent service for deleting Consent
    Given I have the data available to create "DELETE" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to delete active consentid
    Then I want to validate service Response and validate the ConsentID is deleted with Status code "204"

  @QACATERALL-2257 @id:QACATERALL-2202_17 @Prateek @deleteaccountconsent
  Scenario: account-access-consents_3.2 : Verify  accessconsentconsent service for deleting Consent with Invalid ConsentID
    Given I have the data available to create "DELETE" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to delete with given consentID
    Then I want to validate service Response and validate the ConsentID is deleted with Status code "400"

  @QACATERALL-2258 @id:QACATERALL-2202_18 @Prateek @deleteaccountconsent
  Scenario: account-access-consents_3.3 : Verify  accessconsentconsent service for deleting Consent with Invalid token
    Given I have the data available to create "DELETE" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to delete with given consentID
    Then I want to validate service Response and validate the ConsentID is deleted with Status code "401"
