package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Remove army action listener class.
 */
public class RemoveArmyActionListener implements ActionListener {

    private Graph graph;

    public RemoveArmyActionListener(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graph.removeArmy();
    }
}
