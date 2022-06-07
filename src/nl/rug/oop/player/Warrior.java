package nl.rug.oop.player;

import nl.rug.oop.items.Item;
import nl.rug.oop.npc.Entity;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Class extends the Player class. Implements the possible player class warrior
 * @author Joni Baarda
 */
public  class Warrior extends Player{
    //health = 100; strength = 5; stamina = 50
    private int stamina;
    private final int MAX_STAMINA = 50;
    private boolean blockAction = false;
    private List<Action> fightActions;

    public Warrior(String name, int stamina, int health, int maxHealth, int gold, float strength) {
        super(name, health, maxHealth, strength, gold);
        this.stamina = stamina;
        fightActions = new ArrayList<>();
        fightActions.add(new Action("hit"));
        fightActions.add(new Action("block"));
        fightActions.add(new Action("slash"));
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    @Override
    public List<Action> getFightActions() {
        return fightActions;
    }

    private String hitAction(NPC target){
        int cost = 5;
        float damage = 5;
        if(cost > stamina){
            return "Not enough stamina";
        }else{
            stamina -= cost;
            damage *= strength;
            target.takeDamage(this, (int)damage);
        }
        return "You hit " + target.getName() + " on the head for " + damage + " damage.";
    }

    private String blockAction(){
        int cost = 10;
        float damage = 0;
        if(cost > stamina){
            return "Not enough stamina";
        } else{
            stamina -= cost;
            blockAction = true;
        }
        return "You block the next attack.";
    }

    private String slashAction(NPC target){
        int cost = 20;
        float damage = 10;
        if(cost > stamina){
            return "Not enough stamina";
        } else{
            stamina -= cost;
            damage *= strength;
            target.takeDamage(this, (int)damage);
        }
        return "You slash " + target.getName() + " for " + damage + " damage.";
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
    public List<String> getInventory() {
        return null;
    }

    @Override
    public List<Item> getInventoryItems() {
        return null;
    }

    @Override
    public String attack(Action action, NPC target, List<NPC> allEnemies, Scene scene) {
        return switch (action.getActionName()) {
            case "hit" -> hitAction(target);
            case "block" -> blockAction();
            case "slash" -> slashAction(target);
            default -> null;
        };
    }

    @Override
    public void addKill(String type) {

    }

    @Override
    public String useItem(String itemName) {
        //Is there a max for stamina?
        stamina += 10;
        return null;
    }

    @Override
    public void consumeAppropriately() {
        stamina = Math.min(MAX_STAMINA, stamina + 10);
    }
}
