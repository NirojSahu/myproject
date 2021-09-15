package com.test.Utils;

import Utilities.APIMethods;
import Utilities.JsonUtilities;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.google.inject.Inject;
import com.test.APIFunctions.TokenService;
import com.test.CustomHooks.GlobalHooks;
import com.test.LoadProperties;
import com.test.configuration.Configuration;
import com.test.helper.NavigationHelper;
import cucumber.api.Scenario;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.nashorn.internal.objects.Global;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;
import java.util.regex.Pattern;

import static com.jayway.restassured.RestAssured.config;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;

public class App_genericFunction {

    @Inject
    SeleniumHelper selenium_helper;
    /*@Inject
    ScrenshotUtils screenshot_extent;*/
    @Inject
    App_genericFunction Mortgage;
    @Inject
    protected NavigationHelper navigationHelper;
    @Inject
    TokenService ts;

    int counter = 0;

    public static String USER_DI = System.getProperty("user.dir");
    public static String bootstrap_path = USER_DI + "/bootstrap.properties";
    //   public static String property_path = USER_DI+"\\src\\test\\resources\\TestData\\MortgageDataSheets\\TextContext.properties";
    //public static String property_path = USER_DI+"/src/test/resources/TestData/MortgageDataSheets/TextContext.properties";
    public static String property_path = USER_DI + "/src/test/resources/TestData/baseValues.properties";
    public static String property_path_1 = USER_DI + "/src/test/resources/TestData/CommonValues.properties";
    public  static String property_path_2 = USER_DI + "/src/test/resources/UserJourney/";

    public static ArrayList<Map<String, String>> getValues(String filepath, String sheetname, String Scenario_Type) {
        Fillo f = new Fillo();
        ArrayList<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        if (ValidateScenario(filepath, sheetname, Scenario_Type)) {
            try {

                //String filepath = "C:\\\\Users\\c0258697\\Desktop\\NewFramework\\NitrowebCageScenario.xlsx";
                // Connection con = f.getConnection(filepath + "\\" + Filename);
                Connection con = f.getConnection(filepath);
                // Connection con = f.getConnection(USER_DI+"/src/test/resources/TestData/MortgageDataSheets/Login.xlsx");
                String strQuery = "select  * from " + sheetname + " where scenario_Type ='" + Scenario_Type + "'";
                System.out.println(strQuery);
                Recordset rs = con.executeQuery(strQuery);
                //System.out.println("Record Count--getvalues --> " + rs.getCount());
                System.out.println("List of Columns in " + sheetname + rs.getFieldNames());
                while (rs.next()) {
                    ArrayList<String> Column_names = rs.getFieldNames();
                    Iterator<String> dataIterator = Column_names.iterator();
                    LinkedHashMap<String, String> row = new LinkedHashMap<String, String>();

                    while (dataIterator.hasNext()) {
                        String Column_name = dataIterator.next();
                        String Column_value = rs.getField(Column_name).toString();
                        //System.out.println("Column_name:" + Column_name);
                        //System.out.println("Column_value:" + Column_value );
                        row.put(Column_name, Column_value);
                    }
                    //System.out.println("List of Columns in "+sheetname + Column_names);
                    //System.out.println("Column_name : "+ rs.getField(Column_name));
                    rows.add(row);
                }
                System.out.println("Map of Column and values:" + rows);
                rs.close();
                con.close();

            } catch (FilloException e) {
                
                System.out.println(e.getMessage());
                //e.printStackTrace();
            }
        } else {
            System.out.println("Value does not exist in Datasheet");
            //value = "Value does not exist in Datasheet";
        }
        System.out.println("========================================================");
        return rows;

    }

    public void enterText1(String webElementString, String numeric) {
        /*try {
            if(webElementString.split(":")[0].equalsIgnoreCase("id")) {
                selenium_helper.getdriver().findElement(By.id(webElementString.split(":")[1].toString())).sendKeys(numeric);
            }else if(webElementString.split(":")[0].equalsIgnoreCase("xpath")){
                selenium_helper.getdriver().findElement(By.xpath(webElementString.split(":")[1].toString())).sendKeys(numeric);
            }
        } catch (Exception t) {
            System.out.println("unable to enterText on the element using enterText(WebElement webElementString,String string) : " + webElementString);
            // throw t;
        }*/

        try {
            selenium_helper.sendkeysenterText(webElementString, numeric);
        } catch (Exception t) {
            System.out.println("unable to enterText on the element using enterText(WebElement webElementString,String string) : " + webElementString);
            // throw t;
        }
    }


