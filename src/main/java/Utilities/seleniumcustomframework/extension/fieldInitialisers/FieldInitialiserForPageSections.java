//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.fieldInitialisers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import Utilities.seleniumcustomframework.extension.PageElement;
import Utilities.seleniumcustomframework.extension.PageElementImpl;
import Utilities.seleniumcustomframework.extension.PageFactory;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import Utilities.seleniumcustomframework.extension.handlers.WebElementHandler;
import Utilities.seleniumcustomframework.extension.helpers.FrameWrapper;
import com.test.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.Annotations;

public class FieldInitialiserForPageSections implements FieldInitialiser {
    @Inject
    private DependencyInjector dependencyInjector = null;
    @Inject
    private Provider<PageFactory> pageFactory = null;
    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator = null;

    public FieldInitialiserForPageSections() {
    }

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWrapper frame) {
        if (!FieldAssessor.isValidPageSection(field)) {
            return false;
        } else {
            Annotations annotations = new Annotations(field);
            PageElement container = this.getPageElementProxy(annotations.buildBy(), searchContext, frame);

            try {
                Object pageSection = this.dependencyInjector.get(field.getType());
                ((PageFactory)this.pageFactory.get()).initializeContainer(pageSection, container, frame);
                field.setAccessible(true);
                field.set(page, pageSection);
            } catch (IllegalAccessException var8) {
                var8.printStackTrace();
            }

            return true;
        }
    }

    private PageElement getPageElementProxy(By by, SearchContext searchContext, FrameWrapper frame) {
        if (frame != null && frame.frameBy.equals(by)) {
            by = By.xpath("//*");
        }

        WebElementHandler elementHandler = new WebElementHandler(this.dependencyInjector, searchContext, by, frame, this.webDriverFrameSwitchingOrchestrator);
        WebElement proxyElement = (WebElement)Proxy.newProxyInstance(WebElement.class.getClassLoader(), new Class[]{WebElement.class, SearchContext.class, WrapsElement.class}, elementHandler);
        return new PageElementImpl(proxyElement);
    }
}
