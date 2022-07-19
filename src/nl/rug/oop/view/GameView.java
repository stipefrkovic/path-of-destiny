package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;
import nl.rug.oop.model.OutputEventListener;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The GameView class for the RPG game (extension of JFrame with OutputEventListener).
 * @author sfrkovic
 */
public class GameView extends JFrame implements OutputEventListener {

    /**
     * Primary font for the text of the game.
     */
    private final Font font = new Font("Arial", Font.PLAIN, 20);
    /**
     * Primary background color.
     */
    private final Color backgroundColor = new Color(0x353839); // Onyx, https://html-color.codes/grey
    /**
     * Primary foreground color.
     */
    private final Color foregroundColor = Color.LIGHT_GRAY;
    /**
     * Name/title of the game.
     */
    private final String gameName = "Path of Destiny";
    /**
     * Layout manager for GameView.
     */
    private final CardLayout cardLayout = new CardLayout();
    /**
     * The GameView ContentPane.
     */
    private final Container contentPane = getContentPane();
    /**
     * (Start)Card which launches the game.
     */
    private StartCard startCard;
    /**
     * (Game)Card which displays the game.
     */
    private GameCard gameCard;
    /**
     * Controller (OutputEventListener) for the GameView and RpgGame.
     */
    private Controller controller;

    /**
     * Constructor for GameView.
     * Sets default values and adds controller.
     * @param controller controller (listener) for the buttons
     */
    public GameView(Controller controller) {
        setTitle(gameName);
        setLocation(0, 0);
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.controller = controller;
    }

    /**
     * Sets up the GameView: its layout, startCard, and GameCard.
     */
    public void setup() {
        setLayout(cardLayout);

        startCard = new StartCard(font, backgroundColor, foregroundColor, controller);
        startCard.setup();
        this.add(startCard, "Start Card");

        gameCard = new GameCard(font, backgroundColor, foregroundColor, controller);
        gameCard.setup();
        this.add(gameCard, "Game Card");

        this.setVisible(true);
    }

    /**
     * Method is activated when there is a change in the game and appropriate more specific update methods are called.
     * @param actions     A list of actions the player can make
     * @param description The description of the current scene.
     * @param image       The image of the current scene.
     * @param NPCs        The NPCs in the current scene, can be empty if no NPCs are in the current scene.
     * @param player      The player that plays the game.
     */
    @Override
    public void updateScene(ArrayList<String> actions, String description, String image, ArrayList<NPC> NPCs, Player player) {
        gameCard.updateElements(actions, description, image, NPCs, player);
        contentPane.revalidate();
        contentPane.repaint();
        cardLayout.show(contentPane, "Game Card");
    }

}
