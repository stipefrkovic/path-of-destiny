package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;
import nl.rug.oop.model.OutputEventListener;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The GameView (JFrame extended class with OutputEventListener) class for the RPG game.
 * @author sfrkovic
 */
public class GameView extends JFrame implements OutputEventListener {

    /**
     * Primary font for the text of the game
     */
    private Font font = new Font("Arial", Font.PLAIN, 20);
    /**
     * Primary color for the background and buttons
     */
    private Color color = new Color(0x353839); // Onyx, https://html-color.codes/grey
    /**
     * Name of the game displayed on the title bar
     */
    private String gameName = "Path of Destiny";
    /**
     * Layout manager for the GameView (JFrame)
     */
    private CardLayout cardLayout = new CardLayout();
    /**
     * The content pane of the GameView (JFrame)
     */
    private Container contentPane = getContentPane();
    private StartCard startCard;
    private GameCard gameCard;
    /**
     * Controller (OutputEventListener) for the View and rpg game
     */
    private Controller controller;

    /**
     * Constructor for GameView object
     * @param controller controller that acts as an ActionListener for the buttons
     */
    public GameView(Controller controller) {
        setTitle(gameName);
        setLocation(0, 0);
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.controller = controller;
    }

    /**
     * Method sets up the view and its 2 cards: startCard and gameCard
     */
    public void setup() {
        setLayout(cardLayout);

        startCard = new StartCard(font, color, controller);
        startCard.setup();
        this.add(startCard, "Start Card");

        gameCard = new GameCard(font, color, controller);
        gameCard.setup();
        this.add(gameCard, "Game Card");

        this.setVisible(true);
    }

    /**
     * The updateScene method which is activated when there is a change in the model
     * and calls appropriate view update method (updateScene)
     *
     * @param actions     A list of possible valid actions that can be chosen.
     * @param description The description of the current scene.
     * @param image       The image/theme of the current scene.
     * @param NPCs        The NPCs in the current scene, can be empty if no NPCs are in the current scene.
     * @param player      The player that plays the game.
     */
    @Override
    public void updateScene(List<String> actions, String description, String image, List<NPC> NPCs, Player player) {
        gameCard.updateElements(actions, description, image, NPCs, player);
        contentPane.revalidate();
        contentPane.repaint();
        cardLayout.show(contentPane, "Game Card");
    }

}