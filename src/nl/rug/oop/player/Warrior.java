package nl.rug.oop.player;

import nl.rug.oop.npc.Entity;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Warrior class extends the Player class. Implements the possible player class warrior
 * @author Joni Baarda
 */
public  class Warrior extends Player{
    private int stamina;
    private final int MAX_STAMINA = 60;
    private boolean blockAction = false;
    private List<Action> fightActions;

    /**
     * initializes the warrior class with the initial values of the class
     * @param name the name of the player
     * @author Joni Baarda
     */
    public Warrior(String name) {
        super(name, 140, 140, 5, 0);
        this.stamina = 60;
        fightActions = new ArrayList<>();
        fightActions.add(new Action("Hit"));
        fightActions.add(new Action("Block"));
        fightActions.add(new Action("Slash"));
    }

    /**
     * returns the current stamina of the player if the player is a warrior
     * @return the current stamina of the player
     * @author Joni Baarda
     */
    public int getEnergy() {
        return stamina;
    }

    /**
     * sets the stamina of the player to the minimum of the maximum stamina and the new amount of stamina
     * @param stamina the new amount of stamina for the player
     * @author Joni Baarda
     */
    public void setEnergy(int stamina) {
        this.stamina = Math.min(MAX_STAMINA, stamina);
    }

    /**
     * returns the available fight actions for a warrior
     * @return the available fight actions
     * @author Joni Baarda
     */
    public List<Action> getFightActions() {
        return new ArrayList<>(fightActions);
    }

    /**
     * The basic action of the warrior. They do they deal damage to themselves (possible if confused) or the target
     * @param target the player's target
     * @return the string that the player attacked themselves or a target for damage
     * @author Joni Baarda
     */
    private String hitAction(NPC target) {
        float damage = 1;
        damage *= strength;
        damage += 3;
        String description = isConfused((int) damage);
        if (description.equals("")) {
            target.takeDamage(this, (int) damage);
            description = "You hit " + target.getName() + " on the head for " + damage + " damage.";
        }
        return description;
    }

    /**
     * Checks if the player has enough stamina and if so, sets blockAction to true so the player blocks the next enemy attack
     * @return the string with the description of what happened
     * @author Joni Baarda
     */
    private String blockAction(){
        int cost = 10;
        if(cost > stamina){
            return "Not enough stamina";
        } else{
            stamina -= cost;
            blockAction = true;
        }
        return "You block the next attack.";
    }

    /**
     * Checks if the player has enough stamina and if they have they either attack themselves (if confused) or the target for damage that is multiplied by the player's strength.
     * @param target the player's target
     * @return the string with the description of what happened
     * @author Joni Baarda
     */
    private String slashAction(NPC target) {
        int cost = 30;
        float damage = 6;
        if (cost > stamina) {
            return "Not enough stamina";
        } else {
            stamina -= cost;
            damage *= strength;
            String description = isConfused((int) damage);
            if (description.equals("")) {
                target.takeDamage(this, (int) damage);
                description = "You slash " + target.getName() + " for " + damage + " damage.";
            }
            return description;
        }
    }

    /**
     * Checks if the player blocks the next action, in which case the boolean if set to false and a string is returned, otherwise does damage to the player
     * @param attacker The entity that attacked this entity.
     * @param damage The amount of damage that was inflicted.
     * @return The string of what happened
     * @author Joni Baarda
     */
    @Override
    public String takeDamage(Entity attacker, int damage){
        if(blockAction){
            blockAction = false;
            return "You have successfully blocked the damage! ";
        } else{
            health -= damage;
            if(health<0){
                health = 0;
            }
            return attacker.getName()+" has attacked "+ this.getName() + " for " +  damage + " damage.";
        }
    }

    /**
     * Checks which action is taken and calls the right method to execute that action
     * @param action the action that is chosen
     * @param target the current target of the player
     * @param allEnemies all available enemies in a scene
     * @param scene the current scene
     * @return a string with the description of what happened
     * @author Joni Baarda
     */
    @Override
    public String fight(Action action, NPC target, List<NPC> allEnemies, Scene scene) {
        return switch (action.getActionName()) {
            case "Hit" -> hitAction(target);
            case "Block" -> blockAction();
            case "Slash" -> slashAction(target);
            default -> "";
        };
    }

    /**
     * consumes the stamina potion and takes the minimum of the current stamina plus 10 stamina or the maximum stamina the player can have to be the new stamina amount.
     * @author Joni Baarda
     */
    @Override
    public void useAppropriatePotion() {
        stamina = Math.min(stamina + 20, MAX_STAMINA);
    }
}
