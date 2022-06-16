package nl.rug.oop.dialogue;

import nl.rug.oop.npc.SceneChange;
import nl.rug.oop.player.Player;

import java.util.HashMap;

/**
 * Increases the base strength of the player when this dialogue becomes active.
 * @author Jonas Scholz
 */
public class IncreaseStrengthDialogue extends Dialogue implements PepTalk{

    private float increaseStrength;

    /**
     * Constructor that initializes all the attributes.
     * @param text What the npc should say.
     * @param possibleAnswers The answers that the player can give and to which dialogue that will lead.
     * @param whichSceneNext Which Scene should be displayed next, if this is a dialogue in the middle of other dialogues this value should be set to SceneChange.CURRENT_SCENE
     * @param increaseStrength The amount by which the base strength is supposed to be increased.
     */
    public IncreaseStrengthDialogue(String text, HashMap<String, Dialogue> possibleAnswers, SceneChange whichSceneNext, Float increaseStrength) {
        super(text, possibleAnswers, whichSceneNext);
        this.increaseStrength = increaseStrength;
    }

    /**
     * Increases the strength of the player.
     * @param player The player that plays the game.
     * @return A description of the increase in strength.
     */
    @Override
    public String powerUpPlayer(Player player) {
        player.setBaseStrength(player.getBaseStrength() + increaseStrength);
        return player.getName() + " increased your strength by " + increaseStrength + "." ;
    }
}
