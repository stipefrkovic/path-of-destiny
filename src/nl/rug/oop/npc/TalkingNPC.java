package nl.rug.oop.npc;

import nl.rug.oop.dialogue.Dialogue;
import nl.rug.oop.dialogue.PepTalk;
import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * An NPC that has the ability to hold a dialogue with the player.
 * @author Jonas Scholz
 */
public class TalkingNPC extends NPC{

    protected Dialogue currentDialogue;
    private Dialogue initialDialogue;

    /**
     * Initializes all the attributes
     * @param name The name of the NPC.
     * @param type The type of the NPC, for example: Spider.
     * @param maxHealth The maximum health of the NPC.
     * @param strength The strength of the NPC.
     * @param dialogue The dialogue that the NPC can have with the player.
     * @param minGold The minimal amount of gold this NPC will have (inclusive).
     * @param maxGold The maximal amount of gold this NPC will have (exclusive).
     * @param lootItems The list of items that the npc will drop on defeat.
     */
    public TalkingNPC(String name, String type, int maxHealth, int strength, Dialogue dialogue, int minGold, int maxGold, ArrayList<Item> lootItems) {
        super(name, type, maxHealth, strength, minGold, maxGold, lootItems);
        this.currentDialogue = dialogue;
        this.initialDialogue = dialogue;
    }

    /**
     * React based on what the player said and through that advance through the dialogue.
     * @param player The player that plays that game.
     * @param currentScene The scene that is currently active, in which this NPC resides.
     * @param action The action that the user took.
     * @return The current description.
     */
    @Override
    protected String takeNonFightActions(Player player, Scene currentScene, Action action) {
        if(currentDialogue.getWhichSceneNext() == SceneChange.NEXT_SCENE || currentDialogue.getWhichSceneNext() == SceneChange.PREVIOUS_SCENE){
            currentDialogue = initialDialogue;
        }
        if(currentDialogue.getActions().contains(action.getActionName())){
            currentDialogue = currentDialogue.answer(action.getActionName());
            if(currentDialogue instanceof PepTalk){
                PepTalk pepTalk = (PepTalk) currentDialogue;
                pepTalk.powerUpPlayer(player);
            }
            return getCurrentDescription();
        }else{
            throw new IllegalArgumentException("Illegal Action chosen");
        }
    }

    /**
     * Returns what the NPC is currently saying.
     * @return A string that is what the NPC is currently saying.
     */
    public String getCurrentDescription(){
        return this.getName() + ": \"" + currentDialogue.getText() + "\"";
    }

    /**
     * Returns the possible answers that the player can give to what was said by the NPC.
     * @return The possible answers.
     */
    public List<String> getPossibleAnswers(){
        return currentDialogue.getActions();
    }

    /**
     * Returns which scene should be shown next.
     * @return One of the values of the SceneChange enum which dictates which scene is shown next.
     */
    public SceneChange nextScene(){
        return currentDialogue.getWhichSceneNext();
    }
}
