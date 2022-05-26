package nl.rug.oop.scene;

import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FightScene extends Scene implements Serializable {

    private ArrayList<NPC> enemies;
    private Scene winScene;
    private Scene fleeScene;
    private Player player;
    private double retreatChance = 0.5;

    public FightScene(String image, String description, Player player, Scene fleeScene, Scene winScene, ArrayList<NPC> enemies) {
        super(image, description);
        this.player = player;
        for (Action action:player.getFightActions()) {
            this.addAction(action, this);
        }
        this.addAction(new Action("Flee"), fleeScene);
        this.winScene = winScene;
        this.fleeScene = fleeScene;
        this.enemies = enemies;
    }

    @Override
    public Scene takeAction(Action action) {
        switch (action.getActionName()){
            case "Fight":
                onlyShowSpecifiedActions(player.getFightActions());
                break;
            case "Flee":
                return tryFleeing(action);
            case "Inventory":
                List<String> actions = new ArrayList<>(player.getInventory());
                actions.add("Back");
                onlyShowSpecifiedActionsByName(actions);
                break;
            case "Back":
                onlyShowSpecifiedActionsByName(new ArrayList<>(Arrays.asList("Fight", "Inventory", "Flee")));
                break;
        }
        
        return super.takeAction(action);
    }

    private Scene tryFleeing(Action action){
        if(Math.random() < retreatChance){
            fleeScene.setDescription("You managed to escape. " + fleeScene.getDescription());
            return fleeScene;
        }
        enemiesResponse("You tried to flee, but you failed. ", action);
        return this;
    }

    private void enemiesResponse(String newDescription, Action action){
        StringBuilder descriptionBuilder = new StringBuilder(newDescription);
        for (NPC enemy:enemies) {
            descriptionBuilder.append(enemy.takeActions(player, this, action));
        }
        setDescription(descriptionBuilder.toString());
    }

    private void onlyShowSpecifiedActions(List<Action> specifiedActions){
        for (Action action:actions.keySet()) {
            action.setEnabled(specifiedActions.contains(action));
        }
    }

    private void onlyShowSpecifiedActionsByName(List<String> specifiedActions){
        for (Action action:actions.keySet()) {
            action.setEnabled(specifiedActions.contains(action.getActionName()));
        }
    }
}
