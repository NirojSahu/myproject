//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.helper;

//import com.test.configuration.Configuration;
//import com.test.exceptions.StopTestException;
import Utilities.WebKit.configuration.Configuration;
import Utilities.WebKit.exceptions.StopTestException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;

public class PropertiesHelper {
    public static String absPath = (new File("")).getAbsolutePath().toString();
    public static Properties properties;

    public PropertiesHelper() {
    }

    public static HashMap<String, String> getSeleniumProperties() throws StopTestException {
        HashMap<String, String> props = new HashMap();
        props.putAll(extractPropertiesFromProperties("selenium.", Configuration.getConfiguration().getProperties()));
        props.putAll(extractPropertiesFromEnvironment("SELENIUM_"));
        return props;
    }

    public static HashMap<String, String> extractPropertiesFromEnvironment(String prefix) throws StopTestException {
        HashMap<String, String> props = new HashMap();
        Iterator var2 = System.getenv().entrySet().iterator();

        while(var2.hasNext()) {
            Entry<String, String> object = (Entry)var2.next();
            String keyName = (String)object.getKey();
            props.putAll(collect(prefix, keyName, (String)object.getValue()));
        }

        return props;
    }

    public static HashMap<String, String> extractPropertiesFromProperties(String prefix, Properties properties) throws StopTestException {
        HashMap<String, String> props = new HashMap();
        Iterator var3 = properties.entrySet().iterator();

        while(var3.hasNext()) {
            Entry<Object, Object> object = (Entry)var3.next();
            String keyName = object.getKey().toString();
            props.putAll(collect(prefix, keyName, object.getValue().toString()));
        }

        return props;
    }

    public static HashMap<String, String> extractPropertiesFromSystemProperties(String prefix) throws StopTestException {
        return extractPropertiesFromProperties(prefix, System.getProperties());
    }

    private static HashMap<String, String> collect(String prefix, String keyName, String value) throws StopTestException {
        HashMap<String, String> stringStringHashMap = new HashMap();
        if (!keyName.startsWith(prefix)) {
            return stringStringHashMap;
        } else {
            String sanitisedKeyName = sanitiseKeyName(keyName, prefix);
            ArrayList<String> excludedSeleniumProperties = Configuration.getConfiguration().getExcludedSeleniumProperties();
            if (!excludedSeleniumProperties.contains(sanitisedKeyName)) {
                stringStringHashMap.put(sanitisedKeyName, value);
            }

            return stringStringHashMap;
        }
    }

    private static String sanitiseKeyName(String keyName, String prefix) {
        String desiredKey = keyName.replace(prefix, "");
        return com.test.helper.StringHelper.toCamelCase(desiredKey);
    }

    public static void loadRunConfigProps() {
        try {
            System.out.print("loading.........\n");
            properties = new Properties();
            String separator = System.getProperty("file.separator");
            String environment = Configuration.getConfiguration().getEnvironment();
            String project = Configuration.getConfiguration().getProjectName();
            System.out.print("\n Environment Types  DEV , CERT , CERT2 , PRE , PRE2 , PROD");
            System.out.print("\n Script running on environment..." + environment);
            System.out.print("\n project>>" + project);
            String profilePath = absPath + separator + "src" + separator + "test" + separator + "resources" + separator + project + separator + "profiles" + separator + environment + separator + "config.properties";
            System.out.print("\n profilePath>>" + profilePath);
            EnvironmentConstants.isCertEnvironment = environment.equalsIgnoreCase(EnvironmentConstants.CERT) || environment.equalsIgnoreCase(EnvironmentConstants.CERT2);
            EnvironmentConstants.isPreEnvironment = environment.equalsIgnoreCase(EnvironmentConstants.PRE) || environment.equalsIgnoreCase(EnvironmentConstants.PRE2);
            EnvironmentConstants.isDevEnvironment = environment.equalsIgnoreCase(EnvironmentConstants.DEV);
            EnvironmentConstants.isProdEnvironment = environment.equalsIgnoreCase(EnvironmentConstants.PROD);
            InputStream input = new FileInputStream(profilePath);
            properties.load(input);
        } catch (StopTestException | IOException var5) {
        }

    }
}
