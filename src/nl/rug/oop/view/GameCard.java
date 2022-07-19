package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class GameCard which is an extension of Card.
 * It has a BorderLayout and the corresponding 5 panels:
 *  North - playerPanel which holds the start and load buttons and player information, inventory, and effects
 *  West - actionPanel which holds the actions a player can take
 *  Center - backgroundPanel which shows the background of the scene
 *  East - npcPanel which holds the NPCs in the current scene
 *  South - descriptionPane which holds the description of the scene
 * @author sfrkovic
 */
public class GameCard extends Card {
    /**
     * Player panel added to North of GameCard's BorderLayout.
     * It contains: saveButton, loadButton, informationPanel, inventoryPanel, effectsPanel.
     */
    private JPanel playerPanel;
    /**
     * Button that saves the current state of the game.
     */
    private JButton saveButton;
    /**
     * Button that loads the saved state of the game.
     */
    private JButton loadButton;
    /**
     * DictionaryPanel that shows the player information:
     *  labels and amounts of health, energy(mana/stamina), strength, gold.
     */
    private DictionaryDynamicPanel informationPanel;
    /**
     * DictionaryPanel that shows the labels and amounts of items in the player inventory:
     *  possible items are health, mana, stamina, and remove effects potions.
     */
    private DictionaryDynamicPanel inventoryPanel;
    /**
     * ArrayDynamicPanel that shows the labels of effects the player is experiencing:
     *  possible effects are confused, poisoned, stunned, and weak.
     */
    private ArrayDynamicPanel effectsPanel;
    /**
     * ArrayDynamicPanel that shows the actions (buttons) a player can take.
     */
    private ArrayDynamicPanel actionsPanel;
    /**
     * BackGround panel that displays the background image of the current scene.
     */
    private BackgroundPanel backgroundPanel;
    /**
     * ArrayDynamicPanel that shows the NPCs (labels) in the current scene.
     */
    private ArrayDynamicPanel npcPanel;
    /**
     * TextPane that shows the description of the current scene.
     */
    private JTextPane descriptionPane;

    /**
     * Constructor for GameCard that calls Card constructor with BorderLayout and given parameter values.
     * @param font Font for GameCard
     * @param backgroundColor background Color for GameCard
     * @param foregroundColor foreground Color for GameCard
     * @param controller Controller for buttons in GameCard
     */
    public GameCard(Font font, Color backgroundColor, Color foregroundColor,Controller controller) {
        super(new BorderLayout(10, 10), font, backgroundColor, foregroundColor, controller);
    }

