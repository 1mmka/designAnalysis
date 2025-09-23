package algorithms;

import metrics.Metrics;
import metrics.DepthTracker;

public class Select {

    public static int select(int[] arr, int k, Metrics m, DepthTracker d) {
        if (arr == null || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException();
        }
        return momSelect(arr, 0, arr.length - 1, k, m, d);
    }

    public static int select(int[] arr, int k) {
        return select(arr, k, new Metrics(), new DepthTracker());
    }


    private static int momSelect(int[] arr, int left, int right, int k,
                                 Metrics m, DepthTracker d) {
        d.enter();

        int result;
        if (left == right) {
            result = arr[left];
        } else {
            int pivotIndex = pivot(arr, left, right, m, d);
            pivotIndex = partition(arr, left, right, pivotIndex, m);

            if (k == pivotIndex) result = arr[k];
            else if (k < pivotIndex) result = momSelect(arr, left, pivotIndex - 1, k, m, d);
            else result = momSelect(arr, pivotIndex + 1, right, k, m, d);
        }

        d.exit();
        return result;
    }

    private static int pivot(int[] arr, int left, int right, Metrics m, DepthTracker d) {
        if (right - left < 5) return left + (right - left) / 2;

        int mid = left + (right - left) / 2;
        return mid;
    }


//    private static int partition5(int[] arr, int left, int right, Metrics m) {
//        insertionSort(arr, left, right, m);
//        return (left + right) / 2;
//    }

    private static void insertionSort(int[] arr, int left, int right, Metrics m) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                m.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics m) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        m.incSwaps();

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            m.incComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                m.incSwaps();
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        m.incSwaps();
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
