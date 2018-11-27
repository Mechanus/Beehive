import java.awt.Point;

public class AppEngine {
    
    /**
     * Used to determine which is the closest hive.
     * @param hive Current hive.
     * @param checkHive Hive to check.
     * @return distance Which will be checked to see if is the biggest.
     */
    public double checkDistance(Point hive, Point checkHive) {
        double distance = 0;
        
        int deltaX = hive.x - checkHive.x;
        int deltaY = hive.y - checkHive.y;
        
        distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        
        return distance;
    }
}
