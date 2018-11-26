import java.awt.Point;
import java.util.ArrayList;

public class Beehive {
    Point loc = new Point();
    ArrayList<Point> area = null;
    
    Bee bee = null;
    
    // Places this name on map.
    String name;
    
    /*
     * Starting bees.
     * Defender/Attacker divided according to ratio.
     * Queen is same amongst all bees, taken down when
     * last defender is defeated.
     */
    int numBees = 6;
    
    /*
     * Food is a staple used in many places.
     * Food is consumed 1:1.
     * Drones eat food first.
     * // This should cause a focus on growth first.
     * Workers then get the next available food.
     * Defenders eat available food next.
     * Attackers then eat available food.
     * Remaining goes to Queen, which is 1:1 for eggs.
     */
    int food = 0;
    
    /*
     * Drone gathers food.
     * 
     * They move towards a random location until they 
     * run out of endurance.
     * 
     * When drone returns, it must consume 1 food to forage again.
     * 
     * Drone production is determined by harvestSpeed
     * // X drone is produced before next type of bee roles are.
     */
    ArrayList<Bee> drone = null;
    
    /*
     * Workers build cells.
     * 
     * Each beehive gets as many workers as drones.
     * 
     * They require 1 food per 5 ticks else they won't build.
     */
    
    /*
     * Defenders defend the Queen.
     * When all defenders are beaten, the queen is captured and killed.
     * 
     * After defenders defend, each surviving member must eat 1 food
     * in order to defend again.
     * 
     * Defender production is determined by Atk/Def ratio.
     * // Defenders are produced until the ratio is minimally met,
     * // Then the next role is created. 
     */
    ArrayList<Bee> defender = null;
    
    /*
     * Attackers attack other beehives.
     * 
     * If their fight with a bee does not finish when their endurance
     * runs out, they will die fighting.
     * 
     * They fly towards the closest beehive. If they cannot make it,
     * they return. 
     * 
     * If they make it to a beehive but determine they don't have enough
     * endurance to kill an enemy defender and then return, they just return
     * 
     * After a return flight, they must consume 1 food in order to attack
     * again.
     * 
     * Attacker production is determined by Atk/Def ratio.
     * // Attackers are produced until the ratio is minimally met
     * // then the next role is created.
     */
    ArrayList<Bee> attacker = null;
    
    /*
     * Production of bee roles is determined by a combination of 
     * harvestSpeed and Atk/Defense ratio.
     * 
     * I.E. *** harvestSpeed = 1.
     * This means that 1 drone will be made, before the next
     * 6 Atk/Def.
     * 
     * *** harvestSpeed = 3.
     * 3 drones will be made, before the next 6 Atk/Def.
     */
    
    public Beehive (int xLoc, int yLoc, BeeBuilder build){
        loc.x = xLoc;
        loc.y = yLoc;
        
        build.buildHarvestSpeed();
        build.buildAttack();
        build.buildHealth();
        build.buildEndurance();
        build.buildAttackDefenseRatio();
        
        bee = build.getBee();
    }
    
    public void addCell() {
        
    }
}
