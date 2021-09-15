package Utilities.WebKit.configuration;

import Utilities.WebKit.helper.StopTestException;
import io.restassured.http.Header;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    public static final String KYEBASE="autotest";
    public static final String SELENIUM_VARIABLE_PREFIX="selenium.";
    public static final String SELENIUM_ENVIRONMENT_VARIABLE_PREFIX="SELENIUM_";
    public static final String KYE_SELENIUM_EXCLUDED_PROPERTIES="executed_selenium_properties";
    public static final String KYE_SETTINGS=".settings";
    public static final String KYE_LOADED=".loaded";
    public static final String KYE_ENVIRONMENT="environment";
    public static final String KYE_PAGE_TIMEOUT="timeout.page.load";
    public static final String KYE_PAGE_TIMING="timing.page.load";
    public static final String KYE_BROWSER="browser";
    public static final String KYE_BROWSER_VERSION="browserversion";
    public static final String KYE_GRID_RUN="grid_run";
    public static final String KYE_GRID_ENVIRONMENT="selenium.grid.env.";
    public static final String KYE_GRID_NODE="selenium.grid.node";
    public static final String KYE_GRID_HUB_SERVERNAME_PORT="grid.hub.server";
    public static final String KYEP_ENV_URL="url";
    public static final String KYEP_ENV_MULTI_JVM="environment_multi_jvm";
    public static final String KYEP_DEBUG_ENABLED="debug.enabled";
    public static final String USER_AGENT="user.agent";
    public static final String KYE_HELPER_DEFINITION="location.helper.definitions";
    public static final String KYEP_WEBDRIVER_CHROME_DRIVER="webdriver.chrome.driver";
    public static final String KYEP_WEBDRIVER_FIREFOX_DRIVER="webdriver.gecko.driver";
    public static final String KYEP_WEBDRIVER_IE_DRIVER="webdriver.ie.driver";
    public static final String KYEP_WEBDRIVER_EDGE_DRIVER="webdriver.edge.driver";
    public static final String KYEP_MOBILE_PLATFORMNAME="platformName";
    public static final String KYEP_MOBILE_DEVICENAME="deviceName";
    public static final String KYEP_MOBILE_PLATFORMVERSION="platformVersion";
    public static final String KYEP_MOBILE_AUTOACCEPTALERTS="autoAcceptAlerts";
    public static final String KYEP_MOBILE_FULLRESET="fullReset";
    public static final String KYEP_MOBILE_NEWCOMMANDTIMEOUT="newCommandTimeout";
    public static final String KYEP_MOBILE_APP="app";
    public static final String KYEP_MOBILE_BROWSER="mobile.browser";
    public static final String KYEP_MOBILE_AUTOMATIONNAME="automationName";
    public static final String KYEP_REPORTING_ENABLED="reporting.enabled";
    public static final String KYEP_RUNNER_NAME="runner.name";
    public static final String KYEP_RESULTS_LOCATION="results.location";
    public static final String KYEP_BROWSER_SIZE="result.size";
    public static final String KYEP_DIMENSION_WIDTH="dimensionWidth";
    public static final String KYEP_DIMENSION_HEIGHT="dimensionHeight";
    public static final String KYEP_PROJECT_NAME="project";
    private static final String KYEP_DEBUG_ERROR_DUMP_ENABLED="debug.error.dump.enabled";
    public static final String KYE_CUSTOM_CAPABILITIES_FLAG="custom.capabilities";
    public static final String KYE_CHROME_CUSTOM_CAP="chrome.custom.cap";
    public static final String KYE_CHROME_SETEXPERIMENTALOPTION="chrome.setExperimentalOption";
    public static final String KYE_CHROME_SETACCEPTINSECURECERTS="chrome.setAcceptInsecureCerts";
    public static final String KYE_IE_CUSTOM_CAP="ie.custom.cap";
    private static final String KYE_EDGE_CUSTOM_CAP="edge.custom.cap";
    private static final String KYE_SAFARI_CUSTOM_CAP="safari.custom.cap";
    private static final String KYE_FIREFOX_CUSTOM_CAP="firefox.custom.cap";
    private static final String[] files=new String[]{"./bootstrap.properties", "./src/test/resource/com.test.default.properties"};
    public static final String testCaseName="";
    private static volatile Configuration configuration;
    private String mode= "WEB";
    private String setUpModifier="none";
    private String setting=null;
    private Properties myprops=null;
    private String platform=null;
    private RestAssuredApi restCall=new RestAssuredApi();
    private Header headersAuth;
    private Header headersRecipient;
    private String jobid;
    private String hub;
    private String hostId;

    public Configuration() {
        this.setting =System.getProperty(".settings", (String) null);
        this.myprops=new Properties(System.getProperties());
        String userhome=System.getProperty("user.home", "./");
        String loaded=this.myprops.getProperty(".loaded");
        if (loaded==null){
            String[] var3=files;
            int var4= var3.length;

            for (int var5=0; var5<var4; ++var5){
                String filename=var3[var5];

                try {
                    filename=filename.replace("{user.home}",userhome);
                    this.myprops.load(new FileInputStream(filename));
                } catch (FileNotFoundException var8) {
                } catch (IOException var9) {
                    System.err.println("ERROR: " +var9.getMessage());
                }
            }

            this.myprops.putAll(System.getProperties());
        }
        if (this.setting==null){
            this.setting=this.myprops.getProperty(".settings", (String) null);
        }
    }

    public static boolean isMobile(){
        return getConfiguration().getApplicationMode().equalsIgnoreCase("Mobile");
    }
    public static boolean isAPI(){
        return getConfiguration().getApplicationMode().equalsIgnoreCase("api");
    }
    public static synchronized Configuration getConfiguration() throws StopTestException {
        if (configuration==null){
            configuration=new Configuration();
        }
        return configuration;
    }
}
