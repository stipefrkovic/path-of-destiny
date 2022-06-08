package nl.rug.oop.npc;

import nl.rug.oop.items.ItemFactory;

import java.util.HashMap;

public class NPCFactory {

    private HashMap<String, Class> register = new HashMap();
    private String[] namePool = new String[]{"John", "Amelie", "Elizabeth", "James", "Lucifer", "Gabriel", "Uriel", "Jacob", "Anna", "Kevin", "Johanna", "Jan", "Richard", "Lisa", "Rumpelstilzchen", "Gretel", "Hänsel", "Pier", "Naut", "Floris", "Kai", "Simone", "Thorn", "Alexa", "Hildegard", "Romulus", "Beatrix", "Maggard", "Ophelia", "Maxim", "Sid", "Manni", "Ursula", "Brie", "Lucia", "Lucinda", "Scarlet", "Suriel", "Sofie", "Mammon", "Asmodeus", "Leviathan", "Beelzebub", "Satan", "Belphegor", "Daktocaez", "Pryrzod", "Icnagneeq", "Aiwin", "Llewel", "Elrond", "Barnaby", "Zach", "Jaxson", "Remy", "Johnny","Mollie", "Daisy", "Lacie", "Rene", "Jasmine", "Nicole", "Vanessa", "Elena", "Zoe", "Junior", "Ernie", "Bart" };

    public NPCFactory(){
        registerNPC("Spider", Spider.class);
        registerNPC("Bandit", Bandit.class);
        registerNPC("CastleGuard", CastleGuard.class);
        registerNPC("Guard", Guard.class);
        registerNPC("KingBoss", KingBoss.class);
        registerNPC("Rat", Rat.class);
        registerNPC("SpiderBoss", SpiderBoss.class);
        registerNPC("Trader", Trader.class);
        registerNPC("Villager", Villager.class);
    }

    public void registerNPC(String type, Class npcClass){
        register.put(type, npcClass);
    }

    public NPC createSimpleNPC(String type, String name, ItemFactory factory){
        try {
            Class npcType = register.get(type);
            return (NPC) npcType.getDeclaredConstructor(String.class, ItemFactory.class).newInstance(name, factory);
        } catch (Exception e) {
            return null;
        }
    }

    public TalkingNPC createTalkingNPC(String type, String name, Dialogue dialogue, ItemFactory factory){
        try {
            Class npcType = register.get(type);
            return (TalkingNPC) npcType.getDeclaredConstructor(String.class, Dialogue.class, ItemFactory.class).newInstance(name, dialogue, factory);
        } catch (Exception e) {
            return null;
        }
    }

    public BossNPC createBossNPC(String type, ItemFactory factory){
        try {
            Class npcType = register.get(type);
            return (BossNPC) npcType.getDeclaredConstructor(String.class, ItemFactory.class).newInstance(factory);
        } catch (Exception e) {
            return null;
        }
    }

    private String getRandomName(){
        int randIndex = (int) (Math.random() * namePool.length);
        return namePool[randIndex];
    }

    public NPC createSimpleNPC(String type, ItemFactory factory){
        return createSimpleNPC(type, getRandomName(), factory);
    }

    public TalkingNPC createTalkingNPC(String type, Dialogue dialogue, ItemFactory factory){
        return createTalkingNPC(type, getRandomName(), dialogue, factory);
    }

}