package nl.rug.oop.scene;

import nl.rug.oop.player.Player;

/**
 * Conditioned Action that is used to determine if the action is available.
 * @author Jonas Scholz
 */
public interface ConditionedAction {

    /**
     * Evaluate the condition and therefore if the action is available.
     * @param player The player that plays the game.
     * @return If the condition is fulfilled/satisfied.
     */
    boolean evaluateCondition(Player player);

    /**
     * Returns the name of the action.
     * @return The name of the action.
     */
    String getActionName();
}
