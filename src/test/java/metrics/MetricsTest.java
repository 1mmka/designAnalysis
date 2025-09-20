package metrics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MetricsTest {
    @Test
    void testComparisonsAndSwaps() {
        Metrics m = new Metrics();
        m.incComparisons();
        m.incSwaps();
        assertEquals(1, m.getComparisons());
        assertEquals(1, m.getSwaps());
    }
}