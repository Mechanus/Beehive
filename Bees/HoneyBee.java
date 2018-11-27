// The average bee
public class HoneyBee extends BeeBuilder {
    
    @Override
    public void buildHarvestSpeed() {
        bee.setHarvestSpeed(5);
        abee.setHarvestSpeed(5);
        dbee.setHarvestSpeed(5);
    }

    @Override
    public void buildAttack() {
        bee.setAttack(3);
        abee.setAttack(3);
        dbee.setAttack(3);
    }

    @Override
    // High defenders
    public void buildHealth() {
        bee.setHealth(10);
        abee.setHealth(10); 
        dbee.setHealth(10);
    }

    @Override
    // Pursues over long distance
    public void buildEndurance() {
        bee.setEndurance(3);
        abee.setEndurance(3);
        dbee.setEndurance(3);
    }

    @Override
    // High defender, high attacker, 50/50.
    public void buildAttackDefenseRatio() {
        bee.setDefenseRatio(2);
        abee.setDefenseRatio(2);
        dbee.setDefenseRatio(2);
    }

    @Override
    public void buildName() {
        bee.setName("HoneyBee");
        abee.setName("HoneyBee");
        dbee.setName("HoneyBee");
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
