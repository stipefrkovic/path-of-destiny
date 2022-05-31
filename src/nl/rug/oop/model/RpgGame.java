package nl.rug.oop.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;

public class RpgGame {

    Collection<PropertyChangeListener> listeners = new ArrayList<>();

    /**
     * Method adds a listener to the array of listeners in the model.
     * @param listener listener
     */
    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }
}
