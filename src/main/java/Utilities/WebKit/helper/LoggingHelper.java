//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.helper;

import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;

public class LoggingHelper {
    public LoggingHelper() {
    }

    public static void printInfo(String message) {
        print(message, "INFO");
    }

    public static void printError(String message) {
        print(message, "ERROR");
    }

    public static void printWarning(String message) {
        print(message, "WARNING");
    }

    public static void printDebug(String message) {
        printDebug(message, "DEBUG");
    }

    public static void printDebug(String message, String prefix) {
        try {
            if (Configuration.getConfiguration().isDebug() || Boolean.valueOf(System.getProperty("debug.enabled"))) {
                print(message, null != prefix ? prefix : "DEBUG");
            }
        } catch (StopTestException var3) {
            print("An error occurred trying to read the autotest-framework configuration: " + var3.getMessage(), "ERROR");
        }

    }

    public static void print(String message, String prefix) {
        System.out.println((null != prefix ? prefix : "INFO") + ": " + message);
    }
}
