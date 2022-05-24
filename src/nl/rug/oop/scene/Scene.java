package nl.rug.oop.scene;

import nl.rug.oop.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scene implements Serializable {

    private String image;
    private String description;
    private HashMap<String, Scene> actions;
    private Player player;

    public Scene(Player player){
        this.player = player;
    }

    public Scene takeAction(String action){
        return actions.get(action);
    }

    public List<String> getActions(){
        return new ArrayList<>(this.actions.keySet());
    }
}
