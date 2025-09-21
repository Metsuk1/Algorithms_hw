package org.example;

import org.example.algorithms.MergeSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    @Test
    void testDebugSmallArray() {
        int[] arr = {5, -1000, 3, -985, 10,532,512,1};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        MergeSort.sort(arr);

        System.out.println("Sorted:   " + Arrays.toString(arr));
        System.out.println("Expected: " + Arrays.toString(expected));

        assertArrayEquals(expected, arr);
    }

    @Test
    void testArrayWithAllocations(){
        int[] arr = {52,12,73,1,0};
        MergeSort.sort(arr);

        assertArrayEquals(new int[]{0,1,12,52,73},arr);
        assertEquals(1,Metrics.getAllocations());
    }


    @Test
    void testEmptyArray() {
        int[] arr = {};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testOneElementArray() {
        int[] arr = {1};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1}, arr);
    }

    @Test
    void testTwoElementArray() {
        int[] arr = {1, 2};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2}, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testWithDuplicates() {
        int[] arr = {5, 1, 5, 2, 5};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 5, 5, 5}, arr);
    }

}
