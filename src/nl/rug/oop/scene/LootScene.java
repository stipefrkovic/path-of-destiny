package nl.rug.oop.scene;

import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;

import java.util.HashMap;
import java.util.List;

/**
 * A Scene that the player can interact with, which can give the player items, if the corresponding action was chosen.
 * @author Jonas Scholz
 */
public class LootScene extends Scene{

    private HashMap<Action, List<Item>> possibleLoot;
    private Player player;

    /**
     * Sets all the attributes.
     * @param image The image/theme of the scene.
     * @param description The description of the scene.
     * @param actions The possible actions the player can take and the scene that comes of that decision.
     * @param possibleLoot The possible actions the player can take and the list of items that comes of that decision.
     * @param player The player that plays the game.
     */
    public LootScene(String image, String description, HashMap<Action, Scene> actions, HashMap<Action, List<Item>> possibleLoot, Player player) {
        super(image, description, actions);
        this.possibleLoot = possibleLoot;
        this.player = player;
    }

    /**
     * Decides on whether the player receives loot and what scene should be shown next.
     * @param action The action the user chose.
     * @return The next scene that should be shown (can be null if the action was invalid).
     */
    @Override
    public Scene takeAction(Action action) {
        if(possibleLoot.containsKey(action)){
            setDescription(getDescription()+" "+player.addLoot(0, possibleLoot.get(action)));
            Scene nextScene = super.takeAction(action);
            removeAction(action);
            return nextScene;
        }
        return super.takeAction(action);
    }
}
