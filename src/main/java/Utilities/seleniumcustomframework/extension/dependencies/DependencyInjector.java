//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.seleniumcustomframework.extension.dependencies;

public interface DependencyInjector {
    <T> T get(Class<T> var1) throws InjectionError;
}
