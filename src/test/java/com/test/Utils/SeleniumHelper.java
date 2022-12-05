package com.test.Utils;


import Utilities.seleniumcustomframework.extension.PageElement;
import com.google.common.base.Function;
import com.google.inject.Inject;
//import com.test.seleniumcustomframework.extension.PageElement;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class SeleniumHelper {
    static WebDriver driver;


    @Inject
    public SeleniumHelper(WebDriver driver) { this.driver = driver;
    }

    WebElement element;

    @Inject
    CommonFunctions cmnFunction;

    @Inject
    CommonVariables cmnVrbl;

    public boolean isTextDisplay(By B, String s) {

        Boolean flag=false;
        flag = isStaleElementContainsGivenValue(B, s);
        return flag;
    }


    public static WebDriver getdriver(){
        return driver;
    }

    public static void quitdriver(){
        getdriver().manage().deleteAllCookies();
        getdriver().close();
        getdriver().quit();
    }

     public void maximizebrowser(){
        driver.manage().window().maximize();
      }
    public boolean isNotTextDisplay(By B, String s) {

        Boolean flag=false;
        flag = isStaleElementContainsGivenValue(B, s);
        return flag;
    }



    public void
    waitForPageToLoad() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("return document.readyState").equals("complete");
        //new WebDriverWait(driver, 15).until((ExpectedCondition<Boolean>) wd ->
               // ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

    }

    public boolean waitForJQueryLoad(){
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        return wait.until(jQueryLoad);
    }

    public void waitForJSandJQueryToLoad() {


        String browsername = browsername();

        WebDriverWait wait = new WebDriverWait(driver, 150);
        switch (browsername) {
            case "chrome":
            //case "microsoftedge":

                ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        try {
                            return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                        } catch (Exception e) {
                            // no jQuery present
                            return true;
                        }
                    }
                };

// wait for Javascript to load
                ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                                .toString().equals("complete");
                    }
                };
                System.out.println("going to wait 30 secs in total for JQuery and JS Load to finish");
                wait.until(jQueryLoad);
                wait.until(jsLoad);
                break;

//                ExpectedCondition<Boolean> pageLoadConditionchrome = new
//                        ExpectedCondition<Boolean>() {
//                            public Boolean apply(WebDriver driver) {
//                                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
//                            }
//                        };
//                try {
//                    Thread.sleep(1000);
//                    wait = new WebDriverWait(driver, 60);
//                    wait.until(pageLoadConditionchrome);
//                } catch (Throwable error) {
//
//                }
//
//                ExpectedCondition<Boolean> jQueryLoadchrome = new ExpectedCondition<Boolean>() {
//                    @Override
//                    public Boolean apply(WebDriver driver) {
//                        return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
//                    }
//                };
//
//                try {
//                    Thread.sleep(1000);
//                    wait = new WebDriverWait(driver, 60);
//                    wait.until(jQueryLoadchrome);
//                } catch (Throwable error) {
//
//                }
//
//                break;


            case "firefox":

                ExpectedCondition<Boolean> pageLoadCondition1 = new
                        ExpectedCondition<Boolean>() {
                            public Boolean apply(WebDriver driver) {
                                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                            }
                        };
                try {
                    Thread.sleep(1700);
                    wait = new WebDriverWait(driver, 120);
                    wait.until(pageLoadCondition1);
                } catch (Throwable error) {

                }

                ExpectedCondition<Boolean> jQueryLoad1 = new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                    }
                };

                try {
                    Thread.sleep(1700);
                    wait = new WebDriverWait(driver, 120);
                    wait.until(jQueryLoad1);
                } catch (Throwable error) {

                }

                break;

            case "internet explorer":

                ExpectedCondition<Boolean> pageLoadCondition2 = new
                        ExpectedCondition<Boolean>() {
                            public Boolean apply(WebDriver driver) {
                                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                            }
                        };
                try {
                    Thread.sleep(1000);
                    wait = new WebDriverWait(driver, 20);
                    wait.until(pageLoadCondition2);
                } catch (Throwable error) {

                }

                ExpectedCondition<Boolean> jQueryLoad2 = new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                    }
                };

                try {
                    Thread.sleep(1000);
                    wait = new WebDriverWait(driver, 20);
                    wait.until(jQueryLoad2);
                } catch (Throwable error) {

                }
                break;
        }
    }


