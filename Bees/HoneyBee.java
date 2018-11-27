// The average bee
public class HoneyBee extends BeeBuilder {
    
    @Override
    public void buildHarvestSpeed() {
        bee.setHarvestSpeed(3);
        aBee.setHarvestSpeed(3);
        dBee.setHarvestSpeed(3);
    }

    @Override
    public void buildAttack() {
        bee.setAttack(3);
        aBee.setAttack(3);
        dBee.setAttack(3);
    }

    @Override
    // High defenders
    public void buildHealth() {
        bee.setHealth(10);
        aBee.setHealth(10); 
        dBee.setHealth(10);
    }

    @Override
    // Pursues over long distance
    public void buildEndurance() {
        bee.setEndurance(3);
        aBee.setEndurance(3);
        dBee.setEndurance(3);
    }

    @Override
    // High defender, high attacker, 50/50.
    public void buildAttackDefenseRatio() {
        bee.setDefenseRatio(2);
        aBee.setDefenseRatio(2);
        dBee.setDefenseRatio(2);
    }

    @Override
    public void buildName() {
        bee.setName("HoneyBee");
        aBee.setName("HoneyBee");
        dBee.setName("HoneyBee");
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
