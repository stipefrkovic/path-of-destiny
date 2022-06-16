package nl.rug.oop.dialogue;

import nl.rug.oop.npc.SceneChange;
import nl.rug.oop.player.Player;

import java.util.HashMap;

public class IncreaseStrengthDialogue extends Dialogue implements PepTalk{

    private float increaseStrength;

    public IncreaseStrengthDialogue(String text, HashMap<String, Dialogue> possibleAnswers, SceneChange whichSceneNext, Float increaseStrength) {
        super(text, possibleAnswers, whichSceneNext);
        this.increaseStrength = increaseStrength;
    }

    @Override
    public String powerUpPlayer(Player player) {
        player.setBaseStrength(player.getBaseStrength() + increaseStrength);
        return player.getName() + " increased your strength by " + increaseStrength + "." ;
    }
}
