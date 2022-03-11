package com.test;

import Utilities.WebKit.exceptions.TestDataError;
import Utilities.WebKit.helper.PropertiesHelper;
//import com.test.exceptions.TestDataError;
//import com.test.helper.PropertiesHelper;

import java.io.File;


public class LoadProperties {

    public static final String NEO_URL = "https://www.google.com";
    public static final String SAN_URL = "https://www.santander.co.uk/uk/index";
    //public static final String CAOB_URL = "https://10.6.184.139:10003/iportalweb/";
    public static final String CAOB_URL = "https://10.6.184.139:10003/iportalweb/iRetail@1";
    public static final String CAOB_URL_PRE = "https://10.6.184.11:10003/iportalweb/iRetail@1";
    public static final String CAOB_URL_UAT = "https://predigitalface.intellectonlinebanking.com/iportalweb/iRetail@1";
    public static final String USERID = "userid";
    public static final String PASSWORD = "password";
    public static final String DBURL = "database.url";
    public static final String DBUSERNAME = "database.username";
    public static final String DBPASSWORD = "database.password";
    public static final String DBDRIVER = "database.driver";
    public static final String POST_GRES_DB_URL = "postgredatabase.url";
    public static final String POST_GRES_DB_USERNAME = "postgredatabase.username";
    public static final String POST_GRES_DB_PASSWORD = "postgredatabase.password";
    public static final String POST_GRES_DB_DRIVER = "postgredatabase.driver";
    public static final String HADOOP_DB_URL = "hadoopdatabase.url";

    public static String absPath = new File("").getAbsolutePath();


    /**
     * Gets the key from Config.properties related to chosen profile
     *
     * @param key
     **/

    public static String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            String value = PropertiesHelper.properties.getProperty(key);
            if (value == null) {
                throw new TestDataError("No value found in config.properties  for '" + key + "'.. ");
            }
            return value;

        }
    }

}
