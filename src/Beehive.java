import java.awt.Point;
import java.util.ArrayList;

public class Beehive {
    Point loc = new Point();
    ArrayList<Point> area = null;
    
    Bee bee = null;
    String name;
    
    int numBees = 6;
    
    int food = 0;
    
    ArrayList<Bee> workers = new ArrayList<>();
    
    ArrayList<Bee> drones = new ArrayList<>();

    ArrayList<Bee> defenders = new ArrayList<>();
    
    ArrayList<Bee> attackers = new ArrayList<>();
    
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
        
        this.name = bee.getName();
        setSupport();
        setWarriors();
    }
    
    public void setSupport() {
        for (int i = 0; i < bee.getHarvestSpeed(); i++) {
            if (numBees > 0) {
                workers.add(bee);
                numBees--;
                
                if(numBees > 0) {
                    drones.add(bee);
                    numBees--;
                }
            }
        }
    }
    
    public int getWorkers() {
        return workers.size();
    }
    
    public int getDrones() {
        return drones.size();
    }
    
    public void setWarriors() {
        for(int i = 0; i < numBees; i++) {
            if(defenders.size() < bee.getDefenseRatio()) {
                defenders.add(bee);
                defenders.get(0).setMoveable(false);
            } else {
                attackers.add(bee);
            }
        }
    }
    public int getDefenders() {
        return defenders.size();
    }
    
    public int getAttackers() {
        return attackers.size();
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
