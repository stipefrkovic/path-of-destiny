package nl.rug.oop.scene;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A basic Scene that the player can interact with.
 * @author Jonas Scholz
 */
public class Scene implements Serializable {

    private String image;
    private String description;
    protected HashMap<Action, Scene> actions;

    /**
     * Sets the attributes of the scene.
     * @param image The image/theme of the scene.
     * @param description The description of the scene.
     * @param actions The possible actions the player can take and the scene that comes of that decision.
     */
    public Scene(String image, String description, HashMap<Action, Scene> actions){
        this.actions = actions;
        this.image = image;
        this.description = description;
    }

    /**
     * Sets the attributes of the scene, but without any actions.
     * @param image The image/theme of the scene.
     * @param description The description of the scene.
     */
    public Scene(String image, String description){
        this(image, description, new HashMap<>());
    }

    /**
     * Decides on what scene should be shown next.
     * @param action The action the user chose.
     * @return The next scene that should be shown (can be null if the action was invalid).
     */
    public Scene takeAction(Action action){
        if(!action.isEnabled()){
            return null;
        }
        if(action.getActionName().equals("Exit Game")){
            System.exit(0);
        }
        return actions.get(action);
    }

    /**
     * Removes all actions that lead to the specified Scene.
     * @param scene The scene whose actions should be removed.
     */
    public void removeActionsOfScene(Scene scene){
        List<Action> removeActions = new ArrayList<>();
        for(Action action:this.actions.keySet()){
            if(this.actions.get(action).equals(scene)){
                removeActions.add(action);
            }
        }
        for (Action action:removeActions) {
            this.actions.remove(action);
        }
    }

    /**
     * Gives the enabled actions that the user can choose from.
     * @return The list of enabled action names.
     */
    public List<String> getActions(){
        List<String> actionStrings = new ArrayList<>();
        for (Action action:this.actions.keySet()) {
            if(action.isEnabled()){
                actionStrings.add(action.getActionName());
            }
        }
        return actionStrings;
    }

    /**
     * Adds an action to this Scene.
     * @param action The action that can be taken.
     * @param scene The scene that will come next as a result of the action.
     */
    public void addAction(Action action, Scene scene){
        actions.put(action, scene);
    }

    /**
     * Adds all actions in the Hashmap to this Scene.
     * @param actions A Hashmap of actions with the scene that will come next when that action is chosen.
     */
    public void addActions(HashMap<Action, Scene> actions){
        this.actions.putAll(actions);
    }

    /**
     * Removes an action from this Scene.
     * @param action The action that is supposed to be removed.
     */
    public void removeAction(Action action){
        actions.remove(action);
    }

    /**
     * Returns the image/theme of the Scene.
     * @return The image/theme of the Scene.
     */
    public String getImage() {
        return image;
    }

    /**
     * Returns the description of the Scene.
     * @return The description of the Scene.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the scene.
     * @param description The new description of the scene.
     */
    protected void setDescription(String description) {
        this.description = description;
    }
}