//    public boolean waitForJSandJQueryToLoad() {
//
//        WebDriverWait wait = new WebDriverWait(driver, 120);
//
//        // wait for jQuery to load
//        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver driver) {
//                try {
//                    return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
//                }
//                catch (Exception e) {
//                    // no jQuery present
//                    return true;
//                }
//            }
//        };
//
//        // wait for Javascript to load
//        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver driver) {
//                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
//                        .toString().equals("complete");
//            }
//        };
//        System.out.println("going to wait 30 secs in total for JQuery and JS Load to finish");
//        return wait.until(jQueryLoad) && wait.until(jsLoad);
//    }
//

    public String browsername(){
//        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        Capabilities cap =new DesiredCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        System.out.println(browserName);
        return browserName;
    }


    public void mouseover(String elementxpath){
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.xpath(elementxpath));
        action.moveToElement(we).click().build().perform();
    }


    public String getText(WebElement element) {
        String txt = null;
        try {
            waitforElementVisible(element);
            txt = element.getText();
            System.out.println("Expected text:" + txt);
        } catch (NoAlertPresentException e) {
            System.out.println("No Text is present");
        }
        return txt;
    }

    public String getText(String element) {
        String txt = null;
        try {
            waitforElementVisible(element);
            txt = getElement(element).getText();
            System.out.println("Expected text:" + txt);
        } catch (NoAlertPresentException e) {
            System.out.println("No Text is present");
        }
        return txt;
    }
    public String getText_novisiblity_check(String element) {
        String txt = null;
        try {
           // waitforElementVisible(element);
            txt = getElement(element).getText();
            System.out.println("Expected text:" + txt);
        } catch (Exception e) {
            System.out.println("No Text is present");
        }
        return txt;
    }

    public boolean waitforElementVisible(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 120);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception t) {
            throw t;
        }

    }

    public boolean waitforElementVisible_(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(webDriver -> webElement.getAttribute("data-disabled").equals("false"));
            return true;
        } catch (Exception t) {
            throw t;
        }

    }

    public boolean waitforElementInVisible(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.invisibilityOf(webElement));
            return true;
        } catch (Exception t) {
            throw t;
        }

    }
    public boolean waitforElementVisible(String webElementString) {
        try {
            WebElement webElement = getElement(webElementString);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception t) {
            throw t;
        }

    }

    public boolean waitforElementClickable(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (Exception t) {
            throw t;
        }

    }

    public boolean waitforElementToBeVisibleAndClickable(WebElement webElement) {
        try {
            return waitforElementVisible(webElement) && waitforElementClickable(webElement);

        } catch (Exception t) {
            throw t;
        }

    }

    public boolean waitLongerforElementVisible(WebElement webElement, Long timeinSeconds ) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeinSeconds);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception t) {
            throw t;
        }

    }



    public boolean safeCheck(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception t) {
            System.out.println("User Thrown Alert: Webelement was not visible at the time of run "+webElement);
            return false;
        }

    }
    public boolean safeTextCheck(String string) {
        try {
            boolean bol= driver.getPageSource().contains(string);
            return bol;
        } catch (Exception t) {
            System.out.println("User Thrown Alert: Safe Text not existing at the time of run "+string);
            return false;
        }

    }
    public void enter(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", webElement);
            //webElement.click();
        } catch (Exception t) {
            System.out.println("unable to click on WebELement using enter(WebElement webElement): "+ webElement);
            throw t;
        }

    }

    public void enter(String elementLocator) {
        try {
            System.out.println("about to click on element with locator "+elementLocator);
            WebElement element =getElement(elementLocator);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            waitTillClickableElementExist(elementLocator);
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
            //element.click();
            System.out.println("element clicked"+elementLocator);


        } catch (Exception t) {
            System.out.println("unable to click on the element using enter(String elementLocator): "+elementLocator);
            throw t;
        }

    }
    public void enter_list(String elementLocator) {
        try {
            System.out.println("about to click on element with locator "+elementLocator);
            WebElement element =getElement(elementLocator);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            waitTillClickableElementExist(elementLocator);
            //JavascriptExecutor executor = (JavascriptExecutor)driver;
            //executor.executeScript("arguments[0].click();", element);
            element.click();
            System.out.println("element clicked"+elementLocator);


        } catch (Exception t) {
            System.out.println("unable to click on the element using enter(String elementLocator): "+elementLocator);
            throw t;
        }

    }

    public void click() {
        try {
            WebDriverWait wait = new WebDriverWait(driver,60);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul[id='LenderName1SelectBoxItOptions'] li:nth-child(2) a")));
             System.out.println("Is Element Displayed : " + driver.findElement(By.cssSelector("ul[id='LenderName1SelectBoxItOptions'] li:nth-child(2) a")).isDisplayed());
            /*JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = null;
            element = (WebElement)js.executeScript("return document.querySelector(\"ul[id='LenderName1SelectBoxItOptions'] li:nth-child(2) a\");");
            element.click();*/
            driver.findElement(By.id("LenderName1SelectBoxIt")).sendKeys(Keys.ARROW_DOWN);
            driver.findElement(By.id("LenderName1SelectBoxIt")).sendKeys(Keys.ENTER);
            //driver.findElement(By.cssSelector("ul[id='LenderName1SelectBoxItOptions'] li:nth-child(2) a")).click();

        } catch (Exception t) {
            System.out.println("unable to click on the element using enter(String elementLocator): ");
            throw t;
        }

    }

