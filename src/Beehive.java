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
    int numDefenders = 0;
    int numDrones = 0;
    int numWorkers = 0;
    int numAttackers = 0;
    
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
        setSupport();
        setWarriors();
    }
    
    public void setSupport() {
        for (int i = 0; i < bee.getHarvestSpeed(); i++) {
            if (numBees > 0) {
                numWorkers++;
                numBees--;
                
                if(numBees > 0) {
                    numDrones++;
                    numBees--;
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
}
