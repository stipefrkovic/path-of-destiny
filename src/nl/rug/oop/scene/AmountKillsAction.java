package nl.rug.oop.scene;

import nl.rug.oop.player.Player;

/**
 * An action that is available, if the total amount of kills is between the specified values.
 * @author Jonas Scholz
 */
public class AmountKillsAction implements ConditionedAction{

    private String actionName;
    private int lowerAmountKills;
    private int upperAmountKills;

    /**
     * Sets the name of the action, as well as the lower and upper bound of the amount of kills of villagers that satisfy the condition (both ends are inclusive).
     * @param actionName The name of the action.
     * @param lowerAmountKills The lower bound of acceptable kills (inclusive).
     * @param upperAmountKills The upper bound of acceptable kills (inclusive).
     */
    public AmountKillsAction(String actionName, int lowerAmountKills, int upperAmountKills){
        this.actionName = actionName;
        this.lowerAmountKills = lowerAmountKills;
        this.upperAmountKills = upperAmountKills;
    }

    /**
     * Evaluates if the kill count of villagers is inside the specified bounds.
     * @param player The player that plays the game.
     * @return If the kill count is inside the specified bounds.
     */
    @Override
    public boolean evaluateCondition(Player player) {
        int totalKills = player.getKillsForType("Villager");
        return lowerAmountKills <= totalKills && upperAmountKills >= totalKills;
    }

    /**
     * Returns the name of the action.
     * @return The name of the action.
     */
    @Override
    public String getActionName() {
        return actionName;
    }
}
