Feature: Navigate to Confluence website

  @steps-web @steps-mobile
    Scenario: I need to navigate to Confluence and click on Confluence Link

    Given I open a browser
    When I navigate to confluence website
    And I click on Confluence link
    Then I should be at confluence Home Page
