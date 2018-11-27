import java.awt.Point;

public class Bee {
    private int harvestSpeed;
    private int attack;
    private int health;
    private int endurance;
    private int defenseRatio;
    private String name;
    private boolean moveable = true;
    Point loc = new Point();
    
    // Empty Constructor
    public Bee () {
        
    }
    
    /*
     * Getters and Setters
     */
    public int getHarvestSpeed() {
        return harvestSpeed;
    }

    public void setHarvestSpeed(int harvestSpeed) {
        this.harvestSpeed = harvestSpeed;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getDefenseRatio() {
        return defenseRatio;
    }

    public void setDefenseRatio(int defenseRatio) {
        this.defenseRatio = defenseRatio;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isMoveable() {
        return moveable;
    }
    
    public void setMoveable(boolean bool) {
        this.moveable = bool;
    }
    
    public Point getLoc() {
        return loc;
    }
    
    public void setLoc(int x, int y) {
        this.loc.x = x;
        this.loc.y = y;
    }
}
