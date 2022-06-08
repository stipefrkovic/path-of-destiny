package nl.rug.oop.scene;

import nl.rug.oop.npc.NPC;
import nl.rug.oop.npc.TalkingNPC;
import nl.rug.oop.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TalkScene extends Scene implements Serializable, NPCScene {

    private TalkingNPC person;
    private Scene nextScene;
    private Scene previousScene;
    private Player player;

    public TalkScene(String image, Scene nextScene, Scene previousScene, TalkingNPC npc, Player player) {
        super(image, "");
        this.nextScene = nextScene;
        this.previousScene = previousScene;
        this.person = npc;
        this.player = player;
        ArrayList<NPC> enemies = new ArrayList<>();
        enemies.add(person);
        this.addAction(new Action("Attack"), new FightScene(image, "You have initiated the fight, you are fighting against "+person.getName(), player, previousScene, nextScene, enemies));
        this.setDescription(this.person.getCurrentDescription());
    }

    public void setPreviousScene(Scene previousScene){
        this.previousScene = previousScene;
    }

    @Override
    public Scene takeAction(Action action) {
        if(action.getActionName().equals("Attack")){
            return super.takeAction(action);
        }
        person.takeActions(player, this, action, false);
        this.setDescription(this.person.getCurrentDescription());
        updateAvailableActions();
        return switch (person.nextScene()) {
            case -1 -> previousScene;
            case 1 -> nextScene;
            default -> this;
        };
    }

    protected void updateAvailableActions(){
        Scene temp = this.actions.get(new Action("Attack"));//Check if this actually works
        this.actions.clear();
        this.actions.put(new Action("Attack"), temp);
        for (String answer:person.getPossibleAnswers()) {
            this.actions.put(new Action(answer), this);
        }
    }

    @Override
    public List<NPC> getNPCs() {
        List<NPC> npcs = new ArrayList<>();
        npcs.add(person.clone());
        return npcs;
    }
}
