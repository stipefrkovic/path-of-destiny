package nl.rug.oop.npc;

import nl.rug.oop.dialogue.Dialogue;
import nl.rug.oop.items.ItemFactory;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A factory class that can create registers NPCs.
 * @author Jonas Scholz
 */
public class NPCFactory implements Serializable {

    private HashMap<String, Class> register = new HashMap();
    private String[] namePool = new String[]{"John", "Amelie", "Elizabeth", "James", "Lucifer", "Gabriel", "Uriel", "Jacob", "Anna", "Kevin", "Johanna", "Jan", "Richard", "Lisa", "Rumpelstilzchen", "Gretel", "Hänsel", "Pier", "Naut", "Floris", "Kai", "Simone", "Thorn", "Alexa", "Hildegard", "Romulus", "Beatrix", "Maggard", "Ophelia", "Maxim", "Sid", "Manni", "Ursula", "Brie", "Lucia", "Lucinda", "Scarlet", "Suriel", "Sofie", "Mammon", "Asmodeus", "Leviathan", "Beelzebub", "Satan", "Belphegor", "Daktocaez", "Pryrzod", "Icnagneeq", "Aiwin", "Llewel", "Elrond", "Barnaby", "Zach", "Jaxson", "Remy", "Johnny","Mollie", "Daisy", "Lacie", "Rene", "Jasmine", "Nicole", "Vanessa", "Elena", "Zoe", "Junior", "Ernie", "Bart" };

    /**
     * Registers standard NPCs.
     */
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

    /**
     * Registers an NPC under a specified key.
     * @param type The key that can be used to get the class.
     * @param npcClass The npc class associated with the key.
     */
    public void registerNPC(String type, Class npcClass){
        register.put(type, npcClass);
    }

    /**
     * Creates an NPC by calling the constructor with the name of the npc and a ItemFactory.
     * Not ever NPC has this constructor.
     * @param type The type of NPC that is supposed to be created.
     * @param name The name of the NPC.
     * @param factory An ItemFactory that is used by the npc to generate its loot.
     * @return The created NPC or null, if the process failed.
     */
    public NPC createSimpleNPC(String type, String name, ItemFactory factory){
        try {
            Class npcType = register.get(type);
            return (NPC) npcType.getDeclaredConstructor(String.class, ItemFactory.class).newInstance(name, factory);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates a talking NPC.
     * Not every type of NPC can be created this way.
     * @param type The type of NPC that is supposed to be created.
     * @param name The name of the NPC.
     * @param dialogue The dialogue that the NPC is supposed to be able to hold.
     * @param factory An ItemFactory that is used by the npc to generate its loot.
     * @return The created TalkingNPC or null, if the process failed.
     */
    public TalkingNPC createTalkingNPC(String type, String name, Dialogue dialogue, ItemFactory factory){
        try {
            Class npcType = register.get(type);
            return (TalkingNPC) npcType.getDeclaredConstructor(String.class, Dialogue.class, ItemFactory.class).newInstance(name, dialogue, factory);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates a Boss NPC.
     * Not every type of NPC can be created this way.
     * @param type The type of NPC that is supposed to be created.
     * @param factory An ItemFactory that is used by the npc to generate its loot.
     * @return The created BossNPC or null, if the process failed.
     */
    public BossNPC createBossNPC(String type, ItemFactory factory){
        try {
            Class npcType = register.get(type);
            return (BossNPC) npcType.getDeclaredConstructor(ItemFactory.class).newInstance(factory);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Generates a random name from the namePool.
     * @return A random name from the namePool.
     */
    private String getRandomName(){
        int randIndex = (int) (Math.random() * namePool.length);
        return namePool[randIndex];
    }

    /**
     * Creates a simple NPC with a random name.
     * Not every type of NPC can be created this way.
     * @param type The type of NPC that is supposed to be created.
     * @param factory An ItemFactory that is used by the npc to generate its loot.
     * @return The created NPC or null, if the process failed.
     */
    public NPC createSimpleNPC(String type, ItemFactory factory){
        return createSimpleNPC(type, getRandomName(), factory);
    }

    /**
     * Creates a talking NPC, with a random name.
     * Not every type of NPC can be created this way.
     * @param type The type of NPC that is supposed to be created.
     * @param dialogue The dialogue that the NPC is supposed to be able to hold.
     * @param factory An ItemFactory that is used by the npc to generate its loot.
     * @return The created TalkingNPC or null, if the process failed.
     */
    public TalkingNPC createTalkingNPC(String type, Dialogue dialogue, ItemFactory factory){
        return createTalkingNPC(type, getRandomName(), dialogue, factory);
    }

}