//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.configuration;

//import com.santander.api.lib.RestAssuredApi;
import Utilities.APIKit.api.lib.RestAssuredApi;
import Utilities.WebKit.exceptions.StopTestException;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;

public class Configuration {
    public static final String KEYBASE = "autotest";
    public static final String SELENIUM_VARIABLES_PREFIX = "selenium.";
    public static final String SELENIUM_ENVIRONMENT_VARIABLES_PREFIX = "SELENIUM_";
    public static final String KEY_SELENIUM_EXCLUDED_PROPERTIES = "excluded_selenium_properties";
    public static final String KEY_SETTINGS = ".settings";
    public static final String KEY_LOADED = ".loaded";
    public static final String KEY_ENVIRONMENT = "environment";
    public static final String KEY_PAGE_TIMEOUT = "timeout.page_load";
    public static final String KEY_PAGE_TIMING = "timing.page_load";
    public static final String KEY_BROWSER = "browser";
    public static final String KEY_BROWSER_VERSION = "browserversion";
    public static final String KEY_GRID_RUN = "grid.run";
    public static final String KEY_GRID_ENVIRONMENT = "selenium.grid.env.";
    public static final String KEY_GRID_NODE = "selenium.grid.node";
    public static final String KEY_GRID_HUB_SERVERNAME_PORT = "grid.hub.server";
    public static final String KEYP_ENV_URL = "url";
    public static final String KEYP_ENV_MULTI_JVM = "environment_multi_jvm";
    public static final String KEYP_DEBUG_ENABLED = "debug.enabled";
    public static final String USER_AGENT = "user.agent";
    public static final String KEY_HELPER_DEFINITIONS = "location.helper_definitions";
    public static final String KEYP_WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    public static final String KEYP_WEBDRIVER_FIREFOX_DRIVER = "webdriver.gecko.driver";
    public static final String KEYP_WEBDRIVER_IE_DRIVER = "webdriver.ie.driver";
    public static final String KEYP_WEBDRIVER_EDGE_DRIVER = "webdriver.edge.driver";
    public static final String KEYP_MOBILE_PLATFORMNAME = "platformName";
    public static final String KEYP_MOBILE_DEVICENAME = "deviceName";
    public static final String KEYP_MOBILE_PLATFORMVERSION = "platformVersion";
    public static final String KEYP_MOBILE_AUTOACCEPTALERTS = "autoAcceptAlerts";
    public static final String KEYP_MOBILE_FULLRESET = "fullReset";
    public static final String KEYP_MOBILE_NEWCOMMANDTIMEOUT = "newCommandTimeout";
    public static final String KEYP_MOBILE_APP = "app";
    public static final String KEYP_MOBILE_BROWSER = "mobile.browser";
    public static final String KEYP_MOBILE_AUTOMATIONNAME = "automationName";
    public static final String KEYP_REPORTING_ENABLED = "reporting.enabled";
    public static final String KEYP_RUNNER_NAME = "runner.name";
    public static final String KEYP_RESULTS_LOCATION = "results.location";
    public static final String KEYP_BROWSER_SIZE = "browser.size";
    public static final String KEYP_DIMENSION_WIDTH = "dimensionWidth";
    public static final String KEYP_DIMENSION_HEIGHT = "dimensionHeight";
    public static final String KEY_PROJECT_NAME = "project";
    private static final String KEYP_DEBUG_ERROR_DUMP_ENABLED = "debug.error.dump.enabled";
    public static final String KEY_CUSTOM_CAPABILITIES_FLAG = "custom.capabilities";
    public static final String KEY_CHROME_CUSTOM_CAP = "chrome.custom.cap";
    public static final String KEY_CHROME_SETEXPERIMENTALOPTION = "chrome.setExperimentalOption";
    public static final String KEY_CHROME_SETACCEPTINSECURECERTS = "chrome.setAcceptInsecureCerts";
    public static final String KEY_IE_CUSTOM_CAP = "ie.custom.cap";
    private static final String KEY_EDGE_CUSTOM_CAP = "edge.custom.cap";
    private static final String KEY_SAFARI_CUSTOM_CAP = "safari.custom.cap";
    private static final String KEY_FIREFOX_CUSTOM_CAP = "firefox.custom.cap";
    private static final String[] files = new String[]{"./bootstrap.properties", "./src/test/resources/com.test.defaults.properties"};
    public static String testCaseName = "";
    private static volatile Configuration configuration;
    private String mode = "WEB";
    private String setUpModifier = "none";
    private String settings = null;
    private Properties myprops = null;
    private String platform = null;
    private RestAssuredApi restCall = new RestAssuredApi();
    private Headers headersAuth;
    private Headers headersRecipient;
    private String jobid;
    private String hub;
    private String hostId;

