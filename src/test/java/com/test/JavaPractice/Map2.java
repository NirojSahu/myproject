package com.test.JavaPractice;

import ch.qos.logback.core.CoreConstants;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Map2 {
    public static void main(String args[]){

        Map<Integer, String > map=new HashMap<Integer,String>();
        map.put(1,"Niroj");
        map.put(2,"Kumar");
        map.put(3,"Sahu");
        map.put(4,"Coforge");
        map.put(5,"Bangalore");

        Iterator itr=map.entrySet().iterator();
        while (itr.hasNext()){
            Map.Entry me=(Map.Entry)itr.next();
            System.out.println("Key:"+me.getKey()+ "  Value:"+me.getValue());
        }
        for (Map.Entry me2:map.entrySet()){
            System.out.println("Key:"+me2.getKey()+ "  Value:"+me2.getValue());
        }
    }
}
