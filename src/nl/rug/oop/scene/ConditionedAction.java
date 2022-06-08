package nl.rug.oop.scene;

import nl.rug.oop.player.Player;

public interface ConditionedAction {

    boolean evaluateCondition(Player player);

    String getActionName();
}