    public Configuration() throws StopTestException {
        this.settings = System.getProperty(".settings", (String)null);
        this.myprops = new Properties(System.getProperties());
        String userhome = System.getProperty("user.home", "./");
        String loaded = this.myprops.getProperty(".loaded");
        if (loaded == null) {
            String[] var3 = files;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String filename = var3[var5];

                try {
                    filename = filename.replace("{user.home}", userhome);
                    this.myprops.load(new FileInputStream(filename));
                } catch (FileNotFoundException var8) {
                } catch (IOException var9) {
                    System.err.println("ERROR: " + var9.getMessage());
                }
            }

            this.myprops.putAll(System.getProperties());
        }

        if (this.settings == null) {
            this.settings = this.myprops.getProperty(".settings", (String)null);
        }

    }

    public static boolean isMobile() throws StopTestException {
        return getConfiguration().getApplicatonMode().equalsIgnoreCase("mobile");
    }

    public static boolean isAPI() throws StopTestException {
        return getConfiguration().getApplicatonMode().equalsIgnoreCase("api");
    }

    public static synchronized Configuration getConfiguration() throws StopTestException {
        if (configuration == null) {
            configuration = new Configuration();
        }

        return configuration;
    }

    public static synchronized void resetConfiguration() {
        configuration = null;
    }

    public void addProperties(String propertyName) {
        try {
            this.myprops.load(new FileReader("src/test/resources/properties/" + propertyName + ".properties"));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public String overrideProperty(String keypart, String value) {
        return (String)this.myprops.setProperty(keypart, value);
    }

    public String getProperty(String keypart) {
        return this.getProperty(keypart, (String)null);
    }

    protected String getProperty(String keypart, String default_value) {
        String value = null;
        if (this.myprops != null) {
            if (this.settings != null && this.settings.length() > 0) {
                value = this.myprops.getProperty(this.settings + "." + keypart);
            } else {
                value = this.myprops.getProperty(keypart);
            }

            if (value == null || value.length() == 0) {
                value = this.myprops.getProperty("default." + keypart, default_value);
            }
        }

        return this.interpolate(value);
    }

    public String interpolate(String value) {
        if (null == value) {
            return value;
        } else {
            Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z.]+}");

            for(Matcher matcher = pattern.matcher(value); matcher.find(); value = this.getProperty(value)) {
                value = value.replaceAll("\\$\\{", "").replaceAll("}", "");
            }

            return value;
        }
    }

    public long getPageLoadTimeout() {
        String timeout = this.getProperty("timeout.page_load");
        return Long.parseLong(timeout);
    }

    public Boolean getPageLoadTiming() {
        String timing = this.getProperty("timing.page_load");
        return Boolean.parseBoolean(timing);
    }

    public String getScreenshotLocation(Class<?> testClass) {
        String screenshotPath = testClass.getName().replace(testClass.getSimpleName(), "screenshots");
        screenshotPath = screenshotPath.replaceAll("\\.", "/");
        return screenshotPath;
    }

    public String getGridRun() {
        return this.getProperty("grid.run");
    }

    public String getGridEnvironment(String envId) {
        return this.getProperty("selenium.grid.env." + envId);
    }

    public String getSetUpModifier() {
        return this.setUpModifier;
    }

    public String getGridNode() {
        return this.getProperty("selenium.grid.node");
    }

    public String getSafeBrowser() {
        try {
            return this.getBrowser();
        } catch (Exception var2) {
            return null;
        }
    }

    public String getBrowser() {
        return this.getProperty("browser");
    }

    public String getBrowserVersion() {
        return this.getProperty("browserversion");
    }

    public String getEnvironment() throws StopTestException {
        String env = this.getProperty("environment");
        if (env == null) {
            throw new StopTestException("environment not set - please set environment");
        } else {
            return env;
        }
    }

    protected String getEnvironmentsProperty(String keypart) throws StopTestException {
        return this.getProperty(keypart);
    }

    public String getEnvironmentUrl() throws StopTestException {
        return this.getEnvironmentsProperty("url");
    }

    private String addLastSlash(String value) {
        if (value != null && !value.endsWith(File.separator)) {
            value = value + "/";
        }

        return value;
    }

    public String getDebugProperty() throws StopTestException {
        String prop = this.getProperty("debug.enabled");
        if (prop == null) {
            prop = "FALSE";
        }

        return prop;
    }

    public boolean isDebug() throws StopTestException {
        return this.getDebugProperty().trim().equalsIgnoreCase("true");
    }

    public String getDebugErrorDumpProperty() throws StopTestException {
        String prop = this.getProperty("debug.error.dump.enabled");
        if (prop == null) {
            prop = "FALSE";
        }

        return prop;
    }

    public boolean getMultiJvm() throws StopTestException {
        String multiJvm = this.getProperty("environment_multi_jvm");
        return multiJvm.toUpperCase().equalsIgnoreCase("TRUE");
    }

    public void setApplicationMode(String mode) {
        this.mode = mode;
    }

    public String getApplicatonMode() throws StopTestException {
        return this.mode;
    }

    public String getHelperDefinitionLocation() {
        return this.addLastSlash(this.getProperty("location.helper_definitions"));
    }

    public URL getGridHubServerDetails() throws StopTestException, IOException, URISyntaxException {
        try {
            String hubURL = null;
            hubURL = this.getWebURL();
            if (!hubURL.contains("http:") && !hubURL.contains("https:")) {
                throw new StopTestException("Grid hub details not available - please check");
            } else {
                return new URL(hubURL);
            }
        } catch (MalformedURLException var2) {
            return null;
        }
    }

    private String getWebURL() throws IOException, URISyntaxException {
        HashMap<String, String> header = new HashMap();
        header.putIfAbsent("Accept", "application/json");
        header.putIfAbsent("charset", "UTF-8");
        header.putIfAbsent("Content-Type", "application/json");
        this.headersAuth = this.restCall.setHTTPHeaders(header);
        JSONObject jsonObject = new JSONObject();
        jsonObject.putIfAbsent("token", this.myprops.getProperty("token"));
        jsonObject.putIfAbsent("applicationtype", this.myprops.getProperty("applicationtype"));
        jsonObject.putIfAbsent("browser", this.myprops.getProperty("browser"));
        jsonObject.putIfAbsent("browserversion", this.myprops.getProperty("browserversion"));
        String webAPIEndPt = this.myprops.getProperty("webservice") + "/weburl";
        Response response = this.restCall.PostWithJSON(this.headersAuth, webAPIEndPt, jsonObject);
        this.hub = response.getBody().print();
        return response.getBody().print();
    }

    String recordSession(String sessionId) {
        HashMap<String, String> header = new HashMap();
        header.putIfAbsent("Accept", "application/json");
        header.putIfAbsent("charset", "UTF-8");
        header.putIfAbsent("Content-Type", "application/json");
        this.headersAuth = this.restCall.setHTTPHeaders(header);
        JSONObject jsonObject = new JSONObject();
        jsonObject.putIfAbsent("token", this.myprops.getProperty("token"));
        jsonObject.putIfAbsent("applicationtype", this.myprops.getProperty("applicationtype"));
        jsonObject.putIfAbsent("browser", this.myprops.getProperty("browser"));
        jsonObject.putIfAbsent("browserversion", this.myprops.getProperty("browserversion"));
        jsonObject.putIfAbsent("sessionId", sessionId);
        jsonObject.putIfAbsent("hub", this.hub.substring(0, this.hub.length() - 6));
        String webAPIEndPt = this.myprops.getProperty("webservice") + "/usage";
        Response response = this.restCall.PostWithJSON(this.headersAuth, webAPIEndPt, jsonObject);
        this.jobid = response.getBody().print();
        return this.jobid;
    }

    String endSession(String jobid) {
        HashMap<String, String> header = new HashMap();
        header.putIfAbsent("Accept", "application/json");
        header.putIfAbsent("charset", "UTF-8");
        header.putIfAbsent("Content-Type", "application/json");
        String webAPIEndPt = this.myprops.getProperty("webservice") + "/endusage";
        this.headersAuth = this.restCall.setHTTPHeaders(header);
        JSONObject jsonObject = new JSONObject();
        jsonObject.putIfAbsent("jobid", jobid);
        Response response = this.restCall.PostWithJSON(this.headersAuth, webAPIEndPt, jsonObject);
        return response.getBody().print();
    }

    public String getWebdriverForChrome() throws StopTestException {
        String driverDetails = null;
        driverDetails = isMac() ? "/usr/local/bin/chromedriver" : this.getProperty("webdriver.chrome.driver");
        if (driverDetails == null) {
            throw new StopTestException("Chrome exe file not available");
        } else {
            return driverDetails;
        }
    }

    public String getWebDriverForFirefox() throws StopTestException {
        String driverDetails = null;
        driverDetails = isMac() ? "/usr/local/bin/geckodriver" : this.getProperty("webdriver.gecko.driver");
        if (driverDetails == null) {
            throw new StopTestException("Gecko exe file not available");
        } else {
            return driverDetails;
        }
    }

    public String getWebdriverForIE() throws StopTestException {
        String driverDetails = null;
        driverDetails = this.getProperty("webdriver.ie.driver");
        if (driverDetails == null) {
            throw new StopTestException("IE exe file not available");
        } else {
            return driverDetails;
        }
    }

    public String getWebdriverForEdge() throws StopTestException {
        String driverDetails = null;
        driverDetails = this.getProperty("webdriver.edge.driver");
        if (driverDetails == null) {
            throw new StopTestException("Edge exe file not available");
        } else {
            return driverDetails;
        }
    }

    public String getPlatformNameforMobile() throws StopTestException {
        String driverDetails = null;
        driverDetails = this.getProperty("platformName");
        if (driverDetails == null) {
            throw new StopTestException("Platform Name not available in properties");
        } else {
            return driverDetails;
        }
    }

    public String getDeviceNameforMobile() throws StopTestException {
        String driverDetails = null;
        driverDetails = this.getProperty("deviceName");
        if (driverDetails == null) {
            throw new StopTestException("Platform Name not available in properties");
        } else {
            return driverDetails;
        }
    }

    public String getNewCommandTimeoutforMobile() throws StopTestException {
        String driverDetails = null;
        driverDetails = this.getProperty("newCommandTimeout");
        if (driverDetails == null) {
            throw new StopTestException("Platform Name not available in properties");
        } else {
            return driverDetails;
        }
    }

    public String getAppforMobile() throws StopTestException {
        String driverDetails = null;
        driverDetails = this.getProperty("app");
        if (driverDetails == null) {
            throw new StopTestException("Platform Name not available in properties");
        } else {
            return driverDetails;
        }
    }

    public String getAutoAcceptAlertsforMobile() throws StopTestException {
        String driverDetails = null;
        driverDetails = this.getProperty("autoAcceptAlerts");
        if (driverDetails == null) {
            throw new StopTestException("Platform Name not available in properties");
        } else {
            return driverDetails;
        }
    }

    public String getFullResetforMobile() throws StopTestException {
        String driverDetails = null;
        driverDetails = this.getProperty("fullReset");
        if (driverDetails == null) {
            throw new StopTestException("Platform Name not available in properties");
        } else {
            return driverDetails;
        }
    }

    public String getPlatformVersionforMobile() throws StopTestException {
        String driverDetails = null;
        driverDetails = this.getProperty("platformVersion");
        if (driverDetails == null) {
            throw new StopTestException("Platform Name not available in properties");
        } else {
            return driverDetails;
        }
    }

    public String getBrowserforMobile() throws StopTestException {
        String driverDetails = null;
        driverDetails = this.getProperty("mobile.browser");
        if (driverDetails == null) {
            throw new StopTestException("Platform Name not available in properties");
        } else {
            return driverDetails;
        }
    }

    public String getAutomationNameforMobile() throws StopTestException {
        String driverDetails = null;
        driverDetails = this.getProperty("automationName");
        if (driverDetails == null) {
            throw new StopTestException("Platform Name not available in properties");
        } else {
            return driverDetails;
        }
    }

    public String getReportingEnabled() throws StopTestException {
        String reportingEnabled = null;
        reportingEnabled = this.getProperty("reporting.enabled");
        if (reportingEnabled == null) {
            reportingEnabled = "false";
        }

        return reportingEnabled;
    }

    public String getRunnerName() throws StopTestException {
        String runnerName = null;
        runnerName = this.getProperty("runner.name");
        if (runnerName == null) {
            throw new StopTestException("Unable to retrieve runner name system properties");
        } else {
            return runnerName;
        }
    }

    public String getResultsLocation() throws StopTestException {
        String resultsLocation = null;
        resultsLocation = this.getProperty("results.location");
        if (resultsLocation == null) {
            throw new StopTestException("Unable to retrieve results location");
        } else {
            return resultsLocation;
        }
    }

    public String getTestCaseName() throws StopTestException {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        Configuration.testCaseName = testCaseName;
    }

    public Properties getProperties() {
        Properties combinedProperties = new Properties();
        Properties existingProperties = new Properties();
        existingProperties.putAll(this.myprops);
        Iterator var3 = (new HashSet(existingProperties.keySet())).iterator();

        Object key;
        while(var3.hasNext()) {
            key = var3.next();
            if (key.toString().startsWith(".settings")) {
                combinedProperties.put(key.toString().replaceFirst(".settings", "autotest"), existingProperties.get(key));
                existingProperties.remove(key);
            }
        }

        var3 = (new HashSet(existingProperties.keySet())).iterator();

        while(var3.hasNext()) {
            key = var3.next();
            if (key.toString().startsWith("default.")) {
                combinedProperties.put(key.toString().replaceFirst("default", "."), existingProperties.get(key));
                existingProperties.remove(key);
            }
        }

        combinedProperties.putAll(existingProperties);
        return combinedProperties;
    }

    public void resetApplicationMode() {
        this.setApplicationMode("WEB");
    }

    public ArrayList<String> getExcludedSeleniumProperties() {
        return new ArrayList(Arrays.asList(this.getProperty("excluded_selenium_properties").toString().split(",")));
    }

    public String getBrowserSize() {
        return this.getProperty("browser.size");
    }

    public Integer getDimensionWidth() {
        return this.getDimensions("dimensionWidth");
    }

    public Integer getDimensionHeight() {
        return this.getDimensions("dimensionHeight");
    }

    private Integer getDimensions(String dimensionType) {
        String dimension_exists = this.getProperty(dimensionType);
        return dimension_exists != null ? Integer.valueOf(this.getProperty(dimensionType)) : null;
    }

    public String getProjectName() throws StopTestException {
        String env = this.getProperty("project");
        if (env == null) {
            throw new StopTestException("project name not set - please set project");
        } else {
            return env;
        }
    }

    String getUserAgent() {
        String userAgent = this.getProperty("user.agent");
        return userAgent != null ? this.getProperty("user.agent") : "Selenium";
    }

    private static boolean isMac() {
        return System.getProperty("os.name").contains("Mac");
    }

    public String getKeyCustomCapabilitiesFlag() {
        return this.getProperty("custom.capabilities");
    }

    public String getSetExperimentalOption() {
        return this.getProperty("chrome.setExperimentalOption");
    }

    public String getSetAcceptInsecureCerts() {
        return this.getProperty("chrome.setAcceptInsecureCerts");
    }

    public ArrayList<String> getChromeCustomCapabilities() {
        String chromeCustomCapab = this.getProperty("chrome.custom.cap");
        return chromeCustomCapab != null ? new ArrayList(Arrays.asList(this.getProperty("chrome.custom.cap").toString().split(";"))) : null;
    }

    public String getIECustomCapabilities() {
        String ieCustomCapab = this.getProperty("ie.custom.cap");
        return ieCustomCapab != null ? ieCustomCapab : null;
    }

    public String getEdgeCustomCapabilities() {
        String edgeCustomCapab = this.getProperty("edge.custom.cap");
        return edgeCustomCapab != null ? edgeCustomCapab : null;
    }

    public String getSafariCustomCapabilities() {
        return this.getProperty("safari.custom.cap");
    }

    public String getFirefoxCustomCapabilities() {
        return this.getProperty("firefox.custom.cap");
    }
}
