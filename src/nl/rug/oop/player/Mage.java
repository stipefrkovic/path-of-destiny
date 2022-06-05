package nl.rug.oop.player;

import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.List;

/**
 * Class extended from the Player class. Implements the possible player class mage
 * @author Joni Baarda
 */
public class Mage extends Player{
    //health = 80; strength = 5; mana = 50
    //actions: mana bolt (basic), heal (defense), chain lightning (empowered)
    private int mana;
    private final int maxMana = 50;

    public Mage(int mana, int health, int maxHealth, float strength, int gold) {
        this.mana = mana;
        this.health = health;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.gold = gold;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public List<Action> getFightActions() {
        return null;
    }

    @Override
    public List<String> getInventory() {
        return null;
    }

    @Override
    public String attack(Action action, NPC target, List<NPC> allEnemies, Scene scene) {
        return null;
    }

    @Override
    public void addKill(String type) {

    }

    @Override
    public String useItem(String itemName) {
        return null;
    }
}
