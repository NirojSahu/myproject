//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.configuration;

import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import com.test.seleniumcustomframework.extension.PageElement;
import com.test.seleniumcustomframework.extension.annotations.Section;
import com.test.seleniumcustomframework.extension.helpers.ClassHelper;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

public class PageObjectModelTypeListener implements TypeListener {
    public PageObjectModelTypeListener() {
    }

    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        if (this.isPageObject(typeLiteral.getRawType())) {
            typeEncounter.register(new PageObjectModelInjectionListener(typeEncounter.getProvider(Injector.class)));
        }

    }

    private boolean isPageObject(Class<?> rawType) {
        Iterator var2 = ClassHelper.getFieldsFromClass(rawType).iterator();

        while(var2.hasNext()) {
            Field field = (Field)var2.next();
            Class<?> type = field.getType();
            if (PageElement.class.isAssignableFrom(type)) {
                return true;
            }

            if (field.isAnnotationPresent(Section.class)) {
                return true;
            }

            if (List.class.isAssignableFrom(type)) {
                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType)genericType;
                    if (!(parameterizedType.getActualTypeArguments()[0] instanceof Class)) {
                        return false;
                    }

                    Class genericTypeArgument = (Class)parameterizedType.getActualTypeArguments()[0];
                    if (PageElement.class.isAssignableFrom(genericTypeArgument)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
