package nl.rug.oop.items;

import nl.rug.oop.player.Player;

/**
 *
 * @author Andro Erdelez
 */
public interface Item {
    void use(Player player);

    String getItemAdjective();
}
