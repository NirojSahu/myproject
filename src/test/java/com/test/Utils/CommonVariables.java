package com.test.Utils;



import cucumber.runtime.java.guice.ScenarioScoped;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

@ScenarioScoped
public class CommonVariables {


    public String currentDayTime;
    public ArrayList<Map<String, String>> sharedcdRows;
    public String createdPayeeName;
    public String createdPayeeNameForStandingOrder;
    public String createdPayeeNameForStandingOrderAmendment;
    public String createdPayeeNameForStandingOrderCancellation;


    public String paymentRefrenceString;
    public String countrySelect_1= "UNITED KINGDOM";
    public String countrySelect_2= "AUSTRALIA";

    public String defaultIntegerAmount = "2";
    public String defaultFractionAmount = "30";
    public String defaultAmount ="2.30";

    //variables for Alert
    public String currentAccountPartialString ="09-01-29 00693385";
    public String creditCardPartialString ="8199";
    public String defaultAlertIntegerAmount= "100";
    public String defaultAlertFractionAmount= "10";

    public static final String USER_DIRECTORY= System.getProperty("user.dir")+ File.separator;
    public static String TEST_DATA_PROPERTY_PATH = USER_DIRECTORY+"src/test/resources/TestData/TestData.properties";
    public static String ELEMENT_LOCATOR_PROPERTY_PATH = USER_DIRECTORY+"src/test/resources/TestData/ElementLocator.properties";
    public static String ENVIRONMENT_CONFIG_PROPERTY_PATH = USER_DIRECTORY+"src/test/resources/TestData/EnvironmentConfig.properties";
    public static String YML_Path = USER_DIRECTORY+"src/test/resources/TestData/headerinfo.yml";

    public PropertyReader testData = new PropertyReader(TEST_DATA_PROPERTY_PATH);
    public PropertyReader elementLocator = new PropertyReader(ELEMENT_LOCATOR_PROPERTY_PATH);
    public PropertyReader environmentConfig = new PropertyReader(ENVIRONMENT_CONFIG_PROPERTY_PATH);

    public String parentWindowHandle;
    public String child1WindowHandle;
    public String child2WindowHandle;

    public String threeDashes= "---";

    public static final String APPLICATIONS_IN_PROGRESS= "Applications in progress";
    public static final String AIP_STATUS = "AIP Status is not as expected";





}
