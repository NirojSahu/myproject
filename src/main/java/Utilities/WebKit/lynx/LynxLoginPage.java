//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.lynx;

import Utilities.WebKit.helper.BasePage;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LynxLoginPage extends BasePage {
    public LynxLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void toLynxHomePage() throws MalformedURLException {
        this.driver.get(PropertyReader.getLynxUrl());
        this.driver.findElement(By.name("sUsuarioForm")).sendKeys(new CharSequence[]{PropertyReader.getLynxUsername()});
        this.driver.findElement(By.id("sClave")).sendKeys(new CharSequence[]{PropertyReader.getLynxPassword()});
        WebElement element = this.driver.findElement(By.name("lAplicacion"));
        (new Select(element)).selectByVisibleText(PropertyReader.getLynxSystype());
        this.driver.findElement(By.id("LOGIN_BUTTON")).click();
    }
}
