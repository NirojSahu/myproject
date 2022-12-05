//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.handlers;

import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import Utilities.seleniumcustomframework.extension.helpers.FrameWrapper;
import Utilities.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementHandler implements InvocationHandler, Refreshable {
    private WebElement webElement;
    private DependencyInjector dependencyInjector;
    private SearchContext searchContext;
    private By by;
    private FrameWrapper frame;
    private WebDriverFrameSwitchingOrchestrator webDriverOrchestrator;
    private boolean needsRefresh;
    private Refreshable parent;

    public WebElementHandler(DependencyInjector dependencyInjector, SearchContext searchContext, By by, FrameWrapper frame, WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator) {
        this(dependencyInjector, searchContext, by, frame, webDriverFrameSwitchingOrchestrator, (WebElement)null);
    }

    WebElementHandler(DependencyInjector dependencyInjector, SearchContext searchContext, By by, FrameWrapper frame, WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator, WebElement webElement) {
        this.needsRefresh = false;
        this.dependencyInjector = dependencyInjector;
        this.searchContext = searchContext;
        this.by = by;
        this.frame = frame;
        this.webDriverOrchestrator = webDriverFrameSwitchingOrchestrator;
        this.webElement = webElement;
    }

    public WebDriver getDriver() {
        return (WebDriver)this.dependencyInjector.get(WebDriver.class);
    }

    public void invalidate() {
        this.needsRefresh = true;
    }

    public void refresh() {
        this.webElement = null;
    }

    public void setParent(Refreshable refreshable) {
        this.parent = refreshable;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!this.by.equals(By.xpath("//*"))) {
            this.webDriverOrchestrator.useFrame(this.frame);
        }

        if (this.needsRefresh) {
            if (this.parent == null) {
                this.webElement = this.searchContext.findElement(this.by);
            } else {
                this.webElement = null;
                this.parent.refresh();
            }

            this.needsRefresh = false;
        }

        if (this.webElement == null) {
            this.webElement = this.searchContext.findElement(this.by);
        }

        try {
            return method.invoke(this.webElement, args);
        } catch (InvocationTargetException var5) {
            throw var5.getCause();
        }
    }
}
