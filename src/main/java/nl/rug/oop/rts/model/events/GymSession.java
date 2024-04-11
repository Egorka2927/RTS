package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Event;
import nl.rug.oop.rts.model.Unit;

import java.util.List;

/**
 * GymSession class (special event that increases the strength of each unit in an army).
 */
public class GymSession extends Event {

    /**
     * GymSession constructor.
     * @param armies The armies array.
     */
    public GymSession(List<Army> armies) {
        super(armies);
        description = "Never skip leg day! Your troops get empowered";
        name = "Gym Session";
    }

    @Override
    public void doEvent(List<Army> armies) {
        for (Army a : armies) {
            for (Unit u : a.getUnits()) {
                u.setDamage(u.getDamage() + 1);
            }
            a.setTotalDamage(a.getTotalDamage() + a.getUnits().size());
            a.setTotalHealth(a.getTotalHealth() + a.getUnits().size());
        }
    }
}
