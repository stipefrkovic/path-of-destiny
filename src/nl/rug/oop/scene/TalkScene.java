package nl.rug.oop.scene;

import nl.rug.oop.npc.NPC;
import nl.rug.oop.npc.SceneChange;
import nl.rug.oop.npc.TalkingNPC;
import nl.rug.oop.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a Scene that handles the dialogue between an npc and the player.
 * @author Jonas Scholz
 */
public class TalkScene extends Scene implements Serializable, NPCScene {

    private TalkingNPC person;
    private Scene nextScene;
    private Scene previousScene;
    private Player player;

    /**
     * Creates a new instance of the TalkScene and sets the relevant fields for this class.
     * @param image A string that can be associated with an image, to set the mood, basically, the theme of this scene.
     * @param nextScene The next scene if the dialogue is successful.
     * @param previousScene The previous scene if the dialogue is unsuccessful.
     * @param npc The talking npc that the player is talking to.
     * @param player The player that plays this game.
     */
    public TalkScene(String image, Scene nextScene, Scene previousScene, TalkingNPC npc, Player player) {
        super(image, "");
        this.nextScene = nextScene;
        this.previousScene = previousScene;
        this.person = npc;
        this.player = player;
        ArrayList<NPC> enemies = new ArrayList<>();
        enemies.add(person);
        this.addAction(new Action("Attack"), new FightScene(image, "You have initiated the fight, you are fighting against "+person.getName(), player, previousScene, nextScene, enemies));
        this.updateAvailableActions();
        this.setDescription(this.person.getCurrentDescription());
    }

    /**
     * Sets the previous scene.
     * @param previousScene The previous scene.
     */
    public void setPreviousScene(Scene previousScene){
        this.previousScene = previousScene;
    }

    /**
     * Executes the decision of the player and changes the scene correspondingly and decides which scene will be the next active scene.
     * @param action The action the user chose.
     * @return The scene that is active after the action was taken, can be null if the action is not allowed.
     */
    @Override
    public Scene takeAction(Action action) {
        if(action.getActionName().equals("Attack")){
            return super.takeAction(action);
        }
        person.takeActions(player, this, action, false);
        SceneChange sceneChange = person.nextScene();
        this.setDescription(this.person.getCurrentDescription());
        updateAvailableActions();
        return switch (sceneChange) {
            case PREVIOUS_SCENE -> previousScene;
            case NEXT_SCENE -> nextScene;
            case CURRENT_SCENE -> this;
        };
    }

    /**
     * Updates the available actions to the player.
     */
    protected void updateAvailableActions(){
        Scene temp = this.actions.get(new Action("Attack"));//Check if this actually works
        this.actions.clear();
        for (String answer:person.getPossibleAnswers()) {
            this.actions.put(new Action(answer), this);
        }
        this.actions.put(new Action("Attack"), temp);
    }

    /**
     * Returns the NPC the player is talking to.
     * @return A List with the singular npc the player is talking to.
     */
    @Override
    public List<NPC> getNPCs() {
        List<NPC> npcs = new ArrayList<>();
        npcs.add(person.clone());
        return npcs;
    }
}
