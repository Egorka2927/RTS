package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.SaveToJSON;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Save action listener class for saving to JSON.
 */
public class SaveActionListener implements ActionListener {

    private Graph graph;

    public SaveActionListener(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SaveToJSON saveToJSON;
        String file = null;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("JSON file",
                "json");

        fileChooser.setFileFilter(fileNameExtensionFilter);

        int userInput = fileChooser.showSaveDialog(null);

        if (userInput == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getCurrentDirectory() + File.separator + fileChooser.getSelectedFile().getName();
        }

        if (file != null) {
            saveToJSON = new SaveToJSON(graph, file);
            saveToJSON.writeToFile(graph, file);
        }
    }
}
