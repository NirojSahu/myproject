/*
package Utilities;

import Utilities.WebKit.annotations.Page;
import Utilities.seleniumcustomframework.extension.PageElement;
import Utilities.WebKit.helper.TimeoutHelper;
import Utilities.WebKit.exceptions.UnexpectedPageException;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class BasePage {
    private static final Logger LOG=Logger.getLogger(BasePage.class.getName());
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT=60000;
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT_SECS=20;
    private static final long WAIT_FOR_PAGE_LOADED_DELAY=20000L;
    private static final long DRIVER_WAIT_TIME=60L;
    public static String absPath=(new File("")).getAbsolutePath().toString();
    @Inject
    public WebDriver driver;
    @Inject
    public PageFactory pageFactory;
    @Inject
    @Named("browser")
    protected String browser;
    @Inject
    @Named("environment")
    protected String environment;
    String parentWindow =null;
    @Inject
    private Injector injector;

    public BasePage(){
    }
    public boolean isDisplayed(){
        try {
            return false;
        }catch (StaleElementReferenceException ver2){
            return false;
        }
    }
    public void load(){
        String pageUrl="https://google.co.in";
        this.driver.navigate().to(pageUrl);
    }
    public void submit() throws TimeoutException {
        throw new UnexpectedPageException("%s Page has not been displayed %n");
    }
    public void continueIfExists(){
        try{
            this.waitFor(5000);
            this.submit();
        } catch (TimeoutException var2) {
            System.out.printf("%s page has not been displayed%n",this);
        }
    }
    public void assertPage(){
        String expectedPage=this.getPageFromAnnotation();
        String actualPage=this.getPageID();
        if(!expectedPage.equals(actualPage)){
            throw new UnexpectedPageException(String.format("Unexpected page displayed: \n\tExpected: %s\n\tbut got: %s",expectedPage,actualPage));
        }
    }
    public String getPageId(){
        return null;
    }
    private List<Page> getPageAnnotations(Class klass){
        List<Page> annotations=new ArrayList<>();
        Page pageAnnotation=(Page)klass.getAnnotation(Page.class);
        if (null!=pageAnnotation){
            annotations.add(pageAnnotation);
        }
        if (null!=klass.getSuperclass()){
            annotations.addAll(this.getPageAnnotations(klass.getSuperclass()));
        }
        return annotations;
    }
    private String getPageIdFromAnnotation(){
        List<Page> pageAnnotations=this.getPageAnnotations(this.getClass());
        Iterator var2=pageAnnotations.iterator();

        Page pageAnnotation;
        do {
            if (!var2.hasNext()){
                return "";
            }
            pageAnnotation=(Page)var2.next();
        }while (pageAnnotation.id().isEmpty());
        return pageAnnotation.id();
    }
    public void waitFor(Integer timeout){
        TimeoutHelper.withTimeout(timeout, new Runnable() {
            public void run() {
                while (!BasePage.this.getPageIdFromAnnotation().equals(BasePage.this.getPageId())){
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException var2) {
                       BasePage.LOG.fine(var2.getMessage());
                    }
                }
            }
        });
    }
    public void waitFor(){
        this.waitFor(60000);
    }
    protected String getReativePath(){
        Page pageAnnotation=(Page)this.getClass().getAnnotation(Page.class);
        return pageAnnotation==null?"":pageAnnotation.path();
    }
    public void continueNext(){
        String.format("Need to implement in child class: %s", this.getClass().getName());
    }
    public void waitForPageToBecomeStable(PageElement element){

    }

}
*/
