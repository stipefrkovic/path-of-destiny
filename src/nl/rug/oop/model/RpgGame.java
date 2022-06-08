package nl.rug.oop.model;

import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RpgGame implements OutputEventListener {
    private Scene scene;
    private Player player;
    Collection<PropertyChangeListener> listeners = new ArrayList<>();

    public RpgGame(Scene scene, Player player) {
        this.scene = scene;
        this.player = player;
    }

    /**
     * Method adds a listener to the array of listeners in the model.
     * @param listener listener
     */
    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Starts a new game
     * @param scene Initial scene of a new game
     * @param player Player class picked by a user
     * @return Game (RpgGame object) with the initial scene and picked player class
     */
    public static RpgGame startGame(Scene scene, Player player) {
        return new RpgGame(scene, player);
    }

    /**
     * Saves a game (RpgGame object) to a file called game.obj
     * @param game Game to save
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
     * Loads a game (RpgGame object) from a file called game.obj
     * @return Game loaded from the file
     */
    public static RpgGame loadGame() {
        RpgGame game = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("game.obj"))) {
            game = (RpgGame) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }

    public void doAction(String a) {
        scene = scene.takeAction(a);
        updateScene(scene.getActions(), scene.getDescription(), scene.getImage(), scene.getNPCs());
    }

    @Override
    public void updateScene(List<String> actions, String description, String image, List<NPC> npcs) {

    }
}
