package com.test.pages;

import Utilities.WebKit.helper.BasePage;
//import com.test.helper.BasePage;
//import com.test.seleniumcustomframework.extension.PageElement;
import Utilities.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "input[name='q']")
    private PageElement searchArea;

    @FindBy(css = "input[value='Google Search']")
    private PageElement searchButton;

    @FindBy(css = "#main-menu--special div ul.menu.nav.navbar-nav.nav-mega li:nth-child(1) a")
    private PageElement currentAccount;

    public void enterKeyword(String keyword) {
       searchArea.set(keyword);
     //   searchArea.sendKeys(keyword);
      // searchArea.sendKeys(Keys.RETURN);
    }

    public void clickOnSearch() {

        searchButton.click();

    }
    public void selectedAccount(String accountType) {
        currentAccount.click();

    }
}
