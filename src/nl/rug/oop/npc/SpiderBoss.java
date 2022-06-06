package nl.rug.oop.npc;

import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.HashMap;

public class SpiderBoss extends BossNPC{

    public SpiderBoss(Dialogue dialogue) {
        super("Wren", "Weaver", 200, 8, new Dialogue("You will never defeat me.", new HashMap<>(), 0));
    }

    @Override
    protected String onFirstBelow75Health(Player player, Scene currentScene, Action action) {
        return "";
    }

    @Override
    protected String onFirstBelow50Health(Player player, Scene currentScene, Action action) {
        return "";
    }

    @Override
    protected String onFirstBelow25Health(Player player, Scene currentScene, Action action) {
        return "";
    }
}
