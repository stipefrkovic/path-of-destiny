package nl.rug.oop.dialogue;

import nl.rug.oop.npc.SceneChange;
import nl.rug.oop.player.Player;

import java.util.HashMap;

/**
 * Increases the maximum health of the player when this dialogue becomes active.
 * @author Jonas Scholz
 */
public class IncreaseMaxHealthDialogue extends Dialogue implements PepTalk{

    private int increaseMaxHealthValue;

    /**
     * Constructor that initializes all the attributes.
     * @param text What the npc should say.
     * @param possibleAnswers The answers that the player can give and to which dialogue that will lead.
     * @param whichSceneNext Which Scene should be displayed next, if this is a dialogue in the middle of other dialogues this value should be set to SceneChange.CURRENT_SCENE
     * @param increaseValue The amount by which the maximum Health is supposed to be increased.
     */
    public IncreaseMaxHealthDialogue(String text, HashMap<String, Dialogue> possibleAnswers, SceneChange whichSceneNext, Float increaseValue) {
        super(text, possibleAnswers, whichSceneNext);
        this.increaseMaxHealthValue = increaseValue.intValue();
    }

    /**
     * Increases the maximum health of the player.
     * @param player The player that plays the game.
     * @return The description of the increase of health.
     */
    @Override
    public String powerUpPlayer(Player player) {
        return player.changeMaxHealth(increaseMaxHealthValue);
    }
}
