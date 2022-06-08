package nl.rug.oop.npc;

import nl.rug.oop.items.ItemFactory;

import java.util.HashMap;

public class NPCFactory {

    private HashMap<String, Class> register = new HashMap();

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

}