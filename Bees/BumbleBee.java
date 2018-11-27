// https://en.wikipedia.org/wiki/Bumblebee#Foraging_behaviour
// https://en.wikipedia.org/wiki/Bumblebee#Cuckoo_bumblebees

public class BumbleBee extends BeeBuilder {
    @Override
    // Don't spread well
    public void buildHarvestSpeed() {
        bee.setHarvestSpeed(3);
        aBee.setHarvestSpeed(3);
        dBee.setHarvestSpeed(3);
    }

    @Override
    // Aggressive and purposefully invades bee colonies
    public void buildAttack() {
        bee.setAttack(5);
        aBee.setAttack(5);
        dBee.setAttack(5);
    }

    @Override
    // Much bigger than other bees
    public void buildHealth() {
        bee.setHealth(20);
        aBee.setHealth(20); 
        dBee.setHealth(20);
    }

    @Override
    // 1~2km of foraging. Compared to .4km of Killer Bee...
    public void buildEndurance() {
        bee.setEndurance(5);
        aBee.setEndurance(5);
        dBee.setEndurance(5);
    }

    @Override
    // Arbitrarily chosen for balance.
    public void buildAttackDefenseRatio() {
        bee.setDefenseRatio(2);
        aBee.setDefenseRatio(2);
        dBee.setDefenseRatio(2);
    }

    @Override
    public void buildName() {
        bee.setName("BumbleBee");
        aBee.setName("BumbleBee");
        dBee.setName("BumbleBee");
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
