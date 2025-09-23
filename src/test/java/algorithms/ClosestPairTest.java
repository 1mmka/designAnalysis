package algorithms;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import metrics.Metrics;
import metrics.DepthTracker;

import java.util.Random;

public class ClosestPairTest {

    private final Random rnd = new Random(2468);

    private double bruteForceClosest(ClosestPair.Point[] pts) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double dx = pts[i].x - pts[j].x;
                double dy = pts[i].y - pts[j].y;
                double d = Math.hypot(dx, dy);
                if (d < best) best = d;
            }
        }
        return best;
    }

    @Test
    void testClosestPairSmallSet() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0,0),
                new ClosestPair.Point(1,1),
                new ClosestPair.Point(2,2)
        };
        Metrics m = new Metrics();
        DepthTracker d = new DepthTracker();
        ClosestPair cp = new ClosestPair(m, d);
        double dist = cp.solve(points);

        assertEquals(Math.sqrt(2), dist, 1e-6);
    }

    @Test
    void testClosestPairAgainstBruteForceRandomSmall() {
        int n = 500; // <= 2000 as required; 500 is safe for unit tests
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new ClosestPair.Point(rnd.nextDouble() * 10000, rnd.nextDouble() * 10000);
        }

        Metrics m = new Metrics();
        DepthTracker d = new DepthTracker();
        ClosestPair cp = new ClosestPair(m, d);
        double fast = cp.solve(pts);

        double brute = bruteForceClosest(pts);
        assertEquals(brute, fast, 1e-6, "ClosestPair (fast) must match brute-force for small n");
    }
    @Disabled("Enable locally to test performance on large sets")
    @Test
    void testClosestPairLargeSetRunsFast() {
        int n = 5000;
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new ClosestPair.Point(rnd.nextDouble() * 1e6, rnd.nextDouble() * 1e6);
        }

        Metrics m = new Metrics();
        DepthTracker d = new DepthTracker();
        ClosestPair cp = new ClosestPair(m, d);
        double fast = cp.solve(pts);

        assertTrue(fast >= 0.0, "ClosestPair returned negative distance");
    }
}
