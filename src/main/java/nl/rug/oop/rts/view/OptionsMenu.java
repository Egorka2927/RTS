package nl.rug.oop.rts.view;

import lombok.Getter;
import nl.rug.oop.rts.controller.*;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.Node;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Options menu class.
 */

public class OptionsMenu extends JPanel implements GraphListener {

    @Getter
    private JLabel label;

    private final JTextField textFieldName;
    private JLabel labelEventName;

    private JLabel labelArmyName;

    private final JLabel labelNode1;

    private final JLabel labelNode2;
    private JButton addArmyButton;
    private JButton removeArmyButton;
    private JButton addEventButton;
    private JButton removeEventButton;

    private Graph graph;

    /**
     * Options menu constructor.
     * @param graph The graph we display.
     */
    public OptionsMenu(Graph graph) {
        this.graph = graph;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        textFieldName = new JTextField();
        makeTextFiled(constraints, textFieldName);
        makeArmyButtons(constraints);
        makeEventButtons(constraints);
        labelNode1 = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        add(labelNode1, constraints);

        labelNode2 = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        add(labelNode2, constraints);

        labelNode1.setVisible(false);
        labelNode2.setVisible(false);
        labelNode1.setForeground(Color.YELLOW);
        labelNode2.setForeground(Color.YELLOW);
        labelNode1.setPreferredSize(new Dimension(60, 30));
        labelNode2.setPreferredSize(new Dimension(60, 30));

        label = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        add(label, constraints);

        label.setText("Nothing selected :0");
        label.setForeground(Color.YELLOW);
        setBackground(Color.darkGray);
    }

    /**
     * Method to make create a text field.
     * @param constraints The field constraints.
     * @param textFieldName The text field.
     */
    public void makeTextFiled(GridBagConstraints constraints, JTextField textFieldName) {
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 0.01;
        add(textFieldName, constraints);
        textFieldName.setVisible(false);
        textFieldName.setPreferredSize(new Dimension(60, 30));
        textFieldName.addActionListener(new TextFieldActionListener(this.graph));
    }

    /**
     * Method to make the event buttons.
     * @param constraints The button constraints.
     */
    public void makeEventButtons(GridBagConstraints constraints) {
        addEventButton = new JButton("+");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weighty = 0.0000000000000001;
        addEventButton.setVisible(false);
        addEventButton.setFocusable(false);
        addEventButton.addActionListener(new AddEventActionListener(this.graph));
        add(addEventButton, constraints);

        removeEventButton = new JButton("-");
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weighty = 0.01;
        removeEventButton.setVisible(false);
        removeEventButton.setFocusable(false);
        removeEventButton.addActionListener(new RemoveEventActionListener(this.graph));
        add(removeEventButton, constraints);

        labelEventName = new JLabel("Events");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 0.0000000000000001;
        labelEventName.setForeground(Color.yellow);
        labelEventName.setVisible(false);
        add(labelEventName, constraints);
    }

    /**
     * Method to make the army buttons.
     * @param constraints The button constraints.
     */
    public void makeArmyButtons(GridBagConstraints constraints) {
        addArmyButton = new JButton("+");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weighty = 0.0000000000000001;
        addArmyButton.setVisible(false);
        addArmyButton.addActionListener(new AddArmyActionListener(this.graph));
        addArmyButton.setFocusable(false);
        add(addArmyButton, constraints);

        removeArmyButton = new JButton("-");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weighty = 0.01;
        removeArmyButton.setVisible(false);
        removeArmyButton.addActionListener(new RemoveArmyActionListener(this.graph));
        removeArmyButton.setFocusable(false);
        add(removeArmyButton, constraints);

        labelArmyName = new JLabel("Armies");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 0.0000000000000001;
        labelArmyName.setForeground(Color.yellow);
        labelArmyName.setVisible(false);
        add(labelArmyName, constraints);
    }

    @Override
    public void graphUpdated(List<Node> nodes, List<Edge> edges, Node selectedNode, Edge selectedEdge) {
        label.setVisible(selectedNode == null && selectedEdge == null);
        textFieldName.setVisible(selectedNode != null || selectedEdge != null);
        if (selectedNode != null) {
            textFieldName.setText(selectedNode.getName());
            labelEventName.setVisible(true);
            labelArmyName.setVisible(true);
            labelNode1.setVisible(false);
            labelNode2.setVisible(false);
            addArmyButton.setVisible(true);
            removeArmyButton.setVisible(true);
            addEventButton.setVisible(true);
            removeEventButton.setVisible(true);
        } else if (selectedEdge != null) {
            labelArmyName.setVisible(false);
            labelEventName.setVisible(true);
            textFieldName.setText(selectedEdge.getName());
            labelNode1.setVisible(true);
            labelNode2.setVisible(true);
            labelNode1.setText(selectedEdge.getNode1().getName());
            labelNode2.setText(selectedEdge.getNode2().getName());
            addArmyButton.setVisible(false);
            removeArmyButton.setVisible(false);
            addEventButton.setVisible(true);
            removeEventButton.setVisible(true);
        } else {
            labelArmyName.setVisible(false);
            labelEventName.setVisible(false);
            labelNode1.setVisible(false);
            labelNode2.setVisible(false);
            addArmyButton.setVisible(false);
            removeArmyButton.setVisible(false);
            addEventButton.setVisible(false);
            removeEventButton.setVisible(false);
        }
    }
}
