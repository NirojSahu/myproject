package Utilities.WebKit.annotations;

import Utilities.WebKit.NullPageIdHelper;
import Utilities.WebKit.PageIdHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Page {
    String id() default "";

    String path() default "";
    String title() default "";

    Class<? extends PageIdHelper> pageIdDeligate() default NullPageIdHelper.class;
}
