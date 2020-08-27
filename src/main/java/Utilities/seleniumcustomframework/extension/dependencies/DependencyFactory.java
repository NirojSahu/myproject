package Utilities.seleniumcustomframework.extension.dependencies;

import com.google.inject.Provider;

public interface DependencyFactory<T> extends Provider {
    T get();
}
