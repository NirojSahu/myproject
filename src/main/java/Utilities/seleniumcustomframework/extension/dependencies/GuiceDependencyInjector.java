//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.seleniumcustomframework.extension.dependencies;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.Stage;
import com.test.seleniumcustomframework.extension.PageFactory;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GuiceDependencyInjector extends AbstractModule implements DependencyInjector {
    private Injector injector;
    private HashMap<Class, DependencyFactory> factories = new HashMap();
    ArrayList<Module> modules = new ArrayList();
    private PageFactory pageFactory;

    public GuiceDependencyInjector(PageFactory pageFactory, DependencyFactory... factories) {
        this.pageFactory = pageFactory;
        this.modules.add(this);
        DependencyFactory[] var3 = factories;
        int var4 = factories.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            DependencyFactory factory = var3[var5];
            this.registerFactory(factory);
        }

    }

    private Injector getInjector() {
        if (this.injector != null) {
            return this.injector;
        } else {
            this.injector = Guice.createInjector(Stage.PRODUCTION, this.modules);
            return this.injector;
        }
    }

    protected void configure() {
        Iterator var1 = this.factories.keySet().iterator();

        while(var1.hasNext()) {
            Type type = (Type)var1.next();
            this.bind((Class)type).toProvider((Provider)this.factories.get(type));
        }

    }

    public <T> T get(Class<T> klass) throws InjectionError {
        return this.getInjector().getInstance(klass);
    }

    public void injectMembers(Object object) {
        this.getInjector().injectMembers(object);
    }

    @Provides
    @Singleton
    public PageFactory pageFactory() {
        return this.pageFactory;
    }

    private <T> void registerFactory(DependencyFactory<T> factory) {
        Type[] typeParameters = factory.getClass().getGenericInterfaces();
        Type[] var3 = typeParameters;
        int var4 = typeParameters.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Type typeParameter = var3[var5];
            if (typeParameter instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType)typeParameter;
                this.factories.put((Class)parameterizedType.getActualTypeArguments()[0], factory);
            }
        }

    }
}
