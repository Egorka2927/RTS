package nl.rug.oop.rts.view;

import nl.rug.oop.rts.util.TextureLoader;

import javax.swing.*;
import java.awt.*;

/**
 * Army panel class.
 */
public class ArmyPanel extends JPanel {

    private int x;
    private int y;
    private String faction;

    /**
     * Army panel constructor.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param faction The army faction.
     * @param numberUnits The number of units in the army.
     */
    public ArmyPanel(int x, int y, String faction, int numberUnits) {
        this.x = x;
        this.y = y;
        this.faction = faction;
        setBounds(x, y, 30, 30);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(TextureLoader.getInstance().getTexture("faction" + faction,
                getWidth(), getHeight()), 0, 0, null);
    }
}
