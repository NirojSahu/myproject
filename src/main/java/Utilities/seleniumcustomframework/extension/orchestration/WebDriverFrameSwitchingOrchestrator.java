package Utilities.seleniumcustomframework.extension.orchestration;

import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import Utilities.seleniumcustomframework.extension.helpers.FrameWeapper;
import com.google.inject.Inject;
import groovy.lang.Singleton;
import org.openqa.selenium.WebDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class WebDriverFrameSwitchingOrchestrator {
    private static final Logger LOG=Logger.getLogger(WebDriverFrameSwitchingOrchestrator.class.getName());
    private FrameWeapper frame;
    @Inject
    private DependencyInjector dependencyInjector=null;

    public WebDriverFrameSwitchingOrchestrator(){
    }

    public void useFrame(FrameWeapper frame){
        LOG.log(Level.FINE,"Called use frame with {0}", frame);
        if (frame==null){
            this.useDefault();
        }else if (this.frame==null || !this.frame.equals(frame)){
            this.useDefault(true);
            this.frame=frame;
            frame.use();
        }
    }
    private void useDefault(){
        this.useDefault(false);
    }
    private void useDefault(boolean force){
        if (force || this.frame !=null){
            this.frame=null;
            LOG.log(Level.FINE,"Switching to default content");
            ((WebDriver)this.dependencyInjector.get(WebDriver.class)).switchTo().defaultContent();
        }
    }
}
