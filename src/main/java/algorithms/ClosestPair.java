package algorithms;

import java.util.*;
import metrics.Metrics;
import metrics.DepthTracker;

public class ClosestPair {

    private final Metrics metrics;
    private final DepthTracker depth;

    public ClosestPair(Metrics metrics, DepthTracker depth) {
        this.metrics = metrics;
        this.depth = depth;
    }

    public static class Point {
        public double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    private double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double solve(Point[] points) {
        if (points.length < 2) return Double.POSITIVE_INFINITY;

        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));
        return closestPairRecursive(px, py);
    }

    private double closestPairRecursive(Point[] pointsByX, Point[] pointsByY) {
        depth.enter();
        try {
            int n = pointsByX.length;

            if (n <= 3) {
                double minDist = Double.POSITIVE_INFINITY;
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        metrics.incComparisons();
                        minDist = Math.min(minDist, distance(pointsByX[i], pointsByX[j]));
                    }
                }
                return minDist;
            }

            int mid = n / 2;
            Point midPoint = pointsByX[mid];

            Point[] leftX = Arrays.copyOfRange(pointsByX, 0, mid);
            Point[] rightX = Arrays.copyOfRange(pointsByX, mid, n);

            List<Point> leftYList = new ArrayList<>();
            List<Point> rightYList = new ArrayList<>();
            for (Point p : pointsByY) {
                metrics.incComparisons();
                if (p.x <= midPoint.x) {
                    leftYList.add(p);
                } else {
                    rightYList.add(p);
                }
            }

            double dLeft = closestPairRecursive(leftX, leftYList.toArray(new Point[0]));
            double dRight = closestPairRecursive(rightX, rightYList.toArray(new Point[0]));
            double d = Math.min(dLeft, dRight);

            List<Point> strip = new ArrayList<>();
            for (Point p : pointsByY) {
                metrics.incComparisons();
                if (Math.abs(p.x - midPoint.x) < d) {
                    strip.add(p);
                }
            }

            double minStrip = d;
            for (int i = 0; i < strip.size(); i++) {
                for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < minStrip; j++) {
                    metrics.incComparisons();
                    minStrip = Math.min(minStrip, distance(strip.get(i), strip.get(j)));
                }
            }

            return minStrip;
        } finally {
            depth.exit();
        }
    }
}
