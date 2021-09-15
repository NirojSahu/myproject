//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.helper;

import com.google.inject.Inject;
import java.util.concurrent.TimeUnit;

public class NavigationHelper {
    @Inject
    BasePage driver;

    public NavigationHelper() {
    }

    public void navigateTo(String url) {
        this.driver.currentBrowser().navigate().to(url);
        this.driver.currentBrowser().manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    }

    public void clearBrowserCache() {
        this.driver.currentBrowser().manage().deleteAllCookies();
    }
}
