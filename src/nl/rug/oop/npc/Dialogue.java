package nl.rug.oop.npc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A dialogue that is used by talking npcs to speak with the player.
 * @author Jonas Scholz
 */
public class Dialogue {

    private String text;
    private HashMap<String, Dialogue> possibleAnswers;
    private SceneChange whichSceneNext;

    /**
     * Initializes the attributes.
     * @param text What the npc should say.
     * @param possibleAnswers The answers that the player can give and to which dialogue that will lead.
     * @param whichSceneNext Which Scene should be displayed next, if this is a dialogue in the middle of other dialogues this value should be set to SceneChange.CURRENT_SCENE
     */
    public Dialogue(String text, HashMap<String, Dialogue> possibleAnswers, SceneChange whichSceneNext){
        this.text = text;
        this.possibleAnswers = possibleAnswers;
        this.whichSceneNext = whichSceneNext;
    }

    public Dialogue(SceneChange whichSceneNext){
        this("", null, whichSceneNext);
    }

    /**
     * Removes all possible answers to this dialogue.
     */
    protected void removeAllAnswers(){
        this.possibleAnswers.clear();
    }

    /**
     * Adds a possible answer to the current dialogue.
     * @param text What the player says.
     * @param dialogue The resulting dialogue if this answer is chosen.
     */
    public void addAnswer(String text, Dialogue dialogue){
        this.possibleAnswers.put(text, dialogue);
    }

    /**
     * Returns what the npc is saying.
     * @return What the npc is saying.
     */
    public String getText() {
        return text;
    }

    /**
     * Returns all possible answers
     * @return A list of possible answers
     */
    public List<String> getActions(){
        return new ArrayList<>(this.possibleAnswers.keySet());
    }

    /**
     * Returns which scene should be shown next.
     * @return Which scene should be shown next.
     */
    public SceneChange getWhichSceneNext() {
        return whichSceneNext;
    }

    /**
     * Returns the dialogue that corresponds to the chosen answer.
     * @param answer The chosen answer.
     * @return The dialogue that belongs to the dialogue.
     */
    public Dialogue answer(String answer){
        return this.possibleAnswers.get(answer);
    }
}
