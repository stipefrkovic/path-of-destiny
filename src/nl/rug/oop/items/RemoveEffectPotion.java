package nl.rug.oop.items;

import nl.rug.oop.player.Player;

/**
 *
 * @author Andro Erdelez
 */
public class RemoveEffectPotion implements Item {
    @Override
    public void use(Player player) {
        player.useItem("effect");
    }

    @Override
    public String getItemAdjective() {
        return "potion removes an effect";
    }
}
