package nl.rug.oop.view;

import nl.rug.oop.controller.Controller;
import nl.rug.oop.model.OutputEventListener;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.player.Player;
import nl.rug.oop.player.Warrior;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameView extends JFrame implements OutputEventListener {

    //TODO load button in the beginning
    //TODO american psycho easter egg
    //TODO comments

    Controller controller;
    String gameName = "Path of Destiny";
    Color color = new Color(0x353839); // Onyx, https://html-color.codes/grey
    Font font = new Font("Arial", Font.PLAIN, 20);
    CardLayout cardLayout = new CardLayout();
    Container contentPane = getContentPane();

    JPanel welcomeCard = new JPanel(new GridBagLayout());
    JButton startButton = new DepthButton("Start game");
    JButton loadButton = new DepthButton("Load game");

    BorderLayout borderLayout = new BorderLayout();
    JPanel sceneCard = new JPanel();
    JPanel northPanel = new JPanel();
    JButton saveButton = new DepthButton("Save game");
    JPanel statsPanel = new JPanel();
    JLabel healthLabel = new JLabel();
    JLabel playerHealth = new JLabel();
    JLabel energyLabel = new JLabel();
    JLabel playerEnergy = new JLabel();
    JLabel strengthLabel = new JLabel();
    JLabel playerStrength = new JLabel();
    JLabel goldLabel = new JLabel();
    JLabel playerGold = new JLabel();
    JPanel inventoryPanel = new JPanel();
    JLabel healthPotionLabel = new JLabel();
    JLabel healthPotionCount = new JLabel();
    JLabel manaPotionLabel = new JLabel();
    JLabel manaPotionCount = new JLabel();
    JLabel clearPotionLabel = new JLabel();
    JLabel clearPotionCount = new JLabel();
    JLabel staminaPotionLabel = new JLabel();
    JLabel staminaPotionCount = new JLabel();
    JPanel effectsPanel = new JPanel();
    JLabel weakLabel = new JLabel();
    JLabel poisonedLabel = new JLabel();
    JLabel stunnedLabel = new JLabel();
    JLabel confusedLabel = new JLabel();
    JPanel westPanel = new JPanel();
    ArrayList<JButton> actionButtons = new ArrayList<>();
    BackgroundPanel centerPanel;
    JPanel eastPanel = new JPanel();
    ArrayList<JLabel> npcLabels = new ArrayList<>();
    JTextPane southPane = new JTextPane();

    public GameView(Controller controller) {
        setTitle(gameName);
        setLocation(0, 0);
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.controller = controller;
    }

    public void setup() {
        contentPane.setLayout(cardLayout);
        setWelcomeCard();
        setSceneCard();
        this.setVisible(true);
    }

    private void setWelcomeCard() {
        startButton.setFont(font);
        startButton.setActionCommand("Start");
        startButton.setBackground(color);
        startButton.addActionListener(controller);

        welcomeCard.setBackground(color);
        welcomeCard.add(startButton);

        this.add(welcomeCard, "Welcome");
    }

    private void setSceneCard() {
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

        westPanel.setBackground(color);
        westPanel.setLayout(new GridLayout(0, 1, 0, 10));

        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/Welcome.png")));
        centerPanel = new BackgroundPanel(backgroundImage.getImage(), BackgroundPanel.SCALED);

        eastPanel.setBackground(color);
        eastPanel.setLayout(new GridLayout(5, 2));

        southPane.setFont(font);
        southPane.setBackground(color);
        southPane.setForeground(Color.LIGHT_GRAY);
        southPane.setEditable(false);

        borderLayout.setHgap(10);
        borderLayout.setVgap(10);
        sceneCard.setLayout(borderLayout);
        sceneCard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sceneCard.setBackground(color);
        sceneCard.add(northPanel, BorderLayout.NORTH);
        sceneCard.add(westPanel, BorderLayout.WEST);
        sceneCard.add(centerPanel, BorderLayout.CENTER);
        sceneCard.add(eastPanel, BorderLayout.EAST);
        sceneCard.add(southPane, BorderLayout.SOUTH);

        this.add(sceneCard, "Scene");
    }

    private void updateSceneCard(List<String> actions, String description, String image, List<NPC> npcs, Player player) {
        System.out.println("SCENE UPDATED");

        updateNorthPanel(player);
        updateWestPanel(actions);
        updateCenterPanel(image);
        updateEastPanel(npcs);
        updateSouthPane(description);

        cardLayout.show(this, "Scene");

        contentPane.revalidate();
        contentPane.repaint();
    }

    private void updateNorthPanel(Player player) {
        String health = String.valueOf(player.getHealth());
        String energy = String.valueOf(player.getEnergy());
        String strength = String.valueOf(player.getStrength());
        String gold = String.valueOf(player.getGold());

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

        playerHealth.setText(health);
        playerEnergy.setText(energy);
        playerStrength.setText(strength);
        playerGold.setText(gold);

        // get inventoryHashMap and then call getValue for each item
        healthPotionCount.setText("0");
        manaPotionCount.setText("0");
        clearPotionCount.setText("0");
        staminaPotionCount.setText("0");

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

    private void updateWestPanel(List<String> actions) {
        westPanel.removeAll();
        actionButtons.clear();
        for (String a : actions) {
            JButton button = new DepthButton("Action: " + a);
            button.setBackground(color);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.setActionCommand(a);
            button.addActionListener(controller);
            actionButtons.add(button);
        }
        for (JButton b : actionButtons) {
            westPanel.add(b);
        }
    }

    private void updateCenterPanel(String image) {
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/" + image + ".png")));
        centerPanel.setImage(backgroundImage.getImage());
    }

    private void updateEastPanel(List<NPC> npcs) {
        eastPanel.removeAll();
        npcLabels.clear();
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

    private void updateSouthPane(String description) {
        southPane.removeAll();
        southPane.setText(description);
    }

    @Override
    public void updateScene(List<String> actions, String description, String image, List<NPC> npcs, Player player) {
        updateSceneCard(actions, description, image, npcs, player);
    }
}