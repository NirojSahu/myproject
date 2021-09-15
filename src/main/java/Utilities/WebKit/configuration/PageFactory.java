//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.configuration;

import Utilities.WebKit.exceptions.*;
import Utilities.WebKit.helper.LoggingHelper;
import com.test.helper.ReportingHelper;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class PageFactory {
    private static final String SAFARI_BROWSER = "SAFARI";
    private static WebDriver driver = null;
    private static WebDriver remoteWebDriver = null;
    private static String sessionId = "";
    private static String jobID = "";

    public PageFactory() {
    }

    public static Object getPage(String pageClassName) throws StopTestException {
        String applicationMode = Configuration.getConfiguration().getApplicatonMode();
        if ("MOBILE".equals(applicationMode)) {
            pageClassName = pageClassName + "Mobile";
        }

        Object page = null;

        try {
            Class<?> pageClass = Class.forName(pageClassName);
            if (pageClass.isInterface()) {
                pageClassName = pageClassName + "Web";
                pageClass = Class.forName(pageClassName);
            }

            LoggingHelper.printDebug("Loading page: " + pageClassName);
            Constructor<?> constructor = pageClass.getConstructor(WebDriver.class);
            page = constructor.newInstance(getDriver());
            return page;
        } catch (Exception var5) {
            throw new StopTestException("could not get page : " + pageClassName);
        }
    }

    public static void switchToIframe(WebElement element) {
        getDriver().switchTo().frame(element);
    }

    public static WebDriver getDriver() {
        try {
            if (Configuration.getConfiguration().getGridRun().equalsIgnoreCase("true")) {
                remoteWebDriver.getTitle();
                return remoteWebDriver;
            } else {
                driver.getTitle();
                return driver;
            }
        } catch (Exception var1) {
            setUp();
            return driver;
        }
    }

    public static WebDriver getCurrentDriver() throws StopTestException {
        try {
            return Configuration.getConfiguration().getGridRun().equalsIgnoreCase("true") ? remoteWebDriver : driver;
        } catch (Exception var1) {
            throw new StopTestException("Unable to getCurrentDriver()");
        }
    }

    public static void tearDown() throws StopTestException {
        String debug = "false";

        try {
            debug = Configuration.getConfiguration().getDebugProperty();
        } catch (Exception var31) {
        }

        if (driver != null) {
            try {
                TemporaryFilesystem.getDefaultTmpFS().deleteTemporaryFiles();
            } catch (Exception var30) {
            }

            try {
                driver.manage().deleteAllCookies();
            } catch (Exception var29) {
            }

            try {
                driver.quit();
            } catch (Exception var27) {
                System.out.println("In tearDown() - Exception thrown when attempting driver.quit() " + var27.getMessage());
            } finally {
                driver = null;
            }
        }

        if (remoteWebDriver != null) {
            try {
                TemporaryFilesystem.getDefaultTmpFS().deleteTemporaryFiles();
            } catch (Exception var33) {
                if (debug.equalsIgnoreCase("true")) {
                    System.out.println("Exception thrown in tearDown(). Unable to delete temporary files. " + var33.getMessage());
                }
            }

            try {
                Set cookies;
                Iterator cookiesIter;
                if (debug.equalsIgnoreCase("true")) {
                    cookies = remoteWebDriver.manage().getCookies();
                    cookiesIter = cookies.iterator();
                    System.out.println("Before remoteWebDriver.manage().deleteAllCookies()");

                    while(cookiesIter.hasNext()) {
                        System.out.println(cookiesIter.next());
                    }
                }

                if (debug.equalsIgnoreCase("true")) {
                    remoteWebDriver.manage().deleteAllCookies();
                    cookies = remoteWebDriver.manage().getCookies();
                    cookiesIter = cookies.iterator();
                    System.out.println("After remoteWebDriver.manage().deleteAllCookies()");

                    while(cookiesIter.hasNext()) {
                        System.out.println(cookiesIter.next());
                    }
                }
            } catch (Exception var32) {
                if (debug.equalsIgnoreCase("true")) {
                    System.out.println("Exception thrown in tearDown(). Unable to delete cookies. " + var32.getMessage());
                }
            }

            try {
                String stopTest = "false";
                if (System.getProperty("autotest.stop_test") != null) {
                    stopTest = System.getProperty("autotest.stop_test");
                }

                if (debug.equalsIgnoreCase("true") && stopTest.equalsIgnoreCase("true")) {
                    ReportingHelper.takeGridScreenShot();
                    ReportingHelper.getPageSourceCode();
                }
            } catch (Exception var26) {
                System.out.println("Unable to perform grid screenshot/page source handling" + var26.getMessage());
            }

            try {
                remoteWebDriver.quit();
            } catch (Exception var24) {
                System.out.println("In tearDown() - Exception thrown when attempting remoteWebDriver.quit() " + var24.getMessage());
            } finally {
                Configuration.getConfiguration().endSession(jobID);
                remoteWebDriver = null;
            }
        }

    }

    public static void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("geo.enabled", false);
        options.addPreference("geo.prompt.testing", false);
        options.addPreference("geo.prompt.testing.allow", false);
        options.setAcceptInsecureCerts(true);
    }

    public static void deleteAllCookies() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }

    }

    public static void deleteNamedCookie(String cookieName) {
        if (driver != null) {
            driver.manage().deleteCookieNamed(cookieName);
        }

    }

    public static void closeBrowser() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception var1) {
            }
        }

    }

    public static void refocusDriver() {
        if (driver != null) {
            try {
                driver.switchTo().defaultContent();
            } catch (Exception var2) {
            }
        }

        if (remoteWebDriver != null) {
            try {
                remoteWebDriver.switchTo().defaultContent();
            } catch (Exception var1) {
            }
        }

    }

    public static void addCookie(String key, String value) throws StopTestException {
        WebDriver driver = getCurrentDriver();
        Cookie newCookie = new Cookie(key, value);
        driver.manage().addCookie(newCookie);
        System.out.println(key + "Cookie Added: " + driver.manage().getCookieNamed(key).toString());
    }

    public static void addCookie(String key, String value, String domain) throws StopTestException {
        WebDriver driver = getCurrentDriver();
        Cookie newCookie = new Cookie(key, value, domain, (String)null, (Date)null);
        driver.manage().addCookie(newCookie);
    }

    public static String readCookieValue(String cookieName) throws StopTestException {
        Cookie cookie = null;
        WebDriver driver = getCurrentDriver();
        cookie = driver.manage().getCookieNamed(cookieName);
        return cookie != null ? cookie.getValue() : null;
    }

    public static WebDriver getRemoteWebDriver(String browser) throws IOException, StopTestException, JSONException, URISyntaxException {
        if (remoteWebDriver != null) {
            return remoteWebDriver;
        } else {
            String hubServerDetails = "";
            String gridRun = "";
            new DesiredCapabilities(browser, "", Platform.ANY);
            gridRun = Configuration.getConfiguration().getGridRun();
            Integer dimensionWidth = null;
            Integer dimensionHeight = null;
            if (Configuration.getConfiguration().getDimensionWidth() != null && Configuration.getConfiguration().getDimensionHeight() != null) {
                dimensionWidth = Configuration.getConfiguration().getDimensionWidth();
                dimensionHeight = Configuration.getConfiguration().getDimensionHeight();
            }

            URL hubUrl = Configuration.getConfiguration().getGridHubServerDetails();
            if (browser != null && browser.equalsIgnoreCase("firefox")) {
                remoteWebDriver = new RemoteWebDriver(hubUrl, getFirefoxOptions());
            }

            if (browser != null && browser.equalsIgnoreCase("chrome")) {
                remoteWebDriver = new RemoteWebDriver(hubUrl, getChromeOptions());
            }

            if (browser != null && (browser.equalsIgnoreCase("internet explorer") || browser.equalsIgnoreCase("internet explorer") || browser.equalsIgnoreCase("IE"))) {
                remoteWebDriver = new RemoteWebDriver(hubUrl, getIEOptions());
            }

            if (browser != null && (browser.equalsIgnoreCase("MicrosoftEdge") || browser.equalsIgnoreCase("Edge"))) {
                remoteWebDriver = new RemoteWebDriver(hubUrl, getEdgeOptions());
            }

            if (browser != null && browser.equalsIgnoreCase("safari")) {
                remoteWebDriver = new RemoteWebDriver(hubUrl, getSafariOptions());
            }

            try {
                remoteWebDriver.manage().deleteAllCookies();
                if (gridRun.equalsIgnoreCase("true") && dimensionHeight != null && dimensionWidth != null) {
                    Dimension dim = new Dimension(dimensionWidth, dimensionHeight);
                    remoteWebDriver.manage().window().setSize(dim);
                }

                remoteWebDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
            } catch (Throwable var11) {
                System.out.printf("Connecting to selenium grid: %s%n", hubUrl);
                var11.printStackTrace();
            } finally {
                sessionId = ((RemoteWebDriver)remoteWebDriver).getSessionId().toString();
                jobID = Configuration.getConfiguration().recordSession(sessionId);
            }

            return remoteWebDriver;
        }
    }

    public static Capabilities getChromeOptions() throws StopTestException {
        ChromeOptions options = new ChromeOptions();
        List<String> chromeOptions = getChromeCapabilities();

        for(int i = 0; i < chromeOptions.size(); ++i) {
            options.addArguments(new String[]{(String)chromeOptions.get(i)});
        }

        options.setExperimentalOption("useAutomationExtension", false);
        options.setAcceptInsecureCerts(true);
        if (Configuration.getConfiguration().getBrowserVersion() != null || !Configuration.getConfiguration().getBrowserVersion().equals("")) {
            options.setCapability("version", Configuration.getConfiguration().getBrowserVersion());
        }

        return options;
    }

    private static List<String> getChromeCapabilities() throws StopTestException {
        List<String> ChromeDefaultOptions = new ArrayList();
        ChromeDefaultOptions.add("enable-automation");
        ChromeDefaultOptions.add("--disable-infobars");
        ChromeDefaultOptions.add("--disable-popup-blocking");
        List<String> ChromeCustomCapabilities = Configuration.getConfiguration().getChromeCustomCapabilities();
        if (ChromeCustomCapabilities != null) {
            for(int i = 0; i < ChromeCustomCapabilities.size(); ++i) {
                for(int j = 0; j < ChromeDefaultOptions.size(); ++j) {
                    if (ChromeDefaultOptions.contains(ChromeCustomCapabilities.get(i))) {
                        ChromeDefaultOptions.remove(ChromeCustomCapabilities.get(i));
                        ChromeDefaultOptions.add(ChromeCustomCapabilities.get(i));
                    } else {
                        ChromeDefaultOptions.add(ChromeCustomCapabilities.get(i));
                    }
                }
            }
        }

        return ChromeDefaultOptions;
    }

    public static Capabilities getEdgeOptions() throws StopTestException, JSONException {
        EdgeOptions options = new EdgeOptions();
        JSONArray EdgeCapabilities = getEdgeCapabilities();

        for(int i = 0; i < EdgeCapabilities.length(); ++i) {
            JSONObject json = EdgeCapabilities.getJSONObject(i);
            Iterator keys = json.keys();

            while(keys.hasNext()) {
                String key = (String)keys.next();
                options.setCapability(key, json.get(key));
            }
        }

        if (Configuration.getConfiguration().getBrowserVersion() != null || !Configuration.getConfiguration().getBrowserVersion().equals("")) {
            options.setCapability("version", Configuration.getConfiguration().getBrowserVersion());
        }

        return options;
    }

    public static Capabilities getIEOptions() throws StopTestException, JSONException {
        InternetExplorerOptions options = new InternetExplorerOptions();
        JSONArray IECapabilities = getIECapabilities();

        for(int i = 0; i < IECapabilities.length(); ++i) {
            JSONObject json = IECapabilities.getJSONObject(i);
            Iterator keys = json.keys();

            while(keys.hasNext()) {
                String key = (String)keys.next();
                options.setCapability(key, json.get(key));
            }
        }

        if (Configuration.getConfiguration().getBrowserVersion() != null || !Configuration.getConfiguration().getBrowserVersion().equals("")) {
            options.setCapability("version", Configuration.getConfiguration().getBrowserVersion());
        }

        return options;
    }

    public static SafariOptions getSafariOptions() throws StopTestException, JSONException {
        SafariOptions options = new SafariOptions();
        JSONArray SafariCapabilities = getSafariCapabilities();

        for(int i = 0; i < SafariCapabilities.length(); ++i) {
            JSONObject json = SafariCapabilities.getJSONObject(i);
            Iterator keys = json.keys();

            while(keys.hasNext()) {
                String key = (String)keys.next();
                options.setCapability(key, json.get(key));
            }
        }

        options.setUseTechnologyPreview(false);
        if (Configuration.getConfiguration().getBrowserVersion() != null || !Configuration.getConfiguration().getBrowserVersion().equals("")) {
            options.setCapability("version", Configuration.getConfiguration().getBrowserVersion());
        }

        return options;
    }

    public static JSONArray getSafariCapabilities() throws StopTestException, JSONException {
        JSONArray SafariDefaultCapabilities = new JSONArray();
        SafariDefaultCapabilities.put((new JSONObject()).put("CapabilityType.ACCEPT_SSL_CERTS", "true"));
        String safariCapab = Configuration.getConfiguration().getSafariCustomCapabilities();
        if (safariCapab != null) {
            JSONArray SafariCustomCapabilities = new JSONArray(safariCapab);

            for(int i = 0; i < SafariCustomCapabilities.length(); ++i) {
                for(int j = 0; j < SafariDefaultCapabilities.length(); ++j) {
                    JSONObject jsonCustom = SafariCustomCapabilities.getJSONObject(i);
                    JSONObject jsonDefault = SafariDefaultCapabilities.getJSONObject(j);
                    Iterator keysCustom = jsonCustom.keys();

                    while(keysCustom.hasNext()) {
                        String keyCustom = (String)keysCustom.next();
                        if (SafariDefaultCapabilities.toString().contains(keyCustom)) {
                            jsonDefault.put(keyCustom, jsonCustom.get(keyCustom));
                        } else {
                            jsonDefault.put(keyCustom, jsonCustom.get(keyCustom));
                        }
                    }
                }
            }
        }

        return SafariDefaultCapabilities;
    }

    public static FirefoxOptions getFirefoxOptions() throws StopTestException, JSONException {
        FirefoxOptions options = new FirefoxOptions();
        JSONArray FirefoxCapabilities = getFirefoxCapabilities();

        for(int i = 0; i < FirefoxCapabilities.length(); ++i) {
            JSONObject json = FirefoxCapabilities.getJSONObject(i);
            Iterator keys = json.keys();

            while(keys.hasNext()) {
                String key = (String)keys.next();
                options.setCapability(key, json.get(key));
            }
        }

        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        options.setAcceptInsecureCerts(true);
        options.setProfile(profile);
        if (Configuration.getConfiguration().getBrowserVersion() != null || !Configuration.getConfiguration().getBrowserVersion().equals("")) {
            options.setCapability("version", Configuration.getConfiguration().getBrowserVersion());
        }

        return options;
    }

    public static JSONArray getFirefoxCapabilities() throws StopTestException, JSONException {
        JSONArray FirefoxDefaultCapabilities = new JSONArray();
        FirefoxDefaultCapabilities.put((new JSONObject()).put("nete.custom.capwork.proxy.type", "5").put("network.http.phishy-userpass-length", "255"));
        String firefoxCap = Configuration.getConfiguration().getFirefoxCustomCapabilities();
        if (firefoxCap != null) {
            JSONArray FirefoxCustomCapabilities = new JSONArray(firefoxCap);

            for(int i = 0; i < FirefoxCustomCapabilities.length(); ++i) {
                for(int j = 0; j < FirefoxDefaultCapabilities.length(); ++j) {
                    JSONObject jsonCustom = FirefoxCustomCapabilities.getJSONObject(i);
                    JSONObject jsonDefault = FirefoxDefaultCapabilities.getJSONObject(j);
                    Iterator keysCustom = jsonCustom.keys();

                    while(keysCustom.hasNext()) {
                        String keyCustom = (String)keysCustom.next();
                        if (FirefoxDefaultCapabilities.toString().contains(keyCustom)) {
                            jsonDefault.put(keyCustom, jsonCustom.get(keyCustom));
                        } else {
                            jsonDefault.put(keyCustom, jsonCustom.get(keyCustom));
                        }
                    }
                }
            }
        }

        return FirefoxDefaultCapabilities;
    }

    public static JSONArray getEdgeCapabilities() throws JSONException, StopTestException {
        JSONArray EdgeDefaultCapabilities = new JSONArray();
        EdgeDefaultCapabilities.put((new JSONObject()).put("CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION", "true").put("CapabilityType.ACCEPT_SSL_CERTS", "true"));
        String edgeCustom = Configuration.getConfiguration().getEdgeCustomCapabilities();
        if (edgeCustom != null) {
            JSONArray EdgeCustomCapabilities = new JSONArray(edgeCustom);

            for(int i = 0; i < EdgeCustomCapabilities.length(); ++i) {
                for(int j = 0; j < EdgeDefaultCapabilities.length(); ++j) {
                    JSONObject jsonCustom = EdgeCustomCapabilities.getJSONObject(i);
                    JSONObject jsonDefault = EdgeDefaultCapabilities.getJSONObject(j);
                    Iterator keysCustom = jsonCustom.keys();

                    while(keysCustom.hasNext()) {
                        String keyCustom = (String)keysCustom.next();
                        if (EdgeDefaultCapabilities.toString().contains(keyCustom)) {
                            jsonDefault.put(keyCustom, jsonCustom.get(keyCustom));
                        } else {
                            jsonDefault.put(keyCustom, jsonCustom.get(keyCustom));
                        }
                    }
                }
            }
        }

        return EdgeDefaultCapabilities;
    }

    public static JSONArray getIECapabilities() throws JSONException, StopTestException {
        JSONArray IEDefaultCapabilities = new JSONArray();
        IEDefaultCapabilities.put((new JSONObject()).put("platform", "WINDOWS").put("InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS", "true").put("InternetExplorerDriver.REQUIRE_WINDOW_FOCUS", "true").put("ie.ensureCleanSession", "true").put("InternetExplorerDriver.IE_SWITCHES", "-private").put("ignoreZoomSetting", "true").put("nativeEvents", "true").put("ignoreProtectedModeSettings", "true").put("disable-popup-blocking", "true").put("enablePersistentHover", "true").put("InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION", "true"));
        String ieCapab = Configuration.getConfiguration().getIECustomCapabilities();
        if (ieCapab != null) {
            JSONArray IECustomCapabilities = new JSONArray(ieCapab);

            for(int i = 0; i < IECustomCapabilities.length(); ++i) {
                for(int j = 0; j < IEDefaultCapabilities.length(); ++j) {
                    JSONObject jsonCustom = IECustomCapabilities.getJSONObject(i);
                    JSONObject jsonDefault = IEDefaultCapabilities.getJSONObject(j);
                    Iterator keysCustom = jsonCustom.keys();

                    while(keysCustom.hasNext()) {
                        String keyCustom = (String)keysCustom.next();
                        if (IEDefaultCapabilities.toString().contains(keyCustom)) {
                            jsonDefault.put(keyCustom, jsonCustom.get(keyCustom));
                        } else {
                            jsonDefault.put(keyCustom, jsonCustom.get(keyCustom));
                        }
                    }
                }
            }
        }

        return IEDefaultCapabilities;
    }

    private static void printSessionId() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (remoteWebDriver instanceof RemoteWebDriver) {
            Method getSessionIdMethod = RemoteWebDriver.class.getMethod("getSessionId");
            String sessionId = getSessionIdMethod.invoke(remoteWebDriver).toString();
            String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", sessionId, System.getenv("SELENIUM_BUILD"));
            System.out.println(message);
        }
    }

    public static WebDriver getChromeWebDriver() throws MalformedURLException, StopTestException {
        if (driver != null && !(driver instanceof ChromeDriver)) {
            try {
                driver.quit();
            } catch (Exception var4) {
            }
        }

        try {
            driver.getTitle();
            return driver;
        } catch (Exception var3) {
            System.setProperty("webdriver.chrome.driver", Configuration.getConfiguration().getWebdriverForChrome());
            ChromeOptions options = new ChromeOptions();
            options.addArguments(new String[]{"enable-automation"});
            options.addArguments(new String[]{"--disable-infobars"});
            options.addArguments(new String[]{"--disable-popup-blocking"});
            options.setAcceptInsecureCerts(true);
            options.setExperimentalOption("useAutomationExtension", false);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("acceptSslCerts", true);
            capabilities.setCapability("goog:chromeOptions", options);
            driver = new ChromeDriver(capabilities);
            driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
            return driver;
        }
    }

    public static WebDriver getSafariWebDriver() throws MalformedURLException, StopTestException {
        if (driver != null && !(driver instanceof SafariDriver)) {
            try {
                driver.quit();
            } catch (Exception var4) {
            }
        }

        try {
            driver.getTitle();
            return driver;
        } catch (Exception var5) {
            System.setProperty("webdriver.safari.noinstall", "true");
            SafariOptions options = new SafariOptions();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("acceptSslCerts", true);
            String browserName = null != Configuration.getConfiguration().getBrowser() ? Configuration.getConfiguration().getBrowser().toUpperCase() : "SAFARI";
            SafariOptions.fromCapabilities(capabilities);
            capabilities.setBrowserName(browserName);
            driver = new SafariDriver(options);
            driver.manage().deleteAllCookies();
            return driver;
        }
    }

    public static WebDriver getIEWebDriver() throws MalformedURLException, StopTestException {
        if (driver != null && !(driver instanceof InternetExplorerDriver)) {
            try {
                driver.quit();
            } catch (Exception var4) {
            }
        }

        try {
            driver.getTitle();
            return driver;
        } catch (Exception var3) {
            System.setProperty("webdriver.ie.driver", Configuration.getConfiguration().getWebdriverForIE());
            Proxy proxy = new Proxy();
            proxy.setProxyType(ProxyType.AUTODETECT);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("internet explorer");
            capabilities.setCapability("ignoreProtectedModeSettings", true);
            capabilities.setCapability("requireWindowFocus", true);
            capabilities.setCapability("ie.ensureCleanSession", true);
            capabilities.setCapability("acceptSslCerts", true);
            capabilities.setCapability("ie.browserCommandLineSwitches", "-private");
            capabilities.setCapability("ignoreZoomSetting", true);
            capabilities.setCapability("nativeEvents", true);
            capabilities.setCapability("unexpectedAlertBehaviour", "accept");
            capabilities.setCapability("ignoreProtectedModeSettings", true);
            capabilities.setCapability("disable-popup-blocking", true);
            capabilities.setCapability("enablePersistentHover", true);
            driver = new InternetExplorerDriver(capabilities);
            driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
            return driver;
        }
    }

    public static WebDriver getFireFoxDriver() throws StopTestException {
        if (driver != null && !(driver instanceof FirefoxDriver)) {
            try {
                driver.quit();
            } catch (Exception var4) {
            }
        }

        try {
            driver.getTitle();
            return driver;
        } catch (Exception var3) {
            System.setProperty("webdriver.gecko.driver", Configuration.getConfiguration().getWebDriverForFirefox());
            DesiredCapabilities dc = DesiredCapabilities.firefox();
            dc.setCapability("acceptSslCerts", true);
            FirefoxProfile profile = new FirefoxProfile();
            profile.setAcceptUntrustedCertificates(true);
            profile.setPreference("geo.enabled", false);
            profile.setPreference("geo.prompt.testing", false);
            profile.setPreference("geo.prompt.testing.allow", false);
            dc.setCapability("firefox_profile", profile);
            driver = new FirefoxDriver(dc);
            driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            return driver;
        }
    }

    public static WebDriver getEdgeDriver() throws StopTestException {
        if (driver != null && !(driver instanceof EdgeDriver)) {
            try {
                driver.quit();
            } catch (Exception var3) {
            }
        }

        try {
            driver.getTitle();
            return driver;
        } catch (Exception var2) {
            System.setProperty("webdriver.edge.driver", Configuration.getConfiguration().getWebdriverForEdge());
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("acceptSslCerts", true);
            capabilities.setBrowserName("MicrosoftEdge");
            driver = new EdgeDriver();
            driver.manage().deleteAllCookies();
            return driver;
        }
    }
}
