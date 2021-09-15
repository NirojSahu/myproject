//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.seleniumcustomframework.extension.handlers;

import Utilities.seleniumcustomframework.extension.PageElementImpl;
import Utilities.seleniumcustomframework.extension.helpers.FrameWrapper;
import Utilities.seleniumcustomframework.extension.orchestration.WebDriverFrameSwitchingOrchestrator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

class PageElementHandler {
    private static final Logger LOG = Logger.getLogger(PageElementHandler.class.getName());
    private PageElementImpl pageElement;
    private FrameWrapper frame;
    private WebDriverFrameSwitchingOrchestrator webDriverOrchestrator;

    PageElementHandler() {
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            this.webDriverOrchestrator.useFrame(this.frame);
            return this.pageElement.canHandle(method) ? method.invoke(this.pageElement, args) : method.invoke(proxy, args);
        } catch (InvocationTargetException var5) {
            throw var5.getCause();
        }
    }
}
