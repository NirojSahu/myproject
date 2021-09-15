//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.handlers;

import com.google.inject.Provider;
import com.test.seleniumcustomframework.extension.PageElementImpl;
import com.test.seleniumcustomframework.extension.PageFactory;
import com.test.seleniumcustomframework.extension.PageSection;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import com.test.seleniumcustomframework.extension.helpers.FrameWrapper;
import com.test.seleniumcustomframework.extension.helpers.ReflectionHelper;
import com.test.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class PageSectionListHandler implements InvocationHandler, Refreshable {
    private DependencyInjector driver;
    private SearchContext searchContext;
    private By by;
    private Type pageSectionType;
    private Provider<PageFactory> pageFactory;
    private FrameWrapper frame;
    private WebDriverFrameSwitchingOrchestrator webDriverOrchestrator;
    private ArrayList<Object> pageSections;
    private Refreshable parent;

    public PageSectionListHandler(DependencyInjector driver, SearchContext searchContext, By by, Type pageSectionType, Provider<PageFactory> pageFactory, FrameWrapper frame, WebDriverFrameSwitchingOrchestrator webDriverOrchestrator) {
        this.driver = driver;
        this.searchContext = searchContext;
        this.by = by;
        this.pageSectionType = pageSectionType;
        this.pageFactory = pageFactory;
        this.frame = frame;
        this.webDriverOrchestrator = webDriverOrchestrator;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (this.pageSections == null) {
            List<WebElement> elements = this.searchContext.findElements(this.by);
            this.pageSections = new ArrayList();
            Iterator var5 = elements.iterator();

            while(var5.hasNext()) {
                WebElement element = (WebElement)var5.next();
                Object pageSection = this.getPageSection(element);
                this.pageSections.add(pageSection);
            }
        }

        return method.invoke(this.pageSections, args);
    }

    private Object getPageSection(WebElement element) {
        PageElementImpl pageElement = this.getPageElement(element);
        Class<?> pageSectionClass = (Class)this.pageSectionType;
        return ((PageFactory)this.pageFactory.get()).get(pageSectionClass, pageElement, this.frame);
    }

    private PageElementImpl getPageElement(WebElement element) {
        WebElement elementProxy = this.getWebElement(element);
        PageElementImpl pageElement = new PageElementImpl(elementProxy);
        pageElement.setParent(this);
        return pageElement;
    }

    private WebElement getWebElement(WebElement element) {
        WebElementHandler webElementHandler = new WebElementHandler(this.driver, this.searchContext, By.id("Dummy page locator - it is not being used as we're passing WebElement anyway"), this.frame, this.webDriverOrchestrator, element);
        webElementHandler.setParent(this);
        return (WebElement)Proxy.newProxyInstance(webElementHandler.getClass().getClassLoader(), new Class[]{WebElement.class}, webElementHandler);
    }

    public void invalidate() {
        if (this.pageSections != null) {
            Iterator var1 = this.pageSections.iterator();

            while(var1.hasNext()) {
                Object pageSection = var1.next();
                ((PageFactory)this.pageFactory.get()).invalidate(pageSection);
            }
        }

    }

    public void refresh() {
        if (this.parent != null) {
            this.parent.refresh();
        }

        if (this.pageSections != null) {
            List<WebElement> elements = this.searchContext.findElements(this.by);
            Field rootElementField = ReflectionHelper.getField(PageSection.class, "rootElement");
            Field webElementField = ReflectionHelper.getField(PageElementImpl.class, "webElement");
            webElementField.setAccessible(true);
            Field webElementHandlersWebElementField = ReflectionHelper.getField(WebElementHandler.class, "webElement");
            webElementHandlersWebElementField.setAccessible(true);

            assert rootElementField != null;

            rootElementField.setAccessible(true);

            for(int i = 0; i < elements.size(); ++i) {
                WebElement e = this.getWebElement((WebElement)elements.get(i));
                Object s = this.pageSections.get(i);
                if (s == null) {
                    this.pageSections.set(i, this.getPageSection(e));
                }

                try {
                    Object rootElement = rootElementField.get(s);
                    Object webElementProxy = webElementField.get(rootElement);
                    if (webElementProxy instanceof Proxy) {
                        InvocationHandler invocationHandler = Proxy.getInvocationHandler(webElementProxy);
                        if (invocationHandler instanceof WebElementHandler) {
                            webElementHandlersWebElementField.set(invocationHandler, e);
                        }
                    }
                } catch (IllegalAccessException var11) {
                    var11.printStackTrace();
                } catch (IllegalArgumentException var12) {
                    var12.printStackTrace();
                }
            }

        }
    }

    public void setParent(Refreshable refreshable) {
        this.parent = refreshable;
    }
}
