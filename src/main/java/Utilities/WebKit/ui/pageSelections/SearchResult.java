//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.ui.selenium.pageSections;

import com.test.seleniumcustomframework.extension.PageElement;
import com.test.seleniumcustomframework.extension.PageSection;
import org.openqa.selenium.support.FindBy;

public class SearchResult extends PageSection {
    @FindBy(
        css = ".st"
    )
    public PageElement description;
    @FindBy(
        css = ".r a"
    )
    private PageElement link;

    public SearchResult() {
    }

    public void select() {
        this.link.click();
    }

    public String getUrl() {
        return this.link.getAttribute("href");
    }
}
