package Utilities.seleniumcustomframework.extension;

import Utilities.seleniumcustomframework.extension.handlers.Refreshable;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageSection implements SearchContext, Refreshable,WebElementExtensions {
    private static final Integer DEFOULT_WAIT_TIMEOUT=20000;
    protected PageElement rootElement;
    private Refreshable parent;
    public PageSection() {
    }
    
    public boolean isPresent() {
        return this.rootElement.isPresent();
    }

    public String getValue(){
        return this.rootElement.getValue();
    }
    
    public void set(String value){
        this.rootElement.set(value);
    }

    public String getHiddenText() {
        return this.rootElement.getValue();
    }

    public void set(String format, Object... args) {
        this.rootElement.set(format,args);
    }

    public void doubleClick() {
        this.rootElement.doubleClick();
    }

    public void dropOnto(PageElement pageElement) {
        this.rootElement.doubleClick();
    }

    public PageElement waitFor(Integer timeout) {
        return this.rootElement.waitFor(timeout);
    }

    public PageElement waitFor() {
        return this.rootElement.waitFor();
    }

    public PageElement waitUntilGone(Integer timeout) {
        this.rootElement.waitUntilGone(timeout);
        return null;
    }

    public void waitUntilGone() {
        this.waitUntilGone(DEFOULT_WAIT_TIMEOUT);
    }

    public PageElement waitUntilHidden(Integer timeout) {
        return this.waitUntilHidden(timeout);
    }

    public PageElement waitUntilHidden() {
        return this.rootElement.waitUntilHidden();
    }

    public PageElement waitUntilVisible(Integer timeout) {
        return this.rootElement.waitUntilVisible(timeout);
    }

    public PageElement waitUntilVisible() {
        return this.rootElement.waitUntilVisible();
    }

    public PageElement waitUntilStopMoving(Integer timeout) {
        return this.rootElement.waitUntilVisible(timeout);
    }

    public PageElement waitUntilStopMoving() {
        return this.rootElement.waitUntilStopMoving();
    }
    public void setParent(Refreshable refreshable) {
        this.parent=refreshable;
    }

    public List<WebElement> findElements(By by) {
        return this.rootElement.findElements(by);
    }

    public WebElement findElement(By by) {
        return (WebElement) this.rootElement.findElements(by);
    }

    public void invalidate() {
       this.parent.invalidate();
    }

    public void refresh() {
        this.parent.refresh();
    }
}
