package Utilities.WebKit.ui.pageSelections

import Utilities.seleniumcustomframework.extension.PageSection
import Utilities.seleniumcustomframework.extension.annotations.Section
import org.openqa.selenium.support.FindBy

class SearchResults  extends PageSection{
    @Section
    @FindBy(
            css="div.rc"
    )
    public List<SearchResult> results;
    public SearchResults(){

    }
}
