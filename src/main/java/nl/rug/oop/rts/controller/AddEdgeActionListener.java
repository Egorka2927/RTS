package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Adding edge action listener class.
 */

public class AddEdgeActionListener implements ActionListener {

    private Graph graph;

    public AddEdgeActionListener(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e ) {
        graph.setPrevSelectedNode(graph.getSelectedNode());
    }
}
