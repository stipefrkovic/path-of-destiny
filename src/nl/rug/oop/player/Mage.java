package nl.rug.oop.player;

import nl.rug.oop.items.Item;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Class extends the Player class. Implements the possible player class mage
 * @author Joni Baarda
 */
public class Mage extends Player{
    //health = 80; strength = 5; mana = 50
    private int mana;
    private final int MAX_MANA = 50;
    private List<Action> fightActions;

    /**
     * initializes the mage class with the initial values of the class
     * @param name the name of the player
     * @author Joni Baarda
     */
    public Mage(String name) {
        super(name, 100, 100, 5, 0);
        this.mana = 50;
        fightActions = new ArrayList<>();
        fightActions.add(new Action("Mana bolt"));
        fightActions.add(new Action("Heal"));
        fightActions.add(new Action("Chain lightning"));
    }

    /**
     * returns the current mana of the player if the player is a mage
     * @return the current mana of the player
     * @author Joni Baarda
     */
    @Override
    public int getEnergy() {
        return mana;
    }

    /**
     * sets the mana of the player to the minimum of the maximum mana and the new amount of mana
     * @param mana the new amount of mana for the player
     * @author Joni Baarda
     */
    @Override
    public void setEnergy(int mana) {
        this.mana = Math.min(MAX_MANA, mana);
    }

    /**
     * returns the available fight actions for a mage
     * @return the available fight actions
     * @author Joni Baarda
     */
    @Override
    public List<Action> getFightActions() {
        return new ArrayList<>(fightActions);
    }

    /**
     * executes the action that was chosen by calling the right method
     * @param action the action that is chosen by the player
     * @param target the current target of the player
     * @param allEnemies all available enemies in a scene
     * @param scene the current scene
     * @return a string of what happened
     * @author Joni Baarda
     */
    @Override
    public String fight(Action action, NPC target, List<NPC> allEnemies, Scene scene) {
        return switch (action.getActionName()) {
            case "Mana bolt" -> manaBoltAction(target);
            case "Heal" -> healAction();
            case "Chain lightning" -> chainLightningAction(allEnemies);
            default -> "";
        };
    }

    /**
     * the basic action of a mage. It checks if the player has enough mana, if they do they deal damage to themselves (possible if confused) or the target
     * @param target the player's target
     * @return a string of who was attacked for how much damage
     * @author Joni Baarda
     */
    private String manaBoltAction(NPC target) {
        int cost = 0;
        float damage = 1;
        if (cost > mana) {
            return "Not enough mana!";
        } else {
            mana -= cost;
            damage *= strength;
            String description = isConfused((int) damage);
            if (description.equals("")) {
                target.takeDamage(this, (int) damage);
                description = "You used mana bolt on " + target.getName() + " for " + damage + " damage.";
            }
            return description;
        }
    }

    /**
     * Heals the player if the player has enough mana
     * @return a string that the player was healed
     * @author Joni Baarda
     */
    private String healAction(){
        int cost = 15;
        int healing = 5;
        if(cost > mana){
            return "Not enough mana!";
        }else{
            mana -= cost;
            healing *= strength;
            health = Math.min(maxHealth, health + healing);
        }
        return "You healed for " + healing + ". Your current health is " + getHealth() + ".";
    }

    /**
     * empowered action of the mage. If the player has enough mana they deal damage to themselves (possible if confused) or all enemy targets
     * @param allEnemies a list of all NPCs in the scene
     * @return a string of who was attacked for how much damage
     * @author Joni Baarda
     */
    private String chainLightningAction(List<NPC> allEnemies) {
        int cost = 30;
        float damage = 5;
        if (cost > mana) {
            return "Not enough mana!";
        } else {
            mana -= cost;
            damage *= strength;
            String description = isConfused((int) damage);
            if (description.equals("")) {
                for (NPC npc : allEnemies) {
                    npc.takeDamage(this, (int) damage);
                }
                description = "You hit all enemies with lightning chain for " + damage + " damage.";
            }
            return description;
        }
    }

    /**
     * 
     */
    @Override
    public void useAppropriatePotion() {
        mana = Math.min(mana + 10, MAX_MANA);
    }
}
