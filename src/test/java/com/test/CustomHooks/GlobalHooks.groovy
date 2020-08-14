package com.test.CustomHooks

import com.google.inject.Inject
import com.sun.media.sound.SimpleSoundbank
import com.test.Utils.App_genericFunction
import cucumber.api.Scenario
import org.junit.Before

class GlobalHooks {
    @Inject
    App_genericFunction app_genericFunction;
    public static String Featureid;
    public static Scenario scenario_screenshot;
    public static String resource;
    public static String resource_API;
    public static String scenario_type;
    public static ArrayList<Map<String,String>> values;
    public static String BaseURI;
    public static String ResourceURI;
    public static String USER_ID=System.getProperty("user.dir");
    public static String module;
    public static String VERSION;
    public static String excel_name;
    public static String Sheetname;
    public static String publickey;
    public static String privatekey;

    //@Before
    public void setEnvornment(){

    }

    @Before
    public void BeforeScenario(Scenario scenario){
        scenario_screenshot=scenario;
        Featureid=App_genericFunction.getFeatureName(scenario);
        System.out.println("Featureid : "+Featureid);
        System.out.println(scenario.getName());
    }
}
