package nl.rug.oop.npc;

import nl.rug.oop.effects.PoisonEffect;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

public class Spider extends StandardFighter{

    public Spider(String name, String type, int maxHealth, float strength) {
        super(name, type, maxHealth, strength);
    }

    public Spider(String name, int maxHealth, float strength) {
        this(name, "Spider", maxHealth, strength);
    }

    public Spider(String name){
        this(name, 24, 5);
    }

    @Override
    protected String attack(Player player, Scene currentScene, Action action) {
        String description = super.attack(player, currentScene, action);
        if(Math.random()>=0.8){
            description += player.addEffect(new PoisonEffect());
        }
        return description;
    }
}
