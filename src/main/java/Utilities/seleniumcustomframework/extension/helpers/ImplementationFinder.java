package Utilities.seleniumcustomframework.extension.helpers;

import Utilities.seleniumcustomframework.extension.annotations.PageFilter;
import Utilities.seleniumcustomframework.extension.annotations.Validator;
import Utilities.seleniumcustomframework.extension.dependencies.DependencyInjector;
import org.reflections.Reflections;

import java.util.*;

public class ImplementationFinder<T> {
    private Class<T> pageClass;
    private DependencyInjector dependencyInjector;
    private static HashMap<String, Reflections> reflectionCache=new HashMap();

    public ImplementationFinder(Class<T> pageClass, DependencyInjector dependencyInjector){
        this.pageClass=pageClass;
        this.dependencyInjector=dependencyInjector;
    }

    public T find(){
        Set<Class<? extends T>> subTypeOf= this.getReflections(this.pageClass).getSubTypesOf(this.pageClass);
        ArrayList<Class<? extends T>> sortedListOfImplementationClasses= new ArrayList(subTypeOf);
        Collections.sort(sortedListOfImplementationClasses,new ImplementationFinder.PageFilterAnnotatedClasssComparator());
        Iterator var3=sortedListOfImplementationClasses.iterator();

        Class klass;
        boolean valid;
        do{
            PageFilter annotation;
            do{
                if(!var3.hasNext()){
                    return this.dependencyInjector.get(this.pageClass);
                }
                klass= (Class) var3.next();
                annotation= (PageFilter) klass.getAnnotation(PageFilter.class);
            }while (annotation==null);
            valid=true;
            Class[] var7=annotation.value();
            int var8=var7.length;

            for (int var9=0; var9<var8; ++var9){
                Class value = var7[var9];
                Validator validetor= (Validator) this.dependencyInjector.get(value);
                if (!validetor.isValid()){
                    valid=false;
                    break;
                }
            }
        }while (!valid);
        return (T) this.dependencyInjector.get(klass);
    }

    private Reflections getReflections(Class<T> pageClass){
        String pakageName=pageClass.getClass().getPackage().getName();
        if (reflectionCache.containsKey(pakageName)){
            return (Reflections)reflectionCache.get(pakageName);
        }else{
            Reflections reflections=new Reflections(pageClass.getPackage().getName(), new Scanner[0]);
            reflectionCache.put(pakageName,reflections);
            return reflections;
        }
    }

    private class PageFilterAnnotatedClasssComparator implements Comparator<Class<? extends T>>{
        private PageFilterAnnotatedClasssComparator(){
        }

        public int compare(Class<? extends T> o1, Class<? extends T> o2){
            int o1ValidatorsCount =0;
            int o2ValidatorsCount =0;

            PageFilter pageFilter01=(PageFilter) o1.getAnnotation(PageFilter.class);
            PageFilter pageFilter02=o1.getAnnotation(PageFilter.class);
            if (pageFilter01 !=null){
                o1ValidatorsCount=pageFilter01.value().length;
            }
            if (pageFilter02 != null){
                o2ValidatorsCount=pageFilter02.value().length;
            }
            return Integer.compare(o2ValidatorsCount, o1ValidatorsCount);
        }
    }

}
