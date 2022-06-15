package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;
import nl.rug.oop.model.OutputEventListener;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;
import nl.rug.oop.player.Warrior;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * The GameView (JFrame extended class with OutputEventListener) class for the RPG game.
 * @author sfrkovic
 */
public class GameView extends JFrame implements OutputEventListener {

    //TODO load button in the beginning
    //TODO american psycho easter egg
    //TODO comments

    /**
     * Controller (OutputEventListener) for the View and rpg game
     */
    Controller controller;
    /**
     * Name of the game displayed on the title bar
     */
    String gameName = "Path of Destiny";
    /**
     * Primary color for the background and buttons
     */
    Color color = new Color(0x353839); // Onyx, https://html-color.codes/grey
    /**
     * Primary font for the text of the game
     */
    Font font = new Font("Arial", Font.PLAIN, 20);
    /**
     * Layout manager for the GameView (JFrame)
     */
    CardLayout cardLayout = new CardLayout();
    /**
     * The content pane of the GameView (JFrame)
     */
    Container contentPane = getContentPane();

    /**
     * Start card for the user - used to start the game
     */
    JPanel startCard = new JPanel(new GridBagLayout());
    /**
     * Button which starts the game
     */
    JButton startButton = new DepthButton("Start game");

    /**
     * Game card - used to display the game scenes
     */
    JPanel gameCard = new JPanel();
    /**
     * Layout manager for the game card
     */
    BorderLayout borderLayout = new BorderLayout();
    /**
     * North panel of the game card - displays 2 buttons, inventory, player stats, and effects
     */
    JPanel northPanel = new JPanel();
    /**
     * Button saves the game
     */
    JButton saveButton = new DepthButton("Save game");
    /**
     * Button loads the previous game
     */
    JButton loadButton = new DepthButton("Load game");
    /**
     * Panel that holds labels for the player stats
     */
    JPanel statsPanel = new JPanel();
    /**
     * Label that displays the health icon
     */
    JLabel healthLabel = new JLabel();
    /**
     * Label that displays the player's health
     */
    JLabel playerHealth = new JLabel();
    /**
     * Label that displays the energy icon (mana/stamina)
     */
    JLabel energyLabel = new JLabel();
    /**
     * Label that displays the player's energy
     */
    JLabel playerEnergy = new JLabel();
    /**
     * Label that displays the strength icon
     */
    JLabel strengthLabel = new JLabel();
    /**
     * Label that displays the player's strength
     */
    JLabel playerStrength = new JLabel();
    /**
     * Label that displays the gold icon
     */
    JLabel goldLabel = new JLabel();
    /**
     * Label that displays the player's gold
     */
    JLabel playerGold = new JLabel();
    /**
     * Panel that holds the labels for the player's inventory
     */
    JPanel inventoryPanel = new JPanel();
    /**
     * Label that displays the health potion icon
     */
    JLabel healthPotionLabel = new JLabel();
    /**
     * Label that displays the player's health potion count
     */
    JLabel healthPotionCount = new JLabel();
    /**
     * Label that displays the mana potion icon
     */
    JLabel manaPotionLabel = new JLabel();
    /**
     * Label that displays the mana potion count
     */
    JLabel manaPotionCount = new JLabel();
    /**
     * Label that displays the clear effects potion icon
     */
    JLabel clearPotionLabel = new JLabel();
    /**
     * Label that displays the player's clear effects potion count
     */
    JLabel clearPotionCount = new JLabel();
    /**
     * Label that displays the stamina potion icon
     */
    JLabel staminaPotionLabel = new JLabel();
    /**
     * Label that displays the player's stamina potion count
     */
    JLabel staminaPotionCount = new JLabel();
    /**
     * Panel that holds the effects labels
     */
    JPanel effectsPanel = new JPanel();
    /**
     * Label that displays the weak effect icon
     */
    JLabel weakLabel = new JLabel();
    /**
     * Label that displays the poisoned effect icon
     */
    JLabel poisonedLabel = new JLabel();
    /**
     * Label that displays the stunned effect icon
     */
    JLabel stunnedLabel = new JLabel();
    /**
     * Label that displays the confused effect icon
     */
    JLabel confusedLabel = new JLabel();
    /**
     * Panel that holds the player's action
     */
    JPanel westPanel = new JPanel();
    /**
     * ArrayList that holds the player's actions buttons
     */
    ArrayList<JButton> actionButtons = new ArrayList<>();
    /**
     * BackGround panel that displays the background of the scene
     */
    BackgroundPanel centerPanel;
    /**
     * Panel that holds the npcs in the scene
     */
    JPanel eastPanel = new JPanel();
    /**
     * ArrayList holding the labels of the npcs in the scene
     */
    ArrayList<JLabel> npcLabels = new ArrayList<>();
    /**
     * TextPane that shows the description of thescene
     */
    JTextPane southPane = new JTextPane();

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
     * Method sets up the view and its 2 cards
     */
    public void setup() {
        contentPane.setLayout(cardLayout);
        setStartCard();
        setGameCard();
        this.setVisible(true);
    }

