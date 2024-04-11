package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.view.ArmyPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Add army action listener class.
 */
public class AddArmyActionListener implements ActionListener {

    private ArmyPopup optionPane;
    private Graph graph;

    public AddArmyActionListener(Graph graph) {
        this.graph = graph;
        optionPane = new ArmyPopup();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int userInput = optionPane.showOptionDialog(null, "Choose your faction:",
                "Faction", JOptionPane.PLAIN_MESSAGE, -1,
                null, optionPane.getButtons(), null);
        if (userInput != -1) {
            graph.addArmy(userInput);
        }
    }
}
