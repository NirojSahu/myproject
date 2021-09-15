package com.test.CustomHooks;

import com.google.inject.Inject;
import com.test.Utils.App_genericFunction;
import com.test.Utils.SeleniumHelper;
import com.test.Utils.jwt;
import com.test.configuration.Configuration;
import com.test.configuration.PageFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import scala.App;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Map;

import static com.test.Utils.jwt.get_publicKey;

public class GlobalHooks {

    @Inject
    App_genericFunction  app_genericFunction;

    public static String Featureid;
    public static Scenario scenario_screenshot;
    public static String resource ;
    public static String resource_API ;
    public static String scenario_type;
    public static ArrayList<Map<String, String>> values;
    public static String BaseURI;
    public static String ResourceURI;
    public static String USER_DI= System.getProperty("user.dir");
    public static String module;
    public static String VERSION;
    public static String excel_name;
    public static String Sheetname;
    public static PublicKey publickey;
    public static PrivateKey privatekey;

   //@Before
    public void setEnvironment()  throws FileNotFoundException, ParseException, IOException {
        /*if (!propsFlag) {
            endPoint.environment = System.getProperty("environment");
            System.out.println("Environment pointing to" + endPoint.environment);
            JPTestData.setEndPoint(endPointFile);
            System.out.println(endPoint.jsplUrl);
            propsFlag = true;
        }*/
       // System.out.println("Hooks implemeneted");
    }

