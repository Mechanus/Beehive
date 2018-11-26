
public class Application {

    public static void main(String[] args) {
        Apiary sim = new Apiary();
        
        sim.createMap(20, 20);
        
        sim.addBeehive(4, 17, new KillerBee());
        sim.addBeehive(17, 4, new SweatBee());

        sim.printMap();
    }

}
