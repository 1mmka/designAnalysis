package algorithms;

import metrics.Metrics;
import metrics.DepthTracker;

import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private final DepthTracker depth;
    private static final Random rand = new Random();

    public QuickSort(Metrics m, DepthTracker d) {
        this.metrics = m;
        this.depth = d;
    }

    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {
        while (left < right) {
            depth.enter();
            int pivotIndex = partition(arr, left, right);
            if (pivotIndex - left < right - pivotIndex) {
                sort(arr, left, pivotIndex - 1);
                left = pivotIndex + 1;
            } else {
                sort(arr, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }
            depth.exit();
        }
    }

    private int partition(int[] arr, int left, int right) {
        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.incComparisons();
            if (arr[i] < pivot) {
                swap(arr, i, storeIndex++);
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private void swap(int[] arr, int i, int j) {
        metrics.incSwaps();
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
