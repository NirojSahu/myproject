package com.test.pages;

//import com.test.helper.BasePage;
//import com.test.seleniumcustomframework.extension.PageElement;
import Utilities.WebKit.helper.BasePage;
import Utilities.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.support.FindBy;

public class ResultsPage extends BasePage {

    @FindBy(css = "#resultStats")
    private PageElement resultsInfo;

    public boolean isResultsDisplayed() {
        return resultsInfo.getText().contains("results");
    }

    public boolean isTitleDisplayed(String title) {
        return driver.getTitle().contains(title);
    }


}
