package Utilities.seleniumcustomframework.extension;

import Utilities.seleniumcustomframework.extension.annotations.Frame;
import Utilities.seleniumcustomframework.extension.dependencies.*;
import Utilities.seleniumcustomframework.extension.exceptions.PageFactortError;
import Utilities.seleniumcustomframework.extension.fieldInitialisers.FieldAssessor;
import Utilities.seleniumcustomframework.extension.fieldInitialisers.FieldInitialiser;
import Utilities.seleniumcustomframework.extension.fromselenium.Annotations;
import Utilities.seleniumcustomframework.extension.handlers.Refreshable;
import Utilities.seleniumcustomframework.extension.helpers.ClassHelper;
import Utilities.seleniumcustomframework.extension.helpers.FrameWeapper;
import Utilities.seleniumcustomframework.extension.helpers.ImplementationFinder;
import Utilities.seleniumcustomframework.extension.helpers.ReflectionHelper;
import Utilities.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import com.google.inject.Inject;
import com.google.inject.Provides;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class PageFactory implements WebDriverEventListener, DependencyFactory<PageFactory> {
    private static final Logger LOG=Logger.getLogger(PageFactory.class.getName());
    @Inject
    public DependencyInjector dependencyInjection;
    @Inject
    private Set<FieldInitialiser> fieldInitialisers;
    @Inject
    private WebDriverFrameSwitchingOrchestrator webDriverOrchestrator;
    private EventFiringWebDriver eventFiringWebDriver;
    public PageFactory(){
        this((DependencyFactory) new WebDriverFactory());
    }

    public PageFactory(DependencyFactory... factories){
        this((DependencyInjector)null,factories);
    }

    public PageFactory(DependencyInjector dependencyInjector,DependencyFactory... factories){
        LOG.fine("Createing PageFactory");
        DependencyFactory extenalDependencyInjector= (DependencyFactory) dependencyInjector;
        if (dependencyInjector==null){
            extenalDependencyInjector= (DependencyFactory) new GuiceDependencyInjector(this,factories);
        }
        GuiceDependencyInjector injector=new InternalGuiceDependencyInjection(this,(DependencyInjector)extenalDependencyInjector, new DependencyFactory[0]);
        injector.injectMembers(this);
    }

    public WebDriver getDriver(){
        this.eventFiringWebDriver = new EventFiringWebDriver(this.dependencyInjection.get(WebDriver.class));
        this.eventFiringWebDriver.register(this);
        return this.eventFiringWebDriver;
    }

    public <T> T get(Class<T> pageClass) throws PageFactortError {
        return this.get(pageClass,this.getDriver());
    }

    public <T> T get(T page) throws PageFactortError {
        return this.initializeContainer(page, this.getDriver());
    }

    public <T> T get(Class<T> pageClass, SearchContext searchContext) throws PageFactortError {
        return this.get(pageClass,searchContext,this.getFrame(pageClass,pageClass.getName(), (FrameWeapper)null));
    }

    public <T> T get(Class<T> pageClass,SearchContext searchContext,FrameWeapper frame)throws PageFactortError{
        T page=this.findImplementationBasedOnPageFilter(pageClass);
        return this.initializeContainer(page, searchContext, frame);
    }

    private <T> T findImplementationBasedOnPageFilter(Class<T> pageClass) {

        ImplementationFinder<T> implementationFinder=new ImplementationFinder(pageClass, this.dependencyInjection);
        return  implementationFinder.find();
    }

    private <T> T initializeContainer(T page, SearchContext searchContext) throws PageFactortError {

        return this.initializeContainer(page,searchContext, this.getFrame(page.getClass(), page.getClass().getName(), (FrameWeapper)null));
    }

    private FrameWeapper getFrame(AnnotatedElement element, String name, FrameWeapper parentFrame){

        Frame annotation=element.getAnnotation(Frame.class);
        if (annotation==null){
            return  parentFrame;
        }else {
            Annotations annotations=new Annotations(element,name);
            By frameIdentifier= annotations.buildBy();
            FrameWeapper frameWrapper=new FrameWeapper(this.getDriver(), frameIdentifier);
            frameWrapper.setParent(parentFrame);
            return frameWrapper;
        }
    }
    public <T> T initializeContainer(T page, SearchContext searchContext, FrameWeapper frameWrapper) throws PageFactortError {
        this.setRootElement(page, searchContext);
        Iterator var4= ClassHelper.getFieldsFromClass(page.getClass()).iterator();

        while (true){
            Field field;
            do{
                if (!var4.hasNext()){
                    return page;
                }

                field=(Field)var4.next();
            }while (field.getName().equals("rootElement"));

            frameWrapper=this.getFrame(field,field.getName(),frameWrapper);
            Iterator var6= this.fieldInitialisers.iterator();
            while (var6.hasNext()){
                FieldInitialiser fieldInitialiser= (FieldInitialiser) var6.next();
                if (fieldInitialiser.initialiseField(field, page, searchContext, frameWrapper)){
                    break;
                }
            }
        }
    }
    private <T> void setRootElement(T pageObject, SearchContext searchContext) throws PageFactortError {
        if (searchContext instanceof PageElement){
            try {
                Field rootElement=this.findField(pageObject.getClass(),"rootElement");
                rootElement.setAccessible(true);
                rootElement.set(pageObject,searchContext);
            }catch (NoSuchFieldException var4){
            }catch (IllegalAccessException var5){
                throw new PageFactortError(var5.getCause());
            }
        }
    }
    private  Field findField(Class<?> klass, String name) throws NoSuchFieldException {
        while (klass !=null){
            Field[] var3=klass.getDeclaredFields();
            int var4= var3.length;

            for (int var5=0; var5<var4; ++var5){
                Field field=var3[var5];
                if (field.getName().equalsIgnoreCase(name)){
                    return field;
                }
            }
            klass=klass.getSuperclass();
        }
        throw new NoSuchFieldException("Could not find field with name "+name);
    }

    public void beforeAlertAccept(WebDriver driver) {
    }
    public void afterAlertAccept(WebDriver driver) {
    }
    public void afterAlertDismiss(WebDriver driver) {
    }
    public void beforeAlertDismiss(WebDriver driver) {
    }
    public void beforeNavigateTo(String url, WebDriver driver) {
    }

    public void afterNavigateTo(String url, WebDriver driver) {
        this.webDriverOrchestrator.useFrame((FrameWeapper) null);
    }
    public void beforeNavigateBack(WebDriver driver) {
    }
    public void afterNavigateBack(WebDriver driver) {
    }
    public void beforeNavigateForward(WebDriver driver) {
    }
    public void afterNavigateForward(WebDriver driver) {
    }
    public void beforeNavigateRefresh(WebDriver driver) {
    }
    public void afterNavigateRefresh(WebDriver driver) {
    }
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
    }
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
    }
    public void beforeClickOn(WebElement element, WebDriver driver) {
    }
    public void afterClickOn(WebElement element, WebDriver driver) {
    }
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
    }
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
    }
    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
    }
    public void afterChangeValueOf(WebElement element, WebDriver driver) {
    }
    public void beforeScript(String script, WebDriver driver) {
    }
    public void afterScript(String script, WebDriver driver) {
    }
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
    }
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
    }
    public void onException(Throwable throwable, WebDriver driver) {
    }
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {
        System.out.println("This function hasn't been implemented yet. Please contact NKS");
    }
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {
        System.out.println("This function hasn't been implemented yet. Please contact NKS");
    }

    public void beforeGetText(WebElement element, WebDriver driver) {
        System.out.println("This function hasn't been implemented yet. Please contact NKS");
    }

    public void afterGetText(WebElement element, WebDriver driver, String s) {
        System.out.println("This function hasn't been implemented yet. Please contact NKS");
    }
    @Provides
    public PageFactory get(){
        LOG.fine("Got request for PageFactory");
        return this;
    }
    public void invalidate(Object object){
        if (object instanceof Refreshable){
            ((Refreshable) object).invalidate();
        }
        if (object instanceof Proxy){
            InvocationHandler invocationHandler= Proxy.getInvocationHandler(object);
            if (invocationHandler instanceof Refreshable){
                ((Refreshable) invocationHandler).invalidate();
            }
        }else {
            List<Field> fields=ReflectionHelper.getAllFields(object);
            Iterator var3 =fields.iterator();
            while (var3.hasNext()){
                Field field= (Field) var3.next();
                if (FieldAssessor.isSeleniumPomField(field)){
                    Object fieldValue= ReflectionHelper.getFieldValue(object, field.getName());
                    this.invalidate(fieldValue);
                }
            }
        }
    }
}
