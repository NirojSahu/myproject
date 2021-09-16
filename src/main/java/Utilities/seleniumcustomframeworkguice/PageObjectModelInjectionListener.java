//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.seleniumcustomframework.guice;

import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.spi.InjectionListener;
import com.test.seleniumcustomframework.extension.PageFactory;

class PageObjectModelInjectionListener implements InjectionListener {
    private Provider<Injector> injector;

    public PageObjectModelInjectionListener(Provider<Injector> injector) {
        this.injector = injector;
    }

    public void afterInjection(Object page) {
        PageFactory pageFactory = (PageFactory)((Injector)this.injector.get()).getInstance(PageFactory.class);
        pageFactory.get(page);
    }
}