    /**
     * Method sets the set card with the start button
     */
    private void setStartCard() {
        startButton.setFont(font);
        startButton.setActionCommand("Start");
        startButton.setBackground(color);
        startButton.addActionListener(controller);

        startCard.setBackground(color);
        startCard.add(startButton);

        this.add(startCard, "Start Card");
    }

    /**
     * Method sets the basics of the GameCard which has a BorderLayout.
     * North - save button, load button, player stats, player inventory, player effects
     * West - player's action buttons
     * Center - background
     * East - labels of npcs in the scnee
     * South - TextPane with scene description
     */
    private void setGameCard() {
        // set save and load buttons (North)
        saveButton.setToolTipText("Save game");
        saveButton.setBackground(color);
        saveButton.setFont(font);
        saveButton.addActionListener(controller);
        saveButton.setActionCommand("Save");
        loadButton.setToolTipText("Load game");
        loadButton.setBackground(color);
        loadButton.setFont(font);
        loadButton.addActionListener(controller);
        saveButton.setActionCommand("Load");

        // set player stats (North)
        ImageIcon healthIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Heart.png")));
        healthLabel.setIcon(healthIcon);
        healthLabel.setToolTipText("Health");
        ImageIcon energyIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Energy.png")));
        energyLabel.setIcon(energyIcon);
        energyLabel.setToolTipText("Energy");
        ImageIcon strengthIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Strength.png")));
        strengthLabel.setIcon(strengthIcon);
        strengthLabel.setToolTipText("Strength");
        ImageIcon goldIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Gold.png")));
        goldLabel.setIcon(goldIcon);
        goldLabel.setToolTipText("Gold");
        playerHealth.setFont(font);
        playerHealth.setForeground(Color.LIGHT_GRAY);
        playerEnergy.setFont(font);
        playerEnergy.setForeground(Color.LIGHT_GRAY);
        playerStrength.setFont(font);
        playerStrength.setForeground(Color.LIGHT_GRAY);
        playerGold.setFont(font);
        playerGold.setForeground(Color.LIGHT_GRAY);
        statsPanel.setLayout(new GridLayout(1, 0));
        statsPanel.setBackground(color);
        statsPanel.add(saveButton);
        statsPanel.add(healthLabel);
        statsPanel.add(playerHealth);
        statsPanel.add(energyLabel);
        statsPanel.add(playerEnergy);
        statsPanel.add(strengthLabel);
        statsPanel.add(playerStrength);
        statsPanel.add(goldLabel);
        statsPanel.add(playerGold);

        // set player inventory (North)
        inventoryPanel.setLayout(new GridLayout(1, 0));
        inventoryPanel.setBackground(color);
        ImageIcon healthPotionIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Health Potion.gif")));
        healthPotionLabel.setIcon(healthPotionIcon);
        healthPotionLabel.setToolTipText("Health Potion");
        ImageIcon manaPotionIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Mana Potion.gif")));
        manaPotionLabel.setIcon(manaPotionIcon);
        manaPotionLabel.setToolTipText("Mana Potion");
        ImageIcon clearPotionIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Clear Effects Potion.gif")));
        clearPotionLabel.setIcon(clearPotionIcon);
        clearPotionLabel.setToolTipText("Clear Effects Potion");
        ImageIcon staminaPotionIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Stamina Potion.gif")));
        staminaPotionLabel.setIcon(staminaPotionIcon);
        staminaPotionLabel.setToolTipText("Stamina Potion");
        healthPotionCount.setFont(font);
        healthPotionCount.setForeground(Color.LIGHT_GRAY);
        manaPotionCount.setFont(font);
        manaPotionCount.setForeground(Color.LIGHT_GRAY);
        clearPotionCount.setFont(font);
        clearPotionCount.setForeground(Color.LIGHT_GRAY);
        staminaPotionCount.setFont(font);
        staminaPotionCount.setForeground(Color.LIGHT_GRAY);
        inventoryPanel.add(healthPotionLabel);
        inventoryPanel.add(healthPotionCount);
        inventoryPanel.add(manaPotionLabel);
        inventoryPanel.add(manaPotionCount);
        inventoryPanel.add(clearPotionLabel);
        inventoryPanel.add(clearPotionCount);
        inventoryPanel.add(staminaPotionLabel);
        inventoryPanel.add(staminaPotionCount);

        // set player effects (North)
        effectsPanel.setLayout(new GridLayout(1, 4));
        effectsPanel.setBackground(color);
        ImageIcon weakIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Weak.png")));
        weakLabel.setIcon(weakIcon);
        weakLabel.setToolTipText("Effect: Weak");
        ImageIcon poisonIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Poisoned.png")));
        poisonedLabel.setIcon(poisonIcon);
        poisonedLabel.setToolTipText("Effect: Poisoned");
        ImageIcon stunIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Stunned.png")));
        stunnedLabel.setIcon(stunIcon);
        stunnedLabel.setToolTipText("Effect: Stunned");
        ImageIcon confusedIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Confused.png")));
        confusedLabel.setIcon(confusedIcon);
        confusedLabel.setToolTipText("Effect: Confused");
        effectsPanel.add(weakLabel);
        effectsPanel.add(poisonedLabel);
        effectsPanel.add(stunnedLabel);
        effectsPanel.add(confusedLabel);
        confusedLabel.setVisible(false);
        stunnedLabel.setVisible(false);
        weakLabel.setVisible(false);
        poisonedLabel.setVisible(false);

        // add aforementioned elements to northPanel
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.ipadx = 40;
        gbc.ipady = 5;
        northPanel.setLayout(gridBagLayout);
        northPanel.setBackground(color);
        gbc.anchor = GridBagConstraints.WEST;
        northPanel.add(saveButton, gbc);
        northPanel.add(loadButton, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        northPanel.add(statsPanel, gbc);
        northPanel.add(inventoryPanel, gbc);
        northPanel.add(effectsPanel, gbc);

        // set westPanel for actions that will be added later
        westPanel.setBackground(color);
        westPanel.setLayout(new GridLayout(0, 1, 0, 10));

        // set centerPanel with a default background, real backgrounds are added later
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Welcome.png")));
        centerPanel = new BackgroundPanel(backgroundImage.getImage(), BackgroundPanel.SCALED);

        // set eastPanel for npcs that will be added later
        eastPanel.setBackground(color);
        eastPanel.setLayout(new GridLayout(5, 2));

        // set southPane for scene description that will be added later
        southPane.setFont(font);
        southPane.setBackground(color);
        southPane.setForeground(Color.LIGHT_GRAY);
        southPane.setEditable(false);

        // set GameCard with a BorderLayout and the 5 big pane(ls) to their corresponding positions
        borderLayout.setHgap(10);
        borderLayout.setVgap(10);
        gameCard.setLayout(borderLayout);
        gameCard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gameCard.setBackground(color);
        gameCard.add(northPanel, BorderLayout.NORTH);
        gameCard.add(westPanel, BorderLayout.WEST);
        gameCard.add(centerPanel, BorderLayout.CENTER);
        gameCard.add(eastPanel, BorderLayout.EAST);
        gameCard.add(southPane, BorderLayout.SOUTH);

        this.add(gameCard, "Game Card");
    }

    /**
     * Update the game card with the given state from the game model
     * @param actions actions available to the player
     * @param description description of the scene
     * @param image image for the background
     * @param npcs npcs in the scene
     * @param player player stats, inventory, and effects
     */
    private void updateGameCard(List<String> actions, String description, String image, List<NPC> npcs, Player player) {
        updateNorthPanel(player);
        updateWestPanel(actions);
        updateCenterPanel(image);
        updateEastPanel(npcs);
        updateSouthPane(description);

        cardLayout.last(contentPane);

        contentPane.revalidate();
        contentPane.repaint();
    }

    /**
     * Update player's stats, inventory, and effects
     * @param player player
     */
    private void updateNorthPanel(Player player) {
        if (player != null) {
            // set player energy to either stamina or mana based on player type
            String energyType;
            ImageIcon energyIcon;
            if (player instanceof Warrior) {
                energyType = "Stamina";
                energyIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Stamina.png")));
            } else {
                energyType = "Mana";
                energyIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Mana.gif")));
            }
            energyLabel.setIcon(energyIcon);
            energyLabel.setToolTipText(energyType);

            // get player stats
            String health = String.valueOf(player.getHealth());
            String energy = String.valueOf(player.getEnergy());
            String strength = String.valueOf(player.getStrength());
            String gold = String.valueOf(player.getGold());
            playerHealth.setText(health);
            playerEnergy.setText(energy);
            playerStrength.setText(strength);
            playerGold.setText(gold);

            // set inventory of player based on the given hashmap
            HashMap<String, Integer> inventory = player.getInventoryCount();
            Integer healthPotions = Objects.requireNonNullElse(inventory.get("Health Potion"), 0);
            healthPotionCount.setText(String.valueOf(healthPotions));
            Integer manaPotions = Objects.requireNonNullElse(inventory.get("Mana Potion"), 0);
            manaPotionCount.setText(String.valueOf(manaPotions));
            Integer clearPotions = Objects.requireNonNullElse(inventory.get("Clear Effects Potion"), 0);
            clearPotionCount.setText(String.valueOf(clearPotions));
            Integer staminaPotions = Objects.requireNonNullElse(inventory.get("Stamina Potion"), 0);
            staminaPotionCount.setText(String.valueOf(staminaPotions));

            // set player effects
            confusedLabel.setVisible(false);
            stunnedLabel.setVisible(false);
            weakLabel.setVisible(false);
            poisonedLabel.setVisible(false);
            List<String> effects = player.getEffects();
            for(String s : effects) {
                switch (s) {
                    case "Confused" -> confusedLabel.setVisible(true);
                    case "Stunned" -> stunnedLabel.setVisible(true);
                    case "Weak" -> weakLabel.setVisible(true);
                    case "Poisoned" -> poisonedLabel.setVisible(true);
                }
            }
        }
    }

    /**
     * Update west panel with actions player can do in the scene
     * @param actions actions the player can do in the scene
     */
    private void updateWestPanel(List<String> actions) {
        // remove all old actions and (current) available ones
        westPanel.removeAll();
        actionButtons.clear();
        for (String a : actions) {
            JButton button = new DepthButton(a);
            button.setBackground(color);
            button.setFont(font);
            button.setActionCommand(a);
            button.addActionListener(controller);
            actionButtons.add(button);
        }
        for (JButton b : actionButtons) {
            westPanel.add(b);
        }
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
     * Update eastPanel with the npcs in the current scene
     * @param npcs npcs in the current scene
     */
    private void updateEastPanel(List<NPC> npcs) {
        // remove previous npcs
        eastPanel.removeAll();
        npcLabels.clear();
        // add current npcs
        if (npcs != null) {
            for (NPC npc: npcs) {
                ImageIcon npcIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/" + npc.getType() +".png")));
                JLabel label = new JLabel(npcIcon);
                label.setToolTipText(npc.getName());
                npcLabels.add(label);
            }
            for (JLabel l : npcLabels) {
                eastPanel.add(l);
                l.setVisible(true);
            }
        }
    }

    /**
     * Update southPane with the current description
     * @param description description of the current scene
     */
    private void updateSouthPane(String description) {
        southPane.removeAll();
        southPane.setText(description);
    }

    /**
     * The updateScene method which is activated when there is a change in the model
     * and calls appropriate view update method (updateScene)
     * @param actions A list of possible valid actions that can be chosen.
     * @param description The description of the current scene.
     * @param image The image/theme of the current scene.
     * @param npcs The npcs in the current scene, can be empty if no npcs are in the current scene.
     * @param player The player that plays the game.
     */
    @Override
    public void updateScene(List<String> actions, String description, String image, List<NPC> npcs, Player player) {
        updateGameCard(actions, description, image, npcs, player);
    }
}