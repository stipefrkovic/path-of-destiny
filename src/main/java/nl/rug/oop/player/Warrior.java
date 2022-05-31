package nl.rug.oop.player;

import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.List;

/**
 * Class extended from the Player class. Implements the possible player class warrior
 * @author Joni Baarda
 */
public  class Warrior extends Player{
    //health = 100; strength = 5; stamina = 50
    //actions: hit (basic), block (defense), slash (empowered)
    private int stamina;
    private final int maxStamina = 50;

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
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
}
