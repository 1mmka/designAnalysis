package metrics;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    private static final String FILE = "results.csv";
    private static boolean headerWritten = false;

    public static void write(String algo, int n, long timeNs, Metrics m, DepthTracker d) {
        try (FileWriter fw = new FileWriter(FILE, true)) {
            if (!headerWritten) {
                fw.write("Algorithm,ArraySize,Time(ns),Comparisons,Swaps,MaxDepth\n");
                headerWritten = true;
            }
            fw.write(algo + "," + n + "," + timeNs + "," +
                    m.getComparisons() + "," + m.getSwaps() + "," +
                    d.getMaxDepth() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String algo, int n, long timeNs, Metrics m, DepthTracker d, double minDist) {
        try (FileWriter fw = new FileWriter(FILE, true)) {
            if (!headerWritten) {
                fw.write("Algorithm,ArraySize,Time(ns),Comparisons,Swaps,MaxDepth,Extra\n");
                headerWritten = true;
            }
            fw.write(algo + "," + n + "," + timeNs + "," +
                    m.getComparisons() + "," + m.getSwaps() + "," +
                    d.getMaxDepth() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
