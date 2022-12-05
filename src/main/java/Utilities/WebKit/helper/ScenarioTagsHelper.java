//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.helper;

import cucumber.runtime.java.guice.ScenarioScoped;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ScenarioScoped
public class ScenarioTagsHelper {
    private final List<String> tags = new ArrayList();

    public ScenarioTagsHelper() {
    }

    public void addAll(Iterable<String> newTags) {
        Iterator var2 = newTags.iterator();

        while(var2.hasNext()) {
            String tag = (String)var2.next();
            this.tags.add(tag.replace("@", ""));
        }

    }

    public List<String> getTags() {
        return this.tags;
    }

    public void clear() {
        this.tags.clear();
    }
}
