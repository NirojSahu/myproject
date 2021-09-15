/*
package Utilities.WebKit.helper;

import Utilities.WebKit.configuration.PageFactory;
import org.openqa.selenium.WebDriver;

public class ExecControlHelper {
    static final String STOP_TEST_PROP_NAME="autotest.stop.test";
    static ThreadLocal<String> screenshotLocationTL=new ThreadLocal();
    private static String exceptionExplanation;
    private static Object[] windowHandle=null;

    public ExecControlHelper(){
    }
    public static void stopTest(String message){
        System.setProperties("autotest.stop_test", "true");
        exceptionExplanation=message;
    }

    public static void resetStopTest(){s
        System.clearProperty("autotest.stop_test");
        exceptionExplanation="";
    }
    public static void checkStopTest() throws StopTestException {
        if (System.getProperties("autotest.stop_test") !=null && System.getProperties("autotest.stop_test").equals("true")){
            throw  new StopTestException(getMessage(), false);
        }
    }

    public static String getMessage(){
        return exceptionExplanation + getSystemDetails();
    }

    public static void switchDriverToWindowZero(){
        WebDriver driver= PageFactory.getCurrentDriver();
        windowHandle=driver.getWindowHandles().toArray();
        driver.switchTo().window((String)windowHandle[0]);
    }

    public static void switchDriverPopup(int handle){
        WebDriver driver=PageFactory.getCurrentDriver();
        windowHandle=driver.getWindowHandles().toArray();
        driver.switchTo().window((String)windowHandle[handle]);
    }
    public static void switchDriverToWindowOne(){
        WebDriver driver=PageFactory.getCurrentDriver();
        windowHandle=driver.getWindowHandles().toArray();

        for (int i=0; windowHandle.length<2; windowHandle=driver.getWindowHandles().toArray()){
            try {
                Thread.sleep(5000L);
            }catch (InterruptedException var4){
                var4.printStackTrace();
            }
            if (i>10){
                break;
            }
            i++;
        }
        try {
            driver.switchTo().window((String) windowHandle[1]);
        }catch (Exception var3){
            driver.switchTo().window((String) windowHandle[0]);
        }
    }
    public static String getScreenshotLocation(){
        return screenshotLocationTL.get();
    }
    public static String getScreenshotLocation(String screenshotLocation){
        return screenshotLocationTL.set(screenshotLocation);
    }
    public static void closePopup

}
*/
