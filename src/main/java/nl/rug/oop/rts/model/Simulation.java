package nl.rug.oop.rts.model;

import nl.rug.oop.rts.controller.GraphListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Simulation class.
 */
public class Simulation {

    private Graph graph;
    private Collection<GraphListener> listeners;

    public Simulation(Graph graph) {
        this.graph = graph;
        listeners = new ArrayList<>();
    }

    private void notifyListeners() {
        for (GraphListener listener : listeners) {
            listener.graphUpdated(graph.getNodes(), graph.getEdges(), graph.getSelectedNode(), graph.getSelectedEdge());
        }
    }

    public void addListener(GraphListener listener) {
        listeners.add(listener);
    }

    /**
     * Method to do the time step for nodes.
     */
    public void nodesTimeStep() {
        for (Node n : graph.getNodes()) {
            List<Army> armiesRemoved = new ArrayList<>();
            for (Army a : n.getArmies()) {
                if (!a.isMoved()) {
                    a.setMoved(true);
                    if (!n.getEdges().isEmpty()) {
                        armiesRemoved.add(a);
                        int randEdge = (int) (Math.random() * (((n.getEdges().size() - 1)) + 1));
                        if (n.getEdges().get(randEdge).getNode1().getId() == n.getId()) {
                            a.setDestinationNode(n.getEdges().get(randEdge).getNode2());
                        } else {
                            a.setDestinationNode(n.getEdges().get(randEdge).getNode1());
                        }
                        n.getEdges().get(randEdge).getArmies().add(a);
                    }
                }
            }
            for (Army a : armiesRemoved) {
                n.getArmies().remove(a);
            }
        }
    }

    /**
     * Method to do the time step for edges.
     */
    public void edgeTimeStep() {
        for (Edge e : graph.getEdges()) {
            List<Army> armiesRemoved = new ArrayList<>();
            for (Army a : e.getArmies()) {
                if (!a.isMoved()) {
                    a.setMoved(true);
                    armiesRemoved.add(a);
                    a.getDestinationNode().getArmies().add(a);
                }
            }
            for (Army a : armiesRemoved) {
                e.getArmies().remove(a);
            }
        }
    }

    /**
     * Main method to do the time step.
     */
    public void doTimeStep() {
        doBattle();
        nodesTimeStep();
        edgeTimeStep();

        for (Node n : graph.getNodes()) {
            for (Army a : n.getArmies()) {
                a.setMoved(false);
            }
        }

        for (Edge e : graph.getEdges()) {
            for (Army a : e.getArmies()) {
                a.setMoved(false);
            }
        }

        doEvent();

        notifyListeners();
    }

    /**
     * Method to call an event.
     */
    public void doEvent() {
        for (Node n : graph.getNodes()) {
            if (!n.getArmies().isEmpty() && !n.getEvents().isEmpty()) {
                int eventHappens = (int) (Math.random() * ((3) + 1));
                if (eventHappens == 1) {
                    int eventIndex = (int) (Math.random() * ((n.getEvents().size() - 1) + 1));
                    n.getEvents().get(eventIndex).doEvent(n.getArmies());
                    n.setEventHappened(true);
                    n.setEventIndex(eventIndex);
                    notifyListeners();
                    n.setEventHappened(false);
                }
            }
        }

        for (Edge e : graph.getEdges()) {
            if (!e.getArmies().isEmpty() && !e.getEvents().isEmpty()) {
                int eventHappens = (int) (Math.random() * ((3) + 1));
                if (eventHappens == 1) {
                    int eventIndex = (int) (Math.random() * ((e.getEvents().size() - 1) + 1));
                    e.getEvents().get(eventIndex).doEvent(e.getArmies());
                    e.setEventHappened(true);
                    e.setEventIndex(eventIndex);
                    notifyListeners();
                    e.setEventHappened(false);
                }
            }
        }
    }

    /**
     * Method to start a batlle.
     */
    public void doBattle() {

        int totalStrength1;
        int totalStrength2;

        for (Node n : graph.getNodes()) {
            if (nodeCheckArmies(n)) {
                totalStrength1 = getTeamDamageNode(n, 0) + getTeamHealthNode(n, 0);
                totalStrength2 = getTeamDamageNode(n, 1) + getTeamHealthNode(n, 1);

                if (totalStrength1 < totalStrength2) {
                    removeArmiesNode(n, 0);
                    decreaseUnitsNode(n, 1);
                } else {
                    removeArmiesNode(n, 1);
                    decreaseUnitsNode(n, 0);
                }
            }
        }

        for (Edge e : graph.getEdges()) {
            if (edgeCheckArmies(e)) {
                totalStrength1 = getTeamDamageEdge(e, 0) + getTeamHealthEdge(e, 0);
                totalStrength2 = getTeamDamageEdge(e, 1) + getTeamHealthEdge(e, 1);

                if (totalStrength1 < totalStrength2) {
                    removeArmiesEdge(e, 0);
                    decreaseUnitsEdge(e, 1);
                } else {
                    removeArmiesEdge(e, 1);
                    decreaseUnitsEdge(e, 0);
                }
            }
        }
    }

