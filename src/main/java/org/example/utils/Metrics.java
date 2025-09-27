package org.example.utils;

/**
 * algorithmName — algorithm name
 * comparisons — counter of element comparisons.
 * allocations — memory allocation counter.
 * maxDepth — maximum recursion depth during sorting.
 */
public class Metrics {
    private static String algorithmName;
    private static long comparisons = 0;
    private static long allocations = 0;
    private static int maxDepth = 0;

    // resets all metrics before running a new algorithm.
    public static void reset(){
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
    }

    // increase the comparison counter by 1.
    public static void recordComparison() {
        comparisons++;
    }

    // increase the memory allocation counter by 1
    public static void recordAllocation() {
        allocations++;
    }

    // update the recursion depth
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

    public static String getAlgorithmName() {
        return algorithmName;
    }

    public static void setAlgorithmName(String algorithmName) {
        Metrics.algorithmName = algorithmName;
    }
}
