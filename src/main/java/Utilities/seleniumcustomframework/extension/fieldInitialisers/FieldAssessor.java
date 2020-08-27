package Utilities.seleniumcustomframework.extension.fieldInitialisers;

import Utilities.seleniumcustomframework.extension.PageElement;
import Utilities.seleniumcustomframework.extension.PageSection;
import Utilities.seleniumcustomframework.extension.annotations.Section;
import okhttp3.internal.connection.RouteSelector;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class FieldAssessor {
    public FieldAssessor(){
    }

    public static boolean isSeleniumPomListField(Field field){
        return isValidPageElementList(field) || isValidWebElementList(field) || isValidPageSelectionList(field);
    }

    public static boolean isSeleniumPomNonListField(Field field){
        return isValidPageElement(field) || isValidWebElement(field) || isValidPageSelection(field);
    }
    public static boolean isSeleniumPomField(Field field){
        return isValidPageElement(field) || isValidPageSelection(field) || isValidWebElement(field)||isValidPageElementList(field) || isValidWebElementList(field) || isValidPageSelectionList(field);
    }
    static boolean isValidPageElementList(Field field){
        Class<?> fieldType =field.getType();
        if (!List.class.isAssignableFrom(fieldType)){
            return false;
        }else {
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)){
                return false;
            }else {
                ParameterizedType genericTypeImpl= (ParameterizedType) genericType;
                return !(genericTypeImpl.getActualTypeArguments()[0] instanceof Class) ? false: PageElement.class.isAssignableFrom((Class)genericTypeImpl.getActualTypeArguments()[0]);
            }
        }
    }
    static boolean isValidWebElement(Field field){
        return PageElement.class.isAssignableFrom(field.getType());
    }
    static boolean isValidPageSelectionList(Field field){
        if (!List.class.isAssignableFrom(field.getType())){
            return false;
        }else if (field.isAnnotationPresent(Section.class)){
            return true;
        }else {
            Type genericType =field.getGenericType();
            if (!(genericType instanceof ParameterizedType)){
                return false;
            }else {
                ParameterizedType genericTypeImpl= (ParameterizedType) genericType;
                if (!(genericTypeImpl.getActualTypeArguments()[0] instanceof Class)){
                    return false;
                }else {
                    Class<?> genericTypeArgument = (Class) genericTypeImpl.getActualTypeArguments()[0];
                    if (PageElement.class.isAssignableFrom(genericTypeArgument)){
                        return false;
                    }else {
                        return PageSection.class.isAssignableFrom(genericTypeArgument)? true:hasSeleniumFindAnnotation(field);
                    }
                }
            }
        }
    }

    static boolean isValidPageSelection(Field field){
        Class<?> fieldType=field.getType();
        if (List.class.isAssignableFrom(fieldType)){
            return false;
        }else if (PageElement.class.isAssignableFrom(fieldType)){
            return false;
        }else if (WebElement.class.isAssignableFrom(fieldType)){
            return false;
        }else if (field.isAnnotationPresent(Section.class)){
            return true;
        }else {
            return PageSection.class.isAssignableFrom(fieldType)? true:hasSeleniumFindAnnotation(field);
        }
    }

    static boolean isValidPageElement(Field field){
        return !PageElement.class.isAssignableFrom(field.getType())&& WebElement.class.isAssignableFrom(field.getType());
    }
    static boolean isValidWebElementList(Field field){
        Class<?> fieldType=field.getType();
        if (!List.class.isAssignableFrom(fieldType)){
            return false;
        }else {
            Type genericType=field.getGenericType();
            if (!(genericType instanceof ParameterizedType)){
                return false;
            }else {
                ParameterizedType genericTypeImpl= (ParameterizedType) genericType;
                return !(genericTypeImpl.getActualTypeArguments()[0] instanceof Class)? false:WebElement.class.isAssignableFrom((Class)genericTypeImpl.getActualTypeArguments()[0]);
            }
        }
    }

    private static boolean hasSeleniumFindAnnotation(Field field){
        if (field.getAnnotation(FindBy.class)!=null){
            return true;
        }else if (field.getAnnotation(FindBys.class)!=null){
            return true;
        }else {
            return field.getAnnotation(FindAll.class) !=null;
        }
    }
}
