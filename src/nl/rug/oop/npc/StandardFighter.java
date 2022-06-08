package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;

public class StandardFighter extends NPC{

    public StandardFighter(String name, String type, int maxHealth, float strength, int minGold, int maxGold, ArrayList<Item> lootItems) {
        super(name, type, maxHealth, strength, minGold, maxGold, lootItems);
    }

    @Override
    public String takeActions(Player player, Scene currentScene, Action action, boolean isFightAction) {
        if(isFightAction){
            return attack(player, currentScene, action);
        }
        return null;
    }

    protected String attack(Player player, Scene currentScene, Action action){
        int damage = (int) Math.round(strength*(Math.random()*0.1+0.95));
        return player.takeDamage(this, damage);
    }
}
