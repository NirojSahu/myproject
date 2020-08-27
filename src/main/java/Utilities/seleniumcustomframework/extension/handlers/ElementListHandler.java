package Utilities.seleniumcustomframework.extension.handlers;

import Utilities.seleniumcustomframework.extension.ElementListImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ElementListHandler  implements InvocationHandler {
    private List elementList;
    private ElementListImpl extensionListHandler;

    public void ElementHandler(List element, ElementListImpl extensionHandler){
        this.elementList=element;
        this.extensionListHandler=extensionHandler;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return this.extensionListHandler.canHandle(method) ? method.invoke(this.extensionListHandler,args):method.invoke(this.elementList,args);
        }catch (InvocationTargetException var5){
            throw var5.getCause();
        }
    }
}
