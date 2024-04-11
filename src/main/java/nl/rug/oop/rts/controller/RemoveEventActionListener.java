package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Remove event action listener.
 */
public class RemoveEventActionListener implements ActionListener {

    private Graph graph;

    public RemoveEventActionListener(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graph.removeEvent();
    }
}
