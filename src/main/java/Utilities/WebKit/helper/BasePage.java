//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.helper;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import Utilities.WebKit.annotations.Page;
import Utilities.WebKit.configuration.Configuration;
import Utilities.WebKit.exceptions.StopTestException;
import Utilities.WebKit.exceptions.TestDataError;
import Utilities.WebKit.exceptions.UnexpectedPageException;
import Utilities.seleniumcustomframework.extension.PageElement;
import Utilities.seleniumcustomframework.extension.PageFactory;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private static final Logger LOG = Logger.getLogger(BasePage.class.getName());
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT = 60000;
    private static final int DEFAULT_WAIT_TIMEOUT_SECS = 20;
    private static final long WAIT_FOR_PAGE_LOADED_DELAY = 20000L;
    private static final long DRIVER_WAIT_TIME = 60L;
    public static String absPath = (new File("")).getAbsolutePath().toString();
    @Inject
    public WebDriver driver;
    @Inject
    public PageFactory pageFactory;
    @Inject
    @Named("browser")
    protected String browser;
    @Inject
    @Named("environment")
    protected String environment;
    String parentWindow = null;
    @Inject
    private Injector injector;

    public BasePage() {
    }

    public boolean isDisplayed() {
        try {
            return false;
        } catch (StaleElementReferenceException var2) {
            return false;
        }
    }

    public void load() {
        String pageUrl = "https://google.co.uk";
        this.driver.navigate().to(pageUrl);
    }

    public void submit() throws TimeoutException {
        throw new UnexpectedPageException("%s page has not been displayed%n");
    }

    public void continueIfExists() {
        try {
            this.waitFor(5000);
            this.submit();
        } catch (TimeoutException var2) {
            System.out.printf("%s page has not been displayed%n", this);
        }

    }

    public void assertPage() {
        String expectedPage = this.getPageIdFromAnnotation();
        String actualPage = this.getPageId();
        if (!expectedPage.equals(actualPage)) {
            throw new UnexpectedPageException(String.format("Unexpected page displayed: \n\tExpected: %s\n\tbut got:  %s", expectedPage, actualPage));
        }
    }

    public String getPageId() {
        return null;
    }

    private List<Page> getPageAnnotations(Class klass) {
        List<Page> annotations = new ArrayList();
        Page pageAnnotation = (Page)klass.getAnnotation(Page.class);
        if (null != pageAnnotation) {
            annotations.add(pageAnnotation);
        }

        if (null != klass.getSuperclass()) {
            annotations.addAll(this.getPageAnnotations(klass.getSuperclass()));
        }

        return annotations;
    }

    private String getPageIdFromAnnotation() {
        List<Page> pageAnnotations = this.getPageAnnotations(this.getClass());
        Iterator var2 = pageAnnotations.iterator();

        Page pageAnnotation;
        do {
            if (!var2.hasNext()) {
                return "";
            }

            pageAnnotation = (Page)var2.next();
        } while(pageAnnotation.id().isEmpty());

        return pageAnnotation.id();
    }

    public void waitFor(Integer timeout) throws TimeoutException {
        TimeoutHelper.withTimeout(timeout, new Runnable() {
            public void run() {
                while(!BasePage.this.getPageIdFromAnnotation().equals(BasePage.this.getPageId())) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException var2) {
                        BasePage.LOG.fine(var2.getMessage());
                    }
                }

            }
        });
    }

    public void waitFor() throws TimeoutException {
        this.waitFor(60000);
    }

    protected String getRelativePath() {
        Page pageAnnotation = (Page)this.getClass().getAnnotation(Page.class);
        return pageAnnotation == null ? "" : pageAnnotation.path();
    }

    public void continueNext() throws TimeoutException {
        String.format("Need to implement in child class: %s", this.getClass().getName());
    }

    public void waitForPageToBecomeStable(PageElement element) throws TimeoutException {
        element.waitUntilVisible();
        boolean isStable = false;
        int count = 0;
        Point point = element.getLocation();

        while(!isStable) {
            if (point.getX() == element.getLocation().getX() && point.getY() == element.getLocation().getY()) {
                ++count;
            } else {
                count = 0;
                point = element.getLocation();
            }

            if (count == 10) {
                isStable = true;
            }

            try {
                Thread.sleep(100L);
            } catch (InterruptedException var6) {
                LOG.fine(var6.getMessage());
            }
        }

    }

    protected String getPageTitleFromAnnotation() {
        List<Page> pageAnnotations = this.getPageAnnotations(this.getClass());
        Iterator var2 = pageAnnotations.iterator();

        Page pageAnnotation;
        do {
            if (!var2.hasNext()) {
                return "";
            }

            pageAnnotation = (Page)var2.next();
        } while(pageAnnotation.title().isEmpty());

        return pageAnnotation.title();
    }

    public PageIdHelper getPageIdHelperDelegate() {
        List<Page> pageAnnotations = this.getPageAnnotations(this.getClass());
        Iterator var2 = pageAnnotations.iterator();

        Page pageAnnotation;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            pageAnnotation = (Page)var2.next();
        } while(pageAnnotation.pageIdDelegate().equals(NullPageIdHelper.class));

        return (PageIdHelper)this.injector.getInstance(pageAnnotation.pageIdDelegate());
    }

    public void waitForPageLoaded() {
        DateTime start = new DateTime();
        WebDriverWait wait = new WebDriverWait(this.driver, 20L);

        try {
            LOG.info("waitForPageLoaded on thread: " + Thread.currentThread().toString());
            wait.until((driver) -> {
                return (new DateTime()).isAfter(start.plus(20000L)) && ((JavascriptExecutor)driver).executeScript("return document.readyState", new Object[0]).equals("complete");
            });
        } catch (Throwable var4) {
            LOG.fine("waitForPageLoaded timeout on thread: " + Thread.currentThread().toString());
            LOG.fine("Timeout waiting for Page Load Request to complete." + var4.getMessage());
        }

    }

    public void waitForDropdownToBePopulated(Select select) {
        (new WebDriverWait(this.driver, 20L)).until((wd) -> {
            return !select.getOptions().isEmpty();
        });
    }

    public boolean checkSelectOptionsNotEmpty(WebElement webElement) {
        return (new Select(webElement)).getOptions().isEmpty();
    }

    public void waitForElementToBecomeEnabled(PageElement element, int timesInMinute) {
        (new WebDriverWait(this.driver, (long)(timesInMinute * 60))).until(ExpectedConditions.stalenessOf(element));
    }

    public void waitForElementToBecomeClickable(PageElement element, int timesInSeconds) {
        (new WebDriverWait(this.driver, (long)timesInSeconds)).until(ExpectedConditions.elementToBeClickable(element));
    }

    private Wait<WebDriver> fluentWait() {
        return (new FluentWait(this.driver)).withTimeout(10L, TimeUnit.SECONDS).pollingEvery(15L, TimeUnit.SECONDS).ignoring(WebDriverException.class);
    }

    public WebElement waitForElementByPolling(By by) {
        try {
            return (WebElement)this.fluentWait().until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public List<WebElement> waitForElementsByPolling(By by) {
        try {
            return (List)this.fluentWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public WebElement waitForElementByPolling(By by, String message) {
        try {
            return (WebElement)this.fluentWait().until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception var4) {
            throw new AssertionError(message);
        }
    }

    public WebElement waitForElementByPolling(WebElement webElement) {
        try {
            return (WebElement)this.fluentWait().until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public WebElement waitForElementByPolling(WebElement webElement, String message) {
        try {
            return (WebElement)this.fluentWait().until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception var4) {
            throw new AssertionError("Webdriver failed....." + var4.getLocalizedMessage());
        }
    }

    public boolean waitForElementByInvisibility(By by) {
        try {
            return (Boolean)this.fluentWait().until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public boolean waitForElementByInvisibility(By by, String message) {
        try {
            return (Boolean)this.fluentWait().until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception var4) {
            throw new AssertionError("Webdriver failed....." + var4.getLocalizedMessage());
        }
    }

    public boolean navigateToiFrameBySeconds(Long seconds, String iframeName) {
        try {
            this.fluentWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeName));
            return true;
        } catch (org.openqa.selenium.TimeoutException var4) {
            throw new AssertionError("iFrame Not found");
        }
    }

    public boolean navigateToiFrameBySeconds(By locator) {
        try {
            this.fluentWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
            return true;
        } catch (org.openqa.selenium.TimeoutException var3) {
            throw new AssertionError("iFrame Not found");
        }
    }

    public boolean navigateToiFrame(WebElement element) {
        try {
            this.fluentWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
            return true;
        } catch (org.openqa.selenium.TimeoutException var3) {
            this.closeBrowseDriver();
            throw new AssertionError("iFrame Not found");
        }
    }

    public boolean elementToBeSelected(By by) {
        try {
            return (Boolean)this.fluentWait().until(ExpectedConditions.elementToBeSelected(by));
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public WebElement presenceOfElementLocated(By by) {
        try {
            return (WebElement)this.fluentWait().until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public boolean elementToBeSelected(WebElement element) {
        try {
            return (Boolean)this.fluentWait().until(ExpectedConditions.elementToBeSelected(element));
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public boolean waitForElementToBecomeEnabled(WebElement element) {
        try {
            return (Boolean)this.fluentWait().until(ExpectedConditions.stalenessOf(element));
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public WebElement waitForElementToBecomeClickable(WebElement element) {
        try {
            return (WebElement)this.fluentWait().until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public void closePopUpWindow() {
        this.driver.close();
    }

    public void implicitWait(Long l) {
        this.currentBrowser().manage().timeouts().implicitlyWait(l, TimeUnit.SECONDS);
    }

    public boolean pageLoadCondition() {
        WebDriverWait wd = new WebDriverWait(this.driver, 20L);
        ExpectedCondition<Boolean> jspageLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState", new Object[0]).toString().equals("complete");
            }
        };
        ExpectedCondition var10000 = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Long)((JavascriptExecutor)driver).executeScript("return jQuery.active", new Object[0]) == 0L;
            }
        };
        return (Boolean)wd.until(jspageLoad);
    }

    public boolean isElementPresent(By by) {
        try {
            boolean isVisible = false;
            this.sleepms(500L);
            if (isVisible = this.driver.findElements(by).size() > 0) {
                Dimension d = this.driver.findElement(by).getSize();
                if (isVisible = d.getHeight() > 0 && d.getWidth() > 0) {
                    String elementStyle = this.driver.findElement(by).getAttribute("style");
                    isVisible = !elementStyle.equals("display: none;") && !elementStyle.equals("visibility: hidden;");
                }
            }

            return isVisible;
        } catch (Exception var5) {
            throw new AssertionError("Webdriver failed....." + var5.getLocalizedMessage());
        }
    }

    public boolean isAttribtuePresent(WebElement element, String attribute) {
        Boolean result = false;

        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception var5) {
        }

        return result;
    }

    public WebElement getElementFromList(List<WebElement> elements, String options) {
        Iterator var3 = elements.iterator();

        WebElement element;
        do {
            if (!var3.hasNext()) {
                throw new AssertionError("\noptions'" + options + "' does not match\n");
            }

            element = (WebElement)var3.next();
        } while(!element.getText().equalsIgnoreCase(options));

        return element;
    }

    public String getMapValue(String key, HashMap<String, String> items) {
        Iterator var3 = items.entrySet().iterator();

        Entry item;
        do {
            if (!var3.hasNext()) {
                throw new TestDataError("No key value was found for '" + key + "'");
            }

            item = (Entry)var3.next();
        } while(!((String)item.getKey()).equalsIgnoreCase(key));

        System.out.println("\n" + (String)item.getValue() + " is returned :-" + (String)item.getKey() + "\n");
        return (String)item.getValue();
    }

    public List<Integer> generateUniqueRandom(int start, int questions) {
        List<Integer> ramdomList = new ArrayList();

        for(int i = 0; i < questions; ++i) {
            int j = RandomUtils.nextInt(start, questions);
            ramdomList.add(j);
        }

        System.out.println("\nUniqueList..." + ramdomList.stream().distinct().collect(Collectors.toList()));
        return (List)ramdomList.stream().distinct().collect(Collectors.toList());
    }

    public String getPageTitle() {
        try {
            return this.driver.getTitle();
        } catch (Exception var2) {
            throw new AssertionError("Webdriver failed....." + var2.getLocalizedMessage());
        }
    }

    public WebDriver currentBrowser() {
        try {
            return this.driver;
        } catch (Exception var2) {
            throw new AssertionError("Webdriver failed....." + var2.getLocalizedMessage());
        }
    }

    public void refreshBrowser() {
        try {
            this.currentBrowser().navigate().refresh();
        } catch (Exception var2) {
            throw new AssertionError("Webdriver failed....." + var2.getLocalizedMessage());
        }
    }

    public void closeBrowseDriver() {
        try {
            this.driver.quit();
        } catch (Exception var2) {
            throw new AssertionError("Webdriver failed....." + var2.getLocalizedMessage());
        }
    }

    public void sleepms(long milliseconds) {
        System.out.println("sleep(" + milliseconds + ")");
        long end_time = System.currentTimeMillis() + milliseconds;

        while(System.currentTimeMillis() < end_time) {
        }

    }

    public void hover(WebElement element) {
        try {
            Actions action = new Actions(this.driver);
            action.moveToElement(element).build().perform();
            this.sleepms(300L);
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public JavascriptExecutor getWebDriverJSExecutor() {
        try {
            return (JavascriptExecutor)this.currentBrowser();
        } catch (Exception var2) {
            throw new AssertionError("Webdriver failed....." + var2.getLocalizedMessage());
        }
    }

    public void scrollToElementWithJavascript(WebElement element) {
        try {
            this.getWebDriverJSExecutor().executeScript("arguments[0].scrollIntoView();", new Object[]{element});
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public void clickCssElement(String cssLocator) {
        try {
            this.getWebDriverJSExecutor().executeScript("document.querySelector('" + cssLocator + "').click()", new Object[0]);
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public String getElementByQueryJSExecutor(String cssSelector) {
        try {
            return this.getWebDriverJSExecutor().executeScript("return window.getComputedStyle(document.querySelector('" + cssSelector + "'))", new Object[0]).toString();
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public void clickPositionXinElement(WebElement element) {
        try {
            this.getWebDriverJSExecutor().executeScript("window.scrollTo(0," + element.getLocation().x + ")", new Object[0]);
            element.click();
            this.sleepms(200L);
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public void clickUsingJavascript(WebElement element) {
        try {
            this.getWebDriverJSExecutor().executeScript("arguments[0].click()", new Object[]{element});
            this.sleepms(200L);
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public void typeUsingJavascript(WebElement element, String text) {
        try {
            this.getWebDriverJSExecutor().executeScript("arguments[0].value = '" + text + "'", new Object[]{element});
        } catch (Exception var4) {
            throw new AssertionError("Webdriver failed....." + var4.getLocalizedMessage());
        }
    }

    public void scrollViewUsingJavascript(WebElement element) {
        try {
            this.getWebDriverJSExecutor().executeScript("arguments[0].scrollIntoView(true);", new Object[]{element});
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public void scrollDown() {
        try {
            this.getWebDriverJSExecutor().executeScript("window.scrollBy(0, document.body.scrollHeight || document.documentElement.scrollHeight)", new Object[]{""});
            this.sleepms(100L);
        } catch (Exception var2) {
            throw new AssertionError("Webdriver failed....." + var2.getLocalizedMessage());
        }
    }

    public void scrollUp() {
        try {
            this.getWebDriverJSExecutor().executeScript("window.scrollBy(0, -document.body.scrollHeight || -document.documentElement.scrollHeight)", new Object[]{""});
            this.sleepms(100L);
        } catch (Exception var2) {
            throw new AssertionError("Webdriver failed....." + var2.getLocalizedMessage());
        }
    }

    public List<String> getListValues(WebElement element) {
        try {
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            List<String> optionVales = new ArrayList();
            options.forEach((option) -> {
                optionVales.add(option.getText());
            });
            return optionVales;
        } catch (Exception var5) {
            throw new AssertionError("Webdriver failed....." + var5.getLocalizedMessage());
        }
    }

    public void updateElement(By locator, String value) {
        try {
            this.waitForElementByPolling(locator);
            this.driver.findElement(locator).clear();
            this.driver.findElement(locator).sendKeys(new CharSequence[]{value});
        } catch (Exception var4) {
            throw new AssertionError("Webdriver failed....." + var4.getLocalizedMessage());
        }
    }

    public void selectItem(WebElement element, String text) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
        } catch (Exception var4) {
            throw new AssertionError("Webdriver failed....." + var4.getLocalizedMessage());
        }
    }

    public <T extends WebElement> String getSelectedValue(T element) {
        try {
            Select select = new Select(element);
            return select.getFirstSelectedOption().getText();
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public List<String> getSelectedDropdownItems(By locator) {
        try {
            List<String> selectedValues = new ArrayList();
            List<WebElement> elements = this.driver.findElements(locator);
            elements.forEach((element) -> {
                selectedValues.add(this.getSelectedValue(element));
            });
            return selectedValues;
        } catch (Exception var4) {
            throw new AssertionError("Webdriver failed....." + var4.getLocalizedMessage());
        }
    }

    public void selectDropDownByIndex(int id, By by) {
        boolean found = false;

        try {
            Select dropdown = new Select(this.waitForElementByPolling(by));
            dropdown.selectByIndex(id);
            found = true;
        } catch (ElementNotVisibleException var5) {
        }

        if (!found) {
            System.out.println("Item not listed:" + id);
        }

    }

    public void selectDropDownByText(String text, By by) {
        boolean found = false;

        try {
            Select dropdown = new Select(this.waitForElementByPolling(by));
            dropdown.selectByVisibleText(text);
            found = true;
        } catch (ElementNotVisibleException var5) {
        }

        if (!found) {
            System.out.println("Item not listed:" + text);
        }

    }

    private String timeStamp() {
        SimpleDateFormat newdate = new SimpleDateFormat("ddMMyyyy HH-mm-ss");
        return newdate.format(new Date());
    }

    public void savePdf(String pdfUrl) {
        String separator = System.getProperty("file.separator");
        String pdfLocation = absPath + separator + "src" + separator + "test" + separator + "resources" + separator + "pdffolder" + separator;

        try {
            URL url = new URL(pdfUrl);
            FileUtils.copyURLToFile(url, new File(pdfLocation + "santander" + this.timeStamp() + ".pdf"));
        } catch (MalformedURLException var5) {
            var5.printStackTrace();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public void takeScreenPrint(String pageTitle) {
        try {
            WebDriver augmentedDriver = (new Augmenter()).augment(this.driver);
            File screenshot = (File)((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
            String separator = System.getProperty("file.separator");
            String environment = Configuration.getConfiguration().getEnvironment();
            String project = Configuration.getConfiguration().getProjectName();
            String screenshotLocation = "";
            if (EnvironmentConstants.productType.isEmpty() || EnvironmentConstants.tagId.isEmpty()) {
                screenshotLocation = absPath + separator + "src" + separator + "test" + separator + "resources" + separator + project + separator + "profiles" + separator + environment + separator + "images" + separator + this.browser + separator + "santanderapps" + separator;
            }

            if (!EnvironmentConstants.productType.isEmpty()) {
                screenshotLocation = absPath + separator + "src" + separator + "test" + separator + "resources" + separator + project + separator + "profiles" + separator + environment + separator + "images" + separator + this.browser + separator + EnvironmentConstants.productType + separator;
            }

            if (!EnvironmentConstants.tagId.isEmpty()) {
                screenshotLocation = absPath + separator + "src" + separator + "test" + separator + "resources" + separator + project + separator + "profiles" + separator + environment + separator + "images" + separator + this.browser + separator + EnvironmentConstants.tagId + separator;
            }

            FileUtils.copyFile(screenshot, new File(screenshotLocation + pageTitle + this.timeStamp() + ".png"));
        } catch (StopTestException | IOException var8) {
            var8.printStackTrace();
        }

    }

    public void selectOptionByTextFromDropdown(WebElement element, String text) {
        element.click();
        List<WebElement> options = element.findElements(By.cssSelector("option"));
        Iterator var4 = options.iterator();

        WebElement option;
        do {
            if (!var4.hasNext()) {
                System.out.println("Item not listed: " + text);
                throw new TestDataError("The option '" + text + "'  is not present in the list");
            }

            option = (WebElement)var4.next();
        } while(!option.getText().trim().equalsIgnoreCase(text));

        option.click();
    }

    public void switchToPopUpWindow() {
        try {
            this.parentWindow = this.driver.getWindowHandle();
            int var1 = 20;

            while(var1-- > 0 && this.driver.getWindowHandles().size() <= 1) {
                this.sleepms(500L);
            }

            if (this.driver.getWindowHandles().size() == 1) {
                this.closeBrowseDriver();
                throw new AssertionError("\nChild Popup window is not detected by Selenium\n");
            } else {
                Set<String> handles = this.driver.getWindowHandles();
                Iterator var3 = handles.iterator();

                while(var3.hasNext()) {
                    String windowHandle = (String)var3.next();
                    if (!windowHandle.equals(this.parentWindow)) {
                        this.driver.switchTo().window(windowHandle);
                        System.out.print("\nDriver has switched to child window successfully \n");
                    }
                }

            }
        } catch (Exception var5) {
            this.closeBrowseDriver();
            throw new AssertionError("\nWebDriver failed...\n");
        }
    }

    public void switchBackToParentWindow() {
        if (this.parentWindow == null) {
            this.closeBrowseDriver();
            throw new AssertionError("\nYou are not on a child pop up window\n");
        } else {
            int var1 = 5;

            while(var1-- > 0) {
                this.driver.switchTo().window(this.parentWindow);
                this.sleepms(500L);
                if (this.driver.getWindowHandle().equalsIgnoreCase(this.parentWindow)) {
                    break;
                }
            }

        }
    }

    public void switchToMainWindow(String sessionId) {
        try {
            this.currentBrowser().switchTo().window(sessionId);
            this.currentBrowser().switchTo().defaultContent();
        } catch (Exception var3) {
            throw new AssertionError("Webdriver failed....." + var3.getLocalizedMessage());
        }
    }

    public void switchToDefaultWindow() {
        if (this.driver.getWindowHandles().size() >= 1) {
            this.driver.switchTo().window((String)this.driver.getWindowHandles().iterator().next());
        }

    }
}
