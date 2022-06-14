package nl.rug.oop.dialogue;

import nl.rug.oop.player.Player;

/**
 * An interface to create permanent
 * @author Jonas Scholz
 */
public interface PepTalk {

    /**
     * Allows for players to power up.
     * @param player The player that plays the game.
     * @return What the function did.
     */
    String powerUpPlayer(Player player);

}
