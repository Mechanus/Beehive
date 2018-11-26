// https://en.wikipedia.org/wiki/Halictidae#Ecology
/*
 *  These are our colonizers. They are highly social, live in 
 *  the ground, and have been around for a long time.
 */
public class SweatBee extends BeeBuilder{

    @Override
    // Colonizers
    public void buildHarvestSpeed() {
        bee.setHarvestSpeed(5);
    }

    @Override
    // Social, non-aggressive
    public void buildAttack() {
        bee.setAttack(1);
    }

    @Override
    // Been around for a long time, a little more than minimum health
    public void buildHealth() {
        bee.setHealth(9);
        
    }

    @Override
    // Foragers, but not attackers
    public void buildEndurance() {
        bee.setEndurance(2);
    }

    @Override
    // High defender, low attacker
    public void buildAttackDefenseRatio() {
        bee.setDefenseRatio(5);
    }

    @Override
    public Bee getBee() {
        return bee;
    }
}
