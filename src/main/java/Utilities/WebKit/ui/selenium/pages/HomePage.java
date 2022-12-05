//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.ui.selenium.pages;

import Utilities.WebKit.ui.selenium.pageSelections.SearchForm;
import Utilities.seleniumcustomframework.extension.annotations.Section;
import Utilities.seleniumcustomframework.extension.annotations.Section;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    @Section
    @FindBy(
        id = "sfdiv"
    )
    public SearchForm searchForm;

    public HomePage() {
    }
}
