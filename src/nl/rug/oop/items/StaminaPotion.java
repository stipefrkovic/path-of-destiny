package nl.rug.oop.items;

import nl.rug.oop.player.Mage;
import nl.rug.oop.player.Player;
import nl.rug.oop.player.Warrior;

/**
 * A class that implements mechanisms of a stamina potion.
 * @author Andro Erdelez
 */
public class StaminaPotion implements Item {
    /**
     * Consumes a stamina potion which increases player's stamina (if player's class is Warrior).
     * @param player Player who consumes a given item from their inventory.
     */
    @Override
    public void use(Player player) {
        if(player instanceof Warrior) {
            player.useAppropriatePotion();
        }
    }

    /**
     * Creates an adjective appropriate to this potion: Stamina Potion.
     * @return Adjective which corresponds to the stamina potion.
     */
    @Override
    public String getItemAdjective() {
        return "Stamina Potion";
    }
}