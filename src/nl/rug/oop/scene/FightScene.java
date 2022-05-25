package nl.rug.oop.scene;

import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class FightScene extends Scene implements Serializable {

    private ArrayList<NPC> enemies;
    private Scene winScene;

    public FightScene(String image, String description, Player player, HashMap<Action, Scene> actions, Scene fleeScene, Scene winScene, ArrayList<NPC> enemies) {
        super(image, description);
        for (Action action:player.getFightActions()) {
            this.addAction(action, this);
        }
        this.addAction(new Action("Flee"), fleeScene);
        this.winScene = winScene;
        this.addActions(actions);

        this.enemies = enemies;
    }
}
