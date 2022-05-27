package nl.rug.oop.player;

import nl.rug.oop.npc.Entity;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.List;

public abstract class Player extends Entity {

    //Just because I put abstract methods in here does not mean that they have to be abstract
    public abstract List<Action> getFightActions();

    public abstract List<String> getInventory();

    //The String is supposed to say what the player did
    public abstract String attack(Action action, NPC target, List<NPC> allEnemies, Scene scene);

    public abstract void addKill(String type);
}
