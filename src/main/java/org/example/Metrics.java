package org.example;

public class Metrics {
    private static long comparisons = 0;
    private static long allocations = 0;
    private static int maxDepth = 0;

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

    public static long getComparisons() {
        return comparisons;
    }

    public static void setComparisons(long comparisons) {
        Metrics.comparisons = comparisons;
    }

    public static long getAllocations() {
        return allocations;
    }

    public static void setAllocations(long allocations) {
        Metrics.allocations = allocations;
    }

    public static int getMaxDepth() {
        return maxDepth;
    }

    public static void setMaxDepth(int maxDepth) {
        Metrics.maxDepth = maxDepth;
    }
}
