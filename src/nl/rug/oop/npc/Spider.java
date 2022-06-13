package nl.rug.oop.npc;

import nl.rug.oop.effects.PoisonEffect;
import nl.rug.oop.items.ItemFactory;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

/**
 * Spider NPC that can inflict the poison effect on the player when attacking.
 * @author Jonas Scholz
 */
public class Spider extends StandardFighter{

    /**
     * Creates a new spider with health, strength, type and name determined by the parameters given.
     * @param name The name of the spider.
     * @param type The type of the npc, for example spider.
     * @param maxHealth The maximum health of the spider.
     * @param strength The strength of the spider.
     * @param factory An item factory to determine the loot of the spider.
     */
    public Spider(String name, String type, int maxHealth, float strength, ItemFactory factory) {
        super(name, type, maxHealth, strength, 0, 4, factory.createRandomItems(0, 1));
    }

    /**
     * Creates a new spider with health, strength and name determined by the parameters given.
     * @param name The name of the spider.
     * @param maxHealth The maximum health of the spider.
     * @param strength The strength of the spider.
     * @param factory An item factory to determine the loot of the spider.
     */
    public Spider(String name, int maxHealth, float strength, ItemFactory factory) {
        this(name, "Spider", maxHealth, strength, factory);
    }

    /**
     * Creates a new spider with the name determined by the parameter given.
     * @param name The name of the spider.
     * @param factory An item factory to determine the loot of the spider.
     */
    public Spider(String name, ItemFactory factory){
        this(name, 24, 5, factory);
    }

    /**
     * Attacks the player and has a chance to poison the player.
     * @param player The player that plays that game.
     * @param currentScene The scene that is currently active, in which this NPC resides.
     * @param action The action that the user took.
     * @return What the spider did.
     */
    @Override
    protected String takeFightActions(Player player, Scene currentScene, Action action) {
        String description = super.takeFightActions(player, currentScene, action);
        if(Math.random()>=0.8){
            description += player.addEffect(new PoisonEffect());
        }
        return description;
    }
}
