package algorithms;

import metrics.Metrics;
import metrics.DepthTracker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    private final Random rnd = new Random(12345);

    @Test
    void testSortedArray() {
        int[] arr = {5, 2, 9, 1};
        Metrics m = new Metrics();
        DepthTracker d = new DepthTracker();
        new MergeSort(m, d).sort(arr);
        assertArrayEquals(new int[]{1, 2, 5, 9}, arr);
    }

    @Test
    void testRandomArraysMultipleSizes() {
        int[] sizes = {10, 100, 1000};
        for (int n : sizes) {
            int[] arr = rnd.ints(n, -10000, 10000).toArray();
            int[] expected = Arrays.copyOf(arr, arr.length);
            Arrays.sort(expected);

            Metrics m = new Metrics();
            DepthTracker d = new DepthTracker();
            new MergeSort(m, d).sort(arr);

            assertArrayEquals(expected, arr, "MergeSort failed for n=" + n);
        }
    }

    @Test
    void testAdversarialCases() {
        // sorted
        int[] sorted = new int[200];
        for (int i = 0; i < sorted.length; i++) sorted[i] = i;
        int[] expected = Arrays.copyOf(sorted, sorted.length);
        Metrics m1 = new Metrics();
        DepthTracker d1 = new DepthTracker();
        new MergeSort(m1, d1).sort(sorted);
        assertArrayEquals(expected, sorted);

        // reverse
        int[] rev = new int[200];
        for (int i = 0; i < rev.length; i++) rev[i] = rev.length - i;
        expected = Arrays.copyOf(rev, rev.length);
        Arrays.sort(expected);
        Metrics m2 = new Metrics();
        DepthTracker d2 = new DepthTracker();
        new MergeSort(m2, d2).sort(rev);
        assertArrayEquals(expected, rev);

        // many duplicates
        int[] dup = new int[200];
        for (int i = 0; i < dup.length; i++) dup[i] = i % 5;
        expected = Arrays.copyOf(dup, dup.length);
        Arrays.sort(expected);
        Metrics m3 = new Metrics();
        DepthTracker d3 = new DepthTracker();
        new MergeSort(m3, d3).sort(dup);
        assertArrayEquals(expected, dup);
    }
}
