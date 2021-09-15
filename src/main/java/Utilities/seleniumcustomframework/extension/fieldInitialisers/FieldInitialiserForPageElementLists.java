//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.fieldInitialisers;

import com.google.inject.Inject;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import Utilities.seleniumcustomframework.extension.exceptions.PageFactoryError;
import Utilities.seleniumcustomframework.extension.handlers.PageElementListHandler;
import Utilities.seleniumcustomframework.extension.helpers.FrameWrapper;
import com.test.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.List;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;

public class FieldInitialiserForPageElementLists implements FieldInitialiser {
    @Inject
    private DependencyInjector dependencyInjector = null;
    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator = null;

    public FieldInitialiserForPageElementLists() {
    }

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWrapper frame) {
        if (!FieldAssessor.isValidPageElementList(field)) {
            return false;
        } else {
            Annotations annotations = new Annotations(field);
            PageElementListHandler elementListHandler = new PageElementListHandler(this.dependencyInjector, searchContext, annotations.buildBy(), frame, this.webDriverFrameSwitchingOrchestrator);
            List webElementListProxy = (List)Proxy.newProxyInstance(WebElement.class.getClassLoader(), new Class[]{List.class}, elementListHandler);

            try {
                field.setAccessible(true);
                field.set(page, webElementListProxy);
            } catch (IllegalAccessException var9) {
                throw new PageFactoryError(var9.getCause());
            }

            return true;
        }
    }
}
