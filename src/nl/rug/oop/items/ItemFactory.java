package nl.rug.oop.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Holds a lot of convenient functions to create items. The basic items are already in the register and only other items have to be added.
 * @author Jonas Scholz
 */
public class ItemFactory implements Serializable {

    private HashMap<String, Class> register = new HashMap();

    /**
     * Registers all the standard Items of the game (Health-, Mana-, Stamina- and RemoveEffectPotions).
     */
    public ItemFactory(){
        registerItemType("HealthPotion", HealthPotionItem.class);
        registerItemType("ManaPotion", ManaPotion.class);
        registerItemType("StaminaPotion", StaminaPotion.class);
        registerItemType("RemoveEffectPotion", RemoveEffectPotion.class);
    }

    /**
     * Registers the class of the item under a String key.
     * @param type The key under which the Item class is registered.
     * @param itemClass The Item class that should be registered.
     */
    public void registerItemType(String type, Class itemClass){
        register.put(type, itemClass);
    }

    /**
     * Creates an Item according to the identifier/key given.
     * @param identifier The identifier/key that is associated with an Item class.
     * @return The created Item.
     */
    public Item createItem(String identifier){
        try {
            Class itemClass = register.get(identifier);
            return (Item) itemClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates the specified number of random Items.
     * @param numberOfItems The number of items that should be created
     * @return A list of the created items.
     */
    public ArrayList<Item> createRandomItems(int numberOfItems){
        ArrayList<Item> items = new ArrayList<>();
        String[] itemsArray = (String[]) register.keySet().toArray();
        for (int i = 0; i < numberOfItems; i++) {
            items.add(createItem(itemsArray[(int) (Math.random()*itemsArray.length)]));
        }
        return items;
    }

    /**
     * Creates a random amount of random items.
     * @param minNumberOfItems The lower bound of the amount of items created (inclusive).
     * @param maxNumberOfItems The upper bound of the amount of items created (exclusive).
     * @return A list of the created items.
     */
    public ArrayList<Item> createRandomItems(int minNumberOfItems, int maxNumberOfItems){
        return createRandomItems((int) ((Math.random()*(maxNumberOfItems-minNumberOfItems))+minNumberOfItems));
    }

}
