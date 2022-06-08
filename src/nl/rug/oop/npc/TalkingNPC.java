package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class TalkingNPC extends NPC{

    protected Dialogue currentDialogue;

    public TalkingNPC(String name, String type, int maxHealth, int strength, Dialogue dialogue, int minGold, int maxGold, ArrayList<Item> lootItems){
        super(name, type, maxHealth, strength, minGold, maxGold, lootItems);
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
        return this.getName()+"cowers in fear.";
    }

    public String getCurrentDescription(){
        return this.getName() + ": \"" + currentDialogue.getText() + "\"";
    }

    public List<String> getPossibleAnswers(){
        return currentDialogue.getActions();
    }

    public int nextScene(){
        return currentDialogue.getWhichSceneNext();
    }
}
