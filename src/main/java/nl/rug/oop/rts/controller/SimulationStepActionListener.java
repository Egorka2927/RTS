package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simulation step action listener class.
 */
public class SimulationStepActionListener implements ActionListener {

    private Simulation simulation;

    public SimulationStepActionListener(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        simulation.doTimeStep();
    }
}
