package util;

import java.util.Random;

public class Shuffle {
    private static final Random rand = new Random();

    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Swap.swap(arr, i, j);
        }
    }
}
