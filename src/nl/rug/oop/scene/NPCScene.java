package nl.rug.oop.scene;

import nl.rug.oop.npc.NPC;

import java.util.List;

/**
 * This interface should be implemented by every scene that includes npcs.
 * @author Jonas Scholz
 */
public interface NPCScene {

    /**
     * Returns the npcs of the scene.
     * @return A list of npcs that are in the scene.
     */
    List<NPC> getNPCs();
}