    public void clickbasedondata(String obj1, String obj2, String value) {

        if (value.equalsIgnoreCase("Yes")) {
            selenium_helper.enter(obj1);
        } else if (value.equalsIgnoreCase("No")) {
            selenium_helper.enter(obj2);
        }

    }

    public static Integer getRecordsCount( String Sheet_Name, String Scenario_Type) {
        Fillo f = new Fillo();
        int count = 0;
        try {
            String filepath = USER_DI + "/src/test/resources/Datasheet";
            Connection con = f.getConnection(filepath + "/" + GlobalHooks.excel_name+".xlsx");
            //String strQuery = "select  * from "+ Sheet_Name +" where Scenario_Type ='"+ Scenario_Type +"' and " + Column_Name + " IS NOT NULL";
            //String strQuery = "select  * from "+ Sheet_Name +" where Scenario_Type ='"+ Scenario_Type +"' and " + Column_Name + "!=''";
            String strQuery = "select  * from " + Sheet_Name + " where Scenario_Type ='" + Scenario_Type + "'";
            System.out.println(strQuery);
            Recordset rs = con.executeQuery(strQuery);
            count = rs.getCount();
            rs.close();
            con.close();
            return count;
        } catch (FilloException e) {
            return count;
        }

    }

    public static void main(String[] args) throws Exception {
       /* String USER_DI = System.getProperty("user.dir");
        String Filename = "FMA.xlsx";
        String Sheetname = "Loan_breakdown";
        String Scenario_Type = "BTLPJMOSA";

        int noOfRecords = App_genericFunction.getsize(Filename, Sheetname, Scenario_Type, "Loan_1_amt");
        System.out.println("Number Of Records " + noOfRecords);*/
       //getDatafromDatabase("600007","48894458");
        App_genericFunction ap = new App_genericFunction();
        PublicKey publickey = jwt.get_publicKey(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/Public.der");
        PrivateKey privatekey =jwt.get_privateKey(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/Private.der");
        String [] x ={"686e18e3-9a0a-3f50-b855-5534c4c7a52a","c443824f-6980-30f9-9a18-70d60b5406ab","79a579fe-dc7d-35e1-aafd-4dce98ece461"};
        ap.generateclients_usingrefreshtokens(publickey, privatekey,x,"eubXX5Wm9TGJNMHf48fS_vQ88foa");
    }


    public static int getsize(String filepath, String Filename, String sheetname, String Scenario_Type) {
        Fillo f = new Fillo();
        int count = 0;
        ArrayList<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        if (ValidateScenario(filepath, sheetname, Scenario_Type)) {
            try {

                //String filepath = "C:\\\\Users\\c0258697\\Desktop\\NewFramework\\NitrowebCageScenario.xlsx";
                // Connection con = f.getConnection(filepath + "\\" + Filename);
                Connection con = f.getConnection(filepath);
                String strQuery = "select  * from " + sheetname + " where Scenario_Type ='" + Scenario_Type + "'";
                //System.out.println(strQuery);
                Recordset rs = con.executeQuery(strQuery);
                //System.out.println("Record Count--getvalues --> " + rs.getCount());
                System.out.println("List of Columns in " + sheetname + rs.getFieldNames());
                while (rs.next()) {
                    count++;
                }
                System.out.println("Map of Column and values:" + rows);
                rs.close();
                con.close();

            } catch (FilloException e) {
                System.out.println(e.getMessage());
                //e.printStackTrace();
            }
        } else {
            System.out.println("Value does not exist in Datasheet");
            //value = "Value does not exist in Datasheet";
        }
        System.out.println("========================================================");
        return count;

    }


    public static Boolean ValidateScenario(String filepath, String sheetname, String Scenario_Type) {
        Fillo f = new Fillo();
        Boolean value = true;
        try {
            System.out.println("========================================================");
            Connection con = f.getConnection(filepath);
            //Connection con = f.getConnection(filepath);
            String strQuery = "select  scenario_type from " + sheetname + " where scenario_type ='" + Scenario_Type + "'";
            System.out.println(strQuery);
            Recordset rs = con.executeQuery(strQuery);
            System.out.println("Record Count --> " + rs.getCount());
            while (rs.next()) {
                //System.out.println("Column_name : "+ rs.getField(Scenario_Type));
            }
            rs.close();
            con.close();
        } catch (FilloException e) {
            
            System.err.println("No Record Found in Datasheet :  Check the sheet OR Parameters");
            value = false;
            //e.printStackTrace();
        }

        return value;

    }

    public static void createPropertyfile(String feature) throws IOException {
        Properties properties = new Properties();
        String Directory = USER_DI + "/src/test/resources/UserJourney/" + feature;
        String property_path2 = USER_DI + "/src/test/resources/UserJourney/" + feature + "/" + feature + ".properties";
        File file = new File(Directory);
        Boolean filecreated = file.mkdir();
        if(filecreated == true)
        {
            App_genericFunction.putcommentinStep("Directory created successfully");
        }else
        {
            App_genericFunction.putcommentinStep("Directory not created successfully");
        }
        File file1 = new File(property_path2);
        FileOutputStream fileOut = new FileOutputStream(file1);
        properties.store(fileOut, "---ScenarioContext---");
        fileOut.close();
    }


    public static void WritePropertiesFile_common(String key, String value) {
        FileInputStream fileIn = null;
        FileOutputStream fileOut = null;
        try {
           // System.out.println("WriteProperty File Value: " + key + ": " + value);
            File file = new File(property_path_1);
            //System.out.println(file);
            Properties properties = new Properties();
            fileIn = new FileInputStream(file);
            properties.load(fileIn);
            properties.setProperty(key, value);

            fileOut = new FileOutputStream(file);
            properties.store(fileOut, "ScenarioContext");
            fileOut.close();
            fileIn.close();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        //properties.store(fileOut, "TexContext");

    }

    public static void WritePropertiesFile_feature(String key, String value) {
        FileInputStream fileIn = null;
        FileOutputStream fileOut = null;
        try {
            // System.out.println("WriteProperty File Value: " + key + ": " + value);
            File file = new File(property_path_2 +GlobalHooks.Featureid+"/"+GlobalHooks.Featureid+".properties");
            //System.out.println(file);
            Properties properties = new Properties();
            fileIn = new FileInputStream(file);
            properties.load(fileIn);
            properties.setProperty(key, value);

            fileOut = new FileOutputStream(file);
            properties.store(fileOut, "ScenarioContext");
            fileOut.close();
            fileIn.close();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        //properties.store(fileOut, "TexContext");

    }

    public static Properties ReadPropertiesFile_all() {
        String Value = null;
        Properties properties=null;
        try {

            //File file = new File(USER_DI+"/src/test/resources/TestData/MortgageDataSheets/TextContext.properties");
            File file = new File(property_path_1);
            //File file = new File(USER_DI+"\\src\\test\\resources\\TestData\\MortgageDataSheets\\RPBSLMPORTNTOPUP\\RPBSLMPORTNTOPUP.properties");
            FileInputStream fileInput = new FileInputStream(file);
             properties  = new Properties();
            properties.load(fileInput);
            //Value = properties.getProperty(Key);
            //System.out.println("Value from Property file:" + Value);
            fileInput.close();

        } catch (IOException e) {
            
            e.printStackTrace();
        }
        //properties.store(fileOut, "TexContext");
        return properties;
    }

    public static String ReadPropertiesFile_feature(String Key) {
        String Value = null;

        try {
            File file = new File(property_path_2 +GlobalHooks.Featureid+"/"+GlobalHooks.Featureid+".properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            Value = properties.getProperty(Key);
            //System.out.println("Value from Property file:" + Value);
            fileInput.close();

        } catch (IOException e) {
            
            e.printStackTrace();
        }
        //properties.store(fileOut, "TexContext");
        return Value;

    }
    public static String ReadPropertiesFile_common(String Key) {
        String Value = null;

        try {

            //File file = new File(USER_DI+"/src/test/resources/TestData/MortgageDataSheets/TextContext.properties");
            File file = new File(property_path_1);
            //File file = new File(USER_DI+"\\src\\test\\resources\\TestData\\MortgageDataSheets\\RPBSLMPORTNTOPUP\\RPBSLMPORTNTOPUP.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            Value = properties.getProperty(Key);
            //System.out.println("Value from Property file:" + Value);
            fileInput.close();

        } catch (IOException e) {
            
            e.printStackTrace();
        }
        //properties.store(fileOut, "TexContext");
        return Value;

    }
    public static String ReadPropertiesFile(String Key) {
        String Value = null;

        try {

            //File file = new File(USER_DI+"/src/test/resources/TestData/MortgageDataSheets/TextContext.properties");
            File file = new File(property_path);
            //File file = new File(USER_DI+"\\src\\test\\resources\\TestData\\MortgageDataSheets\\RPBSLMPORTNTOPUP\\RPBSLMPORTNTOPUP.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            Value = properties.getProperty(Key);
            //System.out.println("Value from Property file:" + Value);
            fileInput.close();

        } catch (IOException e) {
            
            e.printStackTrace();
        }
        //properties.store(fileOut, "TexContext");
        return Value;

    }

    public static String getFeatureName(Scenario scenario) {
        //String featureName = "Feature ";
        System.out.println(scenario.getId());
       // String rawFeatureName = scenario.getId().split(";")[0].replace("-", " ");
        String rawFeatureName = scenario.getId().split("\\.")[0];
        int lenghtofString = rawFeatureName.length();
        int index = rawFeatureName.lastIndexOf("/");
        String featureName = rawFeatureName.substring(index+1,lenghtofString);
      //  String featureName = rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1);

        return featureName;
    }

    public static Boolean UpdateValue(String filepath, String Filename, String sheetname, String Scenario_Type, String Column, String Column_Value) {
        Fillo f = new Fillo();
        // if(ValidateScenario(filepath,Filename,sheetname,Scenario_Type))
        {
            try {

                //String filepath = "C:\\\\Users\\pratekug\\Desktop\\CucumberDatasheet\\Scenario.xlsx";
                Connection con = f.getConnection(filepath);
                System.out.println("Before:");
                // ValidateUpdate(Filename, sheetname, Scenario_Type, Column,Column_Value);
                System.out.println("========================================================");
                String strQuery1 = "Update " + sheetname + " Set " + Column + "='" + Column_Value + "' where Scenario_Type ='" + Scenario_Type + "'";
                System.out.println(strQuery1);
                //System.out.println(strQuery);
                con.executeUpdate(strQuery1);
                System.out.println("========================================================");

                // Connection con1 = f.getConnection(filepath + "\\" + Filename);
                // Recordset rs = con1.executeQuery(strQuery);
                //System.out.println("Record Count--getvalues --> " + rs.getCount());
                //String Column_value = rs.getField(Column).toString();
                System.out.println("After:");
//                if(ValidateUpdate(Filename, sheetname, Scenario_Type, Column,Column_Value))
//                {
//                    System.out.println("Insert Happened Successfully");
//                }else
//                {
//                    System.out.println("Value Not Updated Successfully at: " +Column );
//                }

                //rs.close();
                con.close();

            } catch (FilloException e) {
                
                System.out.println(e.getMessage());
                System.out.println("Exception : Insert Not Happened");
                //e.printStackTrace();
                return false;
            }
        }//else
//        {
//            System.out.println("Value does not exist in Datasheet");
//            //value = "Value does not exist in Datasheet";
//        }
        System.out.println("========================================================");
        return true;

    }

    public static Boolean ValidateUpdate(String Filename, String sheetname, String Scenario_Type, String Column, String Column_value) {
        Fillo f = new Fillo();
        Boolean value = true;
        try {
            System.out.println("========================================================");
            String filepath = USER_DI + "\\src\\test\\resources\\TestData\\MortgageDataSheets";
            Connection con = f.getConnection(filepath + "\\" + Filename);
            String strQuery = "select  * from " + sheetname + " where Scenario_Type ='" + Scenario_Type + "'";
            System.out.println(strQuery);
            Recordset rs = con.executeQuery(strQuery);
            System.out.println("Record Count --> " + rs.getCount());
            while (rs.next()) {
                //System.out.println("Column_name : "+ rs.getField(Scenario_Type));
            }
            System.out.println("Column Name : " + Column);
            if (Column_value.contentEquals(rs.getField(Column).toString())) {
                System.out.println("Column Value : " + rs.getField(Column).toString());
            } else {
                System.out.println("Column Value : " + rs.getField(Column).toString());
                return false;
            }

            rs.close();
            con.close();
        } catch (FilloException e) {
            
            System.err.println("Value Not Updated in :" + Filename + "  " + "  Check the sheet OR Parameters");
            value = false;
            //e.printStackTrace();
        }

        return value;

    }

    public void scrolldownend_page() {
        JavascriptExecutor runJS = ((JavascriptExecutor) selenium_helper.driver);
        runJS.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void action_force(String w, String xw) {
        WebElement web = selenium_helper.driver.findElement(By.id((w.split(":")[1])));
        Actions a = new Actions(selenium_helper.driver);
        a.sendKeys(w, xw);
    }

    public String browsername_Report() {
        Capabilities cap = ((RemoteWebDriver) selenium_helper.getdriver()).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        System.out.println(browserName);
        return browserName;
    }

    public static String ReadBootstrap_static(String env) {
        String PreEnv = null;

        try {

            File file = new File(bootstrap_path);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            PreEnv = properties.getProperty(env);
            //System.out.println("Value from Property file:" + PreEnv);
            fileInput.close();

        } catch (IOException e) {
            
            e.printStackTrace();
        }
        //properties.store(fileOut, "TexContext");
        return PreEnv;
    }

    public static void putScreenshotInStep() {
        if (ReadBootstrap_static("screenShotTotake").equalsIgnoreCase("true")) {
            byte[] screenshot = ((TakesScreenshot) SeleniumHelper.getdriver()).getScreenshotAs(OutputType.BYTES);
            GlobalHooks.scenario_screenshot.embed(screenshot, "image/png");  // Stick it in the report
            //screenshot_extent.takeScreenShot();
        }
    }

    public static void putcommentinStep(Object value) {
        System.out.println(value);
          GlobalHooks.scenario_screenshot.write(value.toString());

    }

    public static Response getDatafromDatabase(String Validation_number,String Account_no) throws JSONException {

        RestAssured.baseURI ="http://10.6.184.139:10008/Intellect/rest/microServices";
        LinkedHashMap<String,String> requestParams = new LinkedHashMap<>();
        requestParams.put("MTI", "9200"); // Cast
        requestParams.put("PC", "100000");
        requestParams.put("TDT", "11092013113525");
        requestParams.put("SSIC", "CBX");

        LinkedHashMap<String,String> requestParams1= new LinkedHashMap<>();
        requestParams1.put("USERID", "IDCADMIN");
        requestParams1.put("AccountNumber", Account_no);
        requestParams1.put("Validation", Validation_number);

        JSONObject Body = new JSONObject();
        Body.put("Header",requestParams);
        Body.put("Payload",requestParams1);

        JSONObject Body1 = new JSONObject();
        Body1.put("RequestMessage",Body);


       // Response res =RestAssured.given().log().all()
        Response res =RestAssured.given()
                .config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false))).relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                //.header("Content-Type","application/json")
              /*  .header("User-Agent", "PostmanRuntime/7.24.0")
                .header("Accept-Encoding","gzip, deflate, br")*/
                .body(Body1.toString())
                .when()
                .post("/idcrest")
                .then().assertThat().log().all().contentType(ContentType.XML).extract().response();
                //.then().assertThat().contentType(ContentType.fromContentType("text/html; charset=UTF-8")).extract().response();
        App_genericFunction.putcommentinStep("======================================Response from Database======================================");
        App_genericFunction.putcommentinStep("Response :" + res.asString());
        App_genericFunction.putcommentinStep("Status Code :" + res.getStatusCode());
        App_genericFunction.putcommentinStep("Does Reponse contains 'ResponseMessage'? :" + res.asString().contains("ResponseMessage"));
        App_genericFunction.putcommentinStep("==================================================================================================");

        return res;
    }

    public static Response getDatafromDatabase_transaction(String Validation_number,String Account_no,String FromDate,String UptoDate,String StmntType,String CurrencyCode) throws JSONException {

        RestAssured.baseURI ="http://10.6.184.139:10008/Intellect/rest/microServices";
        LinkedHashMap<String,String> requestParams = new LinkedHashMap<>();
        requestParams.put("MTI", "9200"); // Cast
        requestParams.put("PC", "100000");
        requestParams.put("TDT", "11092013113525");
        requestParams.put("SSIC", "CBX");

        LinkedHashMap<String,String> requestParams1= new LinkedHashMap<>();
        requestParams1.put("USERID", "IDCADMIN");
        requestParams1.put("AccountNumber", Account_no);
        requestParams1.put("CurrencyCode", CurrencyCode);
        requestParams1.put("FromDate", FromDate);
        requestParams1.put("UptoDate", UptoDate);
        requestParams1.put("StmntType", StmntType);
        requestParams1.put("Validation", Validation_number);

        JSONObject Body = new JSONObject();
        Body.put("Header",requestParams);
        Body.put("Payload",requestParams1);

        JSONObject Body1 = new JSONObject();
        Body1.put("RequestMessage",Body);


        // Response res =RestAssured.given().log().all()
        Response res =RestAssured.given()
                .config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false))).relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                //.header("Content-Type","application/json")
                /*  .header("User-Agent", "PostmanRuntime/7.24.0")
                  .header("Accept-Encoding","gzip, deflate, br")*/
                .body(Body1.toString())
                .when()
                .post("/idcrest")
                //.then().assertThat().log().all().contentType(ContentType.XML).extract().response();
                .then().assertThat().contentType(ContentType.XML).extract().response();
        App_genericFunction.putcommentinStep("======================================Response from Database======================================");
        App_genericFunction.putcommentinStep("Response :" + res.asString());
        App_genericFunction.putcommentinStep("Status Code :" + res.getStatusCode());
        App_genericFunction.putcommentinStep("Does Reponse contains 'ResponseMessage'? :" + res.asString().contains("ResponseMessage"));
        App_genericFunction.putcommentinStep("==================================================================================================");

        return res;
    }


