package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;

/**
 *
 * @author Jonas Scholz
 */
public abstract class BossNPC extends TalkingNPC{

    private boolean healthBelow75 = false;
    private boolean healthBelow50 = false;
    private boolean healthBelow25 = false;

    public BossNPC(String name, String type, int maxHealth, int strength, Dialogue dialogue, int minGold, int maxGold, ArrayList<Item> lootItems) {
        super(name, type, maxHealth, strength, dialogue, minGold, maxGold, lootItems);
    }

    @Override
    protected String takeFightActions(Player player, Scene currentScene, Action action) {
        String description;
        if(this.health<0.75*this.maxHealth && !healthBelow75){
            description = onFirstBelow75Health(player, currentScene, action);
            healthBelow75 = true;
        }else if(this.health<0.5*this.maxHealth && !healthBelow50){
            description = onFirstBelow50Health(player, currentScene, action);
            healthBelow50 = true;
        }else if(this.health<0.25*this.maxHealth && !healthBelow25){
            description = onFirstBelow25Health(player, currentScene, action);
            healthBelow25 = true;
        }else{
            int damage = (int) Math.round(strength*(Math.random()*0.1+0.95));
            description = player.takeDamage(this, damage);
        }

        return description;
    }

    @Override
    public String getName() {
        return super.getName() + " the " + getType();
    }

    protected abstract String onFirstBelow75Health(Player player, Scene currentScene, Action action);

    protected abstract String onFirstBelow50Health(Player player, Scene currentScene, Action action);

    protected abstract String onFirstBelow25Health(Player player, Scene currentScene, Action action);
}
