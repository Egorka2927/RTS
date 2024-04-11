package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Removing node action listener class.
 */

public class RemoveNodeActionListener implements ActionListener {

    private Graph graph;

    public RemoveNodeActionListener(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graph.removeNode(graph.getSelectedNode());
        graph.setSelectedNode(null);
    }
}
