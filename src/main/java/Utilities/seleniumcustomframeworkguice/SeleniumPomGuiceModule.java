//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframeworkguice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.Stage;
import com.google.inject.matcher.Matchers;
import Utilities.seleniumcustomframework.extension.PageFactory;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyFactory;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import Utilities.seleniumcustomframework.extension.dependencies.InjectionError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SeleniumPomGuiceModule extends AbstractModule implements DependencyInjector {
    private Injector injector;
    private HashMap<Class, DependencyFactory> factories = new HashMap();
    private PageFactory pageFactory;
    private ArrayList<Module> modules = new ArrayList();

    public SeleniumPomGuiceModule(Module... modules) {
        this.modules.add(this);
        Collections.addAll(this.modules, modules);
    }

    protected void configure() {
        this.bindListener(Matchers.any(), new PageObjectModelTypeListener());
    }

    @Provides
    @Singleton
    PageFactory providePageFactory() {
        if (this.pageFactory != null) {
            return this.pageFactory;
        } else {
            this.pageFactory = new PageFactory(this, new DependencyFactory[0]);
            return this.pageFactory;
        }
    }

    public Injector getInjector() {
        if (this.injector == null) {
            this.injector = Guice.createInjector(Stage.PRODUCTION, this.modules);
        }

        return this.injector;
    }

    public <T> T get(Class<T> aClass) throws InjectionError {
        return this.getInjector().getInstance(aClass);
    }

    public void injectMembers(Object object) {
        this.getInjector().injectMembers(object);
    }
}
