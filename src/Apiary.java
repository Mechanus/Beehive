import java.awt.Point;
import java.util.ArrayList;

public class Apiary {
    private static Apiary single_instance = null;
    private static String[][] aMap;
    private static appEngine game = new appEngine();
    
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
                    aMap[i][j] = " FD";
                } else {
                    aMap[i][j] = " - ";
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
            Point loc = beeHives.get(i).getHome();
            //aMap[loc.x][loc.y - 1] = " B" + (i+1);
            
            droneMove(i, loc);
            
            Point enemyHive = attackerChoice(i, loc);
            attackerMove(i, loc, enemyHive);
            if (enemyHive == loc) {
                System.out.println(beeHives.get(i).getName() + " wins!");
                System.exit(1);
            }
        }
    }
    
    /*
     * Locates closest hive.
     */
    public Point attackerChoice(int index, Point loc) {
        double closestHive = 200000000;
        int tIndex = 0;
        Point chLoc = loc;
        
        Point hive = beeHives.get(index).getHome();
        
        for (int i = 0; i < beeHives.size(); i++) {
            if (i != index) {
                Point checkHive = beeHives.get(i).getHome();
                
                double chDistance = game.checkDistance(
                        hive, checkHive);
                
                if (chDistance < closestHive) {
                    closestHive = chDistance;
                    chLoc = checkHive;
                    tIndex = i;
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
    public void droneMove(int index, Point loc) {
            
        int[][] directions = new int[][]
                {{0, 1}, {1, 1}, {1, 0}, {1, -1}, 
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
            
        int food = 0;
        
        for (int i = 0; i < beeHives.get(index).getDrones(); i++) {
            
            food += forageFood(index, loc.x + directions[i][0], 
                    loc.y + directions[i][1], i);
            
            aMap[loc.x + directions[i][0]]
                    [loc.y + directions[i][1]] = " D" + (index + 1);
        }
        System.out.println(beeHives.get(index).getName() + "found " +
        food + " sources of food!");
    }
    
    private int forageFood(int index, int xVal, int yVal, int droneIndex) {
        if(aMap[xVal][yVal] == "FD") {
            beeHives.get(index).drones.get(droneIndex).setMoveable(false);
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
