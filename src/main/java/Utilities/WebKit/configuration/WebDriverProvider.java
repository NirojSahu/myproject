//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.configuration;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.test.exceptions.StopTestException;
import java.io.IOException;
import java.net.URISyntaxException;
import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.WebDriver;

public class WebDriverProvider implements Provider<WebDriver> {
    @Inject
    public Configuration config;
    @Inject
    @Named("browser")
    private String browser;

    public WebDriverProvider() {
    }

    public WebDriver get() {
        try {
            if (this.config.getGridRun().equalsIgnoreCase("true")) {
                return PageFactory.getRemoteWebDriver(this.browser);
            }

            if (this.browser.equalsIgnoreCase("chrome")) {
                return PageFactory.getChromeWebDriver();
            }

            if (this.browser.equalsIgnoreCase("internet explorer")) {
                return PageFactory.getIEWebDriver();
            }

            if (this.browser.equalsIgnoreCase("safari")) {
                return PageFactory.getSafariWebDriver();
            }

            if (this.browser.equalsIgnoreCase("firefox")) {
                return PageFactory.getFireFoxDriver();
            }

            if (this.browser.equalsIgnoreCase("MicrosoftEdge")) {
                return PageFactory.getEdgeDriver();
            }
        } catch (IOException | JSONException | URISyntaxException | StopTestException var2) {
            var2.printStackTrace();
        }

        return null;
    }
}
