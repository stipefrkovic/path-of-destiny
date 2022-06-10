package nl.rug.oop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Objects;

public class GameView extends JFrame implements PropertyChangeListener {

    //TODO load button in the beginning
    //TODO american psycho easter egg
    //TODO add actions & property change
    //TODO comments

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
    JLabel manaLabel = new JLabel();
    JLabel playerMana = new JLabel();
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
    JLabel poisonLabel = new JLabel();
    JLabel stunLabel = new JLabel();
    JLabel confusedLabel = new JLabel();
    JPanel westPanel = new JPanel();
    ArrayList<JButton> actionButtons = new ArrayList<>();
    BackgroundPanel centerPanel;
    JPanel eastPanel = new JPanel();
    ArrayList<JLabel> enemyLabels = new ArrayList<>();
    JTextPane southPane = new JTextPane();

    public GameView() {
        setTitle(gameName);
        setLocation(0, 0);
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setup() {
        contentPane.setLayout(cardLayout);
        setWelcomeCard();
        setSceneCard();
        this.setVisible(true);
    }

    private void setWelcomeCard() {
        startButton.setActionCommand("Start"); //setAction could also be used
        startButton.setFont(font);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(contentPane);
            }
        });
        startButton.setBackground(color);

        welcomeCard.setBackground(color);
        welcomeCard.add(startButton);

        contentPane.add(welcomeCard);
    }

    private void setSceneCard(){
        saveButton.setToolTipText("Save game");
        saveButton.setBackground(color);
        saveButton.setFont(font);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save game");
            }
        });
        loadButton.setToolTipText("Load game");
        loadButton.setBackground(color);
        loadButton.setFont(font);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Load game");
                updateSceneCard();
            }
        });

        ImageIcon healthIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/heart.png")));
        healthLabel.setIcon(healthIcon);
        healthLabel.setToolTipText("Health");
        ImageIcon manaIcon =  new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/mana.png")));
        manaLabel.setIcon(manaIcon);
        manaLabel.setToolTipText("Mana");
        ImageIcon strengthIcon =  new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/strength.png")));
        strengthLabel.setIcon(strengthIcon);
        strengthLabel.setToolTipText("Strength");
        ImageIcon goldIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/gold.png")));
        goldLabel.setIcon(goldIcon);
        goldLabel.setToolTipText("Gold");
        playerHealth.setFont(font);
        playerHealth.setForeground(Color.WHITE);
        playerMana.setFont(font);
        playerMana.setForeground(Color.WHITE);
        playerStrength.setFont(font);
        playerStrength.setForeground(Color.WHITE);
        playerGold.setFont(font);
        playerGold.setForeground(Color.WHITE);
        statsPanel.setLayout(new GridLayout(1, 0));
        statsPanel.setBackground(color);
        statsPanel.add(saveButton);
        statsPanel.add(healthLabel);
        statsPanel.add(playerHealth);
        statsPanel.add(manaLabel);
        statsPanel.add(playerMana);
        statsPanel.add(strengthLabel);
        statsPanel.add(playerStrength);
        statsPanel.add(goldLabel);
        statsPanel.add(playerGold);

        inventoryPanel.setLayout(new GridLayout(1, 0));
        inventoryPanel.setBackground(color);
        ImageIcon healthPotionIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/healthPotion.gif")));
        healthPotionLabel.setIcon(healthPotionIcon);
        healthPotionLabel.setToolTipText("Health Potion");
        ImageIcon manaPotionIcon =  new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/manaPotion.png")));
        manaPotionLabel.setIcon(manaPotionIcon);
        manaPotionLabel.setToolTipText("Mana Potion");
        ImageIcon clearPotionIcon =  new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/clearPotion.png")));
        clearPotionLabel.setIcon(clearPotionIcon);
        clearPotionLabel.setToolTipText("Clear Effects Potion");
        ImageIcon staminaPotionIcon =  new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/staminaPotion.png")));
        staminaPotionLabel.setIcon(staminaPotionIcon);
        staminaPotionLabel.setToolTipText("Stamina Potion");
        healthPotionCount.setFont(font);
        healthPotionCount.setForeground(Color.WHITE);
        manaPotionCount.setFont(font);
        manaPotionCount.setForeground(Color.WHITE);
        clearPotionCount.setFont(font);
        clearPotionCount.setForeground(Color.WHITE);
        staminaPotionCount.setFont(font);
        staminaPotionCount.setForeground(Color.WHITE);
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
        ImageIcon weakIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/weak.png")));
        weakLabel.setIcon(weakIcon);
        weakLabel.setToolTipText("Effect: Weak");
        ImageIcon poisonIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/poison.png")));
        poisonLabel.setIcon(poisonIcon);
        poisonLabel.setToolTipText("Effect: Poisoned");
        ImageIcon stunIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/stun.png")));
        stunLabel.setIcon(stunIcon);
        stunLabel.setToolTipText("Effect: Stunned");
        ImageIcon confusedIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/confused.png")));
        confusedLabel.setIcon(confusedIcon);
        confusedLabel.setToolTipText("Effect: Confused");
        effectsPanel.add(weakLabel);
        effectsPanel.add(poisonLabel);
        effectsPanel.add(stunLabel);
        effectsPanel.add(confusedLabel);
        confusedLabel.setVisible(false);
        stunLabel.setVisible(false);
        weakLabel.setVisible(false);
        poisonLabel.setVisible(false);

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

        ImageIcon backgroundImage =  new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/welcome.png")));
        centerPanel = new BackgroundPanel(backgroundImage.getImage(), BackgroundPanel.SCALED);

        eastPanel.setBackground(color);
        eastPanel.setLayout(new GridLayout(5, 2));

        southPane.setFont(font);
        southPane.setBackground(color);
        southPane.setForeground(Color.WHITE);

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

        contentPane.add(sceneCard);
    }

    private void updateSceneCard() {
        playerHealth.setText("0");
        playerMana.setText("0");
        playerStrength.setText("0");
        playerGold.setText("0");

        healthPotionCount.setText("0");
        manaPotionCount.setText("0");
        clearPotionCount.setText("0");
        staminaPotionCount.setText("0");

        confusedLabel.setVisible(false);
        stunLabel.setVisible(false);
        weakLabel.setVisible(false);
        poisonLabel.setVisible(false);

        confusedLabel.setVisible(true);
        stunLabel.setVisible(true);
        weakLabel.setVisible(true);
        poisonLabel.setVisible(true);

        westPanel.removeAll();
        actionButtons.clear();
        for(int i=0; i<3; i++) {
            JButton button = new DepthButton("Action " + i);
            button.setBackground(color);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            //button.setIcon(ImageIcon);
            button.setActionCommand("Command");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(button.getActionCommand());
                }
            });
            actionButtons.add(button);
        }
        for (JButton b : actionButtons) {
            westPanel.add(b);
        }

        ImageIcon backgroundImage =  new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/village.jpeg")));
        centerPanel.setImage(backgroundImage.getImage());

        eastPanel.removeAll();
        enemyLabels.clear();
        for(int i=0; i<7; i++) {
            ImageIcon castleGuardIcon = new ImageIcon(Objects.requireNonNull(GameView.class.getResource("resources/castleGuard.png")));
            JLabel label = new JLabel(castleGuardIcon);
            label.setToolTipText("Enemy");
            enemyLabels.add(label);
        }
        for (JLabel l : enemyLabels) {
            eastPanel.add(l);
            l.setVisible(true);
        }

        southPane.removeAll();
        String sampleText = "The path of the righteous man is beset on all sides by the iniquities of the " +
                "selfish and the tyranny of the evil men. Blessed is he who, in the name of charity and goodwill, " +
                "shepherds the weak through the valley of darkness, for he is truly his brother's keeper, and the " +
                "finder of lost children. And I will strike down upon thee with great vengeance and furious anger " +
                "those who attempt to poison and destroy my brothers. And you will know my name is the Lord when I " +
                "lay my vengeance upon thee! PEW PEW!!!";
        southPane.setText(sampleText);

        contentPane.revalidate();
        contentPane.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property change event.");
    }

}
