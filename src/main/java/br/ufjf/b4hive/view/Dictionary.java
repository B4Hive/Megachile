package br.ufjf.b4hive.view;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private static final Map<Integer, Character> IDtoIcon = new HashMap<>();

    static void init(){
        //juro que vou fazer isso ficar melhor
        IDtoIcon.put(00, ',');
        IDtoIcon.put(01, '#');
        IDtoIcon.put(10, '@');
        
    }
    static char getIcon(int id){
        return IDtoIcon.get(id);
    }
    
}
