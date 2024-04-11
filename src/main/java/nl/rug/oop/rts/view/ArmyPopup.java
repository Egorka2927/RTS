package nl.rug.oop.rts.view;

import javax.swing.*;

/**
 * Army popup class.
 */
public class ArmyPopup extends JOptionPane {
    private String[] buttons = {"Men", "Elves", "Dwarves", "Mordor", "Isengard"};

    public String[] getButtons() {
        return buttons;
    }
}
