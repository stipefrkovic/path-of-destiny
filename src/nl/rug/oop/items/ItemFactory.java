package nl.rug.oop.items;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Holds a lot of convenient functions to create items. The basic items are already in the register and only other items have to be added.
 * @author Jonas Scholz
 */
public class ItemFactory {

    private HashMap<String, Class> register = new HashMap();

    public ItemFactory(){
        registerItemType("HealthPotion", HealthPotionItem.class);
        registerItemType("ManaPotion", ManaPotion.class);
        registerItemType("StaminaPotion", StaminaPotion.class);
        registerItemType("RemoveEffectPotion", RemoveEffectPotion.class);
    }

    public void registerItemType(String type, Class itemClass){
        register.put(type, itemClass);
    }

    public Item createItem(String identifier){
        try {
            Class itemClass = register.get(identifier);
            return (Item) itemClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Item> createRandomItems(int numberOfItems){
        ArrayList<Item> items = new ArrayList<>();
        String[] itemsArray = (String[]) register.keySet().toArray();
        for (int i = 0; i < numberOfItems; i++) {
            items.add(createItem(itemsArray[(int) (Math.random()*itemsArray.length)]));
        }
        return items;
    }

    public ArrayList<Item> createRandomItems(int minNumberOfItems, int maxNumberOfItems){
        return createRandomItems((int) ((Math.random()*(maxNumberOfItems-minNumberOfItems))+minNumberOfItems));
    }

}
