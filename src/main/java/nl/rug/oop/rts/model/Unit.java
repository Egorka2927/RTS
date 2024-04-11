package nl.rug.oop.rts.model;

/**
 * Unit class.
 */
public class Unit {

    private int damage;
    private int health;
    private String name;

    /**
     * Unit constructor.
     * @param damage Units damage.
     * @param health Unit health.
     * @param name Unit name.
     */
    public Unit(int damage, int health, String name) {
        this.damage = damage;
        this.health = health;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
