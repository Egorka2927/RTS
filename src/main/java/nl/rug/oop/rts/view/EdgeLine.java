package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.*;

/**
 * Edge line (panel) class.
 */

public class EdgeLine extends JPanel {
    private int id;
    private String name;
    private Node node1;
    private Node node2;
    private boolean selected;

    /**
     * Edge line constructor.
     * @param id Edge line id.
     * @param name Edge line name.
     * @param node1 Edge line first node.
     * @param node2 Edge line second node.
     */
    public EdgeLine(int id, String name, Node node1, Node node2) {

        this.id = id;
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
        selected = false;

        if (node1.getX() <= node2.getX() && node1.getY() <= node2.getY()) {
            setBounds(node1.getX() + 25, node1.getY() + 30, Math.abs(node2.getX() - node1.getX()),
                    Math.abs(node2.getY() - node1.getY()));
        } else if (node1.getX() >= node2.getX() && node1.getY() <= node2.getY()){
            setBounds(node2.getX() + 25, node1.getY() + 30, Math.abs(node2.getX() - node1.getX()),
                    Math.abs(node2.getY() - node1.getY()));
        } else if (node1.getX() <= node2.getX()) {
            setBounds(node1.getX() + 25, node2.getY() + 30, Math.abs(node2.getX() - node1.getX()),
                    Math.abs(node2.getY() - node1.getY()));
        } else {
            setBounds(node2.getX() + 25, node2.getY() + 30, Math.abs(node2.getX() - node1.getX()),
                    Math.abs(node2.getY() - node1.getY()));
        }
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (selected) {
            g.setColor(Color.BLUE);
        }

        if (node1.getX() <= node2.getX() && node1.getY() <= node2.getY()) {
            g.drawLine(0, 0, node2.getX() - node1.getX(), node2.getY() - node1.getY());
        } else if (node1.getX() >= node2.getX() && node1.getY() <= node2.getY()){
            g.drawLine(node1.getX() - node2.getX(), 0, 0 , node2.getY() - node1.getY());
        } else if (node1.getX() <= node2.getX()) {
            g.drawLine(node2.getX() - node1.getX(), 0, 0, node1.getY() - node2.getY());
        } else {
            g.drawLine(0, 0, node1.getX() - node2.getX(), node1.getY() - node2.getY());
        }

    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }
}
