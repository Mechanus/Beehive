
public class Bee {
    private int harvestSpeed;
    private int attack;
    private int health;
    private int endurance;
    
    // Empty Constructor
    public Bee () {
        
    }
    
    // Constructor in case it should be used.
    public Bee (int hs, int at, int hl, int en) {
        this.harvestSpeed = hs;
        this.attack = at;
        this.health = hl;
        this.endurance = en;
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
}
