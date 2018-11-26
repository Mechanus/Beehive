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
                aMap[i][j] = "_*";
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
        beeHives.add(new Beehive(x, y, build));        
        aMap[x][y] = "H"+beeHives.size();
    }
    
    public class Cell {
        int x, y;
        ArrayList<Cell> neighbors = new ArrayList<>();
        
        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public void addHiveCell(Cell other) {
            if (!this.neighbors.contains(other)) {
                this.neighbors.add(other);
            }
        }
    }
}
