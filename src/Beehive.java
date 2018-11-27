import java.awt.Point;
import java.util.ArrayList;

public class Beehive {
    Point loc = new Point();
    ArrayList<Point> area = null;
    
    Bee bee = null;
    Bee abee = null;
    Bee dbee = null;
    
    String name;
    
    int numBees = 6;
    int liveBees = 0;
    
    int numDefenders = 0;
    int numDrones = 0;
    int numWorkers = 0;
    int numAttackers = 0;
    
    // Used to determine if support or warriors should be produced.
    int tnumdrones = 0;
    int tnumworkers = 0;
    int tnumdefenders = 0;
    int tnumattackers = 0;
    
    // Used to alternate drones direction
    int direction = 0;
    
    int endurance;
    int harvestSpeed;
    
    int food = 0;
    
    BeeBuilder build;
    
    /**
     * The home of the bees.
     * @param xloc X-VAL of bees on map.
     * @param yloc Y-VAL of bees on map.
     * @param build The type of bees going in hive.
     */
    public Beehive(int xloc, int yloc, BeeBuilder build) {
        loc.x = xloc;
        loc.y = yloc;
        
        build.buildHarvestSpeed();
        build.buildAttack();
        build.buildHealth();
        build.buildEndurance();
        build.buildAttackDefenseRatio();
        build.buildName();
        
        bee = build.getBee();
        abee = build.getABee();
        dbee = build.getDBee();
        
        bee.setLoc(xloc, yloc);
        abee.setLoc(xloc, yloc);
        dbee.setLoc(xloc, yloc);
        
        this.name = bee.getName();
        this.endurance = bee.getEndurance();
        
        setSupport();
        setWarriors();
    }
    
    /**
     * Get starting support. Workers/Drones.
     */
    public void setSupport() {
        for (int i = 0; i < bee.getHarvestSpeed(); i++) {
            if (numBees > 0) {
                numWorkers++;
                numBees--;
                liveBees++;
                tnumworkers++;
                
                if (numBees > 0) {
                    numDrones++;
                    numBees--;
                    liveBees++;
                    tnumdrones++;
                }
            }
        }
    }
    
    public int getWorkers() {
        return numWorkers;
    }
    
    public int getDrones() {
        return numDrones;
    }
    
    /**
     * Sets starting defenders/warriors if possible.
     */
    public void setWarriors() {
        for (int i = 0; i < numBees; i++) {
            if (numDefenders < bee.getDefenseRatio()) {
                numDefenders++;
            } else {
                numAttackers++;
            }
        }
    }
    
    public int getDefenders() {
        return numDefenders;
    }
    
    public int getAttackers() {
        return numAttackers;
    }
    
    public Point getHome() {
        return loc;
    }
    
    public String getName() {
        return name;
    }

    public String setName(int index) {
        return this.name = name + index;
    }
    
    public void lowerEndurance() {
        endurance--;
    }
    
    public int getEndurance() {
        return endurance;
    }
    
    public void resetEndurance() {
        this.endurance = bee.getEndurance();
    }
    
    public void addFood(int food) {
        this.food += food * 4 * numDrones;
    }
    
    public int getFood() {
        return food;
    }
    
    /**
     * Starts process of consuming food by first feeding live bees.
     */
    public void consumeFood() {
        int tempBees = liveBees;
        for (int i = 0; i < tempBees; i++) {
            if (food > 0) {
                food--;
            } else {
                liveBees--;
            }
        }
        hatchEggs();
    }
    
    /**
     * Remaining food is portioned out to different types of bees.
     */
    public void hatchEggs() {
        if (tnumdrones < bee.getHarvestSpeed() && food != 0) {
            numDrones++;
            tnumdrones++;
            liveBees++;
            food--;
        }
        if (tnumworkers < bee.getHarvestSpeed() && food != 0) {
            numWorkers++; 
            tnumworkers++;
            liveBees++;
            food--;
            if (tnumdrones < bee.getHarvestSpeed() && food != 0) {
                hatchEggs();
            }
        } 
        if (tnumdefenders < bee.getDefenseRatio() && food != 0) {
            numDefenders++;
            tnumdefenders++;
            liveBees++;
            food--;
        } 
        if (tnumattackers < (6 - bee.getDefenseRatio()) && food != 0) {
            numAttackers++;
            tnumattackers++;
            liveBees++;
            food--;
            if (food != 0) {
                hatchEggs();
            }
        } else {
            if (food != 0) {
                // Warrior requirements have been met, produce support.
                tnumdrones = 0;
                tnumworkers = 0;
                tnumdefenders = 0;
                tnumattackers = 0;
                hatchEggs();
            }
        }
        
    }
    
    public int getNumBees() {
        return liveBees;
    }
    
    public void newDir() {
        direction++;
    }
    
    public int getDir() {
        return (direction % 8);
    }
}
