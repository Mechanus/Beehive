import java.awt.Point;

public class appEngine {
    public double checkDistance(Point hive, Point checkHive) {
        double distance = 0;
        
        int deltaX = hive.x - checkHive.x;
        int deltaY = hive.y - checkHive.y;
        
        distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        
        return distance;
    }
}
