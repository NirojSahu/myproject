//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.lynx;

import Utilities.WebKit.helper.BasePage;
import java.net.MalformedURLException;
import java.net.URL;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LynxUtility extends BasePage {
    public LynxUtility() {
    }

    public boolean searchAndVerifyTransactionForAcc(String accNum, String amount, String transactioncode, String responsecode) throws MalformedURLException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "browserdrivers/chromedriver.exe");
        String serverURL = "http://sh-cap2-zone3-taassel-pro.appls.cap2.paas.gsnetcloud.corp/wd/hub";
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL(serverURL), options);
        LynxLoginPage lynxLoginPage = new LynxLoginPage(this.driver);
        lynxLoginPage.toLynxHomePage();
        LynxHomePage lynxHomePage = new LynxHomePage(this.driver);
        lynxHomePage.inputAndSearch(accNum);
        LynxTransactionPage lynxTransactionPage = new LynxTransactionPage(this.driver);
        boolean result = lynxTransactionPage.verifyTransactionForAcc(accNum, amount, transactioncode, responsecode);
        lynxTransactionPage.logout();
        this.driver.close();
        return result;
    }

    @Test
    public void result() throws MalformedURLException, InterruptedException {
        boolean result = this.searchAndVerifyTransactionForAcc("09012659331574", "5.00", "118", "000");
        System.out.println(result);
    }
}
