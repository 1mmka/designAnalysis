package algorithms;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    public void testRandomArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        QuickSort.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSortedArray() {
        int[] arr = {1,2,3,4,5};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        QuickSort.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testDuplicates() {
        int[] arr = {2,3,2,1,1};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        QuickSort.sort(arr);

        assertArrayEquals(expected, arr);
    }
}
