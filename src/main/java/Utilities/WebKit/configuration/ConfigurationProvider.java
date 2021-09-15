//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.configuration;

import Utilities.WebKit.exceptions.StopTestException;
import com.google.inject.Provider;

public class ConfigurationProvider implements Provider<Configuration> {
    public ConfigurationProvider() {
    }

    public Configuration get() {
        try {
            return Configuration.getConfiguration();
        } catch (StopTestException var2) {
            var2.printStackTrace();
            return null;
        }
    }
}
