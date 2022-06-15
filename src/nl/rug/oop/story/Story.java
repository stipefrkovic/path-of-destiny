package nl.rug.oop.story;

import nl.rug.oop.dialogue.Dialogue;
import nl.rug.oop.dialogue.DialogueFactory;
import nl.rug.oop.items.Item;
import nl.rug.oop.items.ItemFactory;
import nl.rug.oop.npc.*;
import nl.rug.oop.player.*;
import nl.rug.oop.scene.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The class initializes the story flow, the dialogue, scenes, and NPCs.
 */
public class Story implements Serializable {
    private Scene beginningScene;
    private ItemFactory itemFactory;
    private NPCFactory npcFactory;
    private SceneFactory sceneFactory;
    private DialogueFactory dialogueFactory;


    /**
     * initializes the start of the different factories and the beginning scene
     */
    public Story(){
        sceneFactory = new SceneFactory();
        npcFactory = new NPCFactory();
        itemFactory = new ItemFactory();
        dialogueFactory = new DialogueFactory();
        HashMap<String, Dialogue> possibleAnswers = new HashMap<>();
        possibleAnswers.put("Warrior", null);
        possibleAnswers.put("Mage", null);
        PlayerFactory.registerPlayer("Warrior", Warrior.class);
        PlayerFactory.registerPlayer("Mage", Mage.class);
        Dialogue dialogue = new Dialogue("Hero you have arrived and I hope that you will be able to defeat the evil king in my world, as I am unable to interfere more directly with the mortal plane, but before that you need to determine your calling. What path calls out for you?", possibleAnswers, SceneChange.CURRENT_SCENE);
        TalkingNPC firstNPC = new TalkingNPC("Janus", "Deity", 9999, 9999, dialogue, 0,0, new ArrayList<>());
        beginningScene = sceneFactory.createTalkScene("TalkScene", "Welcome", null, null, firstNPC, new Classless());
    }

    /**
     * returns the beginning scene
     * @return the beginning scene
     */
    public Scene getBeginningScene() {
        return beginningScene;
    }

    /**
     * makes the price list for the trader
     * @return the shop dialogue
     */
    private Dialogue traderDialogue(){
        HashMap<String, Integer> buyPriceList = new HashMap<>();
        HashMap<String, Integer> sellPriceList = new HashMap<>();
        buyPriceList.put("HealthPotion", 12);
        buyPriceList.put("RemoveEffectPotion", 10);
        buyPriceList.put("StaminaPotion", 8);
        buyPriceList.put("ManaPotion", 8);
        sellPriceList.put("StaminaPotion", 6);
        sellPriceList.put("ManaPotion", 6);
        return dialogueFactory.createShopDialogue(buyPriceList, sellPriceList,itemFactory);
    }

    private Dialogue traderDialogue2(){
        HashMap<String, Integer> buyPriceList = new HashMap<>();
        HashMap<String, Integer> sellPriceList = new HashMap<>();
        buyPriceList.put("HealthPotion", 12);
        buyPriceList.put("RemoveEffectPotion", 10);
        buyPriceList.put("StaminaPotion", 8);
        buyPriceList.put("ManaPotion", 8);
        sellPriceList.put("StaminaPotion", 6);
        sellPriceList.put("ManaPotion", 6);
        return dialogueFactory.createShopDialogue(buyPriceList, sellPriceList,itemFactory);
    }

