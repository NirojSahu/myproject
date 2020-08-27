package Utilities.WebKit.ui.pageSelections;

import Utilities.seleniumcustomframework.extension.PageElement;
import Utilities.seleniumcustomframework.extension.PageSection;
import org.openqa.selenium.support.FindBy;

public class SearchResult extends PageSection {
    @FindBy(
            css=".st"
    )
    public PageElement description;
    @FindBy(
            css=".r a"
    )
    private PageElement link;
    public SearchResult(){
    }
    public void select(){
        this.link.click();
    }
    public String getUrl(){
        return this.link.getAttribute("href");
    }
}
