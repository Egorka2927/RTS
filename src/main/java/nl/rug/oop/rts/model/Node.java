package nl.rug.oop.rts.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Node class.
 */

public class Node {

    @Getter
    private int x;
    @Getter
    private int y;
    private int id;
    private String name;
    private List<Edge> edges;

    private List<Army> armies;
    private List<Event> events;
    private boolean eventHappened = false;
    private int eventIndex;

    /**
     * Node constructor.
     * @param x Node x coordinate.
     * @param y Node y coordinate.
     * @param id Node id.
     * @param name Node name.
     */
    public Node(int x, int y, int id, String name) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.name = name;
        edges = new ArrayList<>();
        armies = new ArrayList<>();
        events = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getId() {
        return id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public void setEventIndex(int eventIndex) {
        this.eventIndex = eventIndex;
    }

    public int getEventIndex() {
        return eventIndex;
    }
}
