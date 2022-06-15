package nl.rug.oop.player;

import nl.rug.oop.items.Item;
import nl.rug.oop.npc.Entity;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Warrior class extends the Player class. Implements the possible player class warrior
 * @author Joni Baarda
 */
public  class Warrior extends Player{
    private int stamina;
    private final int MAX_STAMINA = 50;
    private boolean blockAction = false;
    private List<Action> fightActions;

    public Warrior(String name) {
        super(name, 100, 100, 5, 0);
        this.stamina = 50;
        fightActions = new ArrayList<>();
        fightActions.add(new Action("hit"));
        fightActions.add(new Action("block"));
        fightActions.add(new Action("slash"));
    }

    public int getEnergy() {
        return stamina;
    }

    public void setEnergy(int stamina) {
        this.stamina = Math.min(MAX_STAMINA, stamina);
    }

    public List<Action> getFightActions() {
        return fightActions;
    }

    private String hitAction(NPC target) {
        int cost = 5;
        float damage = 1;
        if (cost > stamina) {
            return "Not enough stamina";
        } else {
            stamina -= cost;
            damage *= strength;
            String description = isConfused((int) damage);
            if (description.equals("")) {
                target.takeDamage(this, (int) damage);
                description = "You hit " + target.getName() + " on the head for " + damage + " damage.";
            }
            return description;
        }
    }

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

    @Override
    public String takeDamage(Entity attacker, int damage){
        if(blockAction){
            blockAction = false;
            return "You have successfully blocked the damage!";
        } else{
            health -= damage;
            if(health<0){
                health = 0;
            }
            return attacker.getName()+" has attacked "+ this.getName() + " for " +  damage + " damage.";
        }
    }

    @Override
    public String fight(Action action, NPC target, List<NPC> allEnemies, Scene scene) {
        return switch (action.getActionName()) {
            case "hit" -> hitAction(target);
            case "block" -> blockAction();
            case "slash" -> slashAction(target);
            default -> "";
        };
    }

    @Override
    public void useAppropriatePotion() {
        stamina = Math.min(stamina + 10, MAX_STAMINA);
    }
}
