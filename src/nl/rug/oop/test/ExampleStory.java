package nl.rug.oop.test;

import nl.rug.oop.items.ItemFactory;
import nl.rug.oop.npc.*;
import nl.rug.oop.player.Classless;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExampleStory {

    private Scene beginningScene;
    private ItemFactory itemFactory;
    private NPCFactory npcFactory;
    private SceneFactory sceneFactory;


    public ExampleStory(){
        sceneFactory = new SceneFactory();
        npcFactory = new NPCFactory();
        itemFactory = new ItemFactory();
        HashMap<String, Dialogue> possibleAnswers = new HashMap<>();
        possibleAnswers.put("Warrior", null);
        possibleAnswers.put("Mage", null);
        Dialogue dialogue = new Dialogue("Hero you have arrived and I hope that you will be able to defeat the evil king in my world, as I am unable to interfere more directly with the mortal plane, but before that you need to determine your calling. What path calls out for you?", possibleAnswers, 0);
        TalkingNPC firstNPC = new TalkingNPC("Janus", "Deity", 9999, 9999, dialogue, 0,0, new ArrayList<>());
        beginningScene = sceneFactory.createTalkScene("TalkScene", "startBackground", null, null, firstNPC, new Classless());
    }

    public Scene getBeginningScene() {
        return beginningScene;
    }

    public Scene createStory(Player player){
        DialogueFactory dialogueFactory = new DialogueFactory();
        Scene goodEnding = sceneFactory.createSimpleScene("Scene", "GoodEnding", "You have not strayed from the path and this shows in your rule, the land previously withering is now bursting with life. The people are happy with their new king and are celebrating your rule. It is a time of prosperity and wealth, but how long will it last?");
        Scene badEnding = sceneFactory.createSimpleScene("Scene", "BadEnding", "You have killed the evil king by any means possible and through that have managed to supplant him, at first the people are ecstatic, but they soon realize that they have just switch one evil ruler for another. One day while holding a ceremony you suddenly feel a sharp pain in your back and stumble forward. As you fall to the ground and the world grows black you hear the cheering of the gathered people. You draw your last breath and are now forever trapped in hell.");
        Scene deadEnding = sceneFactory.createSimpleScene("Scene", "deadEnding", "You have killed the evil king, but at what cost? Everywhere you look you see the victims of your ruthless murders to supplant the evil king. You are now the ruler, but where there once was life there is only death. Generations later while your name has already been lost your title \"The ruler of death\" still remains, as a warning for generations to come.");
        Scene fleeEnding = sceneFactory.createSimpleScene("Scene", "FleeEnding", "You have made it to the king, but as you step into the throne hall you can tell with a single glance that you can not defeat the king. So you do the only sensible thing and run. You run without looking back. Now you are living a quiet live in another country and while you still feel guilty about your decision, you are absolutely sure that fighting the king would have only resulted in your death.");
        HashMap<ConditionedAction, Scene> conditionedActions = new HashMap<>();
        conditionedActions.put(new AmountKillsAction("Continue", 0, 10), goodEnding);
        conditionedActions.put(new AmountKillsAction("Continue", 11, 20), badEnding);
        conditionedActions.put(new AmountKillsAction("Continue", 21, 999), deadEnding);
        Scene kingDefeatScene = new EvaluatingScene("Castle", "The king draws a last shuddering breath and falls over, the wounds on his body too severe to keep his soul in the mortal realm.",conditionedActions, player);
        BossNPC king = npcFactory.createBossNPC("BossKing", itemFactory);
        TalkScene kingScene = sceneFactory.createTalkScene("FightScene", "Castle", kingDefeatScene, fleeEnding, king, player);
        ArrayList<NPC> enemies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            enemies.add(npcFactory.createSimpleNPC("CastleGuard", itemFactory));
        }
        FightScene guardsScene = sceneFactory.createFightScene("FightScene", "Castle", "You go into the castle and encounter three guards, you darw your weapon knowing that they will not let you past without a fight.", player, null, kingScene, enemies);
        Scene walkScene = sceneFactory.createSimpleScene("Scene", "Forest", "You walk through the forest, which is devoid of any sound, this puts you on edge, put you can not see any enemies. Just as you begin to lower your guard you see the castle in front of you.");
        guardsScene.setFleeScene(walkScene);
        walkScene.addAction(new Action("Continue"), guardsScene);
        HashMap<String, Integer> buyPriceList = new HashMap<>();
        HashMap<String, Integer> sellPriceList = new HashMap<>();
        buyPriceList.put("HealthPotion", 12);
        buyPriceList.put("RemoveEffectPotion", 10);
        buyPriceList.put("StaminaPotion", 8);
        buyPriceList.put("ManaPotion", 8);
        sellPriceList.put("StaminaPotion", 6);
        sellPriceList.put("ManaPotion", 6);
        Dialogue shopDialogue = dialogueFactory.createShopDialogue(buyPriceList, sellPriceList,itemFactory);
        TalkingNPC trader = npcFactory.createTalkingNPC("Trader", shopDialogue, itemFactory);
        TalkScene shopScene = sceneFactory.createTalkScene("TalkScene", "Village", null, null, trader, player);
        Scene villageScene = sceneFactory.createSimpleScene("Scene", "Village", "You stand in the center of a village you see a trader and a villager. What do you want to do?");
        villageScene.addAction(new Action("Go to the trader"), shopScene);
        shopScene.setPreviousScene(villageScene);
        List<String> texts = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        texts.add("The evil king originally set out to save us, but instead he just replaced the last ruler with himself.");
        answers.add("What makes him so evil?");
        texts.add("He raised the taxes to unaffordable levels and sends his guards out to keep us suppressed, furthermore he does nothing against crime, as long as it does not target him. And then there is the story about the village GreenRock.");
        answers.add("What happened to the village?");
        texts.add("They tried to rebel against the oppressive rule and were slaughtered down to the last child. Anyway ... I don't think we should talk about this anymore, you never know who is listening.");
        answers.add("You are right.");
        Dialogue linearDialogue = dialogueFactory.createLinearDialogue(texts, answers);
        Dialogue whyHere = dialogueFactory.createDialogue("Scene", "I have lived here my whole live.", new HashMap<>(), 0);
        Dialogue villagerDialogue = dialogueFactory.createDialogue("Scene", "Hey, can I help you?", new HashMap<>(), 0);
        whyHere.addAnswer("Okay", villagerDialogue);
        villagerDialogue.addAnswer("How long have you lived here?", whyHere);
        villagerDialogue.addAnswer("Who is the evil king?", linearDialogue);
        TalkingNPC villager = npcFactory.createTalkingNPC("Trader", villagerDialogue, itemFactory);
        TalkScene villagerScene = sceneFactory.createTalkScene("TalkScene", "Village", villageScene, villageScene, villager, player);
        villageScene.addAction(new Action("Go to the villager"), villagerScene);
        villageScene.addAction(new Action("Leave the village"), walkScene);
        return villageScene;
    }


}
