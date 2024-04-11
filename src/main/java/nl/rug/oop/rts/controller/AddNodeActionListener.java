package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Adding node action listener class.
 */

public class AddNodeActionListener implements ActionListener {

    private Graph graph;

    public AddNodeActionListener(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graph.addNode(graph.getNodeGap(), graph.getNodeGap(),
                graph.getCurrentNodePos(), "Node" + graph.getCurrentNodePos());
    }
}