    /**
     * Method to reduce the number of units in a node.
     * @param node The node from which we remove.
     * @param team The team from which we remove.
     */
    public void decreaseUnitsNode(Node node, int team) {
        for (Army a : node.getArmies()) {
            if (team == a.getTeam()) {
                a.setNumberOfUnits(a.getNumberOfUnits() / 2);
                a.setTotalDamage(a.getTotalDamage() / 2);
                a.setTotalHealth(a.getTotalHealth() / 2);
                for (int i = 0; i < a.getNumberOfUnits(); i++) {
                    a.getUnits().remove(a.getUnits().size() - 1);
                }
            }
        }
    }

    /**
     * Method to reduce the number of units from an edge.
     * @param edge The edge we reove from.
     * @param team The team we remove from.
     */
    public void decreaseUnitsEdge(Edge edge, int team) {
        for (Army a : edge.getArmies()) {
            if (team == a.getTeam()) {
                a.setNumberOfUnits(a.getNumberOfUnits() / 2);
                a.setTotalDamage(a.getTotalDamage() / 2);
                a.setTotalHealth(a.getTotalHealth() / 2);
                for (int i = 0; i < a.getNumberOfUnits(); i++) {
                    a.getUnits().remove(a.getUnits().size() - 1);
                }
            }
        }
    }

    /**
     * Method to remove armies from a node.
     * @param node The node we remove from.
     * @param team The team we remove from.
     */
    public void removeArmiesNode(Node node, int team) {

        List<Army> armiesRemoved = new ArrayList<>();

        for (Army a : node.getArmies()) {
            if (a.getTeam() == team) {
                armiesRemoved.add(a);
            }
        }

        for (Army a : armiesRemoved) {
            node.getArmies().remove(a);
        }
    }

    /**
     * Method to remove armies from an edge.
     * @param edge The edge we remove from.
     * @param team The team we remove from.
     */
    public void removeArmiesEdge(Edge edge, int team) {

        List<Army> armiesRemoved = new ArrayList<>();

        for (Army a : edge.getArmies()) {
            if (a.getTeam() == team) {
                armiesRemoved.add(a);
            }
        }

        for (Army a : armiesRemoved) {
            edge.getArmies().remove(a);
        }
    }

    /**
     * Method to check armies in a node.
     * @param node The node we check.
     * @return True or false.
     */
    public boolean nodeCheckArmies(Node node) {
        boolean team0Found = false;
        boolean team1Found = false;
        for (Army a : node.getArmies()) {
            if (a.getTeam() == 0) {
                team0Found = true;
            } else {
                team1Found = true;
            }
        }
        return team0Found && team1Found;
    }

    /**
     * Method to check armies in an edge.
     * @param edge The edge we check.
     * @return True or false.
     */
    public boolean edgeCheckArmies(Edge edge) {
        boolean team0Found = false;
        boolean team1Found = false;
        for (Army a : edge.getArmies()) {
            if (a.getTeam() == 0) {
                team0Found = true;
            } else {
                team1Found = true;
            }
        }
        return team0Found && team1Found;
    }

    /**
     * Method to get the damage from a team.
     * @param node The node we check.
     * @param team The team we get the damage from.
     * @return Total damage.
     */
    public int getTeamDamageNode(Node node, int team) {
        int totalDamage = 0;
        for (Army a : node.getArmies()) {
            if (a.getTeam() == team) {
                totalDamage += a.getTotalDamage();
            }
        }
        return totalDamage;
    }

    /**
     * Method to get the health from a team.
     * @param node The node we check.
     * @param team The team we get the health from.
     * @return Total health.
     */
    public int getTeamHealthNode(Node node, int team) {
        int totalHealth = 0;
        for (Army a : node.getArmies()) {
            if (a.getTeam() == team) {
                totalHealth += a.getTotalHealth();
            }
        }
        return totalHealth;
    }

    /**
     * Method to get the damage from an edge.
     * @param edge The edge we check.
     * @param team The team we get the health from.
     * @return Total health.
     */
    public int getTeamDamageEdge(Edge edge, int team) {
        int totalDamage = 0;
        for (Army a : edge.getArmies()) {
            if (a.getTeam() == team) {
                totalDamage += a.getTotalDamage();
            }
        }
        return totalDamage;
    }

    /**
     * Method to get the health from an edge.
     * @param edge The edge we check.
     * @param team The team we get the health from.
     * @return Total health.
     */
    public int getTeamHealthEdge(Edge edge, int team) {
        int totalHealth = 0;
        for (Army a : edge.getArmies()) {
            if (a.getTeam() == team) {
                totalHealth += a.getTotalHealth();
            }
        }
        return totalHealth;
    }
}
