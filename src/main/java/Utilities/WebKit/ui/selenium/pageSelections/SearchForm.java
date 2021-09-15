//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.ui.selenium.pageSelections;

import com.google.inject.Inject;
import com.test.seleniumcustomframework.extension.PageElement;
import com.test.seleniumcustomframework.extension.PageSection;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchForm extends PageSection {
    @Inject
    WebDriver driver;
    @FindBy(
        css = "input[name='q']"
    )
    PageElement query;

    public SearchForm() {
    }

    public void searchFor(String term) {
        this.query.sendKeys(new CharSequence[]{term});
        this.query.submit();
    }
}
