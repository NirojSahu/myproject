package com.test.CustomHooks

import Utilities.Configuration
import com.google.inject.Inject
import com.sun.media.sound.SimpleSoundbank
import com.test.Utils.App_genericFunction
import com.test.Utils.Jwt
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
        try{
            scenario_type=scenario.getName().split(":")[0].split("_")[1].trim();
            resource=scenario.getName().split(":")[0].split("_")[0].trim();
            resource_API=scenario.getName().split(":")[0].split("\\.")[0];
        }catch(Exception e){
            resource="";
        }
        System.out.println("resource  : "+resource);
        System.out.println("resource_API  : "+resource_API);
        System.out.println("scenario_type  : "+scenario_type);
        if(Configuration.getConfiguration().getProperty("environment").startsWith("Dev")){
            BaseURI="https://10.6.184.149:8243";
            excel_name="Datasource_3A";
            module="open-banking/{VERSION}/aisp";
            publickey= Jwt.get_publicKey(System.getProperty("user.dir")+"/src/test/resources/Certficates/3A/Public.der")
            privatekey=Jwt.get_privateKey(System.getProperty("user.dir")+"/src/test/resources/Certficates/3A/Private.der");
        }
        else if (Configuration.getConfiguration().getProperty("environment").startsWith("Per")){
            BaseURI="https://10.6.184.26:8243";
            excel_name="Datasource_5A";
            module="AccountsInfoAPI/{VERSION}";
            publickey= Jwt.get_publicKey(System.getProperty("user.dir")+"/src/test/resources/Certficates/5A/Public.der")
            privatekey=Jwt.get_privateKey(System.getProperty("user.dir")+"/src/test/resources/Certficates/5A/Private.der");
        }
        else if (Configuration.getConfiguration().getProperty("environment").startsWith("Prod")){
            BaseURI="https://developer.caterallen.co.uk/";
            excel_name="Datasource_6A";
            module="open-banking/{VERSION}/aisp";
            publickey= Jwt.get_publicKey(System.getProperty("user.dir")+"/src/test/resources/Certficates/6A/Public.der")
            privatekey=Jwt.get_privateKey(System.getProperty("user.dir")+"/src/test/resources/Certficates/6A/Private.der");
        }
        System.out.println("BaseURI  : "+BaseURI);
        VERSION=App_genericFunction.ReadPropertiesFile("VERSION");
        System.out.println("Version : "+VERSION);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        switch (resource){
            case "account-access-consents":

                Sheetname="account-access-consents";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if (resource_API.contentEquals("account-access-consents_1"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("account-access-consents_1");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                else if (resource_API.contentEquals("account-access-consents_2"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("account-access-consents_2");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                else if (resource_API.contentEquals("account-access-consents_3"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("account-access-consents_3");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("permissions : "+values.get(0).get("permissions"));
                System.out.println("transactionfromdatetime : "+values.get(0).get("transactionfromdatetime"));
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "Token":

                Sheetname="token_generation";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);
                if (resource_API.contentEquals("Token_1"))
                {
                    ResourceURI=App_genericFunction.ReadPropertiesFile("Token_1");
                }else if (resource_API.contentEquals("Token_2"))
                {
                    ResourceURI=App_genericFunction.ReadPropertiesFile("Token_2");
                }else if (resource_API.contentEquals("Token_3"))
                {
                    ResourceURI=App_genericFunction.ReadPropertiesFile("Token_3");
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "balances":
                Sheetname="balances";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if (resource_API.contentEquals("balances_1"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("balances_1");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "transactions":
                Sheetname="transactions";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if (resource_API.contentEquals("transactions_1"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("transactions_1");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "accounts":
                Sheetname="accounts";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if (resource_API.contentEquals("accounts_1"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("accounts_1");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                if (resource_API.contentEquals("accounts_2"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("accounts_2");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "beneficiaries":
                Sheetname="beneficiaries";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if (resource_API.contentEquals("beneficiaries_1"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("beneficiaries_1");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "directdebits":
                Sheetname="directdebits";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if (resource_API.contentEquals("directdebits_1"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("directdebits_1");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "standingorders":
                Sheetname="standingorders";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if (resource_API.contentEquals("standingorders_1"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("standingorders_1");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "products":
                Sheetname="products";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if (resource_API.contentEquals("products_1"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("products_1");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "scheduledpayments":
                Sheetname="scheduledpayments";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if (resource_API.contentEquals("products_1"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("scheduledpayments_1");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "statements":
                Sheetname="statements";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if (resource_API.contentEquals("statements_1"))
                {
                    ResourceURI=module+App_genericFunction.ReadPropertiesFile("statements_1");
                    ResourceURI=ResourceURI.replace("{VERSION}",VERSION);
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
            case "dummy":
                Sheetname="dummy";
                values=App_genericFunction.getValues(USER_ID+"/src/test/resources/Datasheet/"+excel_name+".xlsx",Sheetname,scenario_type);

                if (resource_API.contentEquals("dummy_1"))
                {
                    BaseURI="https://ob-acc-info-ob-aggr--personal-accounts-dev.appls.cap1.pass.gsnetcloud.corp/";
                    ResourceURI=App_genericFunction.ReadPropertiesFile("dummy_1");
                }
                System.out.println("ResourceURI : "+ResourceURI);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
        }

    }

}
