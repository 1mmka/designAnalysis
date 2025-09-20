package util;

public class Partition {
    public static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        Swap.swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                Swap.swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        Swap.swap(arr, storeIndex, right);
        return storeIndex;
    }
}
