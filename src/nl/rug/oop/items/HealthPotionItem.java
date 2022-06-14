package nl.rug.oop.items;

import nl.rug.oop.player.Player;

/**
 *
 * @author Andro Erdelez
 */
public class HealthPotionItem implements Item {
    @Override
    public void use(Player player) {
        player.useHealthPotion();
    }

    @Override
    public String getItemAdjective() {
        return "health potion";
    }
}