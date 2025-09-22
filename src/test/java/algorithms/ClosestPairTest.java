package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import metrics.Metrics;
import metrics.DepthTracker;

public class ClosestPairTest {

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
    void testClosestPairLargeSet() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0,0),
                new ClosestPair.Point(3,4),
                new ClosestPair.Point(1,1),
                new ClosestPair.Point(5,2)
        };
        Metrics m = new Metrics();
        DepthTracker d = new DepthTracker();
        ClosestPair cp = new ClosestPair(m, d);
        double dist = cp.solve(points);

        assertEquals(Math.sqrt(2), dist, 1e-6);
    }
}
