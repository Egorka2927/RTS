package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Event;

import java.util.List;

/**
 * GodBless class (special event that increases the number of units in an army).
 */
public class GodBless extends Event {

    private int numberOfReinforcements;

    /**
     * GodBless constructor.
     * @param armies The armies array.
     */
    public GodBless(List<Army> armies) {
        super(armies);
        description = "God bless the Queen! Reinforcements have arrived";
        name = "God Bless";
    }

    @Override
    public void doEvent(List<Army> armies) {
        for (Army a : armies) {
            Army reinforcements = new Army(a.getFaction(), a.getTeam());
            a.getUnits().addAll(reinforcements.getUnits());
            a.setNumberOfUnits(a.getUnits().size());
            a.setTotalDamage(a.getTotalDamage() * 2);
            a.setTotalHealth(a.getTotalHealth() * 2);
        }
    }
}
