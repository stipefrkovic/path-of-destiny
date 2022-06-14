package nl.rug.oop.model;

import nl.rug.oop.player.Player;
import nl.rug.oop.player.PlayerFactory;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.NPCScene;
import nl.rug.oop.scene.Scene;
import nl.rug.oop.story.Story;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * The main model which keeps track of the relevant RPG game specifics and notifies game view based on the input from
 * the game controller.
 * @author Andro Erdelez
 */
public class RpgGame implements Serializable{
    private Scene scene;
    private Player player;
    private Story story;
    Collection<OutputEventListener> listeners = new ArrayList<>();

    /**
     * Initializes the relevant RPG game specifics.
     */
    public RpgGame() {
        story = new Story();
        scene = story.getBeginningScene();
        player = null;
    }

    /**
     * Adds a listener to the array of listeners in the model.
     * @param listener Listener.
     */
    public void addListener(OutputEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Starts a new game.
     * @return Game (RpgGame object) with the initial scene and picked player class.
     */
    public void startGame() {
        scene = story.getBeginningScene();
        Iterator<OutputEventListener> allListeners = listeners.iterator();
        while (allListeners.hasNext()) {
            allListeners.next().updateScene(scene.getActions(), scene.getDescription(), scene.getImage(), null, player);
        }
    }

    /**
     * Saves a game (RpgGame object) to a file called game.obj.
     * @param game Game to save.
     */
    public static void saveGame(RpgGame game) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("game.obj"))) {
            output.writeObject(game);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Loads a game (RpgGame object) from a file called game.obj.
     * @return Game loaded from the file
     */
    public void loadGame() {
        RpgGame game;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("game.obj"))) {
            game = (RpgGame) input.readObject();
            this.player = game.player;
            this.scene = game.scene;
            this.story = game.story;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Notifies the appropriate listeners based on the name of the action. If
     * @param a Name of the given action.
     */
    public void doAction(String a) {
        if(a.equals("Warrior") || a.equals("Mage")) {
            player = PlayerFactory.createPlayer(a);
            scene = story.createStory(player);
        }else{
            scene = scene.takeAction(new Action(a));
        }

        Iterator<OutputEventListener> allListeners = listeners.iterator();
        while (allListeners.hasNext()) {
            if(scene instanceof NPCScene) {
                NPCScene npcScene = (NPCScene) scene;
                allListeners.next().updateScene(scene.getActions(), scene.getDescription(), scene.getImage(), npcScene.getNPCs(), player);
            } else {
                allListeners.next().updateScene(scene.getActions(), scene.getDescription(), scene.getImage(), null, player);
            }
        }
    }
}