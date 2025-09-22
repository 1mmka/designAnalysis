package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SelectTest {

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
}
