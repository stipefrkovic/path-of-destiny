package nl.rug.oop.player;

import nl.rug.oop.items.Item;
import nl.rug.oop.npc.Entity;
import nl.rug.oop.scene.Action;

import java.util.List;

public abstract class Player extends Entity {

    public abstract List<Action> getFightActions();

    //This one does not have to be abstract
    public abstract List<String> getInventory();
}