//    public void keys_Down() {
//        try {
//            System.out.println("about to click on element with locator "+elementLocator);
//            WebElement element =getElement(elementLocator);
//            WebDriverWait wait = new WebDriverWait(driver, 15);
//            wait.until(ExpectedConditions.elementToBeClickable(element));
//            waitTillClickableElementExist(elementLocator);
//
//
//        } catch (Exception t) {
//            System.out.println("unable to click on the element using enter(String elementLocator): "+elementLocator);
//            throw t;
//        }
//
//    }

    public void enterbasedondata(String elementLocator, String Testdata) {
        if(!Testdata.isEmpty()) {
            try {
                System.out.println("about to click on element with locator " + elementLocator);
                WebElement element = getElement(elementLocator);
                WebDriverWait wait = new WebDriverWait(driver, 15);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                waitTillClickableElementExist(elementLocator);
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", element);
                //element.click();
                System.out.println("element clicked" + elementLocator);


            } catch (Exception t) {
                System.out.println("unable to click on the element using enter(String elementLocator): " + elementLocator);
                throw t;
            }
        }

    }

    public void enterUsingSendKeys(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.sendKeys(Keys.RETURN);
        } catch (Exception t) {
            throw t;
        }

    }

    public void enterUsingSendKeys(String webElementString) {
        try {
            WebElement element = getElement(webElementString);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(Keys.RETURN);
        } catch (Exception t) {
            System.out.println("unable to click on the element usingenterUsingSendKeys(String webElementString): "+webElementString);
            throw t;
        }

    }

    public void enterUsingKeys(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.sendKeys(Keys.ENTER);
        } catch (Exception t) {
            throw t;
        }

    }
    public void useTABKey(String webElementString) {
        try {
            WebElement element = getElement(webElementString);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(Keys.TAB);
        } catch (Exception t) {
            throw t;
        }

    }
    public void useTabPlusEnterKey(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.sendKeys(Keys.TAB,Keys.ENTER);
        } catch (Exception t) {
            System.out.println("unable to click on the element usingenter Using useTabPlusEnterKey(WebElement webElement): "+webElement);
            throw t;
        }

    }


    public String useTabPlusCtrlKey(WebElement webElement) {
        String url = "";
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.sendKeys(Keys.CONTROL, Keys.TAB);
             url =driver.getCurrentUrl();
        } catch (Exception t) {
            System.out.println("unable to click on the element usingenter Using useTabPlusEnterKey(WebElement webElement): " + webElement);
            throw t;
        }
        return url;
    }
        public void enterUsingJS(PageElement webElement) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
        } catch (Exception t) {
            throw t;
        }

    }

    public void enterUsingJS(String webElement) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(getElement(webElement)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(webElement));
        } catch (Exception t) {
            throw t;
        }

    }

    public void enterText(WebElement webElement, String string) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(string);
        } catch (Exception t) {
            System.out.println("unable to enterText on the element using enterText(WebElement webElement,String string) : "+webElement);
            throw t;
        }

    }


    public void sendkeysenterText(String webElementString, String string) {
        if(!string.isEmpty()) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 5);
                WebElement element = getElement(webElementString);
                wait.until(ExpectedConditions.visibilityOf(element));
                element.sendKeys(string);
            } catch (Exception t) {
                System.out.println("unable to enterText on the element using enterText(WebElement webElement,String string) : " + element);
                throw t;
            }
        }
    }


    public void enterText(String webElementString, String string) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 35);
            WebElement element = getElement(webElementString);
            wait.until(ExpectedConditions.visibilityOf(element));
           // element.click();
            element.clear();
            JavascriptExecutor runJS= ((JavascriptExecutor) driver);
            runJS.executeScript("arguments[0].value='"+string+"';", element);

            //element.sendKeys(string);
        } catch (Exception t) {
            System.out.println("unable to enterText on the element using enterText(WebElement webElementString,String string) : "+webElementString);
            throw t;
        }

    }

    public void enterTextbasedondata(String webElementString, String string) {
        if(!string.isEmpty()) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 35);
                WebElement element = getElement(webElementString);
                wait.until(ExpectedConditions.visibilityOf(element));
                // element.click();
                element.clear();
                JavascriptExecutor runJS = ((JavascriptExecutor) driver);
                runJS.executeScript("arguments[0].value='" + string + "';", element);

                //element.sendKeys(string);
            } catch (Exception t) {
                System.out.println("unable to enterText on the element using enterText(WebElement webElementString,String string) : " + webElementString);
                throw t;
            }
        }

    }

    public void enterTextbasedondata_new(String webElementString, String string) {
        if(!string.isEmpty()) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 35);
                WebElement element = getElement(webElementString);
                wait.until(ExpectedConditions.visibilityOf(element));
                // element.click();
                element.clear();
                element.sendKeys(string);
                /*JavascriptExecutor runJS = ((JavascriptExecutor) driver);
                runJS.executeScript("arguments[0].value='" + string + "';", element);*/

                //element.sendKeys(string);
            } catch (Exception t) {
                System.out.println("unable to enterText on the element using enterText(WebElement webElementString,String string) : " + webElementString);
                throw t;
            }
        }

    }

    public void clearText(String webElementString) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            WebElement element = getElement(webElementString);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
        } catch (Exception t) {
            System.out.println("unable to clear text box : "+webElementString);
            throw t;
        }
    }

    public void clearText(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
        } catch (Exception t) {
            System.out.println("unable to clear text box : "+element);
            throw t;
        }
    }

    public void enterTextWithoutCleaning(String webElementString, String string) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            WebElement element = getElement(webElementString);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
            element.clear();
            element.sendKeys(string);
        } catch (Exception t) {
            System.out.println("unable to enterText on the element using enterTextWithoutCleaning(String webElementString,String string) : "+webElementString);
            throw t;
        }

    }

    public boolean isTextExistingonPage(String string) {
        try {
            waitTillTextExist(string);
            waitForPageToLoad();
            waitTillTextExist(string);
            boolean bol= driver.getPageSource().contains(string);
            return bol;
        } catch (Exception t) {
            System.out.println("string not existing on the page : "+ string);
            throw t;
        }

    }

    public boolean areStringsInListExistingonPage(List<String> list) {
        int count = list.size();
        boolean bol= false;
        boolean finalresult = true;
        for (int i=0;i<count;i++) {
            bol = (isTextExistingonPage(list.get(i)));
            finalresult = bol&&finalresult;
        }
        return finalresult;

    }

    public boolean isTextNotExistingonPage(String string) {
        try {
            waitTillTextExist(string);
            waitForPageToLoad();
            boolean bol= driver.getPageSource().contains(string);
            return bol;
        } catch (Exception t) {
            System.out.println("string not existing on the page : "+ string);
            throw t;
        }

    }

    public void printPageSource() {
        try {
            waitForPageToLoad();
            System.out.println(driver.getPageSource());
        } catch (Exception t) {
            throw t;
        }

    }

    public int getNoOfWindowHandles() {
        try {
            waitForPageToLoad();
            return driver.getWindowHandles().size();
        } catch (Exception t) {
            throw t;
        }

    }

    public WebElement getElementofListUsingAttribute(List<WebElement> webElements, String attributeName, String attributeValue) {
        try {
            WebElement element = null;
            for(int i=0;i<webElements.size();i++) {

                if(webElements.get(i).getAttribute(attributeName).equals(attributeValue))    {
                    element= webElements.get(i);
                } else {
                    //System.out.println("No Element found");
                }
            }
            return element;
        } catch (Exception t) {
            throw t;
        }

    }

    public List<WebElement> getElementList(String locatorType, String locatorValue) {
        List<WebElement> elements ;
        switch(locatorType) {
            case "tagname":
                elements= driver.findElements(By.tagName(locatorValue));
                break;
            case "xpath":
                elements= driver.findElements(By.xpath(locatorValue));
                break;
            case "name" :
                elements= driver.findElements(By.name(locatorValue));
                break;
            case "css":
                elements = driver.findElements(By.cssSelector(locatorValue));
                break;
            default :
                //unable to open URL
                //log.log_library_error("unable to identify element using LocatorType \"" + strLocatorType+ "\" and Locator Value \"" +strLocatorValue+"\"");
                //LOGGER.error("unable to identify element using LocatorType " + strLocatorType+ "and Locator Value " +strLocatorValue);
                throw new NoSuchElementException("unknown locator Type or locatorName");

        }
        return elements;

    }
    public List<WebElement> getElementList(String strLocator) {
        List<WebElement> elements ;

//        waitForPageToLoad();
       // sleepms(500);
        String strLocatorType= (strLocator.split(":",2)[0]).toLowerCase();
        String strLocatorValue= (strLocator.split(":",2)[1]);


        switch(strLocatorType) {
            case "tagname":
                elements= driver.findElements(By.tagName(strLocatorValue));
                break;
            case "xpath":
                elements= driver.findElements(By.xpath(strLocatorValue));
                break;
            case "name" :
                elements= driver.findElements(By.name(strLocatorValue));
                break;
            case "css":
                elements = driver.findElements(By.cssSelector(strLocatorValue));
                break;
            case "id":
                elements = driver.findElements(By.id(strLocatorValue));
                break;
            default :
                //unable to open URL
                //log.log_library_error("unable to identify element using LocatorType \"" + strLocatorType+ "\" and Locator Value \"" +strLocatorValue+"\"");
                //LOGGER.error("unable to identify element using LocatorType " + strLocatorType+ "and Locator Value " +strLocatorValue);
                throw new NoSuchElementException("unknown locator Type or locatorName");

        }
        return elements;

    }
    public List<WebElement> getElementListUsingTagName(String locatorName) {

        return driver.findElements(By.tagName(locatorName));

    }



    public void printListWebELement(List<WebElement> webElements) {

        for(int i=0;i<webElements.size();i++) {
            System.out.println(webElements.get(i).getText());
        }
    }

    public String getPageSource() {
        String str;
        waitForJSandJQueryToLoad();
        waitForPageToLoad();

        str = driver.getPageSource();
        return str;
    }

    public void check_invisible_element(String str){

        String strLocatorType= (str.split(":",2)[0]).toLowerCase();
        String strLocatorValue= (str.split(":",2)[1]);

        WebDriverWait wait = new WebDriverWait(driver, 30);


        switch(strLocatorType) {

            case "id":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(strLocatorValue)));
                break;
            case "name":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(strLocatorValue)));
                break;
            case "partiallink":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.partialLinkText(strLocatorValue)));
                break;
            case "linktext":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(strLocatorValue)));
                break;
            case "css":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(strLocatorValue)));
                break;
            case "tagname":
                element = driver.findElement(By.tagName(strLocatorValue));
                break;
            case "xpath":
                 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(strLocatorValue)));
                break;
            default :
                throw new NoSuchElementException("unknown locator Type");

        }

    }



    public WebElement getElement(String str)  {
      //  waitForPageToLoad();
        String strLocatorType= (str.split(":",2)[0]).toLowerCase();
        String strLocatorValue= (str.split(":",2)[1]);

        switch(strLocatorType) {

            case "id":
               // driver.manage().timeouts().implicitlyWait(5, TimeUnit.MILLISECONDS);
                element = driver.findElement(By.id(strLocatorValue));
                //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            case "name":
                element = driver.findElement(By.name(strLocatorValue));
                break;
            case "partiallink":
                element = driver.findElement(By.partialLinkText(strLocatorValue));
                break;
            case "linktext":
                element = driver.findElement(By.linkText(strLocatorValue));

                break;
            case "css":
                element = driver.findElement(By.cssSelector(strLocatorValue));
                break;
            case "tagname":
                element = driver.findElement(By.tagName(strLocatorValue));
                break;
            case "xpath":
               // driver.manage().timeouts().implicitlyWait(8, TimeUnit.MILLISECONDS);
                element = driver.findElement(By.xpath(strLocatorValue));
               // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            default :
                //unable to open URL
                //log.log_library_error("unable to identify element using LocatorType \"" + strLocatorType+ "\" and Locator Value \"" +strLocatorValue+"\"");
                //LOGGER.error("unable to identify element using LocatorType " + strLocatorType+ "and Locator Value " +strLocatorValue);
                throw new NoSuchElementException("unknown locator Type");

        } waitforElementVisible(element);
        return element;
    }

    public WebElement getElement(WebElement element, String str)  {
        waitForPageToLoad();
        String strLocatorType= (str.split(":",2)[0]).toLowerCase();
        String strLocatorValue= (str.split(":",2)[1]);
        WebElement elementToReturn;
        switch(strLocatorType) {

            case "id":
                elementToReturn = element.findElement(By.id(strLocatorValue));
                break;
            case "name":
                elementToReturn = element.findElement(By.name(strLocatorValue));
                break;
            case "partiallink":
                elementToReturn = element.findElement(By.partialLinkText(strLocatorValue));
                break;
            case "linktext":
                elementToReturn = element.findElement(By.linkText(strLocatorValue));

                break;
            case "css":
                elementToReturn = element.findElement(By.cssSelector(strLocatorValue));
                break;
            case "tagname":
                elementToReturn = element.findElement(By.tagName(strLocatorValue));
                break;
            case "xpath":
                elementToReturn = element.findElement(By.xpath(strLocatorValue));
                break;
            default :
                //unable to open URL
                //log.log_library_error("unable to identify element using LocatorType \"" + strLocatorType+ "\" and Locator Value \"" +strLocatorValue+"\"");
                //LOGGER.error("unable to identify element using LocatorType " + strLocatorType+ "and Locator Value " +strLocatorValue);
                throw new NoSuchElementException("unknown locator Type");

        } waitforElementVisible(elementToReturn);
        return elementToReturn;
    }

    public WebElement getElement_(String str)  {
        //  waitForPageToLoad();
        String strLocatorType= (str.split(":",2)[0]).toLowerCase();
        String strLocatorValue= (str.split(":",2)[1]);

        switch(strLocatorType) {

            case "id":
                element = driver.findElement(By.id(strLocatorValue));
                break;
            case "name":
                element = driver.findElement(By.name(strLocatorValue));
                break;
            case "partiallink":
                element = driver.findElement(By.partialLinkText(strLocatorValue));
                break;
            case "linktext":
                element = driver.findElement(By.linkText(strLocatorValue));

                break;
            case "css":
                element = driver.findElement(By.cssSelector(strLocatorValue));
                break;
            case "tagname":
                element = driver.findElement(By.tagName(strLocatorValue));
                break;
            case "xpath":
                element = driver.findElement(By.xpath(strLocatorValue));
                break;
            default :
                //unable to open URL
                //log.log_library_error("unable to identify element using LocatorType \"" + strLocatorType+ "\" and Locator Value \"" +strLocatorValue+"\"");
                //LOGGER.error("unable to identify element using LocatorType " + strLocatorType+ "and Locator Value " +strLocatorValue);
                throw new NoSuchElementException("unknown locator Type");

        } waitforElementVisible_(element);
        return element;
    }

    public boolean isTextBoxContainingValue(WebElement webElement) {
        return !webElement.getText().isEmpty();
    }

    public boolean isRadioButtonSelected(String webElement) {
        return getElement(webElement).isSelected();
    }

    public boolean isTextBoxContainingValue(String webElementString) {
        WebElement webElement = getElement(webElementString);
        return !webElement.getText().isEmpty();
    }

    public boolean isElementContainingValue(String webElementString) {
        WebElement webElement = getElement(webElementString);
        return !webElement.getText().isEmpty();
    }
    public boolean isElementContainsGivenValue(WebElement webElement, String string) {
        return webElement.getText().contains(string);
    }
    public boolean isElementContainsGivenValue(String webElementString, String string) {
        WebElement webElement = getElement(webElementString);
        return webElement.getText().toLowerCase().contains(string.toLowerCase());
    }
    public String getElementText(String webElementString) {
        WebElement webElement = getElement(webElementString);
        return webElement.getText();
    }

    public boolean isElementAttributeContainingValue(WebElement webElement, String atributeName ) {
        return !webElement.getAttribute(atributeName).isEmpty();
    }
    public boolean isElementAttributeContainingValue(String webElementString, String atributeName ) {
        WebElement webElement = getElement(webElementString);
        return !webElement.getAttribute(atributeName).isEmpty();
    }

    public boolean isElementAttributeContainsGivenValue(WebElement webElement, String atributeName, String attributeValue ) {
        return webElement.getAttribute(atributeName).equals(attributeValue);
    }

    public void clickElementUsingTagNameAndAttribute(String tagName, String attributeName, String attributeValue) {
        List<WebElement> elements = driver.findElements(By.tagName(tagName));
        WebElement element = getElementofListUsingAttribute(elements,attributeName,attributeValue);
        waitTillClickableElementExist(element);
        enter(element);
        waitForJSandJQueryToLoad();

    }
    public WebElement getElementUsingTagNameAndAttribute(String tagName, String attributeName, String attributeValue) {
        List<WebElement> elements = driver.findElements(By.tagName(tagName));
        WebElement element = getElementofListUsingAttribute(elements,attributeName,attributeValue);
        return element;

    }

    public boolean isURLContainingString(String urlPartialString)
    {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        return wait.until(ExpectedConditions.urlContains(urlPartialString));

    }

    public void selectByVisibleText(WebElement element, String toBeSelected) {
        waitforElementVisible(element);
        Select oSelect = new Select(element);
        oSelect.selectByVisibleText(toBeSelected);
    }

    public void selectByVisibleText(String element, String toBeSelected) {
        waitforElementVisible(element);
        Select oSelect = new Select(getElement(element));
        oSelect.selectByVisibleText(toBeSelected);
    }

    public void selectByMatchingVisibleText(WebElement element, String matchingString) {
        waitforElementVisible(element);
        Select oSelect = new Select(element);
        List<WebElement> elements= oSelect.getOptions();
        for (int i =0;i<elements.size();i++) {
            waitforElementVisible(elements.get(i));
            if (elements.get(i).getText().contains(matchingString)) {
                elements.get(i).click();
                break;
            }
        }

    }

    public void selectByIndex(String elementString, int index) {
        waitforElementVisible(elementString);
        element=getElement(elementString);
        Select oSelect = new Select(element);
        oSelect.selectByIndex(index);
    }

    public void selectByValue(String elementString, String value) {
        waitforElementVisible(elementString);
        element=getElement(elementString);
        Select oSelect = new Select(element);
        oSelect.selectByValue(value);
    }

    public void selectByMatchingVisibleText(String elementString, String matchingString) {
        waitforElementVisible(elementString);
        element=getElement(elementString);
        Select oSelect = new Select(element);
        List<WebElement> elements= oSelect.getOptions();
        for (int i =0;i<elements.size();i++) {
            waitforElementVisible(elements.get(i));
            if (elements.get(i).getText().contains(matchingString)) {
                elements.get(i).click();
                break;
            }
        }
        waitForPageToLoad();
    }

    public boolean selectByMatchingVisibleTextWithReturnType(String elementString, String matchingString) {
        waitforElementVisible(elementString);
        element=getElement(elementString);
        Select oSelect = new Select(element);
        List<WebElement> elements= oSelect.getOptions();
        for (int i =0;i<elements.size();i++) {
            waitforElementVisible(elements.get(i));
            if (elements.get(i).getText().contains(matchingString)) {
                elements.get(i).click();
                // break;
                return true;
            }
        }
        waitForPageToLoad();
        return false;
    }


    public void selectDropDownVisibleText(String elementString, String matchingString) {
        waitforElementVisible(elementString);
        element = getElement(elementString);
        Select oSelect = new Select(element);
        List<WebElement> elements = oSelect.getOptions();
        outer:for (int i = 0; i < elements.size(); i++) {
            waitforElementVisible(elements.get(i));

            elements.get(i).click();
            String str="Download transactions";
            WebElement downloadTransaction = driver.findElement(By.xpath("//a[@class='download']"));
            if(downloadTransaction.getText().contains(str))
            {
                break outer;
            }
/*
                List<WebElement> allLinks = driver.findElements(By.tagName("a"));
                System.out.println("--List size-******-"+ allLinks.size());
                for (WebElement specificlink : allLinks) {
                    System.out.println("--for loop-((((((-"+ specificlink.getText());
                    if (specificlink.getText().contains(matchingString)) {
                        System.out.println("--if loop-)))))-");
                        break outer;
                    }

            }*/
        }
    }     //waitForPageToLoad();


    public Boolean isClickableElementExisting(String elementLocator) {
        WebElement element = getElement(elementLocator);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        //waitforElementToBeVisibleAndClickable(element);
        return waitforElementVisible(element);
    }

    public Boolean isClickableElementExisting(WebElement element) {
        //WebElement element = getElement(elementLocator);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        //waitforElementToBeVisibleAndClickable(element);
        return waitforElementVisible(element);
    }

    public Boolean isVisibleElementExisting(String elementLocator) {
        WebElement element = getElement(elementLocator);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(element));
        //waitforElementToBeVisibleAndClickable(element);
        return waitforElementVisible(element);
    }

    public Boolean isVisibleElementExisting(WebElement element) {
        //WebElement element = getElement(elementLocator);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        //waitforElementToBeVisibleAndClickable(element);
        return waitforElementVisible(element);
    }

    @Deprecated
    public void waitTillTextExist(String string) {
        waitForJSandJQueryToLoad();
        final Boolean[] isWaitSuccessfull = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
//        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
//        wait.withTimeout(2, TimeUnit.MINUTES);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {
                //System.out.println("waitTillTextExist method entering " + string + " STATUS is: " + isWaitSuccessfull[0]+cmnFunction.getCurrentDate());
                //getPageSource();
                if(getPageSource().contains(string)) {
                    isWaitSuccessfull[0] = true;
                    System.out.println("waitTillTextExist method passing time" + string + " STATUS is: " + isWaitSuccessfull[0]+cmnFunction.getCurrentDate());
                    return true;

                }
                else {

                    return false;
                }
            }
        };
        wait.until(function);
        System.out.println("waitTillTextExist method just before exist " + string + " STATUS is: " + isWaitSuccessfull[0]+cmnFunction.getCurrentDate());

    }

    @Deprecated
    public void waitTillTextExist(String string, Integer timeinSeconds) {
        final Boolean[] isWaitSuccessfull = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
//        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
//        wait.withTimeout(timeinSeconds, TimeUnit.SECONDS);


        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {
                String pageSource = getPageSource();
                if(pageSource.contains(string)) {
                    isWaitSuccessfull[0] = true;
                    return true;

                } else {
                    //System.out.println("value of Page source at the time of failure is"+ pageSource);
                    System.out.println("waitTillTextExist method " + string + " STATUS is: " + isWaitSuccessfull[0]+cmnFunction.getCurrentDate());
                    return false;
                }
            }
        };
        wait.until(function);
        System.out.println("waitTillTextExist method " + string + " STATUS is: " + isWaitSuccessfull[0]+cmnFunction.getCurrentDate());

    }

    public void safeWaitTillTextExists(String string, Integer timeinSeconds) {
        try {
            waitTillTextExist(string,timeinSeconds);
        } catch (TimeoutException e) {
            System.out.println("Text safe checked and does not exist :" +string );
        }
    }

    @Deprecated
    public void waitTillTextNotExist(String string, Integer timeinSeconds) {
        final Boolean[] isWaitSuccessfull = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
//        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
//        wait.withTimeout(timeinSeconds, TimeUnit.SECONDS);


        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {
                String pageSource = driver.getPageSource();
                if(!pageSource.contains(string)) {
                    isWaitSuccessfull[0] = true;
                    return true;

                }
                return false;
            }
        };
        wait.until(function);
        System.out.println("waitTillTextExist method " + string + " STATUS is: " + isWaitSuccessfull[0]+cmnFunction.getCurrentDate());

    }

    @Deprecated
    public void waitTillClickableElementExist(String string) {
        final Boolean[] isElementFound = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
//        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
//        wait.withTimeout(2, TimeUnit.MINUTES);
        wait.ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {

                if(isClickableElementExisting(string)) {
                    isElementFound[0] = true;
                    return true;

                }
                return false;
            }
        };
        wait.until(function);
        System.out.println("waitTillClickableElementExist method "+ string + " STATUS is: "+ isElementFound[0]);

    }
    @Deprecated
    public void waitTillClickableElementExist(WebElement element) {
        final Boolean[] isElementFound = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
//        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
//        wait.withTimeout(2, TimeUnit.MINUTES);
        wait.ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {

                if(isClickableElementExisting(element)) {
                    isElementFound[0] = true;
                    return true;

                }
                return false;
            }
        };
        wait.until(function);
        System.out.println("waitTillTextExist method "+ element + " STATUS is: "+ isElementFound[0]);

    }
    @Deprecated
    public void waitTillVisibleElementExist(String string) {
        final Boolean[] isElementFound = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
//        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
//        wait.withTimeout(2, TimeUnit.MINUTES);
        wait.ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {

                if(isVisibleElementExisting(string)) {
                    isElementFound[0] = true;
                    return true;

                }
                return false;
            }
        };
        wait.until(function);
        System.out.println("waitTillClickableElementExist method "+ string + " STATUS is: "+ isElementFound[0]);

    }
    @Deprecated
    public void waitTillVisibleElementExist(WebElement element) {
        final Boolean[] isElementFound = new Boolean[1];
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
//        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
//        wait.withTimeout(2, TimeUnit.MINUTES);
        wait.ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

            public Boolean apply(WebDriver arg) {

                if(isClickableElementExisting(element)) {
                    isElementFound[0] = true;
                    return true;

                }
                return false;
            }
        };
        wait.until(function);
        System.out.println("waitTillTextExist method "+ element + " STATUS is: "+ isElementFound[0]);

    }

    public void printFrames() {
        List<WebElement> elements = driver.findElements(By.tagName("iframe"));
        System.out.println("Count of frames now is "+elements.size());
        for(int i=0;i<elements.size();i++) {
            System.out.println(elements.get(i));
            System.out.println(elements.get(i).getAttribute("title"));
        }
    }

    public void switchFrameByElementName(WebElement element) {
        try {
            waitforElementVisible(element);
            driver.switchTo().frame(element);
        } catch(Exception e) {
            System.out.println("Unable to Switch- may be check eleemnt existence");
            throw e;

        }
    }

    public void switchFrameByElementName(String element) {
        try {
            waitforElementVisible(getElement(element));
            driver.switchTo().frame(getElement(element));
        } catch(Exception e) {
            System.out.println("Unable to Switch- may be check eleemnt existence");
            throw e;

        }
    }

    public void switchToDefaultFrame() {
        try {
            driver.switchTo().defaultContent();
        } catch(Exception e) {
            System.out.println("Unable to Defauklt Content- check!");
            throw e;

        }
    }

    public Integer getElementCountFromList(String StringLocator) {
        List<WebElement> list = getElementList(StringLocator);
        return list.size();
    }

    public String getAttributeValue(String StringLocator, String attribute) {
        return getElement(StringLocator).getAttribute(attribute);

    }

    public boolean switchWindow(String title) {

        String currentWindow = driver.getWindowHandle();
        Set<String> availableWindows = driver.getWindowHandles();
        if (!availableWindows.isEmpty()) {
            for (String windowId : availableWindows) {
                if (driver.switchTo().window(windowId).getTitle().equals(title)) {
                    return true;
                } else {
                    driver.switchTo().window(currentWindow);
                    driver.switchTo().defaultContent();
                }
            }
        }
        return false;
    }

    public void switchToWindow(String windowHandle) {

        driver.switchTo().window(windowHandle);
    }

    public void CapsLockSwitchOn()
    {
        boolean isCapsLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(
                KeyEvent.VK_CAPS_LOCK);

        System.out.print("isCapsLockOn>>>>" + isCapsLockOn);
        if (isCapsLockOn)
        {
            Toolkit.getDefaultToolkit().setLockingKeyState(
                    KeyEvent.VK_CAPS_LOCK, true);
            isCapsLockOn = true;
            System.out.print("isCapsLockOn>>> true \n");
        }
    }

    public void CapsLockSwitchOff()
    {
        boolean isCapsLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(
                KeyEvent.VK_CAPS_LOCK);

        System.out.print("isCapsLockOn>>>>" + isCapsLockOn);
        if (isCapsLockOn)
        {
            Toolkit.getDefaultToolkit().setLockingKeyState(
                    KeyEvent.VK_CAPS_LOCK, false);
            isCapsLockOn = false;
            System.out.print("isCapsLockOn>>> false \n");
        }
    }



    public void hover(WebElement element) {
        sleepms(200);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click().perform();
        sleepms(500);
    }

    public void clickAndHold(WebElement element) {
        sleepms(200);
        Actions actions = new Actions(driver);
        actions.clickAndHold(element);
        actions.click().perform();
        sleepms(500);
    }

    public void rightClick(WebElement element) {
        waitforElementVisible(element);
        Actions actions = new Actions(driver);
        actions.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
    }

    public void clickPositionXinElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ element.getLocation().x + ")");
        element.click();
        sleepms(200);
    }
    public void sleepms(long milliseconds) {
        System.out.println("sleep(" + milliseconds + ")");
        long end_time = System.currentTimeMillis() + milliseconds;
        while (System.currentTimeMillis() < end_time) {}
    }


    public void clickUsingJavascript(WebElement element) {
        scrollViewUsingJavascript(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",element);
    }

    public void typeUsingJavascript(WebElement element, String text){
        scrollViewUsingJavascript(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + text + "'", element);
    }

    public void typeUsingActions(WebElement element, String text){
        Actions builder = new Actions(driver);
        builder.click(element).sendKeys(text).perform();

    }



    public void scrollViewUsingJavascript(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }catch(NoSuchElementException e){
            throw e;
        }
    }

    public void scrollDownUsingJavascript() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }catch(NoSuchElementException e){
            throw e;
        }
    }
    public void scrollTopUsingJavascript() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        }catch(NoSuchElementException e){
            throw e;
        }
    }
    public void scrolltoWebelement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
        }catch(NoSuchElementException e){
            throw e;
        }
    }


    public void acceptAlertIfExists() throws InterruptedException {
        try {
            Thread.sleep(5000); // waiting enough for random Alert to appear, if it was to be.
            Alert alert=driver.switchTo().alert();
            //System.out.println("2. switched to alert and number rof window handles are"+driver.getWindowHandles());
            System.out.println(alert.getText());
            alert.accept();
            System.out.println("3. alert accepted and number rof window handles are"+driver.getWindowHandles());
//            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert is present");
        }
    }

    public void setParentWindow(String window) {
        cmnVrbl.parentWindowHandle=window;
    }

    public void setChild1Window(String window) {
        cmnVrbl.child1WindowHandle=window;
    }

    public void setWindow(String windowType, String window) {
        String windorTypeUpperCase =windowType.toUpperCase();
        switch(windowType) {
            case "PARENT":
                setParentWindow(window);
                break;
            case "CHILD1" :
                setChild1Window(window);

        }
    }

    public String getNewWindowOutOfSet() {
        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println("number of window handles are"+windowHandles);
        windowHandles.remove(cmnVrbl.parentWindowHandle);
        windowHandles.remove(cmnVrbl.child1WindowHandle);
        windowHandles.remove(cmnVrbl.child2WindowHandle);
        return windowHandles.iterator().next();
    }

    public void closeDriver() {
        ///hooks.driver = driver;
       // driver.close();
        //driver.quit();
    }

    public void donotacceptAlertIfExists() throws InterruptedException {
        try {
            Thread.sleep(5000); // waiting enough for random Alert to appear, if it was to be.
            Alert alert=driver.switchTo().alert();
            //System.out.println("2. switched to alert and number rof window handles are"+driver.getWindowHandles());
            System.out.println(alert.getText());
            alert.dismiss();
            System.out.println("3. alert accepted and number rof window handles are"+driver.getWindowHandles());
//            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert is present");
        }
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public void loadGivenURL(String url) {
        //String pageUrl = "https://google.co.uk";
        driver.navigate().to(url);
    }

    public void waitForNumberOfWindowsToEqual(final int numberOfWindows) {
        System.out.println(driver.getWindowHandles().size());
        new WebDriverWait(driver, 30) {
        }.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (driver.getWindowHandles().size() == numberOfWindows);
            }
        });
    }

    public void switchToPopUp() {
        final String mainWindowHandle = driver.getWindowHandle();
        for (String activeHandle : driver.getWindowHandles()) {
            if (!activeHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(activeHandle);
            } else {
                System.out.println("No Pop up found");
            }
        }
    }

    public void switchPopUp(int winIndex) {
        waitForNumberOfWindowsToEqual(winIndex);
        switchToPopUp();
    }
    public void refreshURL() {
        driver.navigate().refresh();
    }

    public boolean waitforElementStaleness(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.stalenessOf(webElement));
            return true;
        } catch (Exception t) {
            throw t;
        }
    }

    public void enterTextStaleElement(By element, String string) {
        WebElement webElement = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                try {
                    return d.findElement(element);
                } catch (StaleElementReferenceException ex) {
                    return null;
                }
            }
        });
        webElement.sendKeys(string);
    }

    public void enterStaleElement(By element) {
        WebElement webElement = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                try {
                    return d.findElement(element);
                } catch (StaleElementReferenceException ex) {
                    return null;
                }
            }
        });
        webElement.click();
    }
    public String getTextFromStaleElement(By element) {
        WebElement webElement = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                try {
                    return d.findElement(element);
                } catch (StaleElementReferenceException ex) {
                    return null;
                }
            }
        });
        return webElement.getText();
    }

    public boolean isStaleElementContainsGivenValue(By by , String string) {
        String txt = getTextFromStaleElement(by);
        System.out.println(txt);
        return txt.toLowerCase().contains(string.toLowerCase());
    }

    public void navigateBack()
    {
        driver.navigate().back();
    }

    public void selectByIndex(WebElement element, int index) {
        waitforElementVisible(element);
        Select oSelect = new Select(element);
        oSelect.selectByIndex(index);
    }

    public void enterByKeyboard()
    {
        sleepms(200);
        Actions act = new Actions(driver);
        act.sendKeys(Keys.ENTER).perform();
    }

    public void deleteAllBrowserCookies()
    {
        driver.manage().deleteAllCookies();
    }

    public boolean isElementDisplayed(String locator)
    {
        try
        {
            WebElement element = getElement(locator);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        }
        catch(Exception e)
        {
            System.out.println("Exception: "+e);
            return false;

        }
    }

    public boolean isElementNotDisplayed(String locator)
    {
        try
        {
            WebElement element = getElement(locator);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Exception: "+e);
            return false;

        }
    }

    public void verifyLinksInPage()
    {
        HttpURLConnection huc = null;
        int respCode = 200;
        String url = "";
        List<WebElement> links = driver.findElements(By.tagName("a"));

        Iterator<WebElement> it = links.iterator();

        while(it.hasNext()){

            url = it.next().getAttribute("href");

            System.out.println(url);

            if(url == null || url.isEmpty()){
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }

            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                respCode = huc.getResponseCode();

                if(respCode >= 400){
                    System.out.println(url+" is a broken link");
                    Assert.assertTrue("Broken link fount "+url,false);
                }
                else{
                    System.out.println(url+" is a valid link");
                }

            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public void click(String elementLocator) {
        try {
            WebElement element =getElement(elementLocator);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            waitTillClickableElementExist(elementLocator);
            element.click();
        } catch (Exception t) {
            System.out.println("unable to click on the element using enter(String elementLocator): "+elementLocator);
            throw t;
        }
    }

    public void assertStringEquals(String expVal, String actVal){
        Assert.assertEquals(expVal, actVal);
    }

    public void Keys_Arrow_Down(String str)  {
        String strLocatorType= (str.split(":",2)[0]).toLowerCase();
        String strLocatorValue= (str.split(":",2)[1]);

        switch(strLocatorType) {

            case "id":
                driver.findElement(By.id(strLocatorValue)).sendKeys(Keys.ARROW_DOWN);
                break;
            case "name":
                driver.findElement(By.name(strLocatorValue)).sendKeys(Keys.ARROW_DOWN);
                break;
            case "partiallink":
                driver.findElement(By.partialLinkText(strLocatorValue)).sendKeys(Keys.ARROW_DOWN);
                break;
            case "linktext":
                driver.findElement(By.linkText(strLocatorValue)).sendKeys(Keys.ARROW_DOWN);
                break;
            case "css":
                driver.findElement(By.cssSelector(strLocatorValue)).sendKeys(Keys.ARROW_DOWN);
                break;
            case "tagname":
                driver.findElement(By.tagName(strLocatorValue)).sendKeys(Keys.ARROW_DOWN);
                break;
            case "xpath":
                driver.findElement(By.xpath(strLocatorValue)).sendKeys(Keys.ARROW_DOWN);
                break;
            default :
                throw new NoSuchElementException("unknown locator Type");

        }
    }

    public void Keys_Enter(String str)  {
        String strLocatorType= (str.split(":",2)[0]).toLowerCase();
        String strLocatorValue= (str.split(":",2)[1]);

        switch(strLocatorType) {

            case "id":
                driver.findElement(By.id(strLocatorValue)).sendKeys(Keys.ENTER);
                break;
            case "name":
                driver.findElement(By.name(strLocatorValue)).sendKeys(Keys.ENTER);
                break;
            case "partiallink":
                driver.findElement(By.partialLinkText(strLocatorValue)).sendKeys(Keys.ENTER);
                break;
            case "linktext":
                driver.findElement(By.linkText(strLocatorValue)).sendKeys(Keys.ENTER);
                break;
            case "css":
                driver.findElement(By.cssSelector(strLocatorValue)).sendKeys(Keys.ENTER);
                break;
            case "tagname":
                driver.findElement(By.tagName(strLocatorValue)).sendKeys(Keys.ENTER);
                break;
            case "xpath":
                driver.findElement(By.xpath(strLocatorValue)).sendKeys(Keys.ENTER);
                break;
            default :
                throw new NoSuchElementException("unknown locator Type");

        }
    }
    public String getCurrentTime(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()).toString();
    }

    public void enterByKeyboardPageDown()
    {
        sleepms(200);
        Actions act = new Actions(driver);
        act.sendKeys(Keys.PAGE_DOWN).perform();
    }

}
