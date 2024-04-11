package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Text field action listener class.
 */

public class TextFieldActionListener implements ActionListener {

    private final Graph graph;

    public TextFieldActionListener(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField textField = (JTextField) e.getSource();
        graph.rename(textField.getText());
    }
}