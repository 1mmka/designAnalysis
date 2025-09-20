package algorithms;

import metrics.Metrics;
import metrics.DepthTracker;

public class MergeSort {
    private final Metrics metrics;
    private final DepthTracker depth;
    private static final int CUTOFF = 16;

    public MergeSort(Metrics m, DepthTracker d) {
        this.metrics = m;
        this.depth = d;
    }

    public void sort(int[] arr) {
        int[] aux = new int[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    private void sort(int[] arr, int[] aux, int left, int right) {
        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right);
            return;
        }
        depth.enter();
        int mid = (left + right) / 2;
        sort(arr, aux, left, mid);
        sort(arr, aux, mid + 1, right);
        merge(arr, aux, left, mid, right);
        depth.exit();
    }

    private void merge(int[] arr, int[] aux, int left, int mid, int right) {
        System.arraycopy(arr, left, aux, left, right - left + 1);
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > right) arr[k] = aux[i++];
            else if (aux[j] < aux[i]) {
                metrics.incComparisons();
                arr[k] = aux[j++];
            } else {
                metrics.incComparisons();
                arr[k] = aux[i++];
            }
        }
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                metrics.incComparisons();
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
