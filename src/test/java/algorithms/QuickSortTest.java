package algorithms;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import metrics.Metrics;
import metrics.DepthTracker;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    private final Random rnd = new Random(54321);

    private int floorLog2(int n) {
        return (int) Math.floor(Math.log(n) / Math.log(2.0));
    }

    @Test
    public void testRandomArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        Metrics m = new Metrics();
        DepthTracker d = new DepthTracker();
        QuickSort qs = new QuickSort(m, d);
        qs.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSortedArray() {
        int[] arr = {1,2,3,4,5};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        Metrics m = new Metrics();
        DepthTracker d = new DepthTracker();
        QuickSort qs = new QuickSort(m, d);
        qs.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testDuplicates() {
        int[] arr = {2,3,2,1,1};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        Metrics m = new Metrics();
        DepthTracker d = new DepthTracker();
        QuickSort qs = new QuickSort(m, d);
        qs.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testRandomAndDepthBounded() {
        int n = 1000;
        int[] arr = rnd.ints(n, -1000000, 1000000).toArray();
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        Metrics m = new Metrics();
        DepthTracker d = new DepthTracker();
        QuickSort qs = new QuickSort(m, d);
        qs.sort(arr);
        assertArrayEquals(expected, arr);

        int bound = 2 * floorLog2(Math.max(2, n)) + 20;
        assertTrue(d.getMaxDepth() <= bound,
                "QuickSort recursion depth exceeded bound: depth=" + d.getMaxDepth() + " bound=" + bound);
    }

    @Test
    public void testAdversarialCases() {
        // sorted
        int n = 2000;
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) sorted[i] = i;
        int[] expected = Arrays.copyOf(sorted, n);
        Arrays.sort(expected);

        Metrics m1 = new Metrics();
        DepthTracker d1 = new DepthTracker();
        QuickSort qs1 = new QuickSort(m1, d1);
        qs1.sort(sorted);
        assertArrayEquals(expected, sorted);

        // reverse
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) rev[i] = n - i;
        expected = Arrays.copyOf(rev, rev.length);
        Arrays.sort(expected);

        Metrics m2 = new Metrics();
        DepthTracker d2 = new DepthTracker();
        QuickSort qs2 = new QuickSort(m2, d2);
        qs2.sort(rev);
        assertArrayEquals(expected, rev);

        // many duplicates
        int[] dup = new int[n];
        for (int i = 0; i < dup.length; i++) dup[i] = i % 3;
        expected = Arrays.copyOf(dup, dup.length);
        Arrays.sort(expected);

        Metrics m3 = new Metrics();
        DepthTracker d3 = new DepthTracker();
        QuickSort qs3 = new QuickSort(m3, d3);
        qs3.sort(dup);
        assertArrayEquals(expected, dup);
    }
}
