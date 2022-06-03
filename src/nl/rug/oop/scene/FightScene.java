package nl.rug.oop.scene;

import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FightScene extends Scene implements Serializable {

    private ArrayList<NPC> enemies;
    private Scene fleeScene;
    private Player player;
    private double retreatChance = 0.5;

    public FightScene(String image, String description, Player player, Scene fleeScene, Scene winScene, ArrayList<NPC> enemies) {
        super(image, description);
        this.player = player;
        for (Action action:player.getFightActions()) {
            this.addAction(action, this);
        }
        for (String item:player.getInventory()) {
            this.addAction(new Action(item), this);
        }
        this.addAction(new Action("Flee"), fleeScene);
        this.addAction(new Action("Continue", false), winScene);
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
        if(player.getFightActions().contains(action)){
            int target = action.getTarget();
            if(target<0 || target>=enemies.size()){
                target = 0;
            }
            enemiesResponse(player.attack(action, enemies.get(target), new ArrayList<>(enemies), this), action);
            if(enemies.size()==0){
                ArrayList<String> nextActions = new ArrayList<>();
                nextActions.add("Continue");
                this.onlyShowSpecifiedActionsByName(nextActions);
            }
        }
        if(player.getInventory().contains(action.getActionName())){
            enemiesResponse(player.useItem(action.getActionName()), action);
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
        descriptionBuilder.append(isEnemyDefeated());
        for (NPC enemy:enemies) {
            descriptionBuilder.append(enemy.takeActions(player, this, action, true));
        }
        setDescription(descriptionBuilder.toString());
    }

    private String isEnemyDefeated(){
        List<NPC> deleteEnemies = new ArrayList<>(enemies);
        for (NPC enemy:enemies) {
            if(enemy.getHealth()<=0){
                player.addKill(enemy.getType());
                deleteEnemies.add(enemy);
            }
        }
        StringBuilder defeatMessage = new StringBuilder();
        for (int i=0; i<deleteEnemies.size(); i++) {
            defeatMessage.append(deleteEnemies.get(i));
            if(deleteEnemies.size()-2==i){
                defeatMessage.append(" and ");
            }else if(deleteEnemies.size()-1>i){
                defeatMessage.append(", ");
            }
        }
        if(deleteEnemies.size()>1){
            defeatMessage.append(" were defeated. ");
        }else if(deleteEnemies.size()==1){
            defeatMessage.append(" was defeated.");
        }
        enemies.removeAll(deleteEnemies);
        return defeatMessage.toString();
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
