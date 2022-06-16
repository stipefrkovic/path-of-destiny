package nl.rug.oop.scene;

import nl.rug.oop.items.Item;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A fighting Scene where the player can fight against enemies.
 * @author Jonas Scholz
 */
public class FightScene extends Scene implements Serializable, NPCScene {

    private ArrayList<NPC> enemies;
    private ArrayList<Item> loot;
    private int lootGold = 0;
    private Scene fleeScene;
    private Scene winScene;
    private Player player;
    private double retreatChance = 0.25;

    /**
     * Initializes the attributes of the class and sets the available actions to the user.
     * @param image The image/theme of the scene.
     * @param description The description of the scene.
     * @param player The player that plays the game.
     * @param fleeScene The scene that the player goes to when fleeing successfully (if set to null fleeing is not an option).
     * @param winScene The scene that the player goes to when defeating all enemies.
     * @param enemies The list of enemies that the player has to fight.
     */
    public FightScene(String image, String description, Player player, Scene fleeScene, Scene winScene, ArrayList<NPC> enemies) {
        super(image, description);
        this.player = player;
        this.loot = new ArrayList<>();
        this.addAction(new Action("Fight"), this);
        this.addAction(new Action("Inventory"), this);
        this.addAction(new Action("Back", false), this);
        for (Action action:player.getFightActions()) {
            action.setEnabled(false);
            this.addAction(action, this);
        }
        for (String item:player.getInventory()) {
            this.addAction(new Action(item), this);
        }
        if(fleeScene != null){
            this.addAction(new Action("Flee"), fleeScene);
        }
        this.addAction(new Action("Continue", false), winScene);
        this.fleeScene = fleeScene;
        this.winScene = winScene;
        this.enemies = enemies;
    }

    /**
     * Sets the flee scene and if set to null removes the flee option.
     * @param fleeScene The scene that the player flees to or null if fleeing should be impossible.
     */
    public void setFleeScene(Scene fleeScene){
        this.fleeScene = fleeScene;
        if(fleeScene != null){
            this.addAction(new Action("Flee"), fleeScene);
        }else{
            this.removeAction(new Action("Flee"));
        }
    }

    /**
     * Evaluates the action and updates this scene accordingly. Through that the fight is facilitated.
     * @param action The action the user chose.
     * @return The scene that should be shown next.
     */
    @Override
    public Scene takeAction(Action action) {
        this.updateInventoryOptions();
        if(fleeScene != null){
            fleeScene.removeActionsOfScene(this);
        }
        if(winScene != null){
            winScene.removeActionsOfScene(this);
        }
        switch (action.getActionName()){
            case "Fight":
                List<Action> temp = player.getFightActions();
                temp.add(new Action("Back", false));
                onlyShowSpecifiedActions(temp);
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
            if(player.getHealth()<=0){
                Scene gameOver = new Scene("GameOver", "You are struck down, your vision grows hazy. You feel your blood flowing out of your body. You have failed in your quest. YOU DIED!");
                gameOver.addAction(new Action("Exit Game"), null);
                return gameOver;
            }
        }
        if(player.getInventory().contains(action.getActionName())){
            enemiesResponse(player.useItem(action.getActionName()), action);
        }
        return super.takeAction(action);
    }

    /**
     * Updates the items that the user can attempt to use.
     */
    private void updateInventoryOptions(){
        List<String> items = this.player.getInventory();
        this.removeActions(items);
        List<String> actions = this.getActions();
        boolean itemsEnabled = false;
        for(String action:actions){
            if(items.contains(action)){
                itemsEnabled = true;
                break;
            }
        }
        for (String item:items) {
            this.addAction(new Action(item, itemsEnabled), this);
        }
    }

    /**
     * Evaluates if the player manages to flee successfully or if the fleeing attempt failed.
     * @param action The action that the player took.
     * @return The scene that should be displayed next.
     */
    private Scene tryFleeing(Action action){
        if(Math.random() < retreatChance){
            fleeScene.setDescription("You managed to escape. " + fleeScene.getDescription());
            return fleeScene;
        }
        enemiesResponse("You tried to flee, but you failed. ", action);
        return this;
    }

    /**
     * Updates the enemies, by checking if they were defeated and the reactions of the enemies and updates the description of the scene accordingly.
     * @param newDescription The description that should be added before this method adds more to the description.
     * @param action The action that the user took.
     */
    private void enemiesResponse(String newDescription, Action action){
        StringBuilder descriptionBuilder = new StringBuilder(newDescription);
        descriptionBuilder.append("\n");
        descriptionBuilder.append(isEnemyDefeated());
        if(enemies.size()==0){
            descriptionBuilder.append(player.addLoot(lootGold, loot));
            lootGold = 0;
            loot.clear();
            this.player.setEnergy(10000);
            this.player.changeHealth(-10000);
        }
        for (NPC enemy:enemies) {
            descriptionBuilder.append(enemy.getName()).append(" has ").append(enemy.getHealth()).append(" health left. ");
            descriptionBuilder.append(enemy.takeActions(player, this, action, true));
            descriptionBuilder.append("\n");
        }
        setDescription(descriptionBuilder.toString());
    }

    /**
     * Checks if an enemy or multiple enemies were defeated and creates an appropriate description.
     * @return The description that reveals if enemies were defeated.
     */
    private String isEnemyDefeated(){
        List<NPC> deleteEnemies = new ArrayList<>();
        for (NPC enemy:enemies) {
            if(enemy.getHealth()<=0){
                player.addKill(enemy.getType());
                loot.addAll(enemy.getLoot());
                lootGold += enemy.getGold();
                deleteEnemies.add(enemy);
            }
        }
        StringBuilder defeatMessage = new StringBuilder();
        for (int i=0; i<deleteEnemies.size(); i++) {
            defeatMessage.append(deleteEnemies.get(i).getName());
            if(deleteEnemies.size()-2==i){
                defeatMessage.append(" and ");
            }else if(deleteEnemies.size()-1>i){
                defeatMessage.append(", ");
            }
        }
        if(deleteEnemies.size()>1){
            defeatMessage.append(" were defeated. ");
            defeatMessage.append("\n");
        }else if(deleteEnemies.size()==1){
            defeatMessage.append(" was defeated.");
            defeatMessage.append("\n");
        }
        enemies.removeAll(deleteEnemies);
        return defeatMessage.toString();
    }

    /**
     * Only enables the specified actions and disables all other actions.
     * @param specifiedActions The actions that should be enabled.
     */
    private void onlyShowSpecifiedActions(List<Action> specifiedActions){
        for (Action action:actions.keySet()) {
            action.setEnabled(specifiedActions.contains(action));
        }
    }

    /**
     * Only enables the specified actions and disables all other actions.
     * @param specifiedActions The action names of the actions that should be enabled.
     */
    private void onlyShowSpecifiedActionsByName(List<String> specifiedActions){
        for (Action action:actions.keySet()) {
            action.setEnabled(specifiedActions.contains(action.getActionName()));
        }
    }

    /**
     * Returns all the non-defeated enemies in the scene.
     * @return All non-defeated enemies in this scene.
     */
    @Override
    public List<NPC> getNPCs() {
        List<NPC> npcs = new ArrayList<>();
        for (NPC enemy:enemies) {
            npcs.add(enemy.clone());
        }
        return npcs;
    }
}
