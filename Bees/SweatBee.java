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
        abee.setHarvestSpeed(6);
        dbee.setHarvestSpeed(6);
    }

    @Override
    // Social, non-aggressive
    public void buildAttack() {
        bee.setAttack(1);
        abee.setAttack(1);
        dbee.setAttack(1);
    }

    @Override
    // Been around for a long time, a little more than minimum health
    public void buildHealth() {
        bee.setHealth(9);
        abee.setHealth(9); 
        dbee.setHealth(9);
    }

    @Override
    // Foragers, but not attackers
    public void buildEndurance() {
        bee.setEndurance(2);
        abee.setEndurance(2);
        dbee.setEndurance(2);
    }

    @Override
    // High defender, low attacker
    public void buildAttackDefenseRatio() {
        bee.setDefenseRatio(4);
        abee.setDefenseRatio(4);
        dbee.setDefenseRatio(4);
    }

    @Override
    public void buildName() {
        bee.setName("SweatBee");
        abee.setName("SweatBee");
        dbee.setName("SweatBee");
    }
    
    @Override
    public Bee getBee() {
        return bee;
    }

    @Override
    public Bee getABee() {
        return abee;
    }

    @Override
    public Bee getDBee() {
        return dbee;
    }
}
