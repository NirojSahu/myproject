package Utilities.seleniumcustomframework.extension.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PageFilter {
    Class<? extends Validator>[] value();
}
