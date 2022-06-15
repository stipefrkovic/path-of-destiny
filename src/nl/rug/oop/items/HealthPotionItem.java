package nl.rug.oop.items;

import nl.rug.oop.player.Player;

/**
 * A class that implements mechanisms of a health potion.
 * @author Andro Erdelez
 */
public class HealthPotionItem implements Item {
    /**
     * Consumes a health potion which increases player's health.
     * @param player Player who consumes a given item from their inventory.
     */
    @Override
    public void use(Player player) {
        player.useHealthPotion();
    }

    /**
     * Creates an adjective appropriate to this potion: Health Potion.
     * @return Adjective which corresponds to the health potion.
     */
    @Override
    public String getItemAdjective() {
        return "Health Potion";
    }
}