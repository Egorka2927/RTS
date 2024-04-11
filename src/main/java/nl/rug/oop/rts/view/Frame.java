package nl.rug.oop.rts.view;

import nl.rug.oop.rts.controller.NodeSelectListener;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Simulation;

import javax.swing.*;
import java.awt.*;

/**
 * Frame class.
 */

public class Frame extends JFrame {

    private Menu menu;

    private MyPanel panel;

    private Graph graph;

    private OptionsMenu optionsMenu;
    private JSplitPane split;
    private Simulation simulation;

    /**
     * Frame constructor.
     */
    public Frame() {
        graph = new Graph();
        simulation = new Simulation(graph);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("The best game ever");
        setPreferredSize(new Dimension(900, 600));
        pack();
        setLocationRelativeTo(null);
        panel = new MyPanel(graph);
        simulation.addListener(panel);
        menu = new Menu(graph, simulation);
        simulation.addListener(menu);
        setJMenuBar(menu);
        optionsMenu = new OptionsMenu(graph);
        simulation.addListener(optionsMenu);
        setLayout(new BorderLayout());
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, optionsMenu, panel);
        split.setDividerLocation(200);
        NodeSelectListener nodeSelectListener = new NodeSelectListener(panel, graph);
        panel.addMouseListener(nodeSelectListener);
        panel.addMouseMotionListener(nodeSelectListener);
        add(split);
        graph.addListener(panel);
        graph.addListener(menu);
        graph.addListener(optionsMenu);
        setVisible(true);
    }
}
