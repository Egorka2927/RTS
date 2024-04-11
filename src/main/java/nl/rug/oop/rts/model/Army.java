package nl.rug.oop.rts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Army class.
 */
public class Army {

    private List<Unit> units;
    private int numberOfUnits;
    private String faction;
    private int team;
    private Node destinationNode;
    private int totalDamage = 0;
    private int totalHealth = 0;

    private boolean moved = false;

    private Faction[] unitNames = Faction.values();

    /**
     * Army constructor.
     * @param faction The army's faction.
     * @param team The army's team.
     */
    public Army(String faction, int team) {
        this.faction = faction;
        this.team = team;

        int factionIndex = makeFactionIndex();

        int unit1Count = 500 + (int)(Math.random() * ((1000 - 500) + 1));
        int unit2Count= 500 + (int)(Math.random() * ((1000 - 500) + 1));
        int unit3Count = 500 + (int)(Math.random() * ((1000 - 500) + 1));

        int unit1Damage = 25 + (int)(Math.random() * ((50 - 25) + 1));
        int unit1Health = 50 + (int)(Math.random() * ((100 - 50) + 1));

        int unit2Damage = 25 + (int)(Math.random() * ((50 - 25) + 1));
        int unit2Health = 50 + (int)(Math.random() * ((100 - 50) + 1));

        int unit3Damage = 25 + (int)(Math.random() * ((50 - 25) + 1));
        int unit3Health = 50 + (int)(Math.random() * ((100 - 50) + 1));

        Unit unit1 = new Unit(unit1Damage, unit1Health, unitNames[factionIndex * 3].name());
        Unit unit2 = new Unit(unit2Damage, unit2Health, unitNames[factionIndex * 3 + 1].name());
        Unit unit3 = new Unit(unit3Damage, unit3Health, unitNames[factionIndex * 3 + 2].name());

        units = new ArrayList<>();

        addUnits(unit1Count, unit1);
        addUnits(unit2Count, unit2);
        addUnits(unit3Count, unit3);

        numberOfUnits = units.size();
    }

    /**
     * Method to add units to an army.
     * @param count Number of units to add.
     * @param unitType Type of unit to add.
     */
    public void addUnits(int count, Unit unitType) {
        for (int i = 0; i < count; i++) {
            units.add(unitType);
            totalDamage += unitType.getDamage();
            totalHealth += unitType.getHealth();
        }
    }

    /**
     * Method to create a faction index based on the name.
     * @return The faction index.
     */
    public int makeFactionIndex() {
        int factionIndex = -1;
        switch (faction) {
            case "Men":
                factionIndex = 0;
                return factionIndex;
            case "Elves":
                factionIndex = 1;
                return factionIndex;
            case "Dwarves":
                factionIndex = 2;
                return factionIndex;
            case "Mordor":
                factionIndex = 3;
                return factionIndex;
            case "Isengard":
                factionIndex = 4;
                return factionIndex;
        }
        return factionIndex;
    }

    public String getFaction() {
        return faction;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(Node destinationNode) {
        this.destinationNode = destinationNode;
    }

    public int getTeam() {
        return team;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }

    public int getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;
    }
}
