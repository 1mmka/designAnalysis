package util;

public class Guards {
    public static void checkNotEmpty(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
    }

    public static void checkBounds(int left, int right, int length) {
        if (left < 0 || right >= length || left > right) {
            throw new IndexOutOfBoundsException("Invalid left or right index");
        }
    }
}
