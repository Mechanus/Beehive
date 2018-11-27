// https://en.wikipedia.org/wiki/Halictidae#Ecology
/*
 *  These are our colonizers. They are highly social, live in 
 *  the ground, and have been around for a long time.
 */
public class SweatBee extends BeeBuilder {

    @Override
    // Colonizers
    public void buildHarvestSpeed() {
        bee.setHarvestSpeed(6);
        aBee.setHarvestSpeed(6);
        dBee.setHarvestSpeed(6);
    }

    @Override
    // Social, non-aggressive
    public void buildAttack() {
        bee.setAttack(1);
        aBee.setAttack(1);
        dBee.setAttack(1);
    }

    @Override
    // Been around for a long time, a little more than minimum health
    public void buildHealth() {
        bee.setHealth(9);
        aBee.setHealth(9); 
        dBee.setHealth(9);
    }

    @Override
    // Foragers, but not attackers
    public void buildEndurance() {
        bee.setEndurance(2);
        aBee.setEndurance(2);
        dBee.setEndurance(2);
    }

    @Override
    // High defender, low attacker
    public void buildAttackDefenseRatio() {
        bee.setDefenseRatio(4);
        aBee.setDefenseRatio(4);
        dBee.setDefenseRatio(4);
    }

    @Override
    public void buildName() {
        bee.setName("SweatBee");
        aBee.setName("SweatBee");
        dBee.setName("SweatBee");
    }
    
    @Override
    public Bee getBee() {
        return bee;
    }

    @Override
    public Bee getABee() {
        return aBee;
    }

    @Override
    public Bee getDBee() {
        return dBee;
    }
}
