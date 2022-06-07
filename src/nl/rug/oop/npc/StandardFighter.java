package nl.rug.oop.npc;

import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

public class StandardFighter extends NPC{

    public StandardFighter(String name, String type, int maxHealth, float strength) {
        super(name, type, maxHealth, strength);
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
