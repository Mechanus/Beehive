import java.awt.Point;
import java.util.ArrayList;

public class Apiary {
    private static Apiary single_instance = null;
    private static String[][][] aMap;
    private static appEngine game = new appEngine();
    private static final String FOOD = " FD"; 
    private static final String EMPTY = " - ";
    
    int x, y;
    
    public ArrayList<Beehive> beeHives = new ArrayList<>();
    
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
    public void createMap (int x, int y) {
        aMap = new String[x][y][2];
        this.x = x;
        this.y = y;
        
        /*
         * The 0's are the printable lines.
         * The 1's are the memories
         * // Allows attacker movement to remember what was there.
         */
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if( i % 2 == 0 && j % 2 == 0) {
                    aMap[i][j][1] = FOOD;
                    aMap[i][j][0] = FOOD;
                } else {
                    aMap[i][j][1] = EMPTY;
                    aMap[i][j][0] = EMPTY;
                }
            }
        }
    }
    
    public void printMap() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(aMap[i][j][0]);
            }
            System.out.println();
        }
    }
    
    public void addBeehive(int x, int y, BeeBuilder build) {
        boolean check = checkMap(x,y);
        
        if(check) {
            beeHives.add(new Beehive(x, y, build));
            beeHives.get(beeHives.size() - 1).setName(beeHives.size());
            aMap[x][y][0] = " H"+beeHives.size();
            aMap[x][y][1] = " H"+beeHives.size();
        } else {
            System.out.println("That spot is already taken");
        }
    }
    
    public void getStats(int index) {
        System.out.println(beeHives.get(index).getName() 
                + " Stats:");
        System.out.println("Workers: " + 
                beeHives.get(index).getWorkers());
        System.out.println("Drones: " + 
                beeHives.get(index).getWorkers());
        System.out.println("Defenders: " + 
                beeHives.get(index).getDefenders());
        System.out.println("Attackers: " + 
                beeHives.get(index).getAttackers());
        System.out.println();
    }
    
    /*
     * Will be tied into ticks later on.
     * Moves the various bees according to ticks
     */
    public void beeMove() {
        for (int i = 0; i < beeHives.size(); i++) {
            Beehive currHive = beeHives.get(i);
            
            if(currHive.getEndurance() > 0) {
                currHive.lowerEndurance();
                
                droneMove(currHive, currHive.dBee, i);
                if (currHive.getAttackers() > 0) {
                    Point enemyHive = attackerChoice(currHive.getName(), 
                            currHive.aBee.getLoc(), i);                 
                    attackerMove(i, currHive.aBee, enemyHive);
                }
            } else {
                /*
                 * Reset endurance
                 * Return bees to home (teleportation right now)
                 * Sets memory to empty as well.
                 */
                currHive.resetEndurance();
                currHive.newDir();
                currHive.dBee.setMoveable(true);
                currHive.aBee.setMoveable(true);
                
                // Reset Drones
                aMap[currHive.dBee.getLoc().x][currHive.dBee.getLoc().y][0]
                        = EMPTY;
                aMap[currHive.dBee.getLoc().x][currHive.dBee.getLoc().y][1]
                        = EMPTY;
                currHive.dBee.setLoc(currHive.loc.x, currHive.loc.y);
                
                // Reset Attackers
                aMap[currHive.aBee.getLoc().x]
                        [currHive.aBee.getLoc().y][0]
                        = aMap[currHive.aBee.getLoc().x]
                                [currHive.aBee.getLoc().y][1];
                System.out.println(currHive.getName()+" has gained "
                        + currHive.getFood() + " food! They have to feed " +
                        currHive.getNumBees() + " bees!");
                
                currHive.consumeFood();
            }
        }
    }
    
    /*
     * Locates closest hive.
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
            System.exit(0);
        }
        return chLoc;
    }
    
    /*
     * Moves Bees towards closest hive
     */
    public void attackerMove(int index, Bee aBee, Point eHive) {
        Point aBeeLoc = aBee.getLoc();
        
        int xVal = aBeeLoc.x;
        int yVal = aBeeLoc.y;
        
        if(aBeeLoc.x < eHive.x) {
            xVal++;
        } else if (aBeeLoc.x > eHive.x) {
            xVal--;
        }
        
        if(aBeeLoc.y < eHive.y) {
            yVal++;
        } else if (aBeeLoc.y > eHive.y) {
            yVal--;
        }
        // Replaces tile bee was on.
        if(aMap[aBeeLoc.x][aBeeLoc.y][0].charAt(1) != 'H') {
            
            aMap[aBeeLoc.x][aBeeLoc.y][0] 
                    = aMap[aBeeLoc.x][aBeeLoc.y][1];
        }
        aBee.setLoc(xVal, yVal);
        aMap[xVal][yVal][0] = " B"+ (index + 1);
    }
    /*
     *  Initial sending of drones
     *  Each Hive sends out all of it's drones in a simple pattern
     *  TODO Check if rested.
     *  TODO Check if it hits wall.
     *  TODO If it hits enemy warrior, it dies.
     */
    public void droneMove(Beehive cHive, Bee dBee, int index) {
        int[][] directions = new int[][]
                {{0, 1}, {1, 1}, {1, 0}, {1, -1}, 
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};  
        /*
         * If bee hasn't already found food. Move.
         * Movement is a simple path.
         */
        if(dBee.isMoveable()) {
            
            int orgX = dBee.getLoc().x;
            int orgY = dBee.getLoc().y;
            
            int xVal = orgX + directions[cHive.getDir()][0];
            int yVal = orgY + directions[cHive.getDir()][1];
            
            if(forageFood(dBee, xVal, yVal)) {
                cHive.addFood(1);
            }
            
            /*
             * Replaces empty trails.
             */
            if(aMap[orgX][orgY][0]
                    .charAt(1) != 'H') {
                aMap[orgX][orgY][0] = EMPTY;
            }
            aMap[xVal][yVal][0] = " D" + (index + 1);
            
            dBee.setLoc(xVal, yVal);
        }
    }
    
    /*
     * Determine if tile is food.
     * If food, the set drone to immovable
     * Refresh imobile when endurance is up.
     */
    private boolean forageFood(Bee dBee, int xVal, int yVal) {
        if(aMap[xVal][yVal][0].equals(FOOD)) {
            dBee.setMoveable(false);
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
        
        if(cell.charAt(0) == 'H') {
            return false;
        }
        return true;
    }
    
    public void getResults() {
        for (int i = 0; i < beeHives.size(); i++) {
            Beehive currHive = beeHives.get(i);
            
            System.out.println(currHive.getName()+" now has " + 
                    currHive.getDrones() + " drones,"
                            + currHive.getWorkers() + " workers, "
                            + currHive.getDefenders() + " defenders, and "
                            + currHive.getAttackers() + " attackers!");
        }
    }
}
