package nl.rug.oop.scene;

import nl.rug.oop.npc.TalkingNPC;

public class TalkScene extends Scene{

    private TalkingNPC person;
    private Scene nextScene;
    private Scene previousScene;

    public TalkScene(String image, String description, Scene nextScene, Scene previousScene, TalkingNPC npc) {
        super(image, description);
        this.nextScene = nextScene;
        this.previousScene = previousScene;
        this.person = npc;
    }

    @Override
    public Scene takeAction(Action action) {
        
        return super.takeAction(action);
    }
}