    public  String getintoCAInternetBanking(String URL,String Customer_no ,String Pac_code,String Password) {
        try{
        SeleniumHelper.getdriver().manage().deleteAllCookies();
        selenium_helper.maximizebrowser();
            /*if(GlobalHooks.resource_API.contains("Token_2"))
            {
                navigationHelper.navigateTo(URL);
            }
             else */
            if(Configuration.getConfiguration().getProperty("environment").startsWith("Dev"))
            {
                navigationHelper.navigateTo(LoadProperties.CAOB_URL);
            }else if (Configuration.getConfiguration().getProperty("environment").startsWith("UAT"))
            {
                navigationHelper.navigateTo(LoadProperties.CAOB_URL_UAT);
            }else if (Configuration.getConfiguration().getProperty("environment").startsWith("Pre"))
            {
                navigationHelper.navigateTo(LoadProperties.CAOB_URL_PRE);
            }


       /* String mainWindow =seleniumHelper.getdriver().getWindowHandle();
        System.out.println(mainWindow);

        Set<String> AllWindow =seleniumHelper.getdriver().getWindowHandles();
        System.out.println(AllWindow.size());
        Iterator<String> itr= AllWindow.iterator();
        while(itr.hasNext()){
            String childWindow=itr.next();
// Compare whether the main windows is not equal to child window. If not equal, we will close.
            if(!mainWindow.equals(childWindow)) {
              WebDriver driver1 = seleniumHelper.getdriver().switchTo().window(childWindow);
                System.out.println(driver1.getWindowHandle());
                 Actions a = new Actions(driver1);
                 a.clickAndHold(seleniumHelper.getElement("xpath://*[@id='userNo']")).sendKeys("74980088").build().perform();
            }
        }*/
        selenium_helper.waitForJSandJQueryToLoad();
       SeleniumHelper.getdriver().findElement(By.xpath("//*[@class='btn acceptAllCookie']")).click();
            selenium_helper.waitForJSandJQueryToLoad();
        Actions a = new Actions(selenium_helper.getdriver());
        //a.clickAndHold(selenium_helper.getElement("xpath://*[@id='userNo']")).sendKeys(GlobalHooks.values.get(0).get("customer_no")).build().perform();
            a.clickAndHold(selenium_helper.getElement("xpath://*[@id='userNo']")).sendKeys(Customer_no).build().perform();
        selenium_helper.enter("xpath://*[@id='LOGIN']");
        selenium_helper.waitForPageToLoad();

        if(selenium_helper.isElementDisplayed("xpath://*[@class='jconfirm-buttons']/button[1]")){
            selenium_helper.enter("xpath://*[@class='jconfirm-buttons']/button[1]");
            Thread.sleep(10000);
        }
        System.out.println(selenium_helper.getdriver().getTitle());

        App_genericFunction.putScreenshotInStep();

        List<String> list_c = new LinkedList<>();
        //String PAC_values = GlobalHooks.values.get(0).get("pac_no");
        String PAC_values = Pac_code;
        char[] PAC_code = PAC_values.toCharArray();
        for (char c : PAC_code) {
            list_c.add(Character.toString(c));
        }

        for (int i = 0; i < 6; i++) {
            if (selenium_helper.getElement("xpath://*[@class='useridfield']/div/div[" + (i + 1) + "]").getAttribute("class").contentEquals("box")) {
                selenium_helper.enterText("xpath://*[@class='useridfield']/div/div[" + (i + 1) + "]/input", list_c.get(i).toString());
            }
        }
        //selenium_helper.enterText("xpath://*[@class='useridfield'][2]/input", GlobalHooks.values.get(0).get("password"));
            selenium_helper.enterText("xpath://*[@class='useridfield'][2]/input", Password);
        selenium_helper.enter("xpath://*[@id='LOGIN']");
            App_genericFunction.putScreenshotInStep();
        System.out.println("Portal Connect Successful");
        return "Portal Connect Successful";

    }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Portal connect Not Successfull");
            return "Portal connect Not Successfull";
        }
    }
    public  String getintoAuthPortal(String URL,String Customer_no ,String Pac_code,String Password) {
        try{
            SeleniumHelper.getdriver().manage().deleteAllCookies();
            selenium_helper.maximizebrowser();
            if(GlobalHooks.resource_API.contains("Token_2"))
            {
                navigationHelper.navigateTo(URL);
            }
            /* else if(Configuration.getConfiguration().getProperty("environment").startsWith("Dev"))
            {
                navigationHelper.navigateTo(LoadProperties.CAOB_URL);
            }else if (Configuration.getConfiguration().getProperty("environment").startsWith("Pre"))
            {
                navigationHelper.navigateTo(LoadProperties.CAOB_URL_PRE);
            }*/

            selenium_helper.waitForPageToLoad();
            Actions a = new Actions(selenium_helper.getdriver());
            //a.clickAndHold(selenium_helper.getElement("xpath://*[@id='userNo']")).sendKeys(GlobalHooks.values.get(0).get("customer_no")).build().perform();
            a.clickAndHold(selenium_helper.getElement("xpath://*[@id='username']")).sendKeys(Customer_no).build().perform();
            selenium_helper.enter("xpath://*[@id='LOGIN']");
            selenium_helper.waitForPageToLoad();
            if(selenium_helper.isElementDisplayed("xpath://*[@class='jconfirm-buttons']/button[1]")){
                selenium_helper.enter("xpath://*[@class='jconfirm-buttons']/button[1]");
                Thread.sleep(10000);
            }
            System.out.println("Page2:"+selenium_helper.getdriver().getCurrentUrl());

            List<String> list_c = new LinkedList<>();
            //String PAC_values = GlobalHooks.values.get(0).get("pac_no");
            String PAC_values = Pac_code;
            char[] PAC_code = PAC_values.toCharArray();
            for (char c : PAC_code) {
                list_c.add(Character.toString(c));
            }

            int size =selenium_helper.getdriver().findElements(By.xpath("//*[@class='useridfield1']/div/div")).size();
            System.out.println("Size : "+size );
            for (int i = 1; i < 7; i++) {
                if (selenium_helper.getElement("xpath://*[@class='useridfield1']/div/div[" + (i + 1) + "]").getAttribute("class").contentEquals("box")) {
                    Thread.sleep(1000);
                    selenium_helper.enter("xpath://*[@class='useridfield1']/div/div[" + (i + 1) + "]/input");
                    selenium_helper.sendkeysenterText("xpath://*[@class='useridfield1']/div/div[" + (i + 1) + "]/input", list_c.get(i-1).toString());
                }
            }
            //selenium_helper.enterText("xpath://*[@class='useridfield'][2]/input", GlobalHooks.values.get(0).get("password"));
            selenium_helper.enterText("xpath://*[@class='useridfield']/input", Password);
            selenium_helper.enter("xpath://*[@id='LOGIN']");
            selenium_helper.waitForPageToLoad();
            Thread.sleep(15000);
            System.out.println("Page3:"+selenium_helper.getdriver().getCurrentUrl());
            selenium_helper.waitForJSandJQueryToLoad();
            System.out.println("Portal Connect Successful");
            return "Portal Connect Successful";

        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Portal connect Not Successfull");
            return "Portal connect Not Successfull";
        }
    }

    public static void OptionalField(String field ,String Expected,String Actual)
    {
        if(Expected.isEmpty())
        {
           putcommentinStep("OptionalfieldIgnored : "+ field);
        }else
        {
            Assert.assertEquals("Optional field Test Failed : "+field,Expected,Actual );
        }
    }
    public static List<String> ListofValues (String Values)
    {
        if(Values.contains(","))
        {
            String [] temp = Values.split(",");
            return Arrays.asList(temp);

        }
        else
        {
            List<String> temp = new ArrayList<String>();
            temp.add(Values);
            return temp;
        }

    }
    public Boolean ReularExpressioncheck(String regex,String Value)
    {
        boolean b3 = Pattern.matches(regex,Value);
        return b3;
    }
    public void validateReularExpression(String regex,String Value)
    {
        if(ReularExpressioncheck(regex,Value))
        {
            System.out.println("regeex : " + regex );
            System.out.println("regeex_value : " + Value );
        }else
        {
            System.out.println("regeex : " + regex );
            System.out.println("regeex_value : " + Value );
            Assert.fail("Regular Expression check Failed");
        }
    }

    public  void generateclients_usingrefreshtokens(PublicKey pubKey, PrivateKey priKey, String[] tokens,String Client_id)
    {
        List<String> refresh_tokens = Arrays.asList(tokens);
        Response response;
        JsonPath JsonPathEvaluator;
        int i=1;
        String jsonString;
        String grant_type ="refresh_token";
        String Audience ="https://developer.caterallen.co.uk/token";
        String client_assertion_type ="urn:ietf:params:oauth:client-assertion-type:jwt.java-bearer";
        String redirect_uri ="https://caterallen.co.uk";

        for(String refresh_token : refresh_tokens)
        {
            try
            {
            String jwt_token =jwt.generateJwtToken_CAT(priKey,pubKey,Audience,Client_id);
           // App_genericFunction.putcommentinStep("JWT TOken CAT : "+jwt_token);
            jsonString = "client_id="+Client_id +
                    "&grant_type="+ grant_type +
                    "&refresh_token="+refresh_token +
                    "&client_assertion_type="+ client_assertion_type +
                    "&client_assertion=" + jwt_token +
                    "&redirect_uri="+redirect_uri;
           // App_genericFunction.putcommentinStep(" Request as raw-text for CAT : " + jsonString);
           // jwt.java.printStructure_cucumber_report(jwt_token,pubKey);

                response = APIMethods.method_post_raw("https://developer.caterallen.co.uk/",jsonString,"token");
                //System.out.println(response.prettyPrint());
                System.out.println("============================================================================================================");
                JsonPathEvaluator= JsonUtilities.verifyResponse(response);
                String Print = "Client_access_token_"+i;
                App_genericFunction.WritePropertiesFile_common(Print,JsonPathEvaluator.get("access_token"));
                //App_genericFunction.putcommentinStep("Client_access_token"+i+" : " +JsonPathEvaluator.get("access_token"));
                System.out.println(Print+" : " +JsonPathEvaluator.get("access_token"));
                i++;
            }catch(Exception e)
            {
                e.getMessage();
            }

        }
    }
}




