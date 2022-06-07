package nl.rug.oop.npc;

import java.util.HashMap;

public class NPCFactory {

    private HashMap<String, Class> register = new HashMap();

    public void registerNPC(String type, Class npcClass){
        register.put(type, npcClass);
    }

    public NPC createSimpleNPC(String type, String name){
        try {
            Class npcType = register.get(type);
            return (NPC) npcType.getDeclaredConstructor(String.class).newInstance(name);
        } catch (Exception e) {
            return null;
        }
    }

    public TalkingNPC createTalkingNPC(String type, String name, Dialogue dialogue){
        try {
            Class npcType = register.get(type);
            return (TalkingNPC) npcType.getDeclaredConstructor(String.class, Dialogue.class).newInstance(name, dialogue);
        } catch (Exception e) {
            return null;
        }
    }

}