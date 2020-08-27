package Utilities.seleniumcustomframework.extension;

import org.openqa.selenium.SearchContext;

import java.lang.reflect.Method;
import java.util.List;

public class ElementListImpl {
    public ElementListImpl(SearchContext searchContext, List WebElementListProxy){
    }
    public boolean canHandle(Method method){
        return false;
    }
}
