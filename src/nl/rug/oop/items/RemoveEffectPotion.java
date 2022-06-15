package nl.rug.oop.items;

import nl.rug.oop.player.Player;

/**
 *
 * @author Andro Erdelez
 */
public class RemoveEffectPotion implements Item {
    @Override
    public void use(Player player) {
        player.removeAllEffects();
    }

    @Override
    public String getItemAdjective() {
        return "Clear Effects Potion";
    }
}