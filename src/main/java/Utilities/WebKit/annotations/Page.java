package Utilities.WebKit.annotations;

import Utilities.WebKit.helper.NullPageIdHelper;
import Utilities.WebKit.helper.PageIdHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Page {
    String id() default "";

    String path() default "";
    String title() default "";

    Class<? extends PageIdHelper> pageIdDeligate() default NullPageIdHelper.class;
}
