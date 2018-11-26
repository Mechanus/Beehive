// The average bee
public class HoneyBee extends BeeBuilder {
    
    @Override
    public void buildHarvestSpeed() {
        bee.setHarvestSpeed(3);
    }

    @Override
    public void buildAttack() {
        bee.setAttack(3);
    }

    @Override
    // High defenders
    public void buildHealth() {
        bee.setHealth(10);
        
    }

    @Override
    // Pursues over long distance
    public void buildEndurance() {
        bee.setEndurance(3);
    }

    @Override
    // High defender, high attacker, 50/50.
    public void buildAttackDefenseRatio() {
        bee.setDefenseRatio(2);
    }

    @Override
    public Bee getBee() {
        return bee;
    }
}
