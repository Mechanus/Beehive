import java.awt.Point;
import java.util.ArrayList;

public class Beehive {
    Point loc = null;
    ArrayList<Point> area = null;
    
    public Beehive (int xLoc, int yLoc){
        loc.x = xLoc;
        loc.y = yLoc;
        area.add(loc);
    }
    
    /*public void addArea(int xLoc, int yLoc) {
        for()
        if (loc.x == xLoc && loc.y == yLoc) {
            System.out.println(");
        }
    }*/
}
