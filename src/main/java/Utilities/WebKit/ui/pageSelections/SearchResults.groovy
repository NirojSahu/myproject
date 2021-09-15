//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.ui.selenium.pageSections;

import com.test.seleniumcustomframework.extension.PageSection;
import com.test.seleniumcustomframework.extension.annotations.Section;
import java.util.List;
import org.openqa.selenium.support.FindBy;

public class SearchResults extends PageSection {
    @Section
    @FindBy(
        css = "div.rc"
    )
    public List<SearchResult> results;

    public SearchResults() {
    }
}
