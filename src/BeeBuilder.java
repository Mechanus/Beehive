/*
 *  All bees will be built according to characteristics presented
 *  by wikipedia.
 */

// TODO determine how queen should be handled.

public abstract class BeeBuilder {
    protected Bee bee = new Bee();
    protected Bee aBee = new Bee();
    protected Bee dBee = new Bee();
    
    /*
     *  Ratio of workers to warriors
     *  Scale is 1:5.
     */
    public abstract void buildHarvestSpeed();
    
    /*
     *  Damage which a Warrior bee can cause.
     *  The range of damage will be from 1~5 with 5
     *  being the most damage.
     *  Attackers come first before defenders.
     */
    public abstract void buildAttack();
    
    /*
     *  How much damage a Warrior can absorb before dying.
     *  The range of health will be from 8~20. 
     */
    public abstract void buildHealth();
    
    /*
     *  Number of ticks before a bee must return.
     *  If in mid-fight, must abandon fight, leaving enemy
     *  bee alive.
     *  The range of endurance is TBD, depends on the layout
     *  of the map.
     */
    public abstract void buildEndurance();
    
    /*
     *  Number of defenders left behind per attacker sent out.
     *  Based on a scale of 1:6. 
     *  // I.E. If 1 is chosen, 1 defender for every 6 
     *  // warriors, with the remainder being attackers
     *  If attacking while endurance runs out, it will die after battle
     */
    public abstract void buildAttackDefenseRatio();
    
    public abstract void buildName();
    // Returns created Bee
    public abstract Bee getBee();
    
    // Returns a separately move-able Attacker bee
    public abstract Bee getABee();
    
    // Returns a separately move-able Drone bee
    public abstract Bee getDBee();
}
