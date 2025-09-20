package algorithms;

import metrics.Metrics;
import metrics.DepthTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    @Test
    void testSortedArray() {
        int[] arr = {5, 2, 9, 1};
        Metrics m = new Metrics();
        DepthTracker d = new DepthTracker();
        new MergeSort(m, d).sort(arr);
        assertArrayEquals(new int[]{1, 2, 5, 9}, arr);
    }
}
