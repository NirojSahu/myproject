//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.ui.selenium.pages;

import com.test.seleniumcustomframework.extension.PageElement;
import com.test.seleniumcustomframework.extension.annotations.Section;
import com.test.ui.selenium.pageSections.SearchResults;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage {
    public PageElement resultstats;
    @Section
    @FindBy(
        id = "center_col"
    )
    public SearchResults searchResults;

    public SearchResultsPage() {
    }
}
