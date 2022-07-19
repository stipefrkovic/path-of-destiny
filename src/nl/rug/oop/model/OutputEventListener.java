package nl.rug.oop.model;


import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;

import java.util.ArrayList;

/**
 * A Listener that allows for the model to communicate what the view should display.
 * @author Jonas Scholz
 */
public interface OutputEventListener {

    /**
     * Updates the view with this information.
     * @param actions A list of possible valid actions that can be chosen.
     * @param description The description of the current scene.
     * @param image The image/theme of the current scene.
     * @param NPCs The npcs in the current scene, can be empty if no npcs are in the current scene.
     * @param player The player that plays the game.
     */
    void updateScene(ArrayList<String> actions, String description, String image, ArrayList<NPC> NPCs, Player player);
}
