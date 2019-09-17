import java.util.ArrayList;
import Point;

public class Spline {

    ArrayList<Point> Waypoints;
    Point currPos, finalPos;
    double currHeading, finalHeading;
    int numPoints;
    ArrayList<Point> path = new ArrayList<Point>();

    // through testing
    static final int WEIGHT_SLOPE_INITIAL = 5;  
    static final int WEIGHT_SLOPE_FINAL   = 10;

    public Spline(Point currPos, double currHeading, Point finalPos, double finalHeading, int numPoints) {
        this.currPos = currPos;
        this.currHeading = currHeading;
        this.finalPos = finalPos;
        this.finalHeading = finalHeading;
        this.numPoints = numPoints;
    }

    public ArrayList<Point> getPath() { return getFinalPath(); }

    private Point calculateP1() {
        double x = this.currPos.x + (Math.sqrt( Math.pow(WEIGHT_SLOPE_INITIAL, 2) / (1 + Math.pow(this.currHeading, 2))));
        double y = this.currPos.y + (Math.sqrt( Math.pow(WEIGHT_SLOPE_INITIAL, 2) / (1 + (1 / Math.pow(this.currHeading, 2)))));
        return new Point (x, y);
    }

    private Point calculateP2() { 
        double x = this.finalPos.x - (Math.sqrt(Math.pow(WEIGHT_SLOPE_FINAL, 2) / (1 + Math.pow(this.finalHeading, 2))));
        double y = this.finalPos.y - (Math.sqrt(Math.pow(WEIGHT_SLOPE_FINAL, 2) / (1 + (1 / Math.pow(this.finalHeading, 2)))));
        return new Point(x, y); 
    }

    private Point calculatePointBezier(Point p0, Point p1, Point p2, Point p3, double t) {
        double x = ((Math.pow((1 - t), 3) * p0.x) + (3 * Math.pow((1 - t), 2) * t * p1.x) + (3 * (1 - t) * Math.pow(t, 2) * p2.x) + (Math.pow(t, 3) * p3.x));
        double y = ((Math.pow((1 - t), 3) * p0.y) + (3 * Math.pow((1 - t), 2) * t * p1.y) + (3 * (1 - t) * Math.pow(t, 2) * p2.y) + (Math.pow(t, 3) * p3.y));
        return new Point(x, y);
    }

    private ArrayList<Point> getFinalPath() {
        double step = 1 / this.numPoints;
        ArrayList<Point> finalPath = new ArrayList<Point>();
        Point p1 = calculateP1();
        Point p2 = calculateP2();

        Point nextCurvePoint  = calculatePointBezier(this.currPos, p1, p2, this.finalPos, 0);
        Point nextCurvePoint1 = calculatePointBezier(this.currPos, p1, p2, this.finalPos, .1);
        Point nextCurvePoint2 = calculatePointBezier(this.currPos, p1, p2, this.finalPos, .2);
        Point nextCurvePoint3 = calculatePointBezier(this.currPos, p1, p2, this.finalPos, .3);
        Point nextCurvePoint4 = calculatePointBezier(this.currPos, p1, p2, this.finalPos, .4);
        Point nextCurvePoint5 = calculatePointBezier(this.currPos, p1, p2, this.finalPos, .5);
        Point nextCurvePoint6 = calculatePointBezier(this.currPos, p1, p2, this.finalPos, .6);
        Point nextCurvePoint7 = calculatePointBezier(this.currPos, p1, p2, this.finalPos, .7);
        Point nextCurvePoint8 = calculatePointBezier(this.currPos, p1, p2, this.finalPos, .8);
        Point nextCurvePoint9 = calculatePointBezier(this.currPos, p1, p2, this.finalPos, .9);
        Point nextCurvePoint10 = calculatePointBezier(this.currPos, p1, p2, this.finalPos, 1.0);
        finalPath.add(nextCurvePoint);
        finalPath.add(nextCurvePoint1);
        finalPath.add(nextCurvePoint2);
        finalPath.add(nextCurvePoint3);
        finalPath.add(nextCurvePoint4);
        finalPath.add(nextCurvePoint5);
        finalPath.add(nextCurvePoint6);
        finalPath.add(nextCurvePoint7);
        finalPath.add(nextCurvePoint8);
        finalPath.add(nextCurvePoint9);
        finalPath.add(nextCurvePoint10);

        //for (double j = 0; j < 1; j += step) {
        //    Point nextCurvePoint = calculatePointBezier(this.currPos, p1, p2, this.finalPos, j);
        //    // System.out.println(nextCurvePoint.x);
        //    finalPath.add(nextCurvePoint);
        //}
        return finalPath;
    }
}