package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * BoredomPandemic class (Special event that decreases the number of troops).
 */
public class BoredomPandemic extends Event {

    /**
     * BoredomPandemic constructor (see class description).
     * @param armies The armies affected by the event.
     */
    public BoredomPandemic(List<Army> armies) {
        super(armies);
        description = "You forced your army to write a report... Your troops start to desert...";
        name = "Boredom Pandemic";
    }

    /**
     * Method to find a specific unit.
     * @param units The units array.
     * @param unitName The name which we need to find.
     * @return The unit we want or null.
     */
    public Unit findUnit(List<Unit> units, String unitName) {
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            if (unit.getName().equals(unitName)) {
                return unit;
            } else {
                i += 499;
            }
        }
        return null;
    }

    /**
     * Method to remove specific units.
     * @param units Units array.
     * @param unit The unit we want to remove.
     * @param numberUnits How many units we want to remove.
     */
    public void removeUnits(List<Unit> units, Unit unit, int numberUnits) {
        for (int i = 0; i < numberUnits; i++) {
            if (units.contains(unit)) {
                units.remove(unit);
            } else {
                break;
            }
        }
    }

    /**
     * Method to add faction names to a list.
     * @param list The final list of names.
     */
    public void addFactions(List<String> list) {
        list.add("Men");
        list.add("Elves");
        list.add("Dwarves");
        list.add("Mordor");
        list.add("Isengard");
    }

    /**
     * Method to remove defeated armies (number of units <= 0).
     * @param armies The armies array.
     */
    public void removeDefeatedArmies(List<Army> armies) {
        for (int i = 0; i < armies.size(); i++) {
            if (armies.get(i).getNumberOfUnits() <= 0) {
                armies.remove(i);
                i -= 1;
            }
        }
    }

    @Override
    public void doEvent(List<Army> armies) {

        List<String> factions = new ArrayList<>();
        addFactions(factions);
        Faction[] unitNames = Faction.values();

        for (Army a : armies) {
            int numDeserters1 = 100 + (int)(Math.random() * ((300 - 100) + 1));
            int numDeserters2 = 100 + (int)(Math.random() * ((300 - 100) + 1));
            int numDeserters3 = 100 + (int)(Math.random() * ((300 - 100) + 1));

            int factionIndex = factions.indexOf(a.getFaction());

            Unit unit1 = findUnit(a.getUnits(), unitNames[factionIndex * 3].name());
            Unit unit2 = findUnit(a.getUnits(), unitNames[factionIndex * 3 + 1].name());
            Unit unit3 = findUnit(a.getUnits(), unitNames[factionIndex * 3 + 2].name());

            if (unit1 != null) {
                removeUnits(a.getUnits(), unit1, numDeserters1);
                a.setTotalDamage(a.getTotalDamage() - numDeserters1 * unit1.getDamage());
                a.setTotalHealth(a.getTotalHealth() - numDeserters1 * unit1.getHealth());
            }

            if (unit2 != null) {
                removeUnits(a.getUnits(), unit2, numDeserters2);
                a.setTotalDamage(a.getTotalDamage() - numDeserters2 * unit2.getDamage());
                a.setTotalHealth(a.getTotalHealth() - numDeserters2 * unit2.getHealth());
            }

            if (unit3 != null) {
                removeUnits(a.getUnits(), unit3, numDeserters3);
                a.setTotalDamage(a.getTotalDamage() - numDeserters3 * unit3.getDamage());
                a.setTotalHealth(a.getTotalHealth() - numDeserters3 * unit3.getHealth());
            }
            a.setNumberOfUnits(a.getUnits().size());
        }
        removeDefeatedArmies(armies);
    }
}
