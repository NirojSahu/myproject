//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.dependencies;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import Utilities.seleniumcustomframework.extension.fieldInitialisers.FieldInitialiser;
import Utilities.seleniumcustomframework.extension.helpers.FieldInitialiserSort;
import Utilities.seleniumcustomframework.extension.helpers.SortingHelper;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

class FieldInitialiserDependencyModule extends AbstractModule {
    private Reflections reflections;

    FieldInitialiserDependencyModule() {
    }

    protected void configure() {
        Multibinder fieldInitialiserMultibinder = Multibinder.newSetBinder(this.binder(), FieldInitialiser.class);
        Set fieldInitialisers = this.getReflections().getSubTypesOf(FieldInitialiser.class);
        List sortedInitialisers = SortingHelper.asSortedList(fieldInitialisers, new FieldInitialiserSort());
        Iterator var4 = sortedInitialisers.iterator();

        while(var4.hasNext()) {
            Class fieldInitialiserClass = (Class)var4.next();
            fieldInitialiserMultibinder.addBinding().to(fieldInitialiserClass).in(Singleton.class);
        }

    }

    private Reflections getReflections() {
        if (this.reflections != null) {
            return this.reflections;
        } else {
            String packageName = FieldInitialiser.class.getPackage().getName();
            ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
            configurationBuilder.setScanners(new Scanner[]{new SubTypesScanner()});
            configurationBuilder.filterInputsBy((new FilterBuilder()).includePackage(new String[]{packageName}));
            configurationBuilder.setUrls(ClasspathHelper.forPackage(packageName, new ClassLoader[0]));
            this.reflections = new Reflections(configurationBuilder);
            return this.reflections;
        }
    }
}
