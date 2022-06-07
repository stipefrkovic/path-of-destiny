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

    public Mage(String name, int mana, int health, int maxHealth, int gold, float strength) {
        super(name, health, maxHealth, strength, gold);
        this.mana = mana;
        fightActions = new ArrayList<>();
        fightActions.add(new Action("mana bolt"));
        fightActions.add(new Action("heal"));
        fightActions.add(new Action("chain lightning"));
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public List<Action> getFightActions() {
        return fightActions;
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
            case "mana bolt" -> manaBoltAction(target);
            case "heal" -> healAction();
            case "chain lightning" -> chainLightningAction(allEnemies);
            default -> null;
        };
    }

    private String manaBoltAction(NPC target){
        int cost = 5;
        float damage = 5;
        if(cost > mana){
            return "Not enough mana!";
        }else{
            mana -= cost;
            damage *= strength;
            target.takeDamage(this, (int)damage);
        }
        return "You used mana bolt on " + target.getName() + " for " + damage + " damage.";
    }

    private String healAction(){
        int cost = 15;
        int healing = 15;
        if(cost > mana){
            return "Not enough mana!";
        }else{
            mana -= cost;
            healing *= strength;
            health += healing;
            if(health > maxHealth){
                health = maxHealth;
            }
        }
        return "You healed for " + healing + ". Your current health is " + getHealth() + ".";
    }

    private String chainLightningAction(List<NPC> allEnemies){
        int cost = 25;
        float damage = 25;
        if(cost > mana){
            return "Not enough mana!";
        }else{
            mana -= cost;
            damage *= strength;
            for (NPC npc:allEnemies) {
                npc.takeDamage(this, (int)damage);
            }
        }
        return "You hit all enemies with lightning chain for " + damage + " damage.";
    }

    @Override
    public void addKill(String type) {

    }

    @Override
    public String useItem(String itemName) {
        //Is there max for mana?
        mana += 10;
        return null;
    }

    @Override
    public void consumeAppropriately() {
        mana = Math.min(MAX_MANA, mana + 10);
    }
}
