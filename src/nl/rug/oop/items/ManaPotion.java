package nl.rug.oop.items;

import nl.rug.oop.player.Mage;
import nl.rug.oop.player.Player;

/**
 *
 * @author Andro Erdelez
 */
public class ManaPotion implements Item {
    @Override
    public void use(Player player) {
        if(player instanceof Mage) {
            player.useAppropriatePotion();
        }
    }

    @Override
    public String getItemAdjective() {
        return "Mana Potion";
    }
}