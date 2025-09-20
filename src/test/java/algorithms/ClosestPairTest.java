package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void testClosestPairSmallSet() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0,0),
                new ClosestPair.Point(1,1),
                new ClosestPair.Point(2,2)
        };
        double result = ClosestPair.closestPair(points);
        assertEquals(Math.sqrt(2), result, 1e-6);
    }

    @Test
    void testClosestPairLargeSet() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0,0),
                new ClosestPair.Point(3,4),
                new ClosestPair.Point(1,1),
                new ClosestPair.Point(5,2)
        };
        double result = ClosestPair.closestPair(points);
        assertEquals(Math.sqrt(2), result, 1e-6);
    }
}
