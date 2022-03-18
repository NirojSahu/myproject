package com.test.JavaPractice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PracticeMap {

    public static void main(String args[]){
        HashMap<Integer,String> obj=new HashMap<Integer, String >();

        obj.put(1,"Niroj");
        obj.put(2,"Kumar");
        obj.put(3,"Sahu");
        obj.put(4,"Coforge");
        obj.put(5,"Bangalore");

        Iterator it=obj.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry ME=(Map.Entry)it.next();
            System.out.println("Key is:" +ME.getKey()+ "Value is:"+ ME.getValue());
        }
        for (Map.Entry me2:obj.entrySet()){
            System.out.println("Key is:" +me2.getKey()+ "Value is:"+ me2.getValue());
        }
    }
}
