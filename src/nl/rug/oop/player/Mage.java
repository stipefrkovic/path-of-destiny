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

    public Mage(String name) {
        super(name, 80, 80, 5, 0);
        this.mana = 50;
        fightActions = new ArrayList<>();
        fightActions.add(new Action("mana bolt"));
        fightActions.add(new Action("heal"));
        fightActions.add(new Action("chain lightning"));
    }

    @Override
    public int getEnergy() {
        return mana;
    }

    @Override
    public void setEnergy(int mana) {
        this.mana = Math.min(MAX_MANA, mana);
    }

    @Override
    public List<Action> getFightActions() {
        return fightActions;
    }

    @Override
    public String fight(Action action, NPC target, List<NPC> allEnemies, Scene scene) {
        return switch (action.getActionName()) {
            case "mana bolt" -> manaBoltAction(target);
            case "heal" -> healAction();
            case "chain lightning" -> chainLightningAction(allEnemies);
            default -> null;
        };
    }

    private String manaBoltAction(NPC target) {
        int cost = 5;
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

    @Override
    public void useAppropriatePotion() {
        mana = Math.min(mana + 10, MAX_MANA);
    }
}
