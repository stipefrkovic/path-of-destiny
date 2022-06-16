package nl.rug.oop.player;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A factory class that registers and creates the player
 * @author Joni Baarda
 */
public class PlayerFactory implements Serializable {

    private static HashMap<String, Class> register = new HashMap();

    /**
     * Registers the player under a registered key
     * @param type The key that can be used to get the class.
     * @param playerClass The player class associated with the key.
     * @author Joni Baarda
     */
    public static void registerPlayer(String type, Class playerClass){
        register.put(type, playerClass);
    }

    /**
     * Creates a player by calling the constructor with the name of the player.
     * @param type the player class
     * @return the created player or null if the process failed
     * @author Joni Baarda
     */
    public static Player createPlayer(String type){
        try {
            Class playerType = register.get(type);
            return (Player) playerType.getDeclaredConstructor(String.class).newInstance("You");
        } catch (Exception e) {
            return null;
        }
    }
}
