import java.io.*;

import Point;
import Spline;

class Main {  
    public static void main(String[] args) {
        
        Point  currPos      = new Point (0, 0);
        double currHeading  = 4;
        Point  finalPos     = new Point (10, 20);
        double finalHeading = 1;

        Spline path = new Spline(currPos, currHeading, finalPos, finalHeading, 5);
        
        String[][] point = new String[path.getPath().size()][2];
        for (int i = 0; i < path.getPath().size(); i++) { 
            point[i][0] = Double.toString(path.getPath().get(i).x); 
            point[i][1] = Double.toString(path.getPath().get(i).y); 
        }
        
        try { 
            FileWriter fw = new FileWriter("test12.csv");
        
            for (int i = 0; i < point.length; i++) { 
                fw.write(point[i][0] + "," + point[i][1] + "\n"); 
            }
            fw.close(); 
        } catch (Exception e) { System.out.println(e); }

        System.out.println("Success...");
        
    }
    
}