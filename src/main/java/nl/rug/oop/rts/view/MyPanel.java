package nl.rug.oop.rts.view;

import nl.rug.oop.rts.controller.GraphListener;
import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;
import nl.rug.oop.rts.util.TextureLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel class.
 */

public class MyPanel extends JPanel implements GraphListener {

    private final List<NodePanel> nodePanels;
    private final List<EdgeLine> edgeLines;

    private final Graph graph;

    /**
     * Panel constructor.
     * @param graph The graph to be displayed.
     */
    public MyPanel(Graph graph) {
        this.graph = graph;
        nodePanels = new ArrayList<>();
        edgeLines = new ArrayList<>();
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.orange);
        setLayout(null);
    }

    /**
     * Method to select a node panel.
     * @param id Node panel id.
     * @return The node panel you selected or null.
     */
    public NodePanel selectNodePanel(int id) {
        for (NodePanel np : nodePanels) {
            if (np.getId() == id) {
                return np;
            }
        }
        return null;
    }

    /**
     * Method to update nodes.
     * @param nodes The array of nodes.
     * @param selectedNode The node we selected.
     */
    public void updateNodes(List<Node> nodes, Node selectedNode) {
        for (Node node : nodes) {
            NodePanel np = new NodePanel(node.getX(), node.getY(), node.getId(), node.getName());
            if (!(node.getArmies().isEmpty())) {
                int gap = 0;
                for (Army a : node.getArmies()) {
                    ArmyPanel ap = new ArmyPanel(np.getX() + gap, np.getY() - 30,
                            a.getFaction(), a.getNumberOfUnits());
                    add(ap);
                    JLabel numberUnitsLabel = new JLabel();
                    numberUnitsLabel.setText(String.valueOf(a.getNumberOfUnits()));
                    numberUnitsLabel.setForeground(Color.black);
                    numberUnitsLabel.setBounds(ap.getX(), ap.getY() - 18, 50, 20);
                    add(numberUnitsLabel);
                    gap += 35;
                }
            }
            if (selectedNode != null && node.getId() == selectedNode.getId()) {
                np.setNewBorder();
            }
            nodePanels.add(np);
            add(np);
        }
    }

    /**
     * Method to update the edges.
     * @param edges The array of edges.
     * @param selectedEdge The selected edge.
     */
    public void updateEdges(List<Edge> edges, Edge selectedEdge) {
        for (Edge edge : edges) {
            EdgeLine el = new EdgeLine(edge.getId(), edge.getName(),
                    edge.getNode1(), edge.getNode2());
            if (!(edge.getArmies().isEmpty())) {
                int gap = 0;
                for (Army a : edge.getArmies()) {
                    ArmyPanel ap = new ArmyPanel(findEdgeX(edge) + gap, findEdgeY(edge),
                            a.getFaction(), a.getNumberOfUnits());
                    add(ap);
                    JLabel numberUnitsLabel = new JLabel();
                    numberUnitsLabel.setText(String.valueOf(a.getNumberOfUnits()));
                    numberUnitsLabel.setForeground(Color.black);
                    numberUnitsLabel.setBounds(ap.getX(), ap.getY() - 18, 50, 20);
                    add(numberUnitsLabel);
                    gap += 35;
                }
            }
            edgeLines.add(el);
            add(el);
            if (selectedEdge != null && edge.getId() == selectedEdge.getId()) {
                el.setSelected(true);
            }
        }
    }

    @Override
    public void graphUpdated(List<Node> nodes, List<Edge> edges, Node selectedNode, Edge selectedEdge) {

        removeAll();
        nodePanels.removeAll(nodePanels);
        edgeLines.removeAll(edgeLines);

        updateNodes(nodes, selectedNode);
        updateEdges(edges, selectedEdge);

        revalidate();
        repaint();

        for (Node node : nodes) {
            if (node.getEventHappened()) {
                JOptionPane.showMessageDialog(null,
                        "Event at " + node.getName() + ":\n"
                                + node.getEvents().get(node.getEventIndex()).getDescription());
            }
        }

        for (Edge edge : edges) {
            if (edge.getEventHappened()) {
                JOptionPane.showMessageDialog(null,
                        "Event at " + edge.getName() + ":\n"
                                + edge.getEvents().get(edge.getEventIndex()).getDescription());
            }
        }
        
        Component component = getComponentAt(0, 0);
        if (!(component instanceof NodePanel)) {
            graph.setNodeGap(0);
        }
    }

    public int findEdgeX(Edge edge) {
        return (edge.getNode1().getX() + edge.getNode2().getX()) / 2;
    }

    public int findEdgeY(Edge edge) {
        return (edge.getNode1().getY() + edge.getNode2().getY()) / 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(TextureLoader.getInstance().getTexture("mapTexture", 1920, 1080), 0, 0, null);
    }
}

