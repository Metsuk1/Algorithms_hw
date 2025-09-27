package org.example.utils;

import java.util.Random;

/**
 * Base utility class
 *
 * Provides common operations used across different algorithm implementations:
 * - Array manipulation utilities (swap, bounds checking)
 * - Partition operations (Lomuto, Three-way)
 * - Small array sorting (insertion sort)
 * - Input validation methods
 */
 public class algorithm {
    private static Random rand = new Random();

    // partitionLt: index where elements < pivot end (exclusive)
    protected  static int partitionLt;
    // partitionGt: index where elements > pivot start (inclusive)
    protected  static int partitionGt;

    /**
     * Swaps two elements in an array
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param arr the array containing elements to swap
     * @param i index of first element
     * @param j index of second element
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Insertion sort implementation for small subarrays
     *
     * Efficient for small arrays (typically n ≤ 10-20 elements).
     *
     * Time Complexity:
     * - Best case: O(n) - already sorted array
     * - Average case: O(n²)
     * - Worst case: O(n²) - reverse sorted array
     *
     * Space Complexity: O(1) - in-place sorting
     *
     * @param arr array to sort
     * @param left starting index (inclusive)
     * @param right ending index (inclusive)
     */
    public static void insertionSort(int[] arr, int left, int right) {
        // Base case: empty or single element subarray
        if(left >= right){
            return;
        }
        // Process each element starting from second position
        for(int i = left + 1; i <= right; i++){
            int key = arr[i]; // Element to be inserted
            int j = i - 1; // Start comparing from previous element

            // Shift elements greater than key to the right
            while(j >= left){
                Metrics.recordComparison();// Record comparison for csv results
                if(arr[j] > key){
                    arr[j+1] = arr[j]; // Shift element right
                    j--;               // Move to previous element
                }else{
                    break;             // Found correct position
                }
            }
            arr[j+1] = key;            // Insert key at correct position
        }
    }

    /**
     * Lomuto partition
     *
     * Partitions array around a randomly selected pivot element.
     * After partitioning:
     * - Elements ≤ pivot are in left part
     * - Elements > pivot are in right part
     * - Pivot is at its final sorted position
     *
     * Time Complexity: O(n) where n = right - left + 1
     * Space Complexity: O(1) - in-place partitioning
     *
     * @param arr array to partition
     * @param left starting index (inclusive)
     * @param right ending index (inclusive)
     * @return final position of pivot element
     */
    public static int partition(int[] arr, int left, int right) {
       // Select random pivot to avoid worst-case scenarios
        int randomPivot = left + rand.nextInt(right - left + 1);
        swap(arr, randomPivot, right); // Move pivot to end

        int pivot = arr[right];  // Pivot value
        int i = left - 1;        // Index of smaller element

        //Partition array around pivot
        for(int j = left; j < right; j++){
            Metrics.recordComparison(); // Record for performance csv results
            if(arr[j] <= pivot){
                i++;
                swap(arr, i, j);       // Move smaller element to left part
            }
        }
       // Place pivot in its final position
        swap(arr, i + 1, right);

        return i + 1; // Return pivot's final position
    }

    /**
     * Validates that array is not null or empty
     *
     * @param arr array to validate
     * @throws IllegalArgumentException if array is null or empty
     */
    public static void checkArray(int[] arr) {
        if(arr == null || arr.length == 0){
            throw new IllegalArgumentException("Input array is null or empty");
        }
    }

    /**
     * Validates that index k is within array bounds
     *
     * @param arr array to check bounds against
     * @param k index to validate (0-based)
     * @throws IllegalArgumentException if k is out of bounds [0, arr.length)
     */
    public static void checkBounds(int[] arr,int k){
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k out of bounds");
        }
    }

    /**
     * Three-way partitioning
     *
     * Partitions array into three parts around a pivot value:
     * - Elements < pivot
     * - Elements = pivot
     * - Elements > pivot
     *
     * This is especially efficient for arrays with many duplicate elements.
     * Results are stored in static variables partitionLt and partitionGt.
     *
     * Time Complexity: O(n) where n = right - left + 1
     * Space Complexity: O(1) - in-place partitioning
     *
     * After execution:
     * - arr[left..partitionLt-1] contains elements < pivot
     * - arr[partitionLt..partitionGt-1] contains elements = pivot
     * - arr[partitionGt..right] contains elements > pivot
     *
     * @param arr array to partition
     * @param left starting index (inclusive)
     * @param right ending index (inclusive)
     * @param pivot value to partition around (not index!)
     */
    protected  static void threeWayPartition(int[] arr, int left, int right, int pivot) {
        int i = left;// Current element being examined
        int lt = left; // Next position for elements < pivot
        int gt = right + 1; // Next position for elements > pivot

        while (i < gt) {
            Metrics.recordComparison();
            if (arr[i] < pivot) {
                // Element is smaller than pivot - move to left region
                swap(arr, i++, lt++);
            } else {
                Metrics.recordComparison();
                if (arr[i] > pivot) {
                    // Element is larger than pivot - move to right region
                    swap(arr, i, --gt);
                } else {
                    // Element equals pivot - leave in middle region
                    i++;
                }
            }
        }

        // save results in static variables
        partitionLt = lt;
        partitionGt = gt;
    }
}

