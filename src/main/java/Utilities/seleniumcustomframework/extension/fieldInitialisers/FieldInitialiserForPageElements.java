//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.fieldInitialisers;

import com.google.inject.Inject;
import Utilities.seleniumcustomframework.extension.PageElement;
import Utilities.seleniumcustomframework.extension.PageElementImpl;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import Utilities.seleniumcustomframework.extension.handlers.WebElementHandler;
import Utilities.seleniumcustomframework.extension.helpers.FrameWrapper;
import Utilities.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.Annotations;

public class FieldInitialiserForPageElements implements FieldInitialiser {
    @Inject
    private DependencyInjector dependencyInjector = null;
    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator = null;

    public FieldInitialiserForPageElements() {
    }

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWrapper frame) {
        if (!FieldAssessor.isValidPageElement(field)) {
            return false;
        } else {
            Annotations annotations = new Annotations(field);

            try {
                PageElement pageElementProxy = this.getPageElementProxy(this.dependencyInjector, annotations.buildBy(), searchContext, frame, this.webDriverFrameSwitchingOrchestrator);
                field.setAccessible(true);
                field.set(page, pageElementProxy);
            } catch (IllegalAccessException var7) {
                var7.printStackTrace();
            }

            return true;
        }
    }

    private PageElement getPageElementProxy(DependencyInjector driver, By by, SearchContext searchContext, FrameWrapper frame, WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator) {
        WebElementHandler elementHandler = new WebElementHandler(driver, searchContext, by, frame, webDriverFrameSwitchingOrchestrator);
        WebElement proxyElement = (WebElement)Proxy.newProxyInstance(WebElement.class.getClassLoader(), new Class[]{WebElement.class, SearchContext.class, WrapsElement.class}, elementHandler);
        return new PageElementImpl(proxyElement);
    }
}
