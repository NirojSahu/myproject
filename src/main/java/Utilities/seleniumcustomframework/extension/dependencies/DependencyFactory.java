//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.seleniumcustomframework.extension.dependencies;

import com.google.inject.Provider;

public interface DependencyFactory<T> extends Provider<T> {
    T get();
}
