package nl.rug.oop.npc;

import nl.rug.oop.dialogue.Dialogue;
import nl.rug.oop.effects.StunEffect;
import nl.rug.oop.effects.WeaknessEffect;
import nl.rug.oop.items.ItemFactory;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.HashMap;

/**
 * The King which is a boss, with special behaviour during fights.
 * @author Jonas Scholz
 */
public class KingBoss extends BossNPC{

    private boolean reflectDamage = false;

    /**
     * Creates a new KingBoss.
     * @param factory A ItemFactory to generate the random loot.
     */
    public KingBoss(ItemFactory factory) {
        super("Wilhelm II.", "King", 400, 12, new Dialogue("You think that you can defeat me.", new HashMap<>(), SceneChange.CURRENT_SCENE), 1000000,1000000, factory.createRandomItems(2));
    }

    /**
     * Performs his special attack.
     * @param player The player that plays the game.
     * @param currentScene The current scene that the boss is in.
     * @param action The action that the user took.
     * @return What the boss did.
     */
    @Override
    protected String onFirstBelow75Health(Player player, Scene currentScene, Action action) {
        return this.doDamage(player, (int) (this.strength*4), currentScene) + player.addEffect(new StunEffect());
    }

    /**
     * Gives the player a weakness effect.
     * @param player The player that plays the game.
     * @param currentScene The current scene that the boss is in.
     * @param action The action that the user took.
     * @return What the boss did.
     */
    @Override
    protected String onFirstBelow50Health(Player player, Scene currentScene, Action action) {
        return this.getName()+ " uses his aura to suppress you. " + player.addEffect(new WeaknessEffect());
    }

    /**
     * Activates reflectDamage, which damages the attacker.
     * @param player The player that plays the game.
     * @param currentScene The current scene that the boss is in.
     * @param action The action that the user took.
     * @return What the boss did.
     */
    @Override
    protected String onFirstBelow25Health(Player player, Scene currentScene, Action action) {
        reflectDamage = true;
        return this.getName()+ " starts to glow and ethereal thorns sprout from his armor. ";
    }

    /**
     * Reflects damage to the attacker when reflectDamage is true.
     * Reduces health by damage.
     * @param attacker The entity that attacked this entity.
     * @param damage The amount of damage that was inflicted.
     * @return
     */
    @Override
    public String takeDamage(Entity attacker, int damage) {
        if (reflectDamage && attacker != this){
            return super.takeDamage(attacker, damage) + " " + attacker.takeDamage(this, (int) (damage*0.25));
        }
        return super.takeDamage(attacker, damage);
    }
}
