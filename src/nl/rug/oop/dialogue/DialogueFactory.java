package nl.rug.oop.dialogue;

import nl.rug.oop.dialogue.Dialogue;
import nl.rug.oop.dialogue.Transaction;
import nl.rug.oop.items.Item;
import nl.rug.oop.items.ItemFactory;
import nl.rug.oop.npc.SceneChange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A factory class that creates dialogues, with some convenient functions for common structures.
 * @author Jonas Scholz
 */
public class DialogueFactory {

    private HashMap<String, Class> register = new HashMap();

    /**
     * Registers the Dialogue and Transaction class to the factory.
     */
    public DialogueFactory(){
        registerDialogue("Dialogue", Dialogue.class);
        registerDialogue("Transaction", Transaction.class);
        registerDialogue("IncreaseStrengthDialogue", IncreaseStrengthDialogue.class);
        registerDialogue("IncreaseMaHealthDialogue", IncreaseMaxHealthDialogue.class);
    }

    /**
     * Registers Dialogue Classes.
     * @param type The name under which the class is registered.
     * @param npcClass The class that is going to be registered.
     */
    public void registerDialogue(String type, Class npcClass){
        register.put(type, npcClass);
    }

    /**
     * Creates a dialogue.
     * @param type The String under which the class was registered.
     * @param text What the npc says for this dialogue.
     * @param possibleAnswers The possible answers the player can give.
     * @param whichSceneNext Which scene is next after this dialogue.
     * @return The created dialogue or null if it could not be created.
     */
    public Dialogue createDialogue(String type, String text, HashMap<String, Dialogue> possibleAnswers, SceneChange whichSceneNext){
        try {
            Class dialogueType = register.get(type);
            return (Dialogue) dialogueType.getDeclaredConstructor(String.class, HashMap.class, SceneChange.class).newInstance(text, possibleAnswers, whichSceneNext);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates a transaction.
     * @param type The String under which the class was registered.
     * @param nextDialogue The dialogue that comes next (if the transaction was successful when previousDialogue is set).
     * @param goldTransfer The amount of gold that is supposed to be transferred, with a positive value being the amount taken from the player and a negative value the amount of gold gained by the player.
     * @param playerGains The items that the player gains due to the transaction.
     * @param playerLosses The items that the player loses due to the transaction.
     * @return The created Transaction.
     */
    public Transaction createTransaction(String type, Dialogue nextDialogue, int goldTransfer, List<Item> playerGains, List<Item> playerLosses){
        try {
            Class dialogueType = register.get(type);
            return (Transaction) dialogueType.getDeclaredConstructor(Dialogue.class, int.class, List.class, List.class).newInstance(nextDialogue, goldTransfer, playerGains, playerLosses);
        } catch (Exception e) {
            return null;
        }
    }


    public Dialogue createPepTalk(String type, String text, HashMap<String, Dialogue> possibleAnswers, SceneChange whichSceneNext, float value){
        try {
            Class dialogueType = register.get(type);
            return (Dialogue) dialogueType.getDeclaredConstructor(String.class, HashMap.class, SceneChange.class, Float.class).newInstance(text, possibleAnswers, whichSceneNext, value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates a linear dialogue, so the player does not have a choice on what to do.
     * @param texts The texts of the dialogue must have the same size as the answers.
     * @param answers The answers must have the same size as the answers.
     * @return The start of the dialogue.
     */
    public Dialogue createLinearDialogue(List<String> texts, List<String> answers){
        HashMap<String, Dialogue> temp = new HashMap<>();
        temp.put(answers.get(answers.size()-1), null);
        Dialogue currentDialogue = createDialogue("Dialogue", texts.get(texts.size()-1), temp, SceneChange.NEXT_SCENE);
        for (int i = texts.size()-2; i >= 0; i--) {
            temp = new HashMap<>();
            temp.put(answers.get(i), currentDialogue);
            currentDialogue = createDialogue("Dialogue", texts.get(i), temp, SceneChange.CURRENT_SCENE);
        }
        return currentDialogue;
    }

    /**
     * Creates a shop dialogue.
     * @param buyPrices A Hashmap of items and corresponding prices for which the player can acquire the item.
     * @param sellPrices A Hashmap of items and corresponding prices for which the player can sell the item.
     * @param factory An ItemFactory to create the items.
     * @return The created shop dialogue.
     */
    public Dialogue createShopDialogue(HashMap<String, Integer> buyPrices, HashMap<String, Integer> sellPrices, ItemFactory factory){
        Dialogue mainDialogue = createDialogue("Dialogue", "Hello Traveller, are you looking to buy or sell?", new HashMap<>(), SceneChange.CURRENT_SCENE);
        if(!buyPrices.isEmpty()){
            mainDialogue.addAnswer("Buy", createSubShopDialogue(mainDialogue, true, buyPrices, factory));
        }
        if(!sellPrices.isEmpty()){
            mainDialogue.addAnswer("Sell", createSubShopDialogue(mainDialogue, false, sellPrices, factory));
        }
        mainDialogue.addAnswer("Exit", new Dialogue(SceneChange.PREVIOUS_SCENE));
        return mainDialogue;
    }

    /**
     * Creates a sub shop dialogue for either buying or selling goods.
     * @param mainDialogue The main shop dialogue.
     * @param isBuying If this subshop is for buying or selling.
     * @param priceList The price list of what item has what price.
     * @param factory An ItemFactory to create the items.
     * @return The created sub shop dialogue.
     */
    private Dialogue createSubShopDialogue(Dialogue mainDialogue, boolean isBuying, HashMap<String, Integer> priceList, ItemFactory factory){
        String text = "Here are my goods. I am ";
        text += isBuying?" selling ":" buying ";
        text += getPriceListString(priceList);
        Dialogue dialogue = createDialogue("Dialogue", text.toString(), new HashMap<>(), SceneChange.CURRENT_SCENE);
        for (String item:priceList.keySet()) {
            List<Item> items = new ArrayList<>();
            items.add(factory.createItem(item));
            if(isBuying){
                dialogue.addAnswer(item, createTransaction("Transaction", dialogue, priceList.get(item), items, new ArrayList<>()));
            }else{
                dialogue.addAnswer(item, createTransaction("Transaction", dialogue, -1*priceList.get(item), new ArrayList<>(), items));
            }
        }
        dialogue.addAnswer("Back", mainDialogue);
        return dialogue;
    }

    /**
     * Creates a description of the items and their prices.
     * @param priceList The price list used to create the description.
     * @return The description of the items and their prices.
     */
    private String getPriceListString(HashMap<String, Integer> priceList){
        StringBuilder text = new StringBuilder();
        for (String item:priceList.keySet()) {
            text.append(item).append(" for ").append(priceList.get(item)).append(", ");
        }
        text = new StringBuilder(text.substring(0, text.length() - 2));
        text.append(".");
        return text.toString();
    }

}
