package br.ufjf.b4hive.view;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private static final Map<Integer, Character> IDtoIcon = new HashMap<>();

    static void init(){

        // ID starts with 0 = map tile
        IDtoIcon.put(00, ','); // grass
        IDtoIcon.put(01, '.'); // dirt
        IDtoIcon.put(02, ';'); // water
        
        // ID starts with 1 = material
        IDtoIcon.put(10, '*'); // pollem
        IDtoIcon.put(11, '~'); // nectar
        IDtoIcon.put(12, '¤'); // leaf
        IDtoIcon.put(13, '&'); // resin
        IDtoIcon.put(14, 'v'); // petal
        IDtoIcon.put(15, 'o'); // fruit
        IDtoIcon.put(16, ';'); // water
        IDtoIcon.put(17, 'e'); // exoskeleton
        
        // ID starts with 2 = unmoveable entity
        IDtoIcon.put(20, 'F'); // flower
        IDtoIcon.put(21, 'T'); // tree
        IDtoIcon.put(22, '#'); // nest
        
        // ID starts with 3 = craftable item
        IDtoIcon.put(30, 'H'); // honey
        IDtoIcon.put(31, '='); // wax
        IDtoIcon.put(32, 'p'); // bread
        IDtoIcon.put(33, '#'); // nest
        
        // ID starts with 4 = equipment
        IDtoIcon.put(40, 'f'); // stinger
        IDtoIcon.put(41, 'a'); // armor
        IDtoIcon.put(42, '>'); // shears
        
        // ID starts with 9 = moveable entity
        IDtoIcon.put(99, '@'); // player
        IDtoIcon.put(90, 'X'); // enemy

    }
    static char getIcon(int id){
        return IDtoIcon.get(id);
    }
    
}
