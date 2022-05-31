package nl.rug.oop.model;

import java.util.List;

/**
 *
 * @author Jonas Scholz
 */
public interface OutputEventListener {

    void updateScene(List<String> actions, String description, String image);
}
