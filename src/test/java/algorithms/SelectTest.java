package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

class SelectTest {

    private final Random rnd = new Random(777);

    @Test
    void testSelect() {
        int[] arr = {7,2,1,6,8,5,3,4};
        assertEquals(1, Select.select(arr,0));
        assertEquals(5, Select.select(arr,4));
        assertEquals(8, Select.select(arr,7));
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        assertEquals(42, Select.select(arr,0));
    }

    @Test
    void testInvalidIndex() {
        int[] arr = {1,2,3};
        assertThrows(IllegalArgumentException.class, () -> Select.select(arr,-1));
        assertThrows(IllegalArgumentException.class, () -> Select.select(arr,3));
    }

    @Test
    void testRandomTrialsCompareWithSort() {
        int trials = 100;
        int n = 200;
        for (int t = 0; t < trials; t++) {
            int[] a = rnd.ints(n, -1000000, 1000000).toArray();
            int k = rnd.nextInt(n);
            int[] copy = Arrays.copyOf(a, a.length);
            Arrays.sort(copy);
            int expected = copy[k];

            int result = Select.select(Arrays.copyOf(a, a.length), k);
            assertEquals(expected, result, "Mismatch on trial " + t + " k=" + k);
        }
    }
}
