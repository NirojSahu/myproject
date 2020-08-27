package Utilities.seleniumcustomframework.extension.helpers;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayHelper {
    private ArrayHelper(){
    }
    public static <T> String join(Iterable<T> aArr, String aSep) {
        StringBuilder sbStr = new StringBuilder();
        boolean first = true;
        Iterator var4=aArr.iterator();
        while (var4.hasNext()){
            T value= (T) var4.next();
            if (!first){
                sbStr.append(aSep);
            }
            first=false;
            sbStr.append(value.toString());
        }
        return sbStr.toString();
    }
}
