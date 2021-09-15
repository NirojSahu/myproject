//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.fieldInitialisers;

import com.google.inject.Inject;
import com.test.seleniumcustomframework.extension.ElementListImpl;
import com.test.seleniumcustomframework.extension.PageElement;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import Utilities.seleniumcustomframework.extension.exceptions.PageFactoryError;
import com.test.seleniumcustomframework.extension.handlers.ElementListHandler;
import Utilities.seleniumcustomframework.extension.handlers.WebElementListHandler;
import Utilities.seleniumcustomframework.extension.helpers.FrameWrapper;
import com.test.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;

public class FieldInitialiserForSeleniumLegacyWebElementLists implements FieldInitialiser {
    @Inject
    private DependencyInjector dependencyInjector = null;
    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator = null;

    public FieldInitialiserForSeleniumLegacyWebElementLists() {
    }

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWrapper frame) {
        if (!FieldAssessor.isValidWebElementList(field)) {
            return false;
        } else {
            Annotations annotations = new Annotations(field);
            WebElementListHandler elementListHandler = new WebElementListHandler(this.dependencyInjector, searchContext, annotations.buildBy(), frame, this.webDriverFrameSwitchingOrchestrator);
            List webElementListProxy = (List)Proxy.newProxyInstance(WebElement.class.getClassLoader(), new Class[]{List.class}, elementListHandler);
            ElementListImpl webElementListExtensions = new ElementListImpl(searchContext, webElementListProxy);
            InvocationHandler pageElementHandler = new ElementListHandler(webElementListProxy, webElementListExtensions);
            List pageElementListProxy = (List)Proxy.newProxyInstance(PageElement.class.getClassLoader(), new Class[]{List.class}, pageElementHandler);

            try {
                field.setAccessible(true);
                field.set(page, pageElementListProxy);
            } catch (IllegalAccessException var12) {
                throw new PageFactoryError(var12.getCause());
            }

            return true;
        }
    }
}
