//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframeworkguice;

import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import Utilities.seleniumcustomframework.extension.fieldInitialisers.FieldAssessor;
import Utilities.seleniumcustomframework.extension.helpers.ReflectionHelper;
import java.lang.reflect.Field;
import java.util.Iterator;

class PageObjectModelTypeListener implements TypeListener {
    PageObjectModelTypeListener() {
    }

    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        if (this.isPageObject(typeLiteral.getRawType())) {
            typeEncounter.register(new PageObjectModelInjectionListener(typeEncounter.getProvider(Injector.class)));
        }

    }

    private boolean isPageObject(Class<?> rawType) {
        Iterator var2 = ReflectionHelper.getAllClassFields(rawType).iterator();

        while(var2.hasNext()) {
            Field field = (Field)var2.next();
            if (FieldAssessor.isSeleniumPomField(field)) {
                return true;
            }
        }

        return false;
    }
}
