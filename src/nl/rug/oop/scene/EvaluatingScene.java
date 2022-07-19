package nl.rug.oop.scene;

import nl.rug.oop.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A scene that allows for actions that are only available under certain conditions.
 * @author Jonas Scholz
 */
public class EvaluatingScene extends Scene{

    private HashMap<ConditionedAction, Scene> conditionedActions;
    private Player player;

    /**
     * Initializes all attributes.
     * @param image The image/theme of the scene.
     * @param description The description of the scene.
     * @param conditionedActions A hashmap of actions with conditions and the scene that they will lead to.
     * @param player The player that plays the game.
     */
    public EvaluatingScene(String image, String description, HashMap<ConditionedAction, Scene> conditionedActions, Player player) {
        super(image, description);
        this.conditionedActions = conditionedActions;
        this.player = player;
    }

    /**
     * Determines which conditions are fulfilled and generates a list of available actions based on that.
     * @return The list of action names of actions whose condition is fulfilled.
     */
    @Override
    public ArrayList<String> getActions() {
        ArrayList<String> actionStrings = new ArrayList<>();
        for (ConditionedAction action:this.conditionedActions.keySet()) {
            if(action.evaluateCondition(player)){
                actionStrings.add(action.getActionName());
            }
        }
        return actionStrings;
    }

    /**
     * Determines if the condition of the taken action was fulfilled and returns the corresponding next scene.
     * @param action The action the user chose.
     * @return The scene that should be displayed next.
     */
    @Override
    public Scene takeAction(Action action) {
        ConditionedAction conditionedAction = getAction(action.getActionName());
        if(conditionedAction == null){
            return null;
        }else{
            return conditionedActions.get(conditionedAction);
        }
    }

    /**
     * Gives back the ConditionedAction whose condition was fulfilled and whose name matches the given name.
     * @param actionName The name of the action.
     * @return The ConditionedAction that has the specified name and whose condition is fulfilled, or null if no applicable ConditionedAction was found.
     */
    private ConditionedAction getAction(String actionName){
        for (ConditionedAction action:conditionedActions.keySet()) {
            if(action.getActionName().equals(actionName) && action.evaluateCondition(player)){
                return action;
            }
        }
        return null;
    }
}
