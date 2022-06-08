package nl.rug.oop.npc;

import nl.rug.oop.effects.WeaknessEffect;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.HashMap;

public class KingBoss extends BossNPC{

    private boolean reflectDamage = false;

    public KingBoss() {
        super("Leopold", "King", 400, 12, new Dialogue("You think that you can defeat me.", new HashMap<>(), 0));
    }

    @Override
    protected String onFirstBelow75Health(Player player, Scene currentScene, Action action) {
        this.strength *= 1.25;
        return this.getName() + " is enraged. ";
    }

    @Override
    protected String onFirstBelow50Health(Player player, Scene currentScene, Action action) {
        return this.getName()+ " uses his aura to suppress you. " + player.addEffect(new WeaknessEffect());
    }

    @Override
    protected String onFirstBelow25Health(Player player, Scene currentScene, Action action) {
        reflectDamage = true;
        return this.getName()+ " starts to glow and ethereal thorns sprout from his armor. ";
    }

    @Override
    public String takeDamage(Entity attacker, int damage) {
        if (reflectDamage && attacker != this){
            return super.takeDamage(attacker, damage) + " " + attacker.takeDamage(this, (int) (damage*0.25));
        }
        return super.takeDamage(attacker, damage);
    }
}