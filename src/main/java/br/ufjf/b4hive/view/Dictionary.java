package br.ufjf.b4hive.view;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private static final Map<Integer, Character> IDtoIcon = new HashMap<>();

    static void init(){
        // i'll... improve this... i swear... trust me...
        // ID starts with 0 = map tile
        // ID starts with 1 = moveable entity
        // ID starts with 2 = unmoveable entity
        // ID starts with 3 = item
        // ID starts with 4 = equipment
        IDtoIcon.put(00, ',');
        IDtoIcon.put(01, '#');
        IDtoIcon.put(10, '@');
        IDtoIcon.put(20, 'T');
        IDtoIcon.put(30, '9');
        IDtoIcon.put(31, '6');
        IDtoIcon.put(40, '1');
        IDtoIcon.put(41, ')');
    }
    static char getIcon(int id){
        return IDtoIcon.get(id);
    }
    
}
