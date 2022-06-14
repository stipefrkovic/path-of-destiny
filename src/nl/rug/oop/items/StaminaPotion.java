package nl.rug.oop.items;

import nl.rug.oop.player.Mage;
import nl.rug.oop.player.Player;
import nl.rug.oop.player.Warrior;

/**
 *
 * @author Andro Erdelez
 */
public class StaminaPotion implements Item {
    @Override
    public void use(Player player) {
        if(player instanceof Warrior) {
            player.useAppropriatePotion();
        }
    }

    @Override
    public String getItemAdjective() {
        return "stamina potion";
    }
}