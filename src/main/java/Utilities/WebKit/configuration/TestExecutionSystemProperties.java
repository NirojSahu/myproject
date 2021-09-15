//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.configuration;

import java.io.IOException;
import java.util.Properties;

public class TestExecutionSystemProperties {
    private final Properties properties = new Properties();

    public TestExecutionSystemProperties() {
        try {
            this.properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("com.test.defaults.properties"));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        this.properties.putAll(System.getProperties());
    }

    public Properties getProperties() {
        return this.properties;
    }
}
