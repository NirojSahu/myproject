package Utilities.WebKit.applications.selenium.web.steps;

import Utilities.WebKit.helper.NavigationHelper;
import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class SeleniumStepDefinitions {
    @Inject
    HomePage homePage;
    @Inject
    SearchResultPage searchResultsPage;
    @Inject
    NavigationHelper navigationHelper;
    @Inject
    WebDriver driver;
    public SeleniumStepDefinitions(){
    }
    @Given("^I open a browser$")
    public void I_open_a_browser_chrome(){
        System.out.println("Starting browser \n");
    }

    @When("I navigate to confluence website")
    public void iNavigateToConfluenceWebsite() {

    }

    @And("I click on confluence link")
    public void iClickOnConfluenceLink() {

    }

    @Then("I should be at confluence Home Page")
    public void iShouldBeAtConfluenceHomePage() {
    }

    @When("I navigate to a Website")
    public void iNavigateToAWebsite() {

    }

    @And("I search for Selenium")
    public void iSearchForSelenium() {

    }

    @Then("I should be able to click on desired result")
    public void iShouldBeAbleToClickOnDesiredResult() {
    }
}
