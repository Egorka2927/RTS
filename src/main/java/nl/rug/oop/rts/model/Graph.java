package nl.rug.oop.rts.model;

import lombok.Getter;
import nl.rug.oop.rts.controller.GraphListener;
import nl.rug.oop.rts.model.events.BoredomPandemic;
import nl.rug.oop.rts.model.events.GodBless;
import nl.rug.oop.rts.model.events.GymSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Graph class.
 */

public class Graph {

    private final List<Node> nodes;
    private final List<Edge> edges;
    private final Collection<GraphListener> listeners;
    private Node selectedNode;
    private Node prevSelectedNode;
    private Edge selectedEdge;
    private int nodeGap = 0;

    @Getter
    private int currentNodeId = 0;

    @Getter
    private int currentNodePos = 0;

    @Getter
    private int currentEdgeId = 0;

    @Getter
    private int currentEdgePos = 0;

    /**
     * Graph constructor.
     */
    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void addListener(GraphListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (GraphListener listener : listeners) {
            listener.graphUpdated(nodes, edges, selectedNode, selectedEdge);
        }
    }

    /**
     * Method to move a node.
     * @param newX The new x coordinate.
     * @param newY The new y coordinate.
     */
    public void moveNode(int newX, int newY) {
        selectedNode.setX(newX);
        selectedNode.setY(newY);
        notifyListeners();
    }

    /**
     * Method to add a new node.
     * @param x New node x coordinate.
     * @param y New node y coordinate.
     * @param id New node id.
     * @param nodeName New node name.
     */
    public void addNode(int x, int y, int id, String nodeName) {
        Node node = new Node(x + nodeGap, y + nodeGap, id, nodeName);
        nodes.add(node);
        currentNodeId++;
        currentNodePos++;
        nodeGap += 5;
        notifyListeners();
    }

    /**
     * Method to remove a node.
     * @param node The node to be removed.
     */
    public void removeNode(Node node) {
        for (int i = node.getEdges().size() - 1; i >= 0; i--) {
            Edge edge = node.getEdges().get(i);
            Node secondNode;
            if (node.getId() == edge.getNode1().getId()) {
                secondNode = edge.getNode2();
            } else {
                secondNode = edge.getNode1();
            }
            node.getEdges().remove(edge);
            secondNode.getEdges().remove(edge);
            edges.remove(edge);
            currentEdgeId--;
        }
        currentNodeId--;
        nodes.remove(node);
        notifyListeners();
    }

    /**
     * Method to add a new edge.
     * @param id New edge id
     * @param edgeName New edge name
     * @param id1 New edge node1 id.
     * @param id2 New edge node2 id.
     */
    public void addEdge(int id, String edgeName, int id1, int id2) {
        if (prevSelectedNode.getId() == selectedNode.getId()) {
            return;
        }

        for (Edge e :edges) {
            if (e.getNode1().getId() == prevSelectedNode.getId() && e.getNode2().getId() == selectedNode.getId() ||
                e.getNode1().getId() == selectedNode.getId() && e.getNode2().getId() == prevSelectedNode.getId()) {
                return;
            }
        }

        Edge edge = new Edge(id, edgeName);
        edge.setNode1(prevSelectedNode);
        edge.setNode2(selectedNode);
        edges.add(edge);
        prevSelectedNode.getEdges().add(edge);
        selectedNode.getEdges().add(edge);
        currentEdgeId++;
        currentEdgePos++;
        notifyListeners();
    }

    /**
     * Method to remove an edge.
     * @param edge The edge to be removed.
     */
    public void removeEdge(Edge edge) {
        edge.getNode1().getEdges().remove(edge);
        edge.getNode2().getEdges().remove(edge);
        edges.remove(edge);
        currentEdgePos--;
        notifyListeners();
    }

