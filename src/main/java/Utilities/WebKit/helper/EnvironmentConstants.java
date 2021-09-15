//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.helper;

import cucumber.api.Scenario;
import java.sql.Connection;

public class EnvironmentConstants {
    public static String tagId;
    public static String productType;
    public static Scenario currentScenario;
    public static Connection connection;
    public static boolean isCertEnvironment;
    public static boolean isPreEnvironment;
    public static boolean isProdEnvironment;
    public static boolean isDevEnvironment;
    public static String DEV = "DEV";
    public static String CERT = "CERT";
    public static String CERT2 = "CERT2";
    public static String PRE = "PRE";
    public static String PRE2 = "PRE2";
    public static String PROD = "PROD";

    public EnvironmentConstants() {
    }
}
