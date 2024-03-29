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
public class RpgGame implements Serializable {

    /**
     * The scene in which the player is currently situated in.
     */
    private Scene scene;

    /**
     * The player in the game.
     */
    private Player player;

    /**
     * The progression of the story.
     */
    private Story story;

    /**
     * Listeners which keep track of chosen actions.
     */
    private transient Collection<OutputEventListener> listeners = new ArrayList<>();

    /**
     * Initializes the relevant RPG game specifics.
     */
    public RpgGame() {
        story = new Story();
        scene = null;
        player = null;
    }

    /**
     * Adds a listener to the array of listeners in the model.
     * @param listener Java listener.
     */
    public void addListener(OutputEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Starts a new game using a scene for the new game.
     */
    public void startGame() {
        scene = story.getBeginningScene();
        notifyListeners();
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
        }
    }

    /**
     * Loads a game from a file called game.obj.
     */
    public void loadGame() {
        RpgGame game;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("game.obj"))) {
            game = (RpgGame) input.readObject();
            this.player = game.player;
            this.scene = game.scene;
            this.story = game.story;
            this.notifyListeners();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the game specifics, namely scene, according to the given action command. If the action command is a
     * name of a player class, it also creates a player.
     * @param a Name of the given action.
     */
    public void doAction(String a) {
        if(a.equals("Warrior") || a.equals("Mage")) {
            player = PlayerFactory.createPlayer(a);
            scene = story.createStory(player);
        }else{
            scene = scene.takeAction(new Action(a));
        }

        notifyListeners();
    }

    /**
     * Notifies the appropriate listeners based on the name of the action. It also checks whether a scene is an NPC
     * scene in order to see whether NPCs should be included when updating the scene.
     */
    public void notifyListeners() {
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