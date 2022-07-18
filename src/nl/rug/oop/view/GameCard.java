package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class GameCard extends Card {
    /**
     * North panel of the game card - displays 2 buttons, inventory, player stats, and effects
     */
    JPanel northPanel;
    /**
     * Button saves the game
     */
    JButton saveButton;
    /**
     * Button loads the previous game
     */
    JButton loadButton;
    /**
     * Panel that holds labels for the player stats
     */
    DictionaryDynamicPanel statsPanel;
    /**
     * Panel that holds the player inventory
     */
    DictionaryDynamicPanel inventoryPanel;

    ArrayDynamicPanel effectsPanel;
    /**
     * Panel that holds the player's action
     */
    ArrayDynamicPanel westPanel;
    /**
     * BackGround panel that displays the background of the scene
     */
    BackgroundPanel centerPanel;
    /**
     * Panel that holds the npcs in the scene
     */
    ArrayDynamicPanel eastPanel;
    /**
     * TextPane that shows the description of thescene
     */
    JTextPane southPane = new JTextPane();

    public GameCard(Font font, Color color, Controller controller) {
        super(new BorderLayout(10, 10), font, color, controller);
    }

    /**
     * Method sets the basics of the GameCard which has a BorderLayout.
     * North - save button, load button, player stats, player inventory, player effects
     * West - player's action buttons
     * Center - background
     * East - labels of npcs in the scnee
     * South - TextPane with scene description
     */
    @Override
    public void setup() {
        saveButton = new DepthButton("Save game", font, color, controller);
        loadButton = new DepthButton("Load game", font, color, controller);
        statsPanel = new DictionaryDynamicPanel(new GridLayout(1, 0), font, color, Color.LIGHT_GRAY);
        inventoryPanel = new DictionaryDynamicPanel(new GridLayout(1, 0), font, color, Color.LIGHT_GRAY);
        effectsPanel = new ArrayDynamicPanel(new GridLayout(1, 0), font, color, Color.LIGHT_GRAY);
        northPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        northPanel.setLayout(gridBagLayout);
        northPanel.setBackground(color);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.ipadx = 40;
        gbc.ipady = 5;
        gbc.anchor = GridBagConstraints.WEST;
        northPanel.add(saveButton, gbc);
        northPanel.add(loadButton, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        northPanel.add(statsPanel, gbc);
        northPanel.add(inventoryPanel, gbc);
        northPanel.add(effectsPanel, gbc);

        westPanel = new ArrayDynamicPanel(new GridLayout(0, 1, 0, 10), font, color, Color.LIGHT_GRAY);

        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Welcome.png")));
        centerPanel = new BackgroundPanel(backgroundImage.getImage(), BackgroundPanel.SCALED);

        eastPanel = new ArrayDynamicPanel(new GridLayout(5, 2), font, color, Color.LIGHT_GRAY);

        // set southPane for scene description that will be added later
        southPane.setFont(font);
        southPane.setBackground(color);
        southPane.setForeground(Color.LIGHT_GRAY);
        southPane.setEditable(false);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(color);
        add(northPanel, BorderLayout.NORTH);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        add(southPane, BorderLayout.SOUTH);

    }

    /**
     * Update the game card with the given state from the game model
     * @param actions actions available to the player
     * @param description description of the scene
     * @param image image for the background
     * @param npcs npcs in the scene
     * @param player player stats, inventory, and effects
     */
    public void updateElements(List<String> actions, String description, String image, List<NPC> npcs, Player player) {
        updateNorthPanel(player);
        updateWestPanel(actions);
        updateCenterPanel(image);
        updateEastPanel(npcs);
        updateSouthPane(description);
    }

    /**
     * Update player's stats, inventory, and effects
     * @param player player
     */
    private void updateNorthPanel(Player player) {
        if (player != null) {
            HashMap<String, String> playerInformation = player.getPlayerInformation();
            statsPanel.updateComponents(playerInformation);

            HashMap<String, Integer> inventoryCount = player.getInventoryCount();
            HashMap<String, String> playerInventory = new HashMap(inventoryCount);
            inventoryPanel.updateComponents(playerInventory);

            // set player effects based on the given list
            ArrayList<JComponent> components = new ArrayList<>();
            List<String> effects = player.getEffects();
            if (effects != null) {
                for (String e : effects) {
                    ImageIcon effectIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/" + e + ".png")));
                    JLabel effectLabel = new JLabel(effectIcon);
                    effectLabel.setToolTipText("Effect: " + e);
                    components.add(effectLabel);
                }
            }
            effectsPanel.updateComponents(components);
        }
    }

    /**
     * Update west panel with actions player can do in the scene
     * @param actions actions the player can do in the scene
     */
    private void updateWestPanel(List<String> actions) {
        ArrayList<JComponent> components = new ArrayList<>();
        if (actions != null) {
            for (String a : actions) {
                JButton button = new DepthButton(a, font, color, controller);
                components.add(button);
            }
        }
        westPanel.updateComponents(components);
    }

    /**
     * Update centerPanel to display the given image string
     * @param image string of the image to be displayed
     */
    private void updateCenterPanel(String image) {
        // get image from resources and set the centerPanel to it
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/" + image + ".png")));
        centerPanel.setImage(backgroundImage.getImage());
    }

    /**
     * Update eastPanel with the NPCs in the current scene
     * @param NPCs NPCs in the current scene
     */
    private void updateEastPanel(List<NPC> NPCs) {
        ArrayList<JComponent> components = new ArrayList<>();
        if (NPCs != null) {
            for (NPC npc : NPCs) {
                ImageIcon npcIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/" + npc.getType() + ".png")));
                JLabel label = new JLabel(npcIcon);
                label.setToolTipText(npc.getName());
                components.add(label);
            }
        }
        eastPanel.updateComponents(components);
    }

    /**
     * Update southPane with the current description
     * @param description description of the current scene
     */
    private void updateSouthPane(String description) {
        southPane.removeAll();
        southPane.setText(description);
    }
}
