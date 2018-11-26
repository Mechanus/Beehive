// https://en.wikipedia.org/wiki/Bumblebee#Foraging_behaviour
// https://en.wikipedia.org/wiki/Bumblebee#Cuckoo_bumblebees

public class BumbleBee extends BeeBuilder {
    @Override
    // Don't spread well
    public void buildHarvestSpeed() {
        bee.setHarvestSpeed(1);
    }

    @Override
    // Aggressive and purposefully invades bee colonies
    public void buildAttack() {
        bee.setAttack(5);
    }

    @Override
    // Much bigger than other bees
    public void buildHealth() {
        bee.setHealth(20);
        
    }

    @Override
    // 1~2km of foraging. Compared to .4km of Killer Bee...
    public void buildEndurance() {
        bee.setEndurance(5);
    }

    @Override
    // Arbitrarily chosen for balance.
    public void buildAttackDefenseRatio() {
        bee.setDefenseRatio(2);
    }

    @Override
    public Bee getBee() {
        return bee;
    }
}
