Feature: Navigate to Selenium Website from Google search

  @steps-web @steps-mobile
    Scenario: I need navigate to Google and search for Selenium

    Given I open a browser
    When I navigate to a Website
    And I search for Selenium
    Then I should be able to click on desired result
