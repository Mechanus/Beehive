// https://en.wikipedia.org/wiki/Bumblebee#Foraging_behaviour
// https://en.wikipedia.org/wiki/Bumblebee#Cuckoo_bumblebees

public class BumbleBee extends BeeBuilder {
    @Override
    // Don't spread well
    public void buildHarvestSpeed() {
        bee.setHarvestSpeed(3);
        abee.setHarvestSpeed(3);
        dbee.setHarvestSpeed(3);
    }

    @Override
    // Aggressive and purposefully invades bee colonies
    public void buildAttack() {
        bee.setAttack(5);
        abee.setAttack(5);
        dbee.setAttack(5);
    }

    @Override
    // Much bigger than other bees
    public void buildHealth() {
        bee.setHealth(20);
        abee.setHealth(20); 
        dbee.setHealth(20);
    }

    @Override
    // 1~2km of foraging. Compared to .4km of Killer Bee...
    public void buildEndurance() {
        bee.setEndurance(5);
        abee.setEndurance(5);
        dbee.setEndurance(5);
    }

    @Override
    // Arbitrarily chosen for balance.
    public void buildAttackDefenseRatio() {
        bee.setDefenseRatio(2);
        abee.setDefenseRatio(2);
        dbee.setDefenseRatio(2);
    }

    @Override
    public void buildName() {
        bee.setName("BumbleBee");
        abee.setName("BumbleBee");
        dbee.setName("BumbleBee");
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
