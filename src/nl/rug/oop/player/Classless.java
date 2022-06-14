package nl.rug.oop.player;

import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * A player that does not have any class, should only be relevant before the player chooses a class.
 * @author Jonas Scholz
 */
public class Classless extends Player{

    /**
     * Creates a player that is classless.
     */
    public Classless() {
        super("You", 10, 10, 0, 0);
    }

    /**
     * Gives back the energy, which is zero.
     * @return The integer 0.
     */
    @Override
    public int getEnergy() {
        return 0;
    }

    /**
     * Does nothing.
     * @param energy As the function does nothing this parameter is ignored.
     */
    @Override
    public void setEnergy(int energy) {

    }

    /**
     * Gives the fighting options of a classless player.
     * @return A list of fighting options available to a classless player.
     */
    @Override
    public List<Action> getFightActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new Action("Punch"));
        actions.add(new Action("Pinch"));
        actions.add(new Action("Kick"));
        return actions;
    }

    /**
     * Performs the action that the user has chosen.
     * @param action The action the user chose.
     * @param target The npc that was targeted.
     * @param allEnemies All of the enemies in the current scene.
     * @param scene The scene that the player is currently in.
     * @return A string denoting what the player did.
     */
    @Override
    protected String fight(Action action, NPC target, List<NPC> allEnemies, Scene scene) {
        return switch (action.getActionName()){
            case "Punch" -> this.getName() + " punch "+ target.getName() + " for 0 damage.";
            case "Pinch" -> this.getName() + " pinch "+ target.getName() + " for 0 damage.";
            default -> this.getName() + " kick "+ target.getName() + " for 0 damage.";
        };
    }

    /**
     * Does Nothing.
     */
    @Override
    public void useAppropriatePotion() {
    }

}
