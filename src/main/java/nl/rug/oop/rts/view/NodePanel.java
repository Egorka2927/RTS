package nl.rug.oop.rts.view;

import nl.rug.oop.rts.util.TextureLoader;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Node panel class.
 */

public class NodePanel extends JPanel {

    private int x;
    private int y;
    private int id;
    private String name;
    private Border border;

    /**
     * Node panel constructor.
     * @param x Node panel x coordinate.
     * @param y Node panel y coordinate.
     * @param id Node panel id.
     * @param name Node panel name.
     */
    public NodePanel(int x, int y, int id, String name) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.name = name;
        border = BorderFactory.createLineBorder(Color.BLACK);
        setBounds(x, y, 75, 80);
        setOpaque(false);
        setLayout(new BorderLayout());
        JLabel label = new JLabel();
        label.setText(name);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.YELLOW);
        label.setVisible(true);
        add(label, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(TextureLoader.getInstance().getTexture("node1", getWidth(), getHeight()), 0, 0, null);
    }

    public void setNewBorder() {
        setBorder(border);
    }

    public int getId() {
        return id;
    }
}
