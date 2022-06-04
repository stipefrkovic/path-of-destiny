package nl.rug.oop.npc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dialogue {

    private String text;
    private HashMap<String, Dialogue> possibleAnswers;
    private int whichSceneNext;

    public Dialogue(String text, HashMap<String, Dialogue> possibleAnswers, int whichSceneNext){
        this.text = text;
        this.possibleAnswers = possibleAnswers;
        this.whichSceneNext = whichSceneNext;
    }

    public void addAnswer(String text, Dialogue dialogue){
        this.possibleAnswers.put(text, dialogue);
    }

    public String getText() {
        return text;
    }

    public List<String> getActions(){
        return new ArrayList<>(this.possibleAnswers.keySet());
    }

    public int getWhichSceneNext() {
        return whichSceneNext;
    }

    public Dialogue answer(String answer){
        return this.possibleAnswers.get(answer);
    }
}
