package nl.rug.oop.player;

import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Class extended from the Player class. Implements the possible player class warrior
 * @author Joni Baarda
 */
public  class Warrior extends Player{
    //health = 100; strength = 5; stamina = 50
    //actions: hit (basic), block (defense), slash (empowered)
    //For the actions I need: name, cost, strength multiplier
    private int stamina;
    private final int maxStamina = 50;

    public Warrior(int stamina, int health, int maxHealth, float strength, int gold) {
        this.stamina = stamina;
        this.health = health;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.gold = gold;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    @Override
    public List<Action> getFightActions() {
        //get the list of possible actions somehow?
        //return the list of possible actions?
        List<Action> fightActions = new ArrayList<>();
        return fightActions;
    }

    @Override
    public List<String> getInventory() {
        return null;
    }

    @Override
    public String attack(Action action, NPC target, List<NPC> allEnemies, Scene scene) {
        //Return the string of which enemy is attacked or the scene of all enemies and which to choose?
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
