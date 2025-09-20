package algorithms;

public class Select {
    public static int select(int[] arr, int k) {
        if (arr == null || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException();
        }
        return momSelect(arr, 0, arr.length - 1, k);
    }

    private static int momSelect(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivotIndex = pivot(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        if (k == pivotIndex) return arr[k];
        else if (k < pivotIndex) return momSelect(arr, left, pivotIndex - 1, k);
        else return momSelect(arr, pivotIndex + 1, right, k);
    }

    private static int pivot(int[] arr, int left, int right) {
        if (right - left < 5) return partition5(arr, left, right);
        for (int i = left; i <= right; i += 5) {
            int subRight = i + 4;
            if (subRight > right) subRight = right;

            int median5 = partition5(arr, i, subRight);
            swap(arr, median5, left + (i - left)/5);
        }
        int mid = (right - left)/10 + left + 1;
        return momSelect(arr, left, left + (right - left)/5, mid);
    }

    private static int partition5(int[] arr, int left, int right) {
        insertionSort(arr, left, right);
        return (left + right)/2;
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
