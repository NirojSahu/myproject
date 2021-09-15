//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Stage;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.test.annotations.StaticInjection;
import com.test.exceptions.StopTestException;
import com.test.seleniumcustomframework.extension.PageFactory;
import com.test.seleniumcustomframework.extension.dependencies.DependencyFactory;
import com.test.seleniumcustomframework.extension.dependencies.DependencyInjector;
import com.test.seleniumcustomframework.extension.dependencies.InjectionError;
import cucumber.api.guice.CucumberModules;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.InjectorSource;
import cucumber.runtime.java.guice.ScenarioScoped;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

public class DI extends AbstractModule implements InjectorSource, DependencyInjector {
    private Injector injector;

    public DI() {
    }

    protected void configure() {
        this.bind(WebDriver.class).toProvider(WebDriverProvider.class).in(ScenarioScoped.class);
        this.bind(Configuration.class).toProvider(ConfigurationProvider.class).in(ScenarioScoped.class);
        ConfigurationBuilder reflectionsConfiguration = (new ConfigurationBuilder()).forPackages(new String[]{"com.test"}).setScanners(new Scanner[]{new MethodAnnotationsScanner(), new SubTypesScanner(), new TypeAnnotationsScanner()});
        Reflections reflections = new Reflections(reflectionsConfiguration);
        this.setAllTestClassesInScenarioScope(reflections);
        this.setupStaticInjection(reflections);
        this.bindListener(Matchers.any(), new PageObjectModelTypeListener());
        Names.bindProperties(this.binder(), this.getProperties());
    }

    @Provides
    @ScenarioScoped
    private PageFactory pageFactory() {
        return new PageFactory(this, new DependencyFactory[0]);
    }

    private void setupStaticInjection(Reflections reflections) {
        Iterator var2 = reflections.getTypesAnnotatedWith(StaticInjection.class).iterator();

        while(var2.hasNext()) {
            Class<?> aClass = (Class)var2.next();
            this.requestStaticInjection(new Class[]{aClass});
        }

    }

    private void setAllTestClassesInScenarioScope(Reflections reflections) {
        ArrayList<Class<?>> scenarioScopedClasses = new ArrayList();
        Class<? extends Annotation>[] cucumberKeywordClasses = new Class[]{Given.class, When.class, Then.class, And.class, But.class, Before.class, After.class};
        Class[] var4 = cucumberKeywordClasses;
        int var5 = cucumberKeywordClasses.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Class<? extends Annotation> cucumberKeywordClass = var4[var6];
            Iterator var8 = reflections.getMethodsAnnotatedWith(cucumberKeywordClass).iterator();

            while(var8.hasNext()) {
                Method method = (Method)var8.next();
                if (!scenarioScopedClasses.contains(method.getDeclaringClass())) {
                    scenarioScopedClasses.add(method.getDeclaringClass());
                }
            }
        }

        Iterator var10 = scenarioScopedClasses.iterator();

        while(var10.hasNext()) {
            Class<?> klass = (Class)var10.next();
            this.bind(klass).in(ScenarioScoped.class);
        }

    }

    private Properties getProperties() {
        try {
            Properties legacyProperties = Configuration.getConfiguration().getProperties();
            legacyProperties.putAll((new TestExecutionSystemProperties()).getProperties());
            return legacyProperties;
        } catch (StopTestException var2) {
            var2.printStackTrace();
            return System.getProperties();
        }
    }

    public Injector getInjector() {
        if (this.injector == null) {
            this.injector = Guice.createInjector(Stage.PRODUCTION, new Module[]{CucumberModules.SCENARIO, this});
        }

        return this.injector;
    }

    public <T> T get(Class<T> aClass) throws InjectionError {
        return this.getInjector().getInstance(aClass);
    }
}
