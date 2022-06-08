package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.items.ItemFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DialogueFactory {

    private HashMap<String, Class> register = new HashMap();

    public void registerDialogue(String type, Class npcClass){
        register.put(type, npcClass);
    }

    public Dialogue createDialogue(String type, String text, HashMap<String, Dialogue> possibleAnswers, int whichSceneNext){
        try {
            Class dialogueType = register.get(type);
            return (Dialogue) dialogueType.getDeclaredConstructor(String.class, HashMap.class, int.class).newInstance(text, possibleAnswers, whichSceneNext);
        } catch (Exception e) {
            return null;
        }
    }

    public Transaction createTransaction(String type, Dialogue nextDialogue, int whichSceneNext, int goldTransfer, List<Item> playerGains, List<Item> playerLosses){
        try {
            Class dialogueType = register.get(type);
            return (Transaction) dialogueType.getDeclaredConstructor(Dialogue.class, int.class, int.class, List.class, List.class).newInstance(nextDialogue, whichSceneNext, goldTransfer, playerGains, playerLosses);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates a linear dialogue, where
     * @param texts The texts of the dialogue must have the same size as the answers.
     * @param answers The answers must have the same size as the answers.
     * @return The start of the dialogue.
     */
    public Dialogue createLinearDialogue(List<String> texts, List<String> answers){
        HashMap<String, Dialogue> temp = new HashMap<>();
        temp.put(answers.get(answers.size()-1), null);
        Dialogue currentDialogue = createDialogue("Dialogue", texts.get(texts.size()-1), temp, 1);
        for (int i = texts.size()-2; i >= 0; i--) {
            temp = new HashMap<>();
            temp.put(answers.get(i), currentDialogue);
            currentDialogue = createDialogue("Dialogue", texts.get(i), temp, 0);
        }
        return currentDialogue;
    }

    public Dialogue createShopDialogue(HashMap<String, Integer> buyPrices, HashMap<String, Integer> sellPrices, ItemFactory factory){
        Dialogue mainDialogue = createDialogue("Dialogue", "Hello Traveller, are you looking to buy or sell?", new HashMap<>(), 0);
        if(!buyPrices.isEmpty()){
            mainDialogue.addAnswer("Buy", createSubShopDialogue(mainDialogue, true, buyPrices, factory));
        }
        if(!sellPrices.isEmpty()){
            mainDialogue.addAnswer("Sell", createSubShopDialogue(mainDialogue, false, sellPrices, factory));
        }
        mainDialogue.addAnswer("Exit", new ExitDialogue(1));
        return mainDialogue;
    }

    private Dialogue createSubShopDialogue(Dialogue mainDialogue, boolean isBuying, HashMap<String, Integer> priceList, ItemFactory factory){
        String text = "Here are my goods. I am ";
        text += isBuying?" selling ":" buying ";
        text += getPriceListString(priceList);
        Dialogue dialogue = createDialogue("Dialogue", text.toString(), new HashMap<>(), 0);
        for (String item:priceList.keySet()) {
            List<Item> items = new ArrayList<>();
            items.add(factory.createItem(item));
            if(isBuying){
                dialogue.addAnswer(item, createTransaction("Transaction", dialogue, 0, priceList.get(item), items, new ArrayList<>()));
            }else{
                dialogue.addAnswer(item, createTransaction("Transaction", dialogue, 0, -1*priceList.get(item), new ArrayList<>(), items));
            }
        }
        dialogue.addAnswer("Back", mainDialogue);
        return dialogue;
    }

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
