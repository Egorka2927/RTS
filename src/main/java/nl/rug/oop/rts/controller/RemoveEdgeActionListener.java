package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Removing edge action listener class.
 */

public class RemoveEdgeActionListener implements ActionListener {

    private Graph graph;

    public RemoveEdgeActionListener(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graph.removeEdge(graph.getSelectedEdge());
        graph.setSelectedEdge(null);
    }
}
