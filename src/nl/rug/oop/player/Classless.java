package nl.rug.oop.player;

import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class Classless extends Player{

    public Classless() {
        super("You", 10, 10, 0, 0);
    }

    @Override
    public List<Action> getFightActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new Action("Punch"));
        actions.add(new Action("Kick"));
        return actions;
    }

    @Override
    public String attack(Action action, NPC target, List<NPC> allEnemies, Scene scene) {
        return switch (action.getActionName()){
            case "Punch" -> this.getName() + " punch "+ target + " for 0 damage.";
            default -> this.getName() + " kick "+ target + " for 0 damage.";
        };
    }

    @Override
    public void consumeAppropriately() {

    }
}
