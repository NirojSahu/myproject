//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.lynx;

import Utilities.WebKit.helper.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LynxHomePage extends BasePage {
    public LynxHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputAndSearch(String keyword) {
        this.driver.findElement(By.id("obj_calificar")).sendKeys(new CharSequence[]{keyword});
        this.driver.findElement(By.id("BUSCAR_BUTTON")).click();
    }
}
