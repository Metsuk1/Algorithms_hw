package org.example.algorithms;

import org.example.utils.Metrics;
import org.example.utils.algorithm;

/**
 * Deterministic Select using Median-of-Medians algorithm
 * Guarantees O(n) worst-case time complexity for finding k-th smallest element
 *
 * Algorithm overview:
 * 1. Divide array into groups of 5 elements
 * 2. Find median of each group (O(1) per group)
 * 3. Recursively find median of medians as pivot
 * 4. Partition array around this pivot
 * 5. Recurse into appropriate partition
 *
 * Time Complexity: O(n) worst-case guaranteed
 * Space Complexity: O(log n) for recursion stack
 */
public class DeterministicSelect extends algorithm {

    /**
     * Find k smallest element (0-indexed) using Median-of-Medians
     */
    public static int select(int[] arr,int k){
        // Input validation
        checkArray(arr);
        checkBounds(arr,k);

        Metrics.reset();

        // Create copy to avoid modifying original
        int[] copy = new int[arr.length];
        System.arraycopy(arr,0,copy,0,arr.length);
        Metrics.recordAllocation();// Record allocation of medians array

        // Start recursive selection process
        return selectInPlace(copy, 0, copy.length - 1, k, 1);
    }

    /**
     * Recursive in-place selection implementation
     */
     private static int selectInPlace(int[] arr, int left, int right, int k, int depth) {
        Metrics.recordDepth(depth);// Record maximum recursion depth reached

        int size = right-left + 1;

        // Base case: small subarray, use insertion sort
        if(size <= 5){
            insertionSort(arr,left,right);
            return arr[left + k];
        }

        // Find median of medians
        int medianOfMedians = findMedianOfMedians(arr, left, right, depth + 1);

        // Three-way partition
        threeWayPartition(arr,left,right,medianOfMedians);

        // Calculate partition sizes
        int leftCount = partitionLt - left;
        int equalCount = partitionGt - partitionLt;

        // Recurse into appropriate partition
        if(k < leftCount){
            // Target is in left partition (elements < pivot)
            return selectInPlace(arr, left, partitionLt - 1, k, depth + 1);
        }else if (k < leftCount + equalCount) {
            // Target equals pivot = we found it
            return medianOfMedians;
        } else {
            // Target is in right partition (elements > pivot)
            return selectInPlace(arr, partitionGt, right, k - leftCount - equalCount, depth + 1);
        }
    }

/**
 * Find median of medians - the heart of the algorithm
 * This ensures we get a pivot that splits array roughly 30%-70%,
 * guaranteeing O(n) worst-case performance
 */
 private static int findMedianOfMedians(int[] arr, int left, int right, int depth) {
        int size = right - left + 1;
        int numGroups = (size + 4) / 5;

        if(numGroups <= 1){
            insertionSort(arr,left,right);
            return arr[left  + (right - left) / 2];
        }

        int[] medians = new int[numGroups];
        Metrics.recordAllocation();// Record allocation of medians array

        for (int i = 0; i < numGroups; i++) {
            int groupLeft = left + i * 5;
            int groupRight = Math.min(groupLeft + 4, right);

            // Sort the group (max 5 elements, so O(1) time)
            insertionSort(arr, groupLeft, groupRight);

            // Extract median of this group
            medians[i] = arr[groupLeft + (groupRight - groupLeft) / 2];
        }

        // Recursively find median of the medians
        // This recursive call is on array of size n/5, contributing to O(n) total time
        return selectInPlace(medians, 0, medians.length - 1, medians.length / 2, depth);
    }
}
