package nl.rug.oop.view;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class GameView extends JFrame implements PropertyChangeListener {

    CardLayout cardLayout = new CardLayout();
    Container contentPane = getContentPane();

    JPanel welcomeScreen;
    JPanel welcomeMenu;
    JButton startButton;
    JButton exitButton;

    JPanel sceneScreen;
    BackgroundPanel sceneBackground;
    JPanel playerInfo;
    JLabel playerHealth;
    JLabel playerArmor;
    JTextPane sceneDescription;
    JPanel playerActions;
    JButton attackButton;
    JButton escapeButton;

    public GameView() {
        setTitle("RPG");
        //setLocation(0, 0);
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setup() {
        contentPane.setLayout(cardLayout);

        welcomeScreen = new JPanel();
        welcomeScreen.setLayout(new GridBagLayout());
        welcomeMenu = new JPanel();
        welcomeMenu.setLayout(new BoxLayout(welcomeMenu, BoxLayout.PAGE_AXIS));
        startButton = new JButton();
        startButton.setText("Start game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starting game...");
                cardLayout.next(contentPane);
            }
        });
        exitButton = new JButton();
        exitButton.setText("Load game");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Loading game...");
            }
        });
        welcomeMenu.add(startButton);
        welcomeMenu.add(exitButton);
        welcomeScreen.add(welcomeMenu, new GridBagConstraints());


        sceneScreen = new JPanel();
        sceneScreen.setLayout(new BorderLayout());
        ImageIcon backgroundImage =  new ImageIcon(Objects.requireNonNull(GameView.class.getResource("/sampleBackground.jpg")));
        sceneBackground = new BackgroundPanel(backgroundImage.getImage(), BackgroundPanel.SCALED);
        playerInfo = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(40);
        playerInfo.setLayout(flowLayout);
        playerHealth = new JLabel("Health: 100");
        playerArmor = new JLabel("Armor: 100");
        playerInfo.add(playerHealth);
        playerInfo.add(playerArmor);
        sceneDescription = new JTextPane();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(attributeSet, 20);
        sceneDescription.setCharacterAttributes(attributeSet, true);
        String sampleText = "The path of the righteous man is beset on all sides by the iniquities of the " +
                "selfish and the tyranny of the evil men. Blessed is he who, in the name of charity and goodwill, " +
                "shepherds the weak through the valley of darkness, for he is truly his brother's keeper, and the " +
                "finder of lost children. And I will strike down upon thee with great vengeance and furious anger " +
                "those who attempt to poison and destroy my brothers. And you will know my name is the Lord when I " +
                "lay my vengeance upon thee! PEW PEW!!!";
        sceneDescription.setText(sampleText);
        playerActions = new JPanel();
        playerActions.setLayout(new GridLayout(0, 1));
        attackButton = new JButton("Attack");
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Attacking...");
            }
        });
        escapeButton = new JButton("Escape");
        escapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Escaping...");
            }
        });
        playerActions.add(attackButton);
        playerActions.add(escapeButton);
        sceneScreen.add(sceneBackground, BorderLayout.CENTER);
        sceneScreen.add(playerInfo, BorderLayout.NORTH);
        sceneScreen.add(sceneDescription, BorderLayout.SOUTH);
        sceneScreen.add(playerActions, BorderLayout.WEST);


        contentPane.add(welcomeScreen);
        contentPane.add(sceneScreen);

        this.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property change event.");
    }

    public static void main(String[] args) {
        GameView view = new GameView();
        view.setup();
    }
}
