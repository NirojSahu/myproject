package Utilities.seleniumcustomframework.extension.fieldInitialisers;

import Utilities.seleniumcustomframework.extension.PageFactory;
import Utilities.seleniumcustomframework.extension.annotations.Section;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import Utilities.seleniumcustomframework.extension.exceptions.PageFactortError;
import Utilities.seleniumcustomframework.extension.handlers.PageSectionListHandler;
import Utilities.seleniumcustomframework.extension.helpers.FrameWeapper;
import Utilities.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

public class FieldInitialiserForPageSelectionLists implements FieldInitialiser {
    @Inject
    private DependencyInjector dependencyInjector=null;
    @Inject
    private Provider<PageFactory> pageFactory= null;
    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverFrameSwitchingOrchestrator =null;

    public FieldInitialiserForPageSelectionLists(){
    }

    public Boolean initialiseField(Field field, Object page, SearchContext searchContext, FrameWeapper frame) throws PageFactortError {
      if (!FieldAssessor.isValidPageSelectionList(field)){
        return false;
        }else {
          ParameterizedType genericTypeImpl= (ParameterizedType) field.getGenericType();
          Type genericTypeArgument =genericTypeImpl.getActualTypeArguments()[0];
          Annotations annotations =new Annotations(field);
          PageSectionListHandler pageSelectionListHandle=new PageSectionListHandler(this.dependencyInjector,searchContext,annotations.buildBy(),genericTypeArgument,this.pageFactory, frame, this.webDriverFrameSwitchingOrchestrator);
          Object proxyInstance= Proxy.newProxyInstance(Section.class.getClassLoader(),new Class[]{List.class}, pageSelectionListHandle);
          field.setAccessible(true);

          try {
              field.set(page, proxyInstance);
          }catch (IllegalAccessException var11){
               throw new PageFactortError(var11.getCause());
          }
          return true;
          }
      }
}
