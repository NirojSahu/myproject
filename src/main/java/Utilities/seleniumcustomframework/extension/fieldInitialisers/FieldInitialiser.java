package Utilities.seleniumcustomframework.extension.fieldInitialisers;

import Utilities.seleniumcustomframework.extension.exceptions.PageFactortError;
import Utilities.seleniumcustomframework.extension.helpers.FrameWeapper;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.Field;

public interface FieldInitialiser{
    Boolean initialiseField(Field var1, Object var2, SearchContext var3, FrameWeapper var4) throws PageFactortError;
}
