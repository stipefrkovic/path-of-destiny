package nl.rug.oop.npc;

import nl.rug.oop.dialogue.Dialogue;
import nl.rug.oop.effects.ConfusionEffect;
import nl.rug.oop.effects.PoisonEffect;
import nl.rug.oop.effects.WeaknessEffect;
import nl.rug.oop.items.ItemFactory;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.HashMap;

/**
 * A spider boss with special behaviour when the health reaches certain thresholds.
 * @author Jonas Scholz
 */
public class SpiderBoss extends BossNPC{

    /**
     * Creates a new Spider boss, with already set parameters, except for the loot.
     * @param factory The item factory which is used to set the loot.
     */
    public SpiderBoss(ItemFactory factory) {
        super("Wren", "Weaver", 150, 6, new Dialogue("You will never defeat me.", new HashMap<>(), SceneChange.CURRENT_SCENE), 10, 30, factory.createRandomItems(2,4));
    }

    /**
     * Attacks the player and has the chance to inflict poison effect on the player.
     * @param player The player that plays that game.
     * @param currentScene The scene that is currently active, in which this NPC resides.
     * @param action The action that the user took.
     * @return What the spider boss did.
     */
    @Override
    protected String takeFightActions(Player player, Scene currentScene, Action action) {
        String description = super.takeFightActions(player, currentScene, action);
        if(Math.random() < 0.4){
            description += player.addEffect(new PoisonEffect());
        }
        return description;
    }

    /**
     * Adds a weakness to the player.
     * @param player The player that plays the game.
     * @param currentScene The current scene that the boss is in.
     * @param action The action that the user took.
     * @return What the spider boss did.
     */
    @Override
    protected String onFirstBelow75Health(Player player, Scene currentScene, Action action) {
        return player.addEffect(new ConfusionEffect());
    }

    /**
     * Adds a weakness to the player.
     * @param player The player that plays the game.
     * @param currentScene The current scene that the boss is in.
     * @param action The action that the user took.
     * @return What the spider boss did.
     */
    @Override
    protected String onFirstBelow50Health(Player player, Scene currentScene, Action action) {
        return player.addEffect(new WeaknessEffect());
    }

    /**
     * Increases its base strength by 50%.
     * @param player The player that plays the game.
     * @param currentScene The current scene that the boss is in.
     * @param action The action that the user took.
     * @return What the spider boss did.
     */
    @Override
    protected String onFirstBelow25Health(Player player, Scene currentScene, Action action) {
        this.setBaseStrength(this.getBaseStrength()*1.5f);
        return this.getName()+" is enraged. ";
    }
}
