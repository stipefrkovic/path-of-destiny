package nl.rug.oop.scene;

import java.io.Serializable;
import java.util.Objects;

/**
 * A possible action that can be taken, identified by its name, with a boolean indicating whether this action is enabled and an integer identifying its target.
 * @author Jonas Scholz
 */
public class Action implements Serializable {

    private String actionName;
    private boolean enabled;
    private int target;

    /**
     * Initializes all attributes of this class.
     * @param actionName The name of the action.
     * @param enabled If the action is available.
     * @param target A natural number that indicates the npc that is targeted.
     */
    public Action(String actionName, boolean enabled, int target){
        this.actionName = actionName;
        this.enabled = enabled;
        this.target = target;
    }

    /**
     * Initializes all attributes of this class, with the target set to zero.
     * @param actionName The name of the action.
     * @param enabled If the action is available.
     */
    public Action(String actionName, boolean enabled){
        this(actionName, enabled, 0);
    }

    /**
     * Initializes all attributes of this class, with the target set to zero and the action being set as available.
     * @param actionName The name of the action.
     */
    public Action(String actionName){
        this(actionName, true);
    }

    /**
     * Returns the name of the action.
     * @return The name of the action.
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Returns if the action is available.
     * @return If the action is available.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets if the action is available.
     * @param enabled If the action is available.
     */
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    /**
     * Returns a natural number that indicates the npc that this action is targeting.
     * @return A natural number that indicates the npc that this action is targeting.
     */
    public int getTarget() {
        return target;
    }

    /**
     * Sets the target to a natural number that represents an npc.
     * @param target A natural number that represents an npc which this action is targeting.
     */
    public void setTarget(int target) {
        this.target = target;
    }

    /**
     * Checks if the other object is equal to this object.
     * @param o The object that this object is compared to.
     * @return If this object is equal to the other object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return actionName.equals(action.getActionName());
    }

    /**
     * Generates the hashcode for this object.
     * @return The hashcode of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(actionName);
    }
}
