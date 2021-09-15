//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.helper;

import com.test.configuration.Configuration;
import com.test.configuration.PageFactory;
import com.test.exceptions.StopTestException;
import org.openqa.selenium.WebDriver;

public class ExecControlHelper {
    static final String STOP_TEST_PROP_NAME = "autotest.stop_test";
    static ThreadLocal<String> screenshotLocationTL = new ThreadLocal();
    private static String exceptionExplanation;
    private static Object[] windowHandle = null;

    public ExecControlHelper() {
    }

    public static void stopTest(String message) {
        System.setProperty("autotest.stop_test", "true");
        exceptionExplanation = message;
    }

    public static void resetStopTest() {
        System.clearProperty("autotest.stop_test");
        exceptionExplanation = "";
    }

    public static void checkStopTest() throws StopTestException {
        if (System.getProperty("autotest.stop_test") != null && System.getProperty("autotest.stop_test").equals("true")) {
            throw new StopTestException(getMessage(), false);
        }
    }

    public static String getMessage() {
        return exceptionExplanation + getSystemDetails();
    }

    public static void switchDriverToWindowZero() throws StopTestException {
        WebDriver driver = PageFactory.getCurrentDriver();
        windowHandle = driver.getWindowHandles().toArray();
        driver.switchTo().window((String)windowHandle[0]);
    }

    public static void switchDriverToPopup(int handle) throws StopTestException {
        WebDriver driver = PageFactory.getCurrentDriver();
        windowHandle = driver.getWindowHandles().toArray();
        driver.switchTo().window((String)windowHandle[handle]);
    }

    public static void switchDriverToWindowOne() throws StopTestException {
        WebDriver driver = PageFactory.getCurrentDriver();
        windowHandle = driver.getWindowHandles().toArray();

        for(int i = 0; windowHandle.length < 2; windowHandle = driver.getWindowHandles().toArray()) {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }

            if (i > 10) {
                break;
            }

            ++i;
        }

        try {
            driver.switchTo().window((String)windowHandle[1]);
        } catch (Exception var3) {
            driver.switchTo().window((String)windowHandle[0]);
        }

    }

    public static String getScreenshotLocation() {
        return (String)screenshotLocationTL.get();
    }

    public static void setScreenshotLocation(String screenshotLocation) {
        screenshotLocationTL.set(screenshotLocation);
    }

    public static void closePopUpWindow(int index) throws StopTestException {
        WebDriver driver = PageFactory.getCurrentDriver();
        windowHandle = driver.getWindowHandles().toArray();

        try {
            driver.switchTo().window((String)windowHandle[index]).close();
        } catch (Exception var3) {
        }

        driver.switchTo().window((String)windowHandle[0]);
    }

    public static String getSystemDetails() {
        String systemDetails = "";

        try {
            systemDetails = " env = " + Configuration.getConfiguration().getEnvironment() + " System host " + Configuration.getConfiguration().getGridNode();
        } catch (Exception var2) {
        }

        return systemDetails;
    }
}
