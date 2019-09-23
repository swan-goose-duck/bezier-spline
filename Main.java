import java.util.ArrayList;
import java.io.*;

import PathSmoother;
import Point;

class Main {
    public static void main(String[] args) {
        //test points
        int numPoints = 10;
        Point currPos = new Point(0, 0);
        double currHeading = Math.PI / 4;
        Point finalPos = new Point(10, 10);
        double finalHeading = 1.25 * Math.PI;
        
        ArrayList<Point> Path = addPointsInLine(currPos, currHeading, numPoints);
        Path.add(currPos);
        Path.add(finalPos);
        Path.addAll(addPointsInLine(finalPos, finalHeading, numPoints));
        
        for (Point point : Path) {
            System.out.println(point.x + ", " + point.y);
        }

        PathSmoother finalPath = new PathSmoother(Path, 20);
        ArrayList<Point> last = finalPath.getFinalPath();

        //create csv
        String[][] point = new String[last.size()][2];
        for (int i = 0; i < last.size(); i++) {
            point[i][0] = Double.toString(last.get(i).x);
            point[i][1] = Double.toString(last.get(i).y);
        }

        try {    
            FileWriter fw = new FileWriter("test.csv");    
            
            for(int i = 0; i < point.length; i++){
                fw.write(point[i][0] + "," + point[i][1] + "\n");       
            }

            fw.close();
        } catch(Exception e) { System.out.println(e); }    
           System.out.println("Success...");
           
      }

    public static ArrayList<Point> addPointsInLine(Point point, double heading, int numPoints) {
        ArrayList<Point> temp = new ArrayList<Point>();
        double increment = 1 / numPoints;
        double slope = Math.tan(heading);
        double b = point.y - (slope * point.x);

        if ((heading > Math.PI / 2) && (heading < 1.5 * Math.PI)) { increment *= -1; }
        for (double i = 0; i <= 1; i += increment) {
            double x = point.x * i ;
            double y = slope * x + b;
            Point temp1 = new Point(x, y);
            temp.add(temp1);
        }
        return temp;
    }
}
