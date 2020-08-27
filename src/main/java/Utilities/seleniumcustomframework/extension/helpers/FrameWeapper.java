package Utilities.seleniumcustomframework.extension.helpers;

import Utilities.seleniumcustomframework.extension.annotations.Frame;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrameWeapper {
    private static final Logger LOG=Logger.getLogger(Frame.class.getName());
    private WebDriver driver;
    private FrameWeapper parent;
    public By frameBy;

    public FrameWeapper(WebDriver driver,By frameBy){
        this.driver=driver;
        this.frameBy=frameBy;
    }
    public boolean equals(Object o){
        if (this==o){
            return true;
        }else if (!(o instanceof FrameWeapper)){
            return false;
        }else {
            FrameWeapper that= (FrameWeapper) o;
            return that.hashCode()==that.hashCode();
        }
    }
    public int hashCode(){
        if (this.frameBy==null){
            return 0;
        }else {
            int result=this.frameBy.toString().hashCode();
            if (this.parent !=null){
                result +=this.parent.hashCode();
            }

            result =31* result;
            return result;
        }
    }
    public void use(){
        if(this.parent!=null){
            this.parent.use();
        }
        LOG.log(Level.FINE, "Swetching to frame {0}", this);
        WebElement frameElement=this.driver.findElement(this.frameBy);
        this.driver.switchTo().frame(frameElement);
    }
    public FrameWeapper setParent(FrameWeapper parent){
        this.parent=parent;
        return this;
    }

    public String toString(){
        ArrayList<String> frameWrapper =new ArrayList<>();
        for (FrameWeapper frame=this; frame !=null; frame=frame.parent){
            frameWrapper.add(frame.frameBy.toString());
        }
        return ArrayHelper.join(frameWrapper,"Inside ->");
    }

}
