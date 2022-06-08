package nl.rug.oop.scene;

import nl.rug.oop.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EvaluatingScene extends Scene{

    private HashMap<ConditionedAction, Scene> conditionedActions;
    private Player player;

    public EvaluatingScene(String image, String description, HashMap<ConditionedAction, Scene> conditionedActions, Player player) {
        super(image, description);
        this.conditionedActions = conditionedActions;
        this.player = player;
    }

    @Override
    public List<String> getActions() {
        List<String> actionStrings = new ArrayList<>();
        for (ConditionedAction action:this.conditionedActions.keySet()) {
            if(action.evaluateCondition(player)){
                actionStrings.add(action.getActionName());
            }
        }
        return actionStrings;
    }

    @Override
    public Scene takeAction(Action action) {
        ConditionedAction conditionedAction = getAction(action.getActionName());
        if(conditionedAction == null){
            return null;
        }else{
            return conditionedActions.get(conditionedAction);
        }
    }

    private ConditionedAction getAction(String actionName){
        for (ConditionedAction action:conditionedActions.keySet()) {
            if(action.getActionName().equals(actionName) && action.evaluateCondition(player)){
                return action;
            }
        }
        return null;
    }
}
