//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.handlers;

import Utilities.seleniumcustomframework.extension.PageElementImpl;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import Utilities.seleniumcustomframework.extension.helpers.FrameWrapper;
import Utilities.seleniumcustomframework.extension.helpers.ReflectionHelper;
import Utilities.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

public class WebElementListHandler implements InvocationHandler, Refreshable {
    private static final Logger LOG = Logger.getLogger(WebElementListHandler.class.getName());
    private DependencyInjector driver;
    private SearchContext searchContext;
    private By by;
    private final FrameWrapper frame;
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator;
    private ArrayList<WebElement> webElements;

    public WebElementListHandler(DependencyInjector driver, SearchContext searchContext, By by, FrameWrapper frame, WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator) {
        this.driver = driver;
        this.searchContext = searchContext;
        this.by = by;
        this.frame = frame;
        this.webDriverFrameSwitchingOrchestrator = webDriverFrameSwitchingOrchestrator;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (this.webElements == null) {
            List<WebElement> elements = this.searchContext.findElements(this.by);
            this.webElements = new ArrayList();
            Iterator var5 = elements.iterator();

            while(var5.hasNext()) {
                WebElement element = (WebElement)var5.next();
                this.webElements.add(this.getWebElementProxy(element));
            }
        }

        try {
            LOG.fine(String.format("Calling %s on %s", method.getName(), this));
            return method.invoke(this.webElements, args);
        } catch (InvocationTargetException var7) {
            LOG.fine(String.format("Error calling %s on %s", method.getName(), this));
            throw var7.getCause();
        }
    }

    private WebElement getWebElementProxy(WebElement webElement) {
        WebElementHandler handler = new WebElementHandler(this.driver, this.searchContext, By.id("Dummy page locator - it is not being used as we're passing WebElement anyway"), this.frame, this.webDriverFrameSwitchingOrchestrator, webElement);
        handler.setParent(this);
        return (WebElement)Proxy.newProxyInstance(WebElement.class.getClassLoader(), new Class[]{WebElement.class, SearchContext.class, WrapsElement.class}, handler);
    }

    public void invalidate() {
        if (this.webElements != null) {
            Iterator var1 = this.webElements.iterator();

            while(var1.hasNext()) {
                WebElement element = (WebElement)var1.next();
                if (element instanceof Proxy) {
                    InvocationHandler invocationHandler = Proxy.getInvocationHandler(element);
                    if (invocationHandler instanceof Refreshable) {
                        ((Refreshable)invocationHandler).refresh();
                    }
                }
            }

        }
    }

    public void refresh() {
        if (this.webElements != null) {
            List<WebElement> elements = this.searchContext.findElements(this.by);
            Field elementField = ReflectionHelper.getField(PageElementImpl.class, "webElement");

            assert elementField != null;

            elementField.setAccessible(true);

            for(int i = 0; i < elements.size(); ++i) {
                WebElement e = (WebElement)elements.get(i);
                Object s = this.webElements.get(i);
                if (s == null) {
                    this.webElements.set(i, this.getWebElementProxy(e));
                }

                try {
                    elementField.set(s, e);
                } catch (IllegalAccessException var7) {
                    var7.printStackTrace();
                } catch (IllegalArgumentException var8) {
                    var8.printStackTrace();
                }
            }

        }
    }

    public void setParent(Refreshable refreshable) {
    }
}
