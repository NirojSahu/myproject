//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.seleniumcustomframework.extension.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingHelper {
    private SortingHelper() {
    }

    public static <T> List<T> asSortedList(Collection<T> c, Comparator<T> comparator) {
        List<T> list = new ArrayList(c);
        Collections.sort(list, comparator);
        return list;
    }
}