    /**
     * makes all the scenes, NPCs, dialogue and puts them together to make a story. The start scene here is the village scene.
     * @param player the player
     * @return the village scene
     */
    //TODO: creepy and helpful citizen dialogue
    public Scene createStory(Player player){
        Scene goodEnding = sceneFactory.createSimpleScene("Scene", "GoodEnding", "You have not strayed from the path and this shows in your rule, the land previously withering is now bursting with life. The people are happy with their new king and are celebrating your rule. It is a time of prosperity and wealth, but how long will it last?");
        Scene badEnding = sceneFactory.createSimpleScene("Scene", "BadEnding", "You have killed the evil king by any means possible and through that have managed to supplant him, at first the people are ecstatic, but they soon realize that they have just switch one evil ruler for another. One day while holding a ceremony you suddenly feel a sharp pain in your back and stumble forward. As you fall to the ground and the world grows black you hear the cheering of the gathered people. You draw your last breath and are now forever trapped in hell.");
        Scene deadEnding = sceneFactory.createSimpleScene("Scene", "DeadEnding", "You have killed the evil king, but at what cost? Everywhere you look you see the victims of your ruthless murders to supplant the evil king. You are now the ruler, but where there once was life there is only death. Generations later while your name has already been lost your title \"The ruler of death\" still remains, as a warning for generations to come.");
        Scene fleeEnding = sceneFactory.createSimpleScene("Scene", "FleeEnding", "You have made it to the king, but as you step into the throne hall you can tell with a single glance that you can not defeat the king. So you do the only sensible thing and run. You run without looking back. Now you are living a quiet life in another country and while you still feel guilty about your decision, you are absolutely sure that fighting the king would have only resulted in your death.");
        goodEnding.addAction(new Action("Exit Game"), null);
        badEnding.addAction(new Action("Exit Game"), null);
        deadEnding.addAction(new Action("Exit Game"), null);
        fleeEnding.addAction(new Action("Exit Game"), null);
        HashMap<ConditionedAction, Scene> conditionedActions = new HashMap<>();
        conditionedActions.put(new AmountKillsAction("Continue", 0, 0), goodEnding);
        conditionedActions.put(new AmountKillsAction("Continue", 1, 3), badEnding);
        conditionedActions.put(new AmountKillsAction("Continue", 4, 100), deadEnding);
        Scene kingDefeatScene = new EvaluatingScene("Throne", "The king draws a last shuddering breath and falls over, the wounds on his body too severe to keep his soul in the mortal realm.",conditionedActions, player);
        BossNPC king = npcFactory.createBossNPC("KingBoss", itemFactory);
        TalkScene kingScene = sceneFactory.createTalkScene("TalkScene", "Throne", kingDefeatScene, fleeEnding, king, player);
        ArrayList<NPC> enemies = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            enemies.add(npcFactory.createSimpleNPC("CastleGuard", itemFactory));
        }
        Scene castleScene = sceneFactory.createSimpleScene("Scene", "Castle", "You made your way into the castle, but not as unseen as you had hoped.");
        Scene guardsScene = sceneFactory.createFightScene("FightScene", "Castle", "You are surrounded by guards. They are the last obstacle before the king.", player, castleScene, kingScene, enemies);
        castleScene.addAction(new Action("Continue..."), guardsScene);
        Scene sewerScene = sceneFactory.createSimpleScene("Scene", "Sewers", "You are standing in the murky waters of the sewers. Deeper into the sewers you hear rats, behind you the sounds of the city");
        ArrayList<NPC> moreRats = new ArrayList<>();
        ArrayList<NPC> sewerRats = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            sewerRats.add(npcFactory.createSimpleNPC("Rat", itemFactory));
            moreRats.add(npcFactory.createSimpleNPC("Rat", itemFactory));
        }
        Scene secondSewerRatsScene = sceneFactory.createFightScene("FightScene", "Sewers", "You are surrounded by small rats. They seem not afraid and try to bite you. Fight them off or flee.", player, sewerScene, castleScene, moreRats);
        Scene sewerRatsScene = sceneFactory.createFightScene("FightScene", "Sewers", "You are surrounded by small rats. They seem not afraid and try to bite you. Fight them off or flee.", player, sewerScene, secondSewerRatsScene, sewerRats);
        sewerScene.addAction(new Action("Walk onwards"), sewerRatsScene);
        Scene cityScene = sceneFactory.createSimpleScene("Scene", "City", "The city before you is bustling with life, you see a trader, a villager, and guards. What do you want to do?");
        sewerScene.addAction(new Action("Go back to the city"), cityScene);
        TalkingNPC cityTrader = npcFactory.createTalkingNPC("Trader", traderDialogue2(), itemFactory);
        Scene cityTraderScene = sceneFactory.createTalkScene("TalkScene", "City", cityScene, cityScene, cityTrader, player);
        Dialogue helpfulCitizenDialogue = dialogueFactory.createDialogue("Dialogue", "I think I can help you.", new HashMap<>(), SceneChange.CURRENT_SCENE);
        Dialogue helpKingDefeatAnswer = dialogueFactory.createPepTalk("IncreaseStrengthDialogue", "Not so loud! Here, take this.", new HashMap<>(), SceneChange.CURRENT_SCENE, 5);
        Dialogue playDumbAnswer = dialogueFactory.createPepTalk("IncreaseStrengthDialogue", "Ah, of course not. You should indeed be careful.", new HashMap<>(), SceneChange.CURRENT_SCENE, 10);
        helpfulCitizenDialogue.addAnswer("Help me defeat the king?", helpKingDefeatAnswer);
        helpfulCitizenDialogue.addAnswer("I have no clue what you are talking about", playDumbAnswer);
        TalkingNPC helpfulCitizen = npcFactory.createTalkingNPC("Villager", helpfulCitizenDialogue, itemFactory);
        Scene helpfulCitizenScene = sceneFactory.createTalkScene("TalkScene", "City", cityScene, cityScene, helpfulCitizen, player);
        List<String> citizenTexts = new ArrayList<>();
        List<String> playerAnswersCitizen = new ArrayList<>();
        citizenTexts.add("I can't talk freely, but the guards are trying their best.");
        playerAnswersCitizen.add("What do you mean?");
        citizenTexts.add("They have to follow orders, but they protect us from outsiders with bad intentions.");
        playerAnswersCitizen.add("Can you tell me more about life here?");
        citizenTexts.add("I have to go before someone notices I'm talking to you.");
        playerAnswersCitizen.add("Ok....");
        Dialogue citizenLinearDialogue = dialogueFactory.createLinearDialogue(citizenTexts, playerAnswersCitizen);
        Dialogue citizenWhyHere = dialogueFactory.createDialogue("Dialogue", "I moved here long ago, when it was a beautiful and friendly country. Now I can't leave.", new HashMap<>(), SceneChange.CURRENT_SCENE);
        Dialogue citizenDialogue = dialogueFactory.createDialogue("Dialogue", "Hey, can I help you?", new HashMap<>(), SceneChange.CURRENT_SCENE);
        citizenWhyHere.addAnswer("Okay", citizenDialogue);
        citizenDialogue.addAnswer("How long have you lived here?", citizenWhyHere);
        citizenDialogue.addAnswer("How is life in the city?", citizenLinearDialogue);
        TalkingNPC citizen = npcFactory.createTalkingNPC("Villager", citizenDialogue, itemFactory);
        Scene citizenScene = sceneFactory.createTalkScene("TalkScene", "City", cityScene, cityScene, citizen, player);
        Dialogue noAnswer = dialogueFactory.createPepTalk("IncreaseMaxHealthDialogue", "A ghost! Please don't hurt me!", new HashMap<>(), SceneChange.CURRENT_SCENE, 10);
        Dialogue niceAnswer = dialogueFactory.createPepTalk("IncreaseMaxHealthDialogue", "Such a nice young person. Please take this as a thank you.", new HashMap<>(), SceneChange.CURRENT_SCENE, 20);
        Dialogue creepyCitizenDialogue = dialogueFactory.createDialogue("Dialogue", "I'm afraid of ghosts! They are everywhere.", new HashMap<>(), SceneChange.CURRENT_SCENE);
        creepyCitizenDialogue.addAnswer("....", noAnswer);
        creepyCitizenDialogue.addAnswer("I understand. Salt helps against ghosts where I come from, maybe it does for you too?", niceAnswer);
        TalkingNPC creepyCitizen = npcFactory.createTalkingNPC("Villager", creepyCitizenDialogue, itemFactory);
        Scene creepyCitizenScene = sceneFactory.createTalkScene("TalkScene", "City", cityScene, cityScene, creepyCitizen, player);
        ArrayList<NPC> cityGuards = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            cityGuards.add(npcFactory.createSimpleNPC("Guard", itemFactory));
        }
        Scene cityGuardsScene = sceneFactory.createFightScene("FightScene", "City", "You approach the guards. They are deep in conversation until you get close. They look wary. Can you trust them?", player, cityScene, cityScene, cityGuards);
        cityScene.addAction(new Action("Visit the trader."), cityTraderScene);
        cityScene.addAction(new Action("Talk to the creepy looking citizen."), creepyCitizenScene);
        cityScene.addAction(new Action("Talk to the citizen motioning you to come towards them."), helpfulCitizenScene);
        cityScene.addAction(new Action("Talk to the citizen looking around."), citizenScene);
        cityScene.addAction(new Action("Approach the city guards."), cityGuardsScene);
        cityScene.addAction(new Action("Sneak into the sewers."), sewerScene);
        Scene forestScene = sceneFactory.createSimpleScene("Scene", "Night Forest", "You enter the forest. Before you are two paths. You can see spider webs on the right path, and you hear shady voices on the path to the left. Which path will you take?");
        BossNPC spiderBoss = npcFactory.createBossNPC("SpiderBoss", itemFactory);
        Scene spiderBossScene = sceneFactory.createTalkScene("TalkScene", "Night Forest", cityScene, null, spiderBoss, player);
        ArrayList<NPC> smallSpiderEnemies = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            smallSpiderEnemies.add(npcFactory.createSimpleNPC("Spider", itemFactory));
        }
        Scene spiderScene = sceneFactory.createFightScene("FightScene", "Night Forest", "A horde of spiders is coming at you. Flee or stand firm.", player, forestScene, spiderBossScene, smallSpiderEnemies);
        ArrayList<NPC> secondForestBandits = new ArrayList<>();
        ArrayList<NPC> forestBandits = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            forestBandits.add(npcFactory.createSimpleNPC("Bandit", itemFactory));
            secondForestBandits.add(npcFactory.createSimpleNPC("Bandit", itemFactory));
        }
        Scene secondForestBanditScene = sceneFactory.createFightScene("FightScene", "Night Forest", "After having defeated the first group of bandits, reinforcements arrive and again three bandits are standing in front of you", player, forestScene, cityScene, secondForestBandits);
        Scene forestBanditScene = sceneFactory.createFightScene("FightScene", "Night Forest", "The shady voices grow louder until three bandits are standing in front of you.", player, forestScene, secondForestBanditScene, forestBandits);
        forestScene.addAction(new Action("Take the right path."), spiderScene);
        forestScene.addAction(new Action("Take the left path."), forestBanditScene);
        Scene villageScene = sceneFactory.createSimpleScene("Scene", "Village", "You stand in the center of a village. You see a trader, a villager, a guard standing around and at the edge of the village you see some shady figures. What do you want to do?");
        TalkingNPC villageTrader = npcFactory.createTalkingNPC("Trader", traderDialogue(), itemFactory);
        Scene villageTraderScene = sceneFactory.createTalkScene("TalkScene", "Village", villageScene, villageScene, villageTrader, player);
        ArrayList<NPC> guard = new ArrayList<>();
        guard.add(npcFactory.createSimpleNPC("Guard", itemFactory));
        Scene villageGuardScene = sceneFactory.createFightScene("FightScene", "Village", "You approach the guard. He looks at you suspiciously, as if to estimate if you are dangerous.", player, villageScene, villageScene, guard);
        HashMap<Action, Scene> villageActions = new HashMap<>();
        villageActions.put(new Action("Pick up shiny object"), villageScene);
        villageActions.put(new Action("Do not pick up the shiny object"), villageScene);
        HashMap<Action, List<Item>> villageLoot = new HashMap<>();
        ArrayList<Item> possibleLoot = new ArrayList<>();
        possibleLoot.add(itemFactory.createItem("HealthPotion"));
        villageLoot.put(new Action("Pick up shiny object"), possibleLoot);
        Scene villageEdge = sceneFactory.createLootScene("LootScene", "Village", "After you defeat the bandits you see something shiny on the ground. You move towards it to pick it up.", villageActions, villageLoot, player);
        ArrayList<NPC> bandits = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            bandits.add(npcFactory.createSimpleNPC("Bandit", itemFactory));
        }
        Scene banditScene = sceneFactory.createFightScene("FightScene", "Village", "The shady figure immediately draw a knife when they see you coming towards them. This will not be a friendly encounter.", player, villageScene, villageEdge, bandits);
        List<String> villagerTexts = new ArrayList<>();
        List<String> playerAnswersVillage = new ArrayList<>();
        villagerTexts.add("The evil king originally set out to save us, but instead he just replaced the last ruler with himself.");
        playerAnswersVillage.add("What makes him so evil?");
        villagerTexts.add("He raised the taxes to unaffordable levels and sends his guards out to keep us suppressed, furthermore he does nothing against crime, as long as it does not target him. And then there is the story about the village GreenRock.");
        playerAnswersVillage.add("What happened to the village?");
        villagerTexts.add("They tried to rebel against the oppressive rule and were slaughtered down to the last child. Anyway ... I don't think we should talk about this anymore, you never know who is listening.");
        playerAnswersVillage.add("You are right.");
        Dialogue linearDialogue = dialogueFactory.createLinearDialogue(villagerTexts, playerAnswersVillage);
        Dialogue whyHere = dialogueFactory.createDialogue("Dialogue", "I have lived here my whole life.", new HashMap<>(), SceneChange.CURRENT_SCENE);
        Dialogue villagerDialogue = dialogueFactory.createDialogue("Dialogue", "Hey, can I help you?", new HashMap<>(), SceneChange.CURRENT_SCENE);
        whyHere.addAnswer("Okay", villagerDialogue);
        villagerDialogue.addAnswer("How long have you lived here?", whyHere);
        villagerDialogue.addAnswer("Who is the evil king?", linearDialogue);
        TalkingNPC villager = npcFactory.createTalkingNPC("Villager", villagerDialogue, itemFactory);
        Scene villagerScene = sceneFactory.createTalkScene("TalkScene", "Village", villageScene, villageScene, villager, player);
        villageScene.addAction(new Action("Talk to the villager."), villagerScene);
        villageScene.addAction(new Action("Go to the trader."), villageTraderScene);
        villageScene.addAction(new Action("Approach the guard."), villageGuardScene);
        villageScene.addAction(new Action("Approach the shady figure."), banditScene);
        villageScene.addAction(new Action("Take the path to the forest"), forestScene);
        forestScene.addAction(new Action("Go back to the village"), villageScene);

        return villageScene;
    }
}
