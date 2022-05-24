package nl.rug.oop.scene;

import java.io.Serializable;

/**
 * A possible action that can be taken, identified by its name, with a boolean indicating whether this action is enabled.
 * @author Jonas Scholz
 */
public class Action implements Serializable {

    private String actionName;
    private boolean enabled;

    /**
     * A constructor that sets all attributes.
     * @param actionName The name of the action.
     * @param enabled If the action is enabled or not.
     */
    public Action(String actionName, boolean enabled){
        this.actionName = actionName;
        this.enabled = enabled;
    }

    /**
     * A constructor to define the name of the action, with enabled being set to true.
     * @param actionName The name of the action.
     */
    public Action(String actionName){
        this(actionName, true);
    }

    public String getActionName() {
        return actionName;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
