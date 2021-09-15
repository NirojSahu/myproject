//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.applications.selenium.web.steps;

import com.google.inject.Inject;
import Utilities.WebKit.ui.selenium.pageSelections.SearchResult;
import Utilities.WebKit.ui.selenium.pages.HomePage;
import Utilities.WebKit.ui.selenium.pages.SearchResultsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Optional;
import org.openqa.selenium.WebDriver;

public class SeleniumStepDefinitions {
    @Inject
    HomePage homePage;
    @Inject
    SearchResultsPage searchResultsPage;
    @Inject
    NavigationHelper navigationHelper;
    @Inject
    WebDriver driver;

    public SeleniumStepDefinitions() {
    }

    @Given("^I open a browser$")
    public void I_open_a_browser_chrome() throws Throwable {
        System.out.println("Starting browser \n");
    }

    @When("^I navigate to a Website$")
    public void i_navigate_to_a_Website() throws Throwable {
        System.out.println("Navigate to Google Home page \n");
        this.navigationHelper.navigateTo("http://www.google.co.uk/");
    }

    @And("^I search for Selenium$")
    public void I_add_a_product_to_basket() throws Throwable {
        System.out.println("Searching for Selenium \n");
        this.homePage.searchForm.searchFor("Selenium");
        Thread.sleep(5000L);
    }

    @Then("^I should be able to click on desired result$")
    public void I_should_go_to_basket_page() throws Throwable {
        System.out.println("Clicking on desired result \n");
        Optional<SearchResult> searchResult = this.searchResultsPage.searchResults.results.stream().filter((r) -> {
            return r.getUrl().contains("seleniumhq.org");
        }).findAny();
        if (!searchResult.isPresent()) {
            throw new RuntimeException("Could not find the url");
        } else {
            ((SearchResult)searchResult.get()).select();
        }
    }
}
