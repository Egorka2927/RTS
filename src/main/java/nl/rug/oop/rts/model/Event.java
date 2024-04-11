package nl.rug.oop.rts.model;

import java.util.List;

/**
 * Event abstract class.
 */
public abstract class Event {

    /**
     * The array for armies.
     */
    protected List<Army> armies;

    /**
     * The description of the event.
     */
    protected String description;

    /**
     * The event name.
     */
    protected String name;

    public Event(List<Army> armies) {
        this.armies = armies;
    }

    public abstract void doEvent(List<Army> armies);

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
