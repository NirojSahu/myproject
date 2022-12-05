package Utilities.WebKit.ui.tass.pages;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
//import Utilities.WebKit.ui.tass.pages.*;
import Utilities.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    @FindBy(
        className = "list-title"
    )
    public PageElement allUpdates;
    @FindBy(
        className = "aui-header-logo-device"
    )
    public PageElement confluenceLogo;

    public LoginPage() {
    }

    public void verifyLabel() {
        this.allUpdates.isDisplayed();
    }

    public void clickConfluence() {
        this.confluenceLogo.click();
    }
}
