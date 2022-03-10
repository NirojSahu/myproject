//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.fieldInitialisers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import Utilities.seleniumcustomframework.extension.PageFactory;
import Utilities.seleniumcustomframework.extension.annotations.Section;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import Utilities.seleniumcustomframework.extension.exceptions.PageFactoryError;
//import Utilities.seleniumcustomframework.extension.exceptions.
import Utilities.seleniumcustomframework.extension.handlers.PageSectionListHandler;
import Utilities.seleniumcustomframework.extension.helpers.FrameWrapper;
import Utilities.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.Annotations;

public class FieldInitialiserForPageSectionLists implements FieldInitialiser {
    @Inject
    private DependencyInjector dependencyInjector = null;
    @Inject
    private Provider<PageFactory> pageFactory = null;
    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator = null;

    public FieldInitialiserForPageSectionLists() {
    }

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWrapper frame) {
        if (!FieldAssessor.isValidPageSectionList(field)) {
            return false;
        } else {
            ParameterizedType genericTypeImpl = (ParameterizedType)field.getGenericType();
            Type genericTypeArgument = genericTypeImpl.getActualTypeArguments()[0];
            Annotations annotations = new Annotations(field);
            PageSectionListHandler pageSectionListHandler = new PageSectionListHandler(this.dependencyInjector, searchContext, annotations.buildBy(), genericTypeArgument, this.pageFactory, frame, this.webDriverFrameSwitchingOrchestrator);
            Object proxyInstance = Proxy.newProxyInstance(Section.class.getClassLoader(), new Class[]{List.class}, pageSectionListHandler);
            field.setAccessible(true);

            try {
                field.set(page, proxyInstance);
            } catch (IllegalAccessException var11) {
                throw new PageFactoryError(var11.getCause());
            }

            return true;
        }
    }
}
