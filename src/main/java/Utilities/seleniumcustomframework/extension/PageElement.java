package Utilities.seleniumcustomframework.extension;

import Utilities.seleniumcustomframework.extension.handlers.DynamicHandler;
import Utilities.seleniumcustomframework.extension.handlers.Refreshable;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.internal.WrapsElement;

public interface PageElement extends WebElement, Locatable,WebElementExtensions, WrapsElement, SearchContext, DynamicHandler, Refreshable {

}
