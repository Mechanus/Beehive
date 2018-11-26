
public class Application {

    public static void main(String[] args) {
        Apiary sim = new Apiary();
        
        sim.createMap(20, 20);
        
        sim.addBeehive(4, 16, new KillerBee());
        sim.addBeehive(17, 4, new SweatBee());
        sim.addBeehive(5, 3, new KillerBee());

        sim.beeMove();
        sim.printMap();
        /*
        sim.getStats(0);
        sim.getStats(1);
        sim.getStats(2);
        */
    }
}
