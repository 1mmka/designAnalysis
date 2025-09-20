package algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public static double closestPair(Point[] points) {
        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));
        return closestPairRecursive(px, py);
    }

    private static double closestPairRecursive(Point[] px, Point[] py) {
        int n = px.length;
        if (n <= 3) {
            double minDist = Double.POSITIVE_INFINITY;
            for (int i = 0; i < n; i++)
                for (int j = i+1; j < n; j++)
                    minDist = Math.min(minDist, distance(px[i], px[j]));
            return minDist;
        }

        int mid = n/2;
        Point midPoint = px[mid];

        Point[] Qx = Arrays.copyOfRange(px, 0, mid);
        Point[] Rx = Arrays.copyOfRange(px, mid, n);

        Point[] Qy = Arrays.stream(py).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] Ry = Arrays.stream(py).filter(p -> p.x > midPoint.x).toArray(Point[]::new);

        double d1 = closestPairRecursive(Qx, Qy);
        double d2 = closestPairRecursive(Rx, Ry);
        double d = Math.min(d1, d2);

        Point[] strip = Arrays.stream(py).filter(p -> Math.abs(p.x - midPoint.x) < d).toArray(Point[]::new);
        double minStrip = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i+1; j < strip.length && (strip[j].y - strip[i].y) < minStrip; j++) {
                minStrip = Math.min(minStrip, distance(strip[i], strip[j]));
            }
        }

        return minStrip;
    }
}
