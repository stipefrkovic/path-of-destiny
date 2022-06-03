package nl.rug.oop.npc;

import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.HashMap;
import java.util.List;

public class Trader extends TalkingNPC{

    public Trader(String name, String type, int maxHealth, int strength, Dialogue dialogue) {
        super(name, type, maxHealth, strength, dialogue);
    }

    @Override
    public String takeActions(Player player, Scene currentScene, Action action, boolean isFightAction) {
        String description = super.takeActions(player, currentScene, action, isFightAction);

        return description;
    }
}
