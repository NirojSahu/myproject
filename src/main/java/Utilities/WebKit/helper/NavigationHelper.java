package Utilities.WebKit.helper;

import Utilities.BasePage;
import com.google.inject.Inject;

public class NavigationHelper {
    @Inject
    BasePage driver;
    public NavigationHelper{

    }
    public void navigateTo(String url){
        this.driver.currentBrowser().navigate().to(url);
        this.driver.currentBrowser.manage().timeout().timeouts().implicityWait(60L,TimeUnit.SECONDS);
    }
    public void clearBrowserCache(){
        this.driver.currentBrowser().manage().deleteAllCookies();
    }
}
