//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.ui.selenium.pages;

import com.test.seleniumcustomframework.extension.PageElement;
import com.test.seleniumcustomframework.extension.annotations.Section;
import Utilities.WebKit.ui.selenium.pageSelections.SearchResults;
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
