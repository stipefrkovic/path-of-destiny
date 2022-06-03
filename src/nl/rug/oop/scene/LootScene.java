package nl.rug.oop.scene;

import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;

import java.util.HashMap;
import java.util.List;

public class LootScene extends Scene{

    private HashMap<Action, List<Item>> possibleLoot;
    private Player player;


    public LootScene(String image, String description, HashMap<Action, Scene> actions, HashMap<Action, List<Item>> possibleLoot, Player player) {
        super(image, description, actions);
        this.possibleLoot = possibleLoot;
        this.player = player;
    }

    @Override
    public Scene takeAction(Action action) {
        if(possibleLoot.containsKey(action)){
            setDescription(getDescription()+" "+player.addLoot(0, possibleLoot.get(action)));
            removeActions(action);
        }
        return super.takeAction(action);
    }
}
