package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.view.EdgeLine;
import nl.rug.oop.rts.view.MyPanel;
import nl.rug.oop.rts.view.NodePanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Node select listener class.
 */

public class NodeSelectListener extends MouseAdapter {

    private final MyPanel panel;

    private final Graph graph;

    public NodeSelectListener(MyPanel panel, Graph graph) {
        this.panel = panel;
        this.graph = graph;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        Component component = panel.getComponentAt(e.getPoint());
        if (component instanceof NodePanel node) {
            graph.selectNode(node.getId());
            graph.setSelectedEdge(null);
        } else if (component instanceof EdgeLine el) {
            graph.selectEdge(el.getId());
            graph.setSelectedNode(null);
            graph.setPrevSelectedNode(null);
        } else {
            graph.setSelectedNode(null);
            graph.setSelectedEdge(null);
            graph.setPrevSelectedNode(null);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        int x = e.getX();
        int y = e.getY();

        if (graph.getSelectedNode() != null) {

            int halfWidth = panel.selectNodePanel(graph.getSelectedNode().getId()).getWidth() / 2;
            int halfHeight = panel.selectNodePanel(graph.getSelectedNode().getId()).getHeight() / 2;

            if (x < halfWidth) {
                x = halfWidth;
            } else if (x > panel.getWidth() - halfWidth) {
                x = panel.getWidth() - halfWidth;
            }

            if (y < halfHeight) {
                y = halfHeight;
            } else if (y > panel.getHeight() - halfHeight) {
                y = panel.getHeight() - halfHeight;
            }
            graph.moveNode(x - halfWidth, y - halfHeight);
        }
    }
}
