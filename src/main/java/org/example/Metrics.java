package org.example;

public class Metrics {
    public static long comparisons = 0;
    public static long allocations = 0;
    public static int maxDepth = 0;

    public static void reset(){
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
    }

    public static void recordComparison() {
        comparisons++;
    }

    public static void recordAllocation() {
        allocations++;
    }

    public static void recordDepth(int depth) {
        maxDepth = Math.max(maxDepth, depth);
    }
}
