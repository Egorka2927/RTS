package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Add event action listener class.
 */
public class AddEventActionListener implements ActionListener {

    private JOptionPane optionPane;

    private Graph graph;

    private String[] buttons = {"God Bless", "Boredom Pandemic", "Gym Session"};

    public AddEventActionListener(Graph graph) {
        this.graph = graph;
        optionPane = new JOptionPane();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int userInput = optionPane.showOptionDialog(null, "Choose your event:", "Event", JOptionPane.PLAIN_MESSAGE,
                -1, null, buttons, null);
        if (userInput != - 1) {
            graph.addEvent(userInput);
        }
    }
}
