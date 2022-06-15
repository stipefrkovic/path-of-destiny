package nl.rug.oop.items;

import nl.rug.oop.player.Mage;
import nl.rug.oop.player.Player;

/**
 * A class that implements mechanisms of a mana potion.
 * @author Andro Erdelez
 */
public class ManaPotion implements Item {
    /**
     * Consumes a mana potion which increases player's mana (if player's class is Mage).
     * @param player Player who consumes a given item from their inventory.
     */
    @Override
    public void use(Player player) {
        if(player instanceof Mage) {
            player.useAppropriatePotion();
        }
    }

    /**
     * Creates an adjective appropriate to this potion: Mana Potion.
     * @return Adjective which corresponds to the mana potion.
     */
    @Override
    public String getItemAdjective() {
        return "Mana Potion";
    }
}