//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.seleniumcustomframework.extension.webdriverConditions;

import javax.annotation.Nullable;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ElementLocationStaticCondition implements ExpectedCondition<WebElement> {
    private Point location;
    private WebElement element;

    public ElementLocationStaticCondition(WebElement element) {
        this.element = element;
        this.location = element.getLocation();
    }

    @Nullable
    public WebElement apply(@Nullable WebDriver webDriver) {
        try {
            Thread.sleep(250L);
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

        Point newLocation = this.element.getLocation();
        if (!this.location.equals(newLocation)) {
            this.location = newLocation;
            return null;
        } else {
            return this.element;
        }
    }
}