    /**
     * Method sets up the GameCard with a BorderLayout and its panels:
     *  North - playerPanel which holds the start and load buttons and player information, inventory, and effects
     *  West - actionPanel which holds the actions a player can take
     *  Center - backgroundPanel which shows the background of the scene
     *  East - npcPanel which holds the NPCs in the current scene
     *  South - descriptionPane which holds the description of the scene
     */
    @Override
    public void setup() {
        saveButton = new DepthButton("Save game", font, backgroundColor, controller);
        loadButton = new DepthButton("Load game", font, backgroundColor, controller);
        informationPanel = new DictionaryDynamicPanel(new GridLayout(1, 0), font, backgroundColor, foregroundColor, controller);
        inventoryPanel = new DictionaryDynamicPanel(new GridLayout(1, 0), font, backgroundColor, foregroundColor, controller);
        effectsPanel = new ArrayDynamicPanel(new GridLayout(1, 0), font, backgroundColor, foregroundColor, controller);
        playerPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        playerPanel.setLayout(gridBagLayout);
        playerPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.ipadx = 40;
        gbc.ipady = 5;
        gbc.anchor = GridBagConstraints.WEST;
        playerPanel.add(saveButton, gbc);
        playerPanel.add(loadButton, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        playerPanel.add(informationPanel, gbc);
        playerPanel.add(inventoryPanel, gbc);
        playerPanel.add(effectsPanel, gbc);

        actionsPanel = new ArrayDynamicPanel(new GridLayout(0, 1, 0, 10), font, backgroundColor, foregroundColor, controller);

        // NullPointerException is thrown if the resource can't be found
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Welcome.png")));
        backgroundPanel = new BackgroundPanel(backgroundImage.getImage(), BackgroundPanel.SCALED);

        npcPanel = new ArrayDynamicPanel(new GridLayout(5, 2), font, backgroundColor, foregroundColor, controller);

        descriptionPane = new JTextPane();
        descriptionPane.setFont(font);
        descriptionPane.setBackground(backgroundColor);
        descriptionPane.setForeground(foregroundColor);
        descriptionPane.setEditable(false);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(backgroundColor);
        add(playerPanel, BorderLayout.NORTH);
        add(actionsPanel, BorderLayout.WEST);
        add(backgroundPanel, BorderLayout.CENTER);
        add(npcPanel, BorderLayout.EAST);
        add(descriptionPane, BorderLayout.SOUTH);
    }

    /**
     * Update the GameCard by calling the update methods of the GameCard's panels
     *  and passing the corresponding information obtained from the game.
     * @param actions ArrayList of String actions available to the player
     * @param description String description of the scene
     * @param image String of the background image
     * @param NPCs ArrayList of String NPCs in the scene
     * @param player Player object containing current information, inventory, and effects of a player
     */
    public void updateElements(ArrayList<String> actions, String description, String image, ArrayList<NPC> NPCs, Player player) {
        updatePlayerPanel(player);
        updateActionsPanel(actions);
        updateBackgroundPanel(image);
        updateNpcPanel(NPCs);
        updateDescriptionPane(description);
    }

    /**
     * Update playerPanel by updating Labels in informationPanel, inventoryPanel, and effectsPanel
     * @param player Player object containing current information, inventory, and effects of a player
     */
    private void updatePlayerPanel(Player player) {
        if (player != null) {
            HashMap<String, String> playerInformation = player.getPlayerInformation();
            informationPanel.createComponents(playerInformation);

            // casting is needed
            HashMap<String, String> playerInventory = new HashMap(player.getInventoryCount());
            inventoryPanel.createComponents(playerInventory);

            ArrayList<String> effectsIcons = player.getEffects();
            ArrayList<String> effectsToolTips = new ArrayList<>();
            for (String l : effectsIcons) {
                effectsToolTips.add("Effect: " + l);
            }
            effectsPanel.createLabels(effectsIcons, effectsToolTips);
        }
    }

    /**
     * Update actionsPanel panel with Buttons i.e. actions the player can take.
     * @param actions ArrayList of String actions available to the player
     */
    private void updateActionsPanel(ArrayList<String> actions) {
        actionsPanel.createButtons(actions);
    }

    /**
     * Update backgroundPanel to display the background image of the scene.
     * @param image String of the background image
     */
    private void updateBackgroundPanel(String image) {
        if (image != null) {
            // NullPointerException is thrown if the resource can't be found
            ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/" + image + ".png")));
            backgroundPanel.setImage(backgroundImage.getImage());
        }
    }

    /**
     * Update npcPanel with the Labels of NPCs in the current scene.
     * @param NPCs ArrayList of String NPCs in the scene
     */
    private void updateNpcPanel(ArrayList<NPC> NPCs) {
        ArrayList<String> npcIcons = new ArrayList<>();
        ArrayList<String> npcToolTips = new ArrayList<>();
        if (NPCs != null) {
            for (NPC npc : NPCs) {
                npcIcons.add(npc.getType());
                npcToolTips.add(npc.getName());
            }
        }
        npcPanel.createLabels(npcIcons, npcToolTips);
    }

    /**
     * Update southPane with the current description.
     * @param description String description of the scene
     */
    private void updateDescriptionPane(String description) {
        descriptionPane.removeAll();
        descriptionPane.setText(description);
    }
}
