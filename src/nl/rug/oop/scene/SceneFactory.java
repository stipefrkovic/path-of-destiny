package nl.rug.oop.scene;

import nl.rug.oop.items.Item;
import nl.rug.oop.npc.Dialogue;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.npc.TalkingNPC;
import nl.rug.oop.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SceneFactory {

    private HashMap<String, Class> register = new HashMap();

    public SceneFactory(){
        registerSceneType("Scene", Scene.class);
        registerSceneType("FightScene", FightScene.class);
        registerSceneType("TalkScene", TalkScene.class);
    }

    public void registerSceneType(String type, Class sceneClass){
        register.put(type, sceneClass);
    }

    public Scene createSimpleScene(String type, String image, String description){
        try {
            Class npcType = register.get(type);
            return (Scene) npcType.getDeclaredConstructor(String.class, String.class).newInstance(image, description);
        } catch (Exception e) {
            return null;
        }
    }

    public FightScene createFightScene(String type, String image, String description, Player player, Scene fleeScene, Scene winScene, ArrayList<NPC> enemies){
        try {
            Class npcType = register.get(type);
            return (FightScene) npcType.getDeclaredConstructor(String.class, String.class, Player.class, Scene.class, Scene.class, ArrayList.class).newInstance(image, description, player, fleeScene, winScene, enemies);
        } catch (Exception e) {
            return null;
        }
    }

    public FightScene createLootScene(String type, String image, String description, HashMap<Action, Scene> actions, HashMap<Action, List<Item>> possibleLoot, Player player){
        try {
            Class npcType = register.get(type);
            return (FightScene) npcType.getDeclaredConstructor(String.class, String.class, HashMap.class, HashMap.class, Player.class).newInstance(image, description, actions, possibleLoot, player);
        } catch (Exception e) {
            return null;
        }
    }

    public TalkScene createTalkScene(String type, String image, String description, Scene nextScene, Scene previousScene, TalkingNPC npc, Player player){
        try {
            Class npcType = register.get(type);
            return (TalkScene) npcType.getDeclaredConstructor(String.class, String.class, Scene.class, Scene.class, TalkingNPC.class, Player.class).newInstance(image, description, nextScene, previousScene, npc, player);
        } catch (Exception e) {
            return null;
        }
    }

}
