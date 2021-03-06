
public class Application {

    /**
     * User interaction.
     * @param args Commands passed from command line.
     */
    public static void main(String[] args) {
        Apiary sim = new Apiary();
        
        sim.createMap(20, 20);
        
        sim.addBeehive(4, 16, new KillerBee());
        //sim.addBeehive(16, 4, new SweatBee());
        //sim.addBeehive(6, 2, new KillerBee());
        //sim.addBeehive(16, 16, new BumbleBee());

        sim.beeMove();
        sim.beeMove();
        sim.beeMove();
        
        sim.beeMove();
        sim.beeMove();
        sim.beeMove();
        
        sim.beeMove();
        sim.beeMove();
        sim.beeMove();
        
        sim.beeMove();
        sim.beeMove();
        sim.beeMove();
        
        sim.beeMove();
        sim.beeMove();
        sim.beeMove();
        
        sim.beeMove();
        sim.beeMove();
        sim.beeMove();
        
        sim.beeMove();
        sim.beeMove();
        sim.beeMove();
        
        sim.beeMove();
        sim.beeMove();
        sim.beeMove();
        
        sim.beeMove();
        sim.beeMove();
        sim.beeMove();
        
        sim.beeMove();
        sim.beeMove();
        sim.beeMove();
        
        sim.printMap();
        sim.getResults();
        /*
        sim.getStats(0);
        sim.getStats(1);
        sim.getStats(2);
        */
    }
}
