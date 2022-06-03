package nl.rug.oop.npc;

import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.List;

public class TalkingNPC extends NPC{

    private Dialogue currentDialogue;

    public TalkingNPC(String name, String type, int maxHealth, int strength, Dialogue dialogue){
        this.name = name;
        this.type = type;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.currentDialogue = dialogue;
    }

    @Override
    public String takeActions(Player player, Scene currentScene, Action action, boolean isFightAction) {
        if(isFightAction){
            return takeFightAction(player, currentScene, action);
        }else if(currentDialogue.getActions().contains(action.getActionName())){
            currentDialogue = currentDialogue.answer(action.getActionName());
            return getCurrentDescription();
        }else{
            throw new IllegalArgumentException("Illegal Action chosen");
        }
    }

    protected String takeFightAction(Player player, Scene currentScene, Action action){
        return this.getFullName()+"cowers in fear.";
    }

    public String getCurrentDescription(){
        return this.getFullName() + ": \"" + currentDialogue.getText() + "\"";
    }

    public List<String> getPossibleAnswers(){
        return currentDialogue.getActions();
    }

    public int nextScene(){
        return currentDialogue.getWhichSceneNext();
    }
}
