package nl.rug.oop.dialogue;

import nl.rug.oop.npc.SceneChange;
import nl.rug.oop.player.Player;

import java.util.HashMap;

/**
 *
 * @author Jonas Scholz
 */
public class IncreaseMaxHealthDialogue extends Dialogue implements PepTalk{

    private int increaseMaxHealthValue;

    public IncreaseMaxHealthDialogue(String text, HashMap<String, Dialogue> possibleAnswers, SceneChange whichSceneNext, float increaseValue) {
        super(text, possibleAnswers, whichSceneNext);
        this.increaseMaxHealthValue = (int) increaseValue;
    }

    @Override
    public String powerUpPlayer(Player player) {
        return player.changeMaxHealth(increaseMaxHealthValue);
    }
}
