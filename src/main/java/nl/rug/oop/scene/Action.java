package nl.rug.oop.scene;

import java.io.Serializable;
import java.util.Objects;

/**
 * A possible action that can be taken, identified by its name, with a boolean indicating whether this action is enabled.
 * @author Jonas Scholz
 */
public class Action implements Serializable {

    private String actionName;
    private boolean enabled;
    private int target;


    public Action(String actionName, boolean enabled, int target){
        this.actionName = actionName;
        this.enabled = enabled;
        this.target = target;
    }

    public Action(String actionName, boolean enabled){
        this(actionName, enabled, -1);
    }

    public Action(String actionName){
        this(actionName, true);
    }

    public String getActionName() {
        return actionName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return actionName.equals(action.getActionName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionName);
    }
}
