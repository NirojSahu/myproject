//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.seleniumcustomframework.extension;

import com.test.seleniumcustomframework.extension.handlers.DynamicHandler;
import com.test.seleniumcustomframework.extension.handlers.Refreshable;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.internal.WrapsElement;

public interface PageElement extends WebElement, Locatable, WebElementExtensions, WrapsElement, SearchContext, DynamicHandler, Refreshable {
}
