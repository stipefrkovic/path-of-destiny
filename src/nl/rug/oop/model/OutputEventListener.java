package nl.rug.oop.model;


import nl.rug.oop.npc.NPC;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jonas Scholz
 */
public interface OutputEventListener {

    void updateScene(List<String> actions, String description, String image, List<NPC> npcs);
}
