//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.helpers;

import Utilities.seleniumcustomframework.extension.fieldInitialisers.FieldInitialiser;
import java.util.Comparator;

public class FieldInitialiserSort implements Comparator<Class<? extends FieldInitialiser>> {
    public FieldInitialiserSort() {
    }

    public int compare(Class<? extends FieldInitialiser> aClass, Class<? extends FieldInitialiser> t1) {
        return aClass.getSimpleName().compareTo(t1.getSimpleName());
    }
}
