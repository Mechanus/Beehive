import java.awt.Point;
import java.util.ArrayList;

public class Apiary {
    private static Apiary single_instance = null;
    private static String[][] aMap;
    
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
    
    public void beeMove() {
        for (int i = 0; i < beeHives.size(); i++) {
            Point loc = beeHives.get(i).getHome();
            aMap[loc.x][loc.y - 1] = " B" + (i+1);
            
            //warriorMove(i, loc);
            droneMove(i, loc);
        }
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
        for (int i = 0; i < beeHives.get(index).getDrones(); i++) {
            aMap[loc.x + directions[i][0]]
                    [loc.y + directions[i][1]] = " D" + (index + 1);
        }
    }
    
    private boolean checkMap(int newX, int newY) {
        String cell = aMap[newX][newY];
        
        if(cell.charAt(0) == 'H') {
            return false;
        }
        return true;
    }
}
