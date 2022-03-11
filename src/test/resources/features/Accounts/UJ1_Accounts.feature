@QACATERALL-2215 @UJAccounts1 @UJAccounts @AISUJ
Feature: UJ Accounts Single Account

  Environments : 3A(Dev), 5A(Pre), 4A(UAT)

  Token_1 -> Application Access Token
  accountaccess-consents_1 ->  Consent ID Generation
  Token_2 -> Authorization URL
  Token_3 -> Customer Access Token
  accounts_2 -> GET /accounts/{AccountId}

  UserJourney:"ReadAccountsDetail Permissions"
  ApplicationAcessToken --> ConsentID Gen --> AUTH URL --> ClientAccessToken  --> Hit AIS Resources

  @QACATERALL-2320 @id:QACATERALL-2215_1 @Prateek @4AAccUJ111
  Scenario: Token_1.16 : Verify application access consent token is generated successfully
    Given I have the data available to create "POST" request for application access consent token with JWT
    When I want to make a call to applicationaccesstoken_service to create token
    Then I want to validate application access token service Response and store the token with Status code "200"

  @QACATERALL-2321 @id:QACATERALL-2215_2 @Prateek @4AAccUJ1
  Scenario: account-access-consents_1.UJ1-Accounts : Verify account access consent service for ReadAccountsDetail permissions
    Given I have the data available to create "POST" request for accountaccessconsent_service with Headers
    When I want to make a call to accountaccessconsent_service to create consentID
    Then I want to validate service Response and store the ConsentID with Status code "201"

  @QACATERALL-2322 @id:QACATERALL-2215_3 @Prateek @4AAccUJ1
  Scenario: Token_2.UJ1-Accounts : Generating Auth URL with Scope_type Account for provided ConsentID
    Given I have the ConsentID for creating Authorization URL
    When I login to the portal with AuthURL
    Then I get authorization code and store it for further processing

  @QACATERALL-2323 @id:QACATERALL-2215_4 @Prateek @4AAccUJ1
  Scenario: Token_3.UJ1-Accounts : Verify  customer access consent token is generated successfully for valid code
    Given I have the data available to create "POST" request for customer access token with jwt
    When I want to make a call to customeraccesstoken_service to create token
    Then I want to validate customer access token service Response and store the token with Status code "200"

  @QACATERALL-2324 @id:QACATERALL-2215_5 @Prateek @4AAccUJ1
  Scenario: accounts_2.UJ1-Accounts : Verify single account service for valid Response
    Given I have the data available to create "GET" request for accounts_service with Headers
    When I want to make a call to specific accounts_service to get consentID status
    #Then I want to validate accounts service Response and store with Status code "200"
    Then I verify the cases against Banking portal for account with response status code "200"
    #Then I want to close driver
