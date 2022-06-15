package nl.rug.oop.items;

import nl.rug.oop.player.Player;

/**
 * An interface for all the possible items in the game. It has all the functions necessary for the consumption of the
 * items.
 * @author Andro Erdelez
 */
public interface Item {
    /**
     * Player consumes an appropriate item.
     * @param player Player who consumes a given item from their inventory.
     */
    void use(Player player);

    /**
     * Returns the name of the item.
     * @return Item name.
     */
    String getItemAdjective();
}