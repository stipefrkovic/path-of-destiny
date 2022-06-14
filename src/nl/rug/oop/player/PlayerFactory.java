package nl.rug.oop.player;

import nl.rug.oop.items.ItemFactory;

import java.io.Serializable;
import java.util.HashMap;

public class PlayerFactory implements Serializable {

    private static HashMap<String, Class> register = new HashMap();


    public static void registerPlayer(String type, Class playerClass){
        register.put(type, playerClass);
    }

    public static Player createPlayer(String type){
        try {
            Class playerType = register.get(type);
            return (Player) playerType.getDeclaredConstructor(String.class).newInstance("You");
        } catch (Exception e) {
            return null;
        }
    }
}
