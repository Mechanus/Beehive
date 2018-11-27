import java.awt.Point;
import java.util.ArrayList;

public class Apiary {
    private static Apiary single_instance = null;
    private static String[][] aMap;
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
        aMap = new String[x][y];
        this.x = x;
        this.y = y;
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if( i % 2 == 0 && j % 2 == 0) {
                    aMap[i][j] = FOOD;
                } else {
                    aMap[i][j] = EMPTY;
                }
            }
        }
    }
    
    public void printMap() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(aMap[i][j]);
            }
            System.out.println();
        }
    }
    
    public void addBeehive(int x, int y, BeeBuilder build) {
        boolean check = checkMap(x,y);
        
        if(check) {
            beeHives.add(new Beehive(x, y, build));
            beeHives.get(beeHives.size() - 1).setName(beeHives.size());
            aMap[x][y] = " H"+beeHives.size();
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
     * TODO doesn't remember drones locations yet.
     */
    public void beeMove() {
        for (int i = 0; i < beeHives.size(); i++) {
            Beehive currHive = beeHives.get(i);
            Point loc = currHive.getHome();
            //aMap[loc.x][loc.y - 1] = " B" + (i+1);
            
            droneMove(currHive, i);
            
            Point enemyHive = attackerChoice(currHive, loc, i);
            attackerMove(i, loc, enemyHive);
            if (enemyHive == loc) {
                System.out.println(currHive.getName() + " wins!");
                System.exit(1);
            }
        }
    }
    
    /*
     * Locates closest hive.
     */
    public Point attackerChoice(Beehive cHive, Point loc, int index) {
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
        return chLoc;
    }
    
    /*
     * Moves Bees towards closest hive
     */
    public void attackerMove(int index, Point hive, Point eHive) {
        int xVal = hive.x;
        int yVal = hive.y;
        
        if(hive.x < eHive.x) {
            xVal++;
        } else if (hive.x > eHive.x) {
            xVal--;
        }
        
        if(hive.y < eHive.y) {
            yVal++;
        } else if (hive.y > eHive.y) {
            yVal--;
        }
        
        aMap[xVal][yVal] = " B"+ (index + 1);
    }
    /*
     *  Initial sending of drones
     *  Each Hive sends out all of it's drones in a simple pattern
     *  TODO track their movements.
     *  TODO Check if rested.
     *  TODO Check if it hits wall.
     *  TODO If it hits enemy warrior, it dies.
     */
    public void droneMove(Beehive cHive, int index) {
        
        int[][] directions = new int[][]
                {{0, 1}, {1, 1}, {1, 0}, {1, -1}, 
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};  

        int food = 0;
        /*
         * If bee hasn't already found food. Move.
         * Movement is a simple path.
         */
        /*for (int i = 0; i < cHive.getDrones(); i++) {
            if(cHive.dBee.isMoveable()) {   
                int xVal = cHive.dBee.getLoc().x
                        + directions[i][0];
                int yVal = cHive.dBee.getLoc().y
                        + directions[i][1];
                food += forageFood(cHive, xVal, yVal, i);
                
                aMap[xVal][yVal] = " D" + (index + 1);
                
                //cHive.drones.get(i).setLoc(xVal, yVal);
            }*/
        if(cHive.dBee.isMoveable()) {   
            int xVal = cHive.dBee.getLoc().x
                    + directions[0][0];
            int yVal = cHive.dBee.getLoc().y
                    + directions[0][1];
            food += forageFood(cHive, xVal, yVal);
            
            aMap[xVal][yVal] = " D" + (index + 1);
            
            cHive.dBee.setLoc(xVal, yVal);
        }
        System.out.println(cHive.getName() + " found " +
        food + " sources of food!");
    }
    
    private int forageFood(Beehive cHive, int xVal, int yVal) {
        if(aMap[xVal][yVal].equals(FOOD)) {
            cHive.dBee.setMoveable(false);
            return 1;
        }
        return 0;
    }
    
    private boolean checkMap(int newX, int newY) {
        String cell = aMap[newX][newY];
        
        if(cell.charAt(0) == 'H') {
            return false;
        }
        return true;
    }
}
