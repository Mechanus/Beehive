
public abstract class BeeBuilder {
    protected Bee bee = new Bee();
    
    // Speed at which workers harvest food.
    public abstract void buildHarvestSpeed();
    
    // Damage which a Warrior bee can cause.
    public abstract void buildAttack();
    
    // How much damage a Warrior can absorb before dying.
    public abstract void buildHealth();
    
    /*
     *  Number of ticks before a bee must return.
     *  If in mid-fight, must abandon fight, leaving enemy
     *  bee alive.
     */
    public abstract void buildEndurance();
}
