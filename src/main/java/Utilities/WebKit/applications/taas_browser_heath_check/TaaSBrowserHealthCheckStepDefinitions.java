//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.applications.taas_browser_heath_check;

import com.google.inject.Inject;
import Utilities.WebKit.helper.NavigationHelper;
import Utilities.WebKit.ui.tass.pages.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class TaaSBrowserHealthCheckStepDefinitions {
    @Inject
    LoginPage loginPage;
    @Inject
    NavigationHelper navigationHelper;
    @Inject
    WebDriver driver;

    public TaaSBrowserHealthCheckStepDefinitions() {
    }

    @Given("^I open a browser1$")
    public void I_open_a_browser_one() throws Throwable {
        System.out.println("Starting browser \n");
    }

    @When("^I navigate to confluence website")
    public void i_navigate_to_a_Website() throws Throwable {
        System.out.println("Navigate to Confluence Login page \n");
        this.navigationHelper.navigateTo("https://confluence.almuk.santanderuk.corp/login.action/");
        Thread.sleep(10000L);
    }

    @And("^I click on Confluence link")
    public void I_click_on_link() throws Throwable {
        System.out.println("Searching for Username Field \n");
        this.loginPage.verifyLabel();
    }

    @Then("^I should be at Confluence Home Page")
    public void I_should_go_to_Home_page() throws Throwable {
        System.out.println("Clicking on desired result \n");
        this.loginPage.clickConfluence();
    }

    @Given("^I open a browser2$")
    public void I_open_a_browser_two() throws Throwable {
        System.out.println("Starting browser \n");
    }
}