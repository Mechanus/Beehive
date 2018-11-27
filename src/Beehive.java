import java.awt.Point;
import java.util.ArrayList;

public class Beehive {
    Point loc = new Point();
    ArrayList<Point> area = null;
    
    Bee bee = null;
    Bee aBee = null;
    Bee dBee = null;
    
    String name;
    
    int numBees = 6;
    int liveBees = 0;
    
    int numDefenders = 0;
    int numDrones = 0;
    int numWorkers = 0;
    int numAttackers = 0;
    
    // Used to determine if support or warriors should be produced.
    int tNumDrones = 0;
    int tNumWorkers = 0;
    int tNumDefenders = 0;
    int tNumAttackers = 0;
    
    // Used to alternate drones direction
    int direction = 0;
    
    int endurance;
    int harvestSpeed;
    
    int food = 0;
    
    BeeBuilder build;
    
    public Beehive (int xLoc, int yLoc, BeeBuilder build){
        loc.x = xLoc;
        loc.y = yLoc;
        
        build.buildHarvestSpeed();
        build.buildAttack();
        build.buildHealth();
        build.buildEndurance();
        build.buildAttackDefenseRatio();
        build.buildName();
        
        bee = build.getBee();
        aBee = build.getABee();
        dBee = build.getDBee();
        
        bee.setLoc(xLoc, yLoc);
        aBee.setLoc(xLoc, yLoc);
        dBee.setLoc(xLoc, yLoc);
        
        this.name = bee.getName();
        this.endurance = bee.getEndurance();
        
        setSupport();
        setWarriors();
    }
    
    public void setSupport() {
        for (int i = 0; i < bee.getHarvestSpeed(); i++) {
            if (numBees > 0) {
                numWorkers++;
                numBees--;
                liveBees++;
                tNumWorkers++;
                
                if(numBees > 0) {
                    numDrones++;
                    numBees--;
                    liveBees++;
                    tNumDrones++;
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
    
    /*
     * TODO This will only work for the first round of ratio adding,
     * // Needs modification for later on.
     */
    public void setWarriors() {
        for(int i = 0; i < numBees; i++) {
            if(numDefenders < bee.getDefenseRatio()) {
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
        return this.name = name+index;
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
        this.food += food*4*numDrones;
    }
    
    public int getFood() {
        return food;
    }
    
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
    
    public void hatchEggs() {
        if (tNumDrones < bee.getHarvestSpeed() && food !=0) {
            numDrones++;
            tNumDrones++;
            liveBees++;
            food--;
        }
        if (tNumWorkers < bee.getHarvestSpeed() && food !=0) {
            numWorkers++; 
            tNumWorkers++;
            liveBees++;
            food--;
            if (tNumDrones < bee.getHarvestSpeed() && food !=0) {
                hatchEggs();
            }
        } 
        if (tNumDefenders < bee.getDefenseRatio() && food !=0) {
            numDefenders++;
            tNumDefenders++;
            liveBees++;
            food--;
        } 
        if (tNumAttackers < (6 - bee.getDefenseRatio()) && food !=0) {
            numAttackers++;
            tNumAttackers++;
            liveBees++;
            food--;
                if (food != 0) {
                    hatchEggs();
                }
        } else {
            if (food != 0) {
                // Warrior requirements have been met, produce support.
                tNumDrones = 0;
                tNumWorkers = 0;
                tNumDefenders = 0;
                tNumAttackers = 0;
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
