package nl.rug.oop.scene;

import nl.rug.oop.player.Player;

public class AmountKillsAction implements ConditionedAction{

    private String actionName;
    private int lowerAmountKills;
    private int upperAmountKills;

    public AmountKillsAction(String actionName, int lowerAmountKills, int upperAmountKills){
        this.actionName = actionName;
        this.lowerAmountKills = lowerAmountKills;
        this.upperAmountKills = upperAmountKills;
    }

    @Override
    public boolean evaluateCondition(Player player) {
        int totalKills = player.getTotalKills();
        return lowerAmountKills <= totalKills && upperAmountKills >= totalKills;
    }

    @Override
    public String getActionName() {
        return actionName;
    }
}
