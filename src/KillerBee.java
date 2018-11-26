// https://en.wikipedia.org/wiki/Africanized_bee#Characteristics
/*
 *  High defenders (Defender numbers high)
 *  Aggressive (Attack and attacker numbers high)
 *  Don't spread well (Low harvest speed)
 *  //// These 3 make me thing small harvest, 50/50 attacker/defender ratio
 *  Pursues over long distances (High endurance)
 */
public class KillerBee extends BeeBuilder{

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
        bee.setEndurance(5);
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
