package nl.rug.oop.rts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Edge class.
 */

public class Edge {
    private int id;
    private String name;
    private Node node1;
    private Node node2;
    private List<Army> armies;
    private List<Event> events;
    private boolean eventHappened = false;
    private int eventIndex;

    /**
     * Edge constructor.
     * @param id Edge id.
     * @param name Edge name.
     */
    public Edge(int id, String name) {
        this.id = id;
        this.name = name;
        armies = new ArrayList<>();
        events = new ArrayList<>();
    }

    public void setNode1(Node node) {
        node1 = node;
    }

    public void setNode2(Node node) {
        node2 = node;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Army> getArmies() {
        return armies;
    }

    public List<Event> getEvents() {
        return events;
    }

    public boolean getEventHappened() {
        return eventHappened;
    }

    public void setEventHappened(boolean eventHappened) {
        this.eventHappened = eventHappened;
    }

    public int getEventIndex() {
        return eventIndex;
    }

    public void setEventIndex(int eventIndex) {
        this.eventIndex = eventIndex;
    }
}
