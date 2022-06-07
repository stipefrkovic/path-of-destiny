package nl.rug.oop.items;

import nl.rug.oop.player.Player;

/**
 *
 * @author Andro Erdelez
 */
public class StaminaPotion implements Item {
    @Override
    public void use(Player player) {
        player.useItem("stamina");
    }

    @Override
    public String getItemAdjective() {
        return "stamina potion";
    }
}
