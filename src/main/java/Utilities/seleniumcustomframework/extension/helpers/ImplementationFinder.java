//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.helpers;

import Utilities.seleniumcustomframework.extension.annotations.PageFilter;
import Utilities.seleniumcustomframework.extension.annotations.Validator;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;

public class ImplementationFinder<T> {
    private Class<T> pageClass;
    private DependencyInjector dependencyInjector;
    private static HashMap<String, Reflections> reflectionsCache = new HashMap();

    public ImplementationFinder(Class<T> pageClass, DependencyInjector dependencyInjector) {
        this.pageClass = pageClass;
        this.dependencyInjector = dependencyInjector;
    }

    public T find() {
        Set<Class<? extends T>> subTypesOf = this.getReflections(this.pageClass).getSubTypesOf(this.pageClass);
        ArrayList<Class<? extends T>> sortedListOfImplementationClasses = new ArrayList(subTypesOf);
        Collections.sort(sortedListOfImplementationClasses, new ImplementationFinder.PageFilterAnnotatedClassComparator());
        Iterator var3 = sortedListOfImplementationClasses.iterator();

        Class klass;
        boolean valid;
        do {
            PageFilter annotation;
            do {
                if (!var3.hasNext()) {
                    return this.dependencyInjector.get(this.pageClass);
                }

                klass = (Class)var3.next();
                annotation = (PageFilter)klass.getAnnotation(PageFilter.class);
            } while(annotation == null);

            valid = true;
            Class[] var7 = annotation.value();
            int var8 = var7.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                Class value = var7[var9];
                Validator validator = (Validator)this.dependencyInjector.get(value);
                if (!validator.isValid()) {
                    valid = false;
                    break;
                }
            }
        } while(!valid);

        return (T) this.dependencyInjector.get(klass);
    }

    private Reflections getReflections(Class<T> pageClass) {
        String packageName = pageClass.getPackage().getName();
        if (reflectionsCache.containsKey(packageName)) {
            return (Reflections)reflectionsCache.get(packageName);
        } else {
            Reflections reflections = new Reflections(pageClass.getPackage().getName(), new Scanner[0]);
            reflectionsCache.put(packageName, reflections);
            return reflections;
        }
    }

    private class PageFilterAnnotatedClassComparator implements Comparator<Class<? extends T>> {
        private PageFilterAnnotatedClassComparator() {
        }

        public int compare(Class<? extends T> o1, Class<? extends T> o2) {
            int o1ValidatorsCount = 0;
            int o2ValidatorsCount = 0;
            PageFilter pageFilterO1 = (PageFilter)o1.getAnnotation(PageFilter.class);
            PageFilter pageFilterO2 = (PageFilter)o2.getAnnotation(PageFilter.class);
            if (pageFilterO1 != null) {
                o1ValidatorsCount = pageFilterO1.value().length;
            }

            if (pageFilterO2 != null) {
                o2ValidatorsCount = pageFilterO2.value().length;
            }

            return Integer.compare(o2ValidatorsCount, o1ValidatorsCount);
        }
    }
}
