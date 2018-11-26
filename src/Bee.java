
public class Bee {
    private int harvestSpeed;
    private int attack;
    private int health;
    private int endurance;
    private int defenseRatio;
    private String name;
    
    // Empty Constructor
    public Bee () {
        
    }
    
    // Constructor in case it should be used.
    public Bee (int hs, int at, int hl, int en, int df) {
        this.harvestSpeed = hs;
        this.attack = at;
        this.health = hl;
        this.endurance = en;
        this.defenseRatio = df;
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
}
