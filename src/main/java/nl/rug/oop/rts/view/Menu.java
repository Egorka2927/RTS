package nl.rug.oop.rts.view;

import nl.rug.oop.rts.controller.*;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.model.Simulation;

import javax.swing.*;
import java.util.List;

/**
 * Menu class.
 */

public class Menu extends JMenuBar implements GraphListener {

    private JButton addNodeButton;
    private JButton removeNodeButton;
    private JButton addEdgeButton;
    private JButton removeEdgeButton;
    private JButton simulationButton;
    private JButton saveButton;

    private final Graph graph;
    private Simulation simulation;

    /**
     * Menu constructor.
     * @param graph The graph we use.
     * @param simulation The simulation we do.
     */
    public Menu(Graph graph, Simulation simulation) {
        this.graph = graph;
        this.simulation = simulation;
        makeMenuBar();
    }

    /**
     * Method to create a button.
     * @param x Button x coordinate.
     * @param y Button y coordinate.
     * @param width Button width.
     * @param height Button height.
     * @param text Button text.
     * @return The button we just created.
     */
    public JButton makeButton(int x, int y, int width, int height, String text) {
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
        button.setText(text);
        add(button);
        return button;
    }

    /**
     * Make menu bar method.
     */
    public void makeMenuBar() {
        addNodeButton = makeButton(0, 0, 100, 30, "Add Node");
        addNodeButton.addActionListener(new AddNodeActionListener(graph));

        removeNodeButton = makeButton(100, 0, 100, 30, "Remove Node");
        removeNodeButton.setEnabled(false);
        removeNodeButton.addActionListener(new RemoveNodeActionListener(graph));

        addEdgeButton = makeButton(200, 0, 100, 30, "Add Edge");
        addEdgeButton.setEnabled(false);
        addEdgeButton.addActionListener(new AddEdgeActionListener(graph));

        removeEdgeButton = makeButton(300, 0, 100, 30, "Remove Edge");
        removeEdgeButton.setEnabled(false);
        removeEdgeButton.addActionListener(new RemoveEdgeActionListener(graph));

        simulationButton = makeButton(400, 0, 100, 30, "Simulate Step");
        simulationButton.addActionListener(new SimulationStepActionListener(simulation));

        saveButton = makeButton(500, 0, 100, 30, "To JSON");
        saveButton.addActionListener(new SaveActionListener(this.graph));

        add(addNodeButton);
        add(removeNodeButton);
        add(addEdgeButton);
        add(removeEdgeButton);
        add(simulationButton);
        add(saveButton);
    }

    @Override
    public void graphUpdated(List<Node> nodes, List<Edge> edges, Node selectedNode, Edge selectedEdge) {
        if (selectedNode != null) {
            removeNodeButton.setEnabled(true);
            addEdgeButton.setEnabled(true);
            removeEdgeButton.setEnabled(false);
        } else if (selectedEdge != null) {
            removeEdgeButton.setEnabled(true);
            removeNodeButton.setEnabled(false);
            addEdgeButton.setEnabled(false);
        } else {
            removeEdgeButton.setEnabled(false);
            removeNodeButton.setEnabled(false);
            addEdgeButton.setEnabled(false);
        }
    }
}
