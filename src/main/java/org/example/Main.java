package org.example;

import algorithms.MergeSort;
import algorithms.QuickSort;
import algorithms.ClosestPair;
import algorithms.Select;
import algorithms.ClosestPair.Point;
import metrics.Metrics;
import metrics.DepthTracker;
import metrics.CsvWriter;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int runs = 3;

        for (int n = 100; n <= 51200; n *= 2) {
            for (int r = 0; r < runs; r++) {
                int[] arr1 = new int[n];
                for (int i = 0; i < n; i++) arr1[i] = rand.nextInt(100000);

                Metrics m1 = new Metrics();
                DepthTracker d1 = new DepthTracker();
                MergeSort ms = new MergeSort(m1, d1);

                long start1 = System.nanoTime();
                ms.sort(arr1);
                long end1 = System.nanoTime();

                CsvWriter.write("MergeSort", n, end1 - start1, m1, d1);

                int[] arr2 = new int[n];
                for (int i = 0; i < n; i++) arr2[i] = rand.nextInt(100000);

                Metrics m2 = new Metrics();
                DepthTracker d2 = new DepthTracker();
                QuickSort qs = new QuickSort(m2, d2);

                long start2 = System.nanoTime();
                qs.sort(arr2);
                long end2 = System.nanoTime();

                CsvWriter.write("QuickSort", n, end2 - start2, m2, d2);

                Point[] points = new Point[n];
                for (int i = 0; i < n; i++) points[i] = new Point(rand.nextDouble(), rand.nextDouble());

                Metrics m3 = new Metrics();
                DepthTracker d3 = new DepthTracker();
                ClosestPair cp = new ClosestPair(m3, d3);

                long start3 = System.nanoTime();
                double minDist = cp.solve(points);
                long end3 = System.nanoTime();

                CsvWriter.write("ClosestPair", n, end3 - start3, m3, d3, minDist);

                int[] arr4 = new int[n];
                for (int i = 0; i < n; i++) arr4[i] = rand.nextInt(100000);
                int k = rand.nextInt(n);

                Metrics m4 = new Metrics();
                DepthTracker d4 = new DepthTracker();

                long start4 = System.nanoTime();
                int kth = Select.select(arr4, k, m4, d4);
                long end4 = System.nanoTime();

                CsvWriter.write("Select(k=" + k + ")", n, end4 - start4, m4, d4, kth);

            }
        }
    }
}
