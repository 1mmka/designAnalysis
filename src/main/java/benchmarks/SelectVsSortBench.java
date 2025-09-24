package benchmarks;

import algorithms.MergeSort;
import algorithms.QuickSort;
import algorithms.Select;
import metrics.Metrics;
import metrics.DepthTracker;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SelectVsSortBench {

    private int[] data;
    private int[] copy;
    private Random random;

    @Param({"1000", "10000", "100000"})
    private int n;

    @Setup(Level.Invocation)
    public void setup() {
        random = new Random();
        data = random.ints(n, 0, n * 10).toArray();
        copy = Arrays.copyOf(data, data.length);
    }

    @Benchmark
    public int testDeterministicSelect() {
        int k = n / 2;
        int[] arr = Arrays.copyOf(data, data.length);
        return Select.select(arr, k); // твой метод select
    }

    @Benchmark
    public int[] testQuickSort() {
        int[] arr = Arrays.copyOf(copy, copy.length);
        QuickSort qs = new QuickSort(new Metrics(), new DepthTracker());
        qs.sort(arr);
        return arr;
    }

    @Benchmark
    public int[] testMergeSort() {
        int[] arr = Arrays.copyOf(copy, copy.length);
        MergeSort ms = new MergeSort(new Metrics(), new DepthTracker());
        ms.sort(arr);
        return arr;
    }
}
