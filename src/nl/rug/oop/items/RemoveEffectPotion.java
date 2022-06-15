package nl.rug.oop.items;

import nl.rug.oop.player.Player;

/**
 * A class that implements mechanisms of a potion that removes all effects.
 * @author Andro Erdelez
 */
public class RemoveEffectPotion implements Item {
    /**
     * Consumes a potion which removes all effects (if existent).
     * @param player Player who consumes a given item from their inventory.
     */
    @Override
    public void use(Player player) {
        player.removeAllEffects();
    }

    /**
     * Creates an adjective appropriate to this potion: Clear Effects Potion.
     * @return Adjective which corresponds to the potion that removes all effects.
     */
    @Override
    public String getItemAdjective() {
        return "Clear Effects Potion";
    }
}