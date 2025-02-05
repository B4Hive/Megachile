package br.ufjf.b4hive.view;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    public static Map<Integer, Character> IDtoIcon = new HashMap<>();

    public static void init(){
        //juro que vou fazer isso ficar melhor
        IDtoIcon.put(00, ',');
        IDtoIcon.put(01, '#');
        IDtoIcon.put(10, '@');
    }
    public static char getIcon(int id){
        return IDtoIcon.get(id);
    }
    
}
