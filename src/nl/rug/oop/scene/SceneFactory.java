package nl.rug.oop.scene;

import nl.rug.oop.items.Item;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.npc.TalkingNPC;
import nl.rug.oop.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A factory class that can create Scenes, given that they are registered and have a constructor, which fits one of the four types.
 * @author Jonas Scholz
 */
public class SceneFactory implements Serializable {

    private HashMap<String, Class> register = new HashMap();

    /**
     * Registers the standard scene types.
     */
    public SceneFactory(){
        registerSceneType("Scene", Scene.class);
        registerSceneType("FightScene", FightScene.class);
        registerSceneType("TalkScene", TalkScene.class);
        registerSceneType("LootScene", LootScene.class);
    }

    /**
     * Registers the class under the string key.
     * @param type The string under which the class is found.
     * @param sceneClass The class of the scene.
     */
    public void registerSceneType(String type, Class sceneClass){
        register.put(type, sceneClass);
    }

    /**
     * Creates a scene with only the image and description as parameters of the constructor, this creation might not fit all scenes.
     * @param type The key under which the scene was registered.
     * @param image The image/theme of the scene.
     * @param description The description of the scene.
     * @return The created Scene.
     */
    public Scene createSimpleScene(String type, String image, String description){
        try {
            Class npcType = register.get(type);
            return (Scene) npcType.getDeclaredConstructor(String.class, String.class).newInstance(image, description);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates a FightScene (or a child of it).
     * @param type The key under which the scene was registered.
     * @param image The image/theme of the scene.
     * @param description The description of the scene.
     * @param player The player that plays the game.
     * @param fleeScene The scene that the player goes to when he successfully flees from the fight.
     * @param winScene The scene that the player goes to when he wins against the enemies.
     * @param enemies An ArrayList of enemies the player has to defeat.
     * @return The created FightScene.
     */
    public FightScene createFightScene(String type, String image, String description, Player player, Scene fleeScene, Scene winScene, ArrayList<NPC> enemies){
        try {
            Class npcType = register.get(type);
            return (FightScene) npcType.getDeclaredConstructor(String.class, String.class, Player.class, Scene.class, Scene.class, ArrayList.class).newInstance(image, description, player, fleeScene, winScene, enemies);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates a LootScene (or a child of it).
     * @param type The key under which the scene was registered.
     * @param image The image/theme of the scene.
     * @param description The description of the scene.
     * @param actions A Hashmap of actions and corresponding scenes.
     * @param possibleLoot A Hashmap of actions and corresponding List of items associated with taking that actions.
     * @param player The player that plays the game.
     * @return The created LootScene.
     */
    public FightScene createLootScene(String type, String image, String description, HashMap<Action, Scene> actions, HashMap<Action, List<Item>> possibleLoot, Player player){
        try {
            Class npcType = register.get(type);
            return (FightScene) npcType.getDeclaredConstructor(String.class, String.class, HashMap.class, HashMap.class, Player.class).newInstance(image, description, actions, possibleLoot, player);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates a TalkScene (or a child of it).
     * @param type The key under which the scene was registered.
     * @param image The image/theme of the scene.
     * @param nextScene The next scene if the dialogue is successful.
     * @param previousScene The previous scene if the dialogue is unsuccessful.
     * @param npc The talking npc that the player is talking to.
     * @param player The player that plays this game.
     * @return The created TalkScene.
     */
    public TalkScene createTalkScene(String type, String image, Scene nextScene, Scene previousScene, TalkingNPC npc, Player player){
        try {
            Class npcType = register.get(type);
            return (TalkScene) npcType.getDeclaredConstructor(String.class, Scene.class, Scene.class, TalkingNPC.class, Player.class).newInstance(image, nextScene, previousScene, npc, player);
        } catch (Exception e) {
            return null;
        }
    }

}
