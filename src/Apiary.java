import java.awt.Point;
import java.util.ArrayList;

public class Apiary {
    private static Apiary single_instance = null;
    private static String[][][] aMap;
    private static AppEngine game = new AppEngine();
    private static final String FOOD = " FD"; 
    private static final String EMPTY = " - ";
    
    int xmap = 0;
    int ymap = 0;
    
    public ArrayList<Beehive> beeHives = new ArrayList<>();
    
    /**
     * Method: getInstance().
     * @return Apiary class
     */
    public Apiary getInstance() {
        if (single_instance == null) {
            single_instance = new Apiary();
            
            return single_instance;
        } else {
            return null;
        }
    }
    
    /*
     * Creates a map.
     * Populates map with two characters, spaces.
     * Two characters are chosen so that bees can be B1 (Bee)(HiveNum)
     * Hive will be H1 (Hive)(HiveNum)
     * Drones will be D1
     */
    /**
     * Method: createMap
     * Creates a map for the apiary.
     * @param x X-AXIS
     * @param y Y-AXIS
     */
    public void createMap(int x, int y) {
        aMap = new String[x][y][2];
        this.xmap = x;
        this.ymap = y;
        
        /*
         * The 0's are the printable lines.
         * The 1's are the memories
         * // Allows attacker movement to remember what was there.
         */
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    aMap[i][j][1] = FOOD;
                    aMap[i][j][0] = FOOD;
                } else {
                    aMap[i][j][1] = EMPTY;
                    aMap[i][j][0] = EMPTY;
                }
            }
        }
    }
    /**
     * Method: printMap
     * 
     * <p>Description: Prints visual of snapshot of current happenings.
     */
    
    public void printMap() {
        for (int i = 0; i < xmap; i++) {
            for (int j = 0; j < ymap; j++) {
                System.out.print(aMap[i][j][0]);
            }
            System.out.println();
        }
    }
    /**
     * Adds beehive to Apiary if it passes check.
     * @param x X-VAL
     * @param y Y-VAL
     * @param build The type of bee to go in beehive.
     */
    
    public void addBeehive(int x, int y, BeeBuilder build) {
        boolean check = checkMap(x,y);
        
        if (check) {
            beeHives.add(new Beehive(x, y, build));
            beeHives.get(beeHives.size() - 1).setName(beeHives.size());
            aMap[x][y][0] = " H" + beeHives.size();
            aMap[x][y][1] = " H" + beeHives.size();
        } else {
            System.out.println("That spot is already taken");
        }
    }
    
    /**
     * Can get stats of bees in specified index.
     * @param index Value depends on creation in Application.
     */
    public void getStats(int index) {
        System.out.println(beeHives.get(index).getName() 
                + " Stats:");
        System.out.println("Workers: " 
                + beeHives.get(index).getWorkers());
        System.out.println("Drones: " 
                + beeHives.get(index).getWorkers());
        System.out.println("Defenders: " 
                + beeHives.get(index).getDefenders());
        System.out.println("Attackers: " 
                + beeHives.get(index).getAttackers());
        System.out.println();
    }
    
    /*
     * Will be tied into ticks later on.
     * Moves the various bees according to ticks
     */
    /**
     * Moves both drone and attackers.
     */
    public void beeMove() {
        for (int i = 0; i < beeHives.size(); i++) {
            Beehive currHive = beeHives.get(i);
            
            if (currHive.getEndurance() > 0) {
                currHive.lowerEndurance();
                
                droneMove(currHive, currHive.dbee, i);
                if (currHive.getAttackers() > 0) {
                    Point enemyHive = attackerChoice(currHive.getName(), 
                            currHive.abee.getLoc(), i);                 
                    attackerMove(i, currHive.abee, enemyHive);
                }
            } else {
                /*
                 * Reset endurance
                 * Return bees to home (teleportation right now)
                 * Sets memory to empty as well.
                 */
                currHive.resetEndurance();
                currHive.newDir();
                currHive.dbee.setMoveable(true);
                currHive.abee.setMoveable(true);
                
                // Reset Drones
                aMap[currHive.dbee.getLoc().x][currHive.dbee.getLoc().y][0]
                        = EMPTY;
                aMap[currHive.dbee.getLoc().x][currHive.dbee.getLoc().y][1]
                        = EMPTY;
                currHive.dbee.setLoc(currHive.loc.x, currHive.loc.y);
                
                // Reset Attackers
                aMap[currHive.abee.getLoc().x]
                        [currHive.abee.getLoc().y][0]
                        = aMap[currHive.abee.getLoc().x]
                                [currHive.abee.getLoc().y][1];
                System.out.println(currHive.getName() + " has gained "
                        + currHive.getFood() + " food! They have to feed " 
                        + currHive.getNumBees() + " bees!");
                
                currHive.consumeFood();
            }
        }
    }
    
    /*
     * Locates closest hive.
     */
    /**
     * Attacker chooses closest hive to attack.
     * @param name Sent in for when winner is chosen
     * @param loc Location of original beehive. Used to compare winner
     * @param index Ensures hive doesn't choose self
     * @return chLoc Bees chosen location
     */
    public Point attackerChoice(String name, Point loc, int index) {
        double closestHive = 200000000;
        Point chLoc = loc;
        
        for (int i = 0; i < beeHives.size(); i++) {
            if (i != index) {
                Point checkHive = beeHives.get(i).getHome();
                
                double chDistance = game.checkDistance(
                        loc, checkHive);
                
                if (chDistance < closestHive) {
                    closestHive = chDistance;
                    chLoc = checkHive;
                }
            }
        }
        if (chLoc == loc) {
            System.out.println(name + " wins!");
            getResults();
            printMap();
            System.exit(0);
        }
        return chLoc;
    }
    
    /*
     * Moves Bees towards closest hive
     */
    /**
     * Moves attacker towards chosen location.
     * @param index Used to help paint map proper hive number
     * @param abee The traveling bee. Helps set location memory
     * @param ehive Enemy hive that bee is moving towards.
     */
    public void attackerMove(int index, Bee abee, Point ehive) {
        Point abeeloc = abee.getLoc();
        
        int xval = abeeloc.x;
        int yval = abeeloc.y;
        
        if (abeeloc.x < ehive.x) {
            xval++;
        } else if (abeeloc.x > ehive.x) {
            xval--;
        }
        
        if (abeeloc.y < ehive.y) {
            yval++;
        } else if (abeeloc.y > ehive.y) {
            yval--;
        }
        // Replaces tile bee was on.
        if (aMap[abeeloc.x][abeeloc.y][0].charAt(1) != 'H') {
            
            aMap[abeeloc.x][abeeloc.y][0] 
                    = aMap[abeeloc.x][abeeloc.y][1];
        }
        abee.setLoc(xval, yval);
        aMap[xval][yval][0] = " B" + (index + 1);
    }
    /*
     *  Initial sending of drones
     *  Each Hive sends out all of it's drones in a simple pattern
     */
    /**
     * Moves drone.
     * @param chive Current Hive. Helps add food.
     * @param dbee Moving drone bee.
     * @param index Helps paint map with proper name.
     */
    
    public void droneMove(Beehive chive, Bee dbee, int index) {
        int[][] directions = new int[][]
            {{0, 1}, {1, 1}, {1, 0}, {1, -1}, 
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};  
        /*
         * If bee hasn't already found food. Move.
         * Movement is a simple path.
         */
        if (dbee.isMoveable()) {
            
            int orgX = dbee.getLoc().x;
            int orgY = dbee.getLoc().y;
            
            int xval = orgX + directions[chive.getDir()][0];
            int yval = orgY + directions[chive.getDir()][1];
            
            if (forageFood(dbee, xval, yval)) {
                chive.addFood(1);
            }
            
            /*
             * Replaces empty trails.
             */
            if (aMap[orgX][orgY][0]
                    .charAt(1) != 'H') {
                aMap[orgX][orgY][0] = EMPTY;
            }
            aMap[xval][yval][0] = " D" + (index + 1);
            
            dbee.setLoc(xval, yval);
        }
    }
    
    /*
     * Determine if tile is food.
     * If food, the set drone to immovable
     * Refresh imobile when endurance is up.
     */
    private boolean forageFood(Bee dbee, int xval, int yval) {
        if (aMap[xval][yval][0].equals(FOOD)) {
            dbee.setMoveable(false);
            return true;
        }
        return false;
    }
    
    /*
     * Determines if a hive cell may be placed in the location
     * Doesn't check wall boundaries.
     */
    private boolean checkMap(int newX, int newY) {
        String cell = aMap[newX][newY][0];
        
        if (cell.charAt(0) == 'H') {
            return false;
        }
        return true;
    }
    
    /**
     * Prints the results of the latest tick of each bee hive.
     */
    public void getResults() {
        for (int i = 0; i < beeHives.size(); i++) {
            Beehive currHive = beeHives.get(i);
            
            System.out.println(currHive.getName() + " now has "
                + currHive.getDrones() + " drones,"
                            + currHive.getWorkers() + " workers, "
                            + currHive.getDefenders() + " defenders, and "
                            + currHive.getAttackers() + " attackers!");
        }
    }
}
