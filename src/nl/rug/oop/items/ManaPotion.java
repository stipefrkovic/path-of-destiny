package nl.rug.oop.items;

import nl.rug.oop.player.Player;

/**
 *
 * @author Andro Erdelez
 */
public class ManaPotion implements Item {
    @Override
    public void use(Player player) {
        player.useItem("mana");
    }

    @Override
    public String getItemAdjective() {
        return "mana potion";
    }
}
