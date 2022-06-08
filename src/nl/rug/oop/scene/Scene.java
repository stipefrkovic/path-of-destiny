package nl.rug.oop.scene;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scene implements Serializable {

    private String image;
    private String description;
    protected HashMap<Action, Scene> actions;

    public Scene(String image, String description, HashMap<Action, Scene> actions){
        this.actions = actions;
        this.image = image;
        this.description = description;
    }

    public Scene(String image, String description){
        this(image, description, null);
    }

    public Scene takeAction(Action action){
        if(!action.isEnabled()){
            return this;
        }
        return actions.get(action);
    }

    public List<String> getActions(){
        List<String> actionStrings = new ArrayList<>();
        for (Action action:this.actions.keySet()) {
            if(action.isEnabled()){
                actionStrings.add(action.getActionName());
            }
        }
        return actionStrings;
    }

    public void addAction(Action action, Scene scene){
        actions.put(action, scene);
    }

    public void addActions(HashMap<Action, Scene> actions){
        this.actions.putAll(actions);
    }

    public void removeActions(Action action){
        actions.remove(action);
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
