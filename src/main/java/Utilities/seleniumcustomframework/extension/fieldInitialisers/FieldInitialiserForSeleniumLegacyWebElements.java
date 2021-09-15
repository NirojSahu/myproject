//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.seleniumcustomframework.extension.fieldInitialisers;

import com.google.inject.Inject;
import com.test.seleniumcustomframework.extension.dependencies.DependencyInjector;
import com.test.seleniumcustomframework.extension.handlers.WebElementHandler;
import com.test.seleniumcustomframework.extension.helpers.FrameWrapper;
import com.test.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.Annotations;

public class FieldInitialiserForSeleniumLegacyWebElements implements FieldInitialiser {
    @Inject
    private DependencyInjector dependencyInjector = null;
    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator = null;

    public FieldInitialiserForSeleniumLegacyWebElements() {
    }

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWrapper frame) {
        if (!FieldAssessor.isValidWebElement(field)) {
            return false;
        } else {
            Annotations annotations = new Annotations(field);

            try {
                WebElementHandler elementHandler = new WebElementHandler(this.dependencyInjector, searchContext, annotations.buildBy(), frame, this.webDriverFrameSwitchingOrchestrator);
                WebElement proxyElement = (WebElement)Proxy.newProxyInstance(WebElement.class.getClassLoader(), new Class[]{WebElement.class, SearchContext.class, WrapsElement.class}, elementHandler);
                field.setAccessible(true);
                field.set(page, proxyElement);
            } catch (IllegalAccessException var8) {
                var8.printStackTrace();
            }

            return true;
        }
    }
}
