package metrics;

public class DepthTracker {
    private int currentDepth = 0;
    private int maxDepth = 0;

    public void enter() {
        currentDepth++;
        maxDepth = Math.max(maxDepth, currentDepth);
    }

    public void exit() {
        currentDepth--;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}