    @Before
    public void BeforeScenario(Scenario scenario) throws Exception {
        //System.out.println(App_genericFunction.getFeatureName(scenario))
        SessionId sessionid = ((RemoteWebDriver) SeleniumHelper.getdriver()).getSessionId();
        scenario_screenshot = scenario;
        App_genericFunction.putcommentinStep("SessionID : " + sessionid);
        Featureid=App_genericFunction.getFeatureName(scenario);
        System.out.println("Featureid : "+Featureid);
        System.out.println(scenario.getName());
        try
        {
            scenario_type = scenario.getName().split(":")[0].split("_")[1].trim();
            resource = scenario.getName().split(":")[0].split("_")[0].trim();
            resource_API = scenario.getName().split(":")[0].split("\\.")[0];
        }catch (Exception e)
        {
            resource ="";
        }


        System.out.println("resource : "+resource);
        System.out.println("resource_API : "+resource_API);
        System.out.println("scenario_type : "+scenario_type);
        //if(App_genericFunction.ReadBootstrap_static("environment").contentEquals("Dev"))
        if( Configuration.getConfiguration().getProperty("environment").startsWith("Dev"))
        {
            BaseURI="https://10.6.184.149:8243/";
            excel_name = "Datasource_3A";
            module = "open-banking/{VERSION}/aisp";
            publickey = jwt.get_publicKey(System.getProperty("user.dir")+"/src/test/resources/Certificates/3A/Public.der");
            privatekey =jwt.get_privateKey(System.getProperty("user.dir")+"/src/test/resources/Certificates/3A/Private.der");
        }//else if (App_genericFunction.ReadBootstrap_static("environment").contentEquals("Pre"))
        else if (Configuration.getConfiguration().getProperty("environment").startsWith("Pre"))
        {
            BaseURI="https://10.6.184.26:8243/";
            //excel_name = "Datasource_5A";
            excel_name = "Datasource_5A_parallel";
            module = "AccountsInfoAPI/{VERSION}";
            publickey = jwt.get_publicKey(System.getProperty("user.dir")+"/src/test/resources/Certificates/5A/Public.der");
            privatekey =jwt.get_privateKey(System.getProperty("user.dir")+"/src/test/resources/Certificates/5A/Private.der");
        }
        //else if (App_genericFunction.ReadBootstrap_static("environment").contentEquals("Prod"))
        else if (Configuration.getConfiguration().getProperty("environment").startsWith("Prod"))
        {
            BaseURI="https://developer.caterallen.co.uk/";
            excel_name = "Datasource_6A";
            module = "open-banking/{VERSION}/aisp";
            publickey = jwt.get_publicKey(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/Public.der");
            privatekey =jwt.get_privateKey(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/Private.der");
        }
        else if (Configuration.getConfiguration().getProperty("environment").startsWith("UAT"))
        {
            BaseURI="https://ppuatdeveloper.intellectonlinebanking.com/";
            excel_name = "Datasource_4A";
            module = "AccountsInfoAPI/{VERSION}";
            publickey = jwt.get_publicKey(System.getProperty("user.dir")+"/src/test/resources/Certificates/5A/Public.der");
            privatekey =jwt.get_privateKey(System.getProperty("user.dir")+"/src/test/resources/Certificates/5A/Private.der");
        }

        System.out.println("BaseURI : " +BaseURI);
        VERSION =App_genericFunction.ReadPropertiesFile("VERSION");
        System.out.println("Version : "+VERSION);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        switch (resource) {

            case "account-access-consents":

                 Sheetname ="account_access_consents";
                 values =  App_genericFunction.getValues(USER_DI+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if(resource_API.contentEquals("account-access-consents_1"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("account-access-consents_1");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                }
                else if(resource_API.contentEquals("account-access-consents_2"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("account-access-consents_2");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                }else if(resource_API.contentEquals("account-access-consents_3"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("account-access-consents_3");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("permissions : " +values.get(0).get("permissions"));
                System.out.println("transactionfromdatetime : " + values.get(0).get("transactionfromdatetime"));
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;

            case "Token":
                 Sheetname ="token_generation";
                values =  App_genericFunction.getValues(USER_DI+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if(resource_API.contentEquals("Token_1"))
                {
                    ResourceURI = App_genericFunction.ReadPropertiesFile("Token_1");
                }else if(resource_API.contentEquals("Token_2"))
                {
                    ResourceURI = App_genericFunction.ReadPropertiesFile("Token_2");
                }else if(resource_API.contentEquals("Token_3"))
                {
                    ResourceURI = App_genericFunction.ReadPropertiesFile("Token_3");
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "balances":
                Sheetname ="balances";
                values =  App_genericFunction.getValues(USER_DI+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if(resource_API.contentEquals("balances_1"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("balances_1");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                    //ResourceURI = App_genericFunction.ReadPropertiesFile("balances_1").replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "transactions":
                Sheetname ="transactions";
                values =  App_genericFunction.getValues(USER_DI+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if(resource_API.contentEquals("transactions_1"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("transactions_1");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                   // ResourceURI = App_genericFunction.ReadPropertiesFile("transactions_1").replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "accounts":
                Sheetname ="accounts";
                values =  App_genericFunction.getValues(USER_DI+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if(resource_API.contentEquals("accounts_1"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("accounts_1");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                    //ResourceURI = App_genericFunction.ReadPropertiesFile("accounts_1").replace("{VERSION}",VERSION);
                }
                else if(resource_API.contentEquals("accounts_2"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("accounts_2");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                    //ResourceURI = App_genericFunction.ReadPropertiesFile("accounts_2").replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "beneficiaries":
                Sheetname ="beneficiaries";
                values =  App_genericFunction.getValues(USER_DI+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if(resource_API.contentEquals("beneficiaries_1"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("beneficiaries_1");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                    //ResourceURI = App_genericFunction.ReadPropertiesFile("beneficiaries_1").replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "directdebits":
                Sheetname ="directdebits";
                values =  App_genericFunction.getValues(USER_DI+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if(resource_API.contentEquals("directdebits_1"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("directdebits_1");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                    //ResourceURI = App_genericFunction.ReadPropertiesFile("directdebits_1").replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "standingorders":
                Sheetname ="standingorders";
                values =  App_genericFunction.getValues(USER_DI+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if(resource_API.contentEquals("standingorders_1"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("standingorders_1");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                   // ResourceURI = App_genericFunction.ReadPropertiesFile("standingorders_1").replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "products":
                Sheetname ="products";
                values =  App_genericFunction.getValues(USER_DI+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if(resource_API.contentEquals("products_1"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("products_1");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                   // ResourceURI = App_genericFunction.ReadPropertiesFile("products_1").replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "scheduledpayments":
                Sheetname ="scheduledpayments";
                values =  App_genericFunction.getValues(USER_DI+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if(resource_API.contentEquals("scheduledpayments_1"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("scheduledpayments_1");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                    //ResourceURI = App_genericFunction.ReadPropertiesFile("scheduledpayments_1").replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "statements":
                Sheetname ="statements";
                values =  App_genericFunction.getValues(USER_DI+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if(resource_API.contentEquals("statements_1"))
                {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("statements_1");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                    //ResourceURI = App_genericFunction.ReadPropertiesFile("statements_1").replace("{VERSION}",VERSION);
                }else if(resource_API.contentEquals("statements_2")) {
                    ResourceURI = module + App_genericFunction.ReadPropertiesFile("statements_2");
                    ResourceURI = ResourceURI.replace("{VERSION}",VERSION);
                   // ResourceURI = App_genericFunction.ReadPropertiesFile("statements_2").replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "dummy":

                if(resource_API.contentEquals("dummy_1"))
                {
                    //BaseURI="https://webapiuk-taassel-pre.appls-ukpre04a.paas.santanderuk.pre.corp";
                    BaseURI="https://ob-acc-info-ob-aggr-personal-accounts-dev.appls.cap1.paas.gsnetcloud.corp/";
                    ResourceURI = App_genericFunction.ReadPropertiesFile("dummy_1");
                }
                System.out.println("ResourceURI : " +ResourceURI);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "" :
            {
                System.out.println("Scenario without Scenario_type link to excel");
            }
            break;
        }


    }

    /*@Before(value ="@UJProp",order =0)
    public void creatProperty(Scenario scenario) throws IOException {
        Featureid=App_genericFunction.getFeatureName(scenario);
        App_genericFunction.createPropertyfile(Featureid);
    }*/
    /*@After("@quitdriver")
    public void teardown(Scenario scenario) throws Exception {
        PageFactory.tearDown();
    }*/
}