    /**
     * Method to select a node.
     * @param id The id of the node to be selected.
     */
    public void selectNode(int id) {
        for (Node node : nodes) {
            if (node.getId() == id) {
                selectedNode = node;
                if (prevSelectedNode != null) {
                    addEdge(currentEdgeId, "Edge" + currentEdgeId,
                            prevSelectedNode.getId(), selectedNode.getId());
                    prevSelectedNode = null;
                }
                break;
            }
        }
        notifyListeners();
    }

    /**
     * Method to select an edge.
     * @param id The id of the edge to be selected.
     */
    public void selectEdge(int id) {
        for (Edge edge : edges) {
            if (edge.getId() == id) {
                selectedEdge = edge;
                break;
            }
        }
        notifyListeners();
    }

    /**
     * Method to rename a node or an edge.
     * @param name The new name of the node or edge.
     */
    public void rename(String name) {
        if (selectedNode != null) {
            selectedNode.setName(name);
            notifyListeners();
        } else if (selectedEdge != null) {
            selectedEdge.setName(name);
            notifyListeners();
        }
    }

    /**
     * Method to check if a specific event is happening.
     * @param events The events array.
     * @param event A specific event.
     * @return True or false.
     */
    public boolean checkEvent(List<Event> events, Event event) {
        for (Event e : events) {
            if (e.description.equals(event.description)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to add an event.
     * @param userInput Which event the user picked.
     */
    public void addEvent(int userInput) {
        List<Event> events = new ArrayList<>();

        if (selectedNode != null) {
            events.add(new GodBless(selectedNode.getArmies()));
            events.add(new BoredomPandemic(selectedNode.getArmies()));
            events.add(new GymSession(selectedNode.getArmies()));

            if (!checkEvent(selectedNode.getEvents(), events.get(userInput))) {
                selectedNode.getEvents().add(events.get(userInput));
            }
        } else {
            events.add(new GodBless(selectedEdge.getArmies()));
            events.add(new BoredomPandemic(selectedEdge.getArmies()));
            events.add(new GymSession(selectedEdge.getArmies()));

            if (!checkEvent(selectedEdge.getEvents(), events.get(userInput))) {
                selectedEdge.getEvents().add(events.get(userInput));
            }
        }

        notifyListeners();
    }

    /**
     * Method to remove an event.
     */
    public void removeEvent() {
        if (selectedNode != null) {
            if (!selectedNode.getEvents().isEmpty()) {
                selectedNode.getEvents().remove(selectedNode.getEvents().size() - 1);
            }
        } else {
            if (!selectedEdge.getEvents().isEmpty()) {
                selectedEdge.getEvents().remove(selectedEdge.getEvents().size() - 1);
            }
        }
        notifyListeners();
    }

    /**
     * Method to add an army.
     * @param userInput What type of army the user picked.
     */
    public void addArmy(int userInput) {
        int team;
        String[] factionNames = {"Men", "Elves", "Dwarves", "Mordor", "Isengard"};

        if (userInput == 0 || userInput == 1 || userInput == 2) {
            team = 0;
        } else {
            team = 1;
        }

        Army army = new Army(factionNames[userInput], team);

        selectedNode.getArmies().add(army);
        notifyListeners();
    }

    /**
     * Method to remove an army.
     */
    public void removeArmy() {
        if (!(selectedNode.getArmies().isEmpty())) {
            selectedNode.getArmies().remove(selectedNode.getArmies().size() - 1);
        }
        notifyListeners();
    }

    public Node getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(Node selectedNode) {
        this.selectedNode = selectedNode;
        notifyListeners();
    }

    public int getNodeGap() {
        return nodeGap;
    }

    public void setNodeGap(int nodeGap) {
        this.nodeGap = nodeGap;
    }

    public void setPrevSelectedNode(Node prevSelectedNode) {
        this.prevSelectedNode = prevSelectedNode;
    }

    public void setSelectedEdge(Edge selectedEdge) {
        this.selectedEdge = selectedEdge;
        notifyListeners();
    }

    public Edge getSelectedEdge() {
        return selectedEdge;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
