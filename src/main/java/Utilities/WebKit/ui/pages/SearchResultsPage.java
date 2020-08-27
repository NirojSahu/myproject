package Utilities.WebKit.ui.pages;

import Utilities.WebKit.ui.pageSelections.SearchResults;
import Utilities.seleniumcustomframework.extension.PageElement;
import Utilities.seleniumcustomframework.extension.annotations.Section;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage {
    public PageElement resultstates;
    @Section
    @FindBy(
            id="center_col"
    )
    public SearchResults searchResults;
    public SearchResultsPage(){

    }
}
