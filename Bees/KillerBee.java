// https://en.wikipedia.org/wiki/Africanized_bee#Characteristics
/*
 *  These are the fighters. They don't colonize well, but they sure do like to attack.
 */
public class KillerBee extends BeeBuilder {

    @Override
    // Don't spread well
    public void buildHarvestSpeed() {
        bee.setHarvestSpeed(2);
    }

    @Override
    // Aggressive but reserving highest attack
    public void buildAttack() {
        bee.setAttack(4);
    }

    @Override
    // High defenders
    public void buildHealth() {
        bee.setHealth(12);
        
    }

    @Override
    // Pursues over long distance
    public void buildEndurance() {
        bee.setEndurance(3);
    }

    @Override
    // High defender, high attacker, 50/50.
    public void buildAttackDefenseRatio() {
        bee.setDefenseRatio(3);
    }

    @Override
    public void buildName() {
        bee.setName("KillerBee");
    }
    
    @Override
    public Bee getBee() {
        return bee;
    }
}
