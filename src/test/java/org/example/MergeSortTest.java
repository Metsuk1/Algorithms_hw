package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

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

//    @Test
//    void testSizeOfArray(){
//        int[] arr = {1,2,3,4,5};
//        MergeSort.sort(arr);
//        assertArrayEquals();
//    }
}
