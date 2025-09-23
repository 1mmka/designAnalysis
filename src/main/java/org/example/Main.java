package org.example;

import algorithms.MergeSort;
import algorithms.QuickSort;
import algorithms.ClosestPair;
import algorithms.ClosestPair.Point;
import algorithms.Select;
import metrics.Metrics;
import metrics.DepthTracker;
import metrics.CsvWriter;

import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Random rand = new Random();
        int runs = 3;
        int[] sizes = {100, 1000, 10000, 51200};

        for (int n : sizes) {

            long[] mergeSortTimes = new long[runs];
            Metrics m1 = new Metrics();
            DepthTracker d1 = new DepthTracker();
            MergeSort ms = new MergeSort(m1, d1);

            for (int r = 0; r < runs; r++) {
                int[] arr1 = new int[n];
                for (int i = 0; i < n; i++) arr1[i] = rand.nextInt(100_000);

                long start = System.nanoTime();
                ms.sort(arr1);
                long end = System.nanoTime();

                mergeSortTimes[r] = end - start;
            }
            long avgMerge = Arrays.stream(mergeSortTimes).sum() / runs;
            CsvWriter.write("MergeSort", n, avgMerge, m1, d1);

            long[] quickSortTimes = new long[runs];
            Metrics m2 = new Metrics();
            DepthTracker d2 = new DepthTracker();
            QuickSort qs = new QuickSort(m2, d2);

            for (int r = 0; r < runs; r++) {
                int[] arr2 = new int[n];
                for (int i = 0; i < n; i++) arr2[i] = rand.nextInt(100_000);

                long start = System.nanoTime();
                qs.sort(arr2);
                long end = System.nanoTime();

                quickSortTimes[r] = end - start;
            }
            long avgQuick = Arrays.stream(quickSortTimes).sum() / runs;
            CsvWriter.write("QuickSort", n, avgQuick, m2, d2);

            long[] closestPairTimes = new long[runs];
            Metrics m3 = new Metrics();
            DepthTracker d3 = new DepthTracker();
            ClosestPair cp = new ClosestPair(m3, d3);

            for (int r = 0; r < runs; r++) {
                Point[] points = new Point[n];
                for (int i = 0; i < n; i++) points[i] = new Point(rand.nextDouble(), rand.nextDouble());

                long start = System.nanoTime();
                double minDist = cp.solve(points);
                long end = System.nanoTime();

                closestPairTimes[r] = end - start;

                if (r == runs - 1) {
                    CsvWriter.write("ClosestPair", n, Arrays.stream(closestPairTimes).sum() / runs, m3, d3, minDist);
                }
            }

            long[] selectTimes = new long[runs];
            Metrics m4 = new Metrics();
            DepthTracker d4 = new DepthTracker();
            int k = rand.nextInt(n);

            for (int r = 0; r < runs; r++) {
                int[] arr4 = new int[n];
                for (int i = 0; i < n; i++) arr4[i] = rand.nextInt(100_000);

                long start = System.nanoTime();
                int kth = Select.select(arr4, k, m4, d4);
                long end = System.nanoTime();

                selectTimes[r] = end - start;

                if (r == runs - 1) {
                    CsvWriter.write("Select(k=" + k + ")", n, Arrays.stream(selectTimes).sum() / runs, m4, d4, kth);
                }
            }

        }

        System.out.println("done");
    }
}
