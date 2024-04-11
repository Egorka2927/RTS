package nl.rug.oop.rts.model;

/**
 * Faction enum (used to get the names of all units in all the factions).
 */
public enum Faction {

    GONDOR_SOLDIER("Men"), TOWER_GUARD("Men"), ITHILIEN_RANGER("Men"),
    LORIEN_WARRIOR("Elves"), MIRKWOOD_ARCHER("Elves") , RIVENDELL_LANCER("Elves"),
    GUARDIAN("Dwarves"), PHALANX("Dwarves"), AXE_THROWER("Dwarves"),
    ORC_WARRIOR("Mordor"), ORC_PIKEMAN("Mordor"), HARADRIM_ARCHER("Mordor"),
    URUK_HAI("Isengard"), URUK_CROSSBOWMAN("Isengard"), WARG_RIDER("Isengard");

    private String faction;

    Faction(String faction) {
        this.faction = faction;
    }
}
