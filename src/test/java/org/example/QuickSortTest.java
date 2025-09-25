package org.example;

import org.example.algorithms.QuickSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    void testEmptyArr(){
        int[] arr = {};
        QuickSort.sort(arr);

        Assertions.assertEquals(0, arr.length);
    }

    @Test
    void testOneArr(){
        int[] arr = {1};
        QuickSort.sort(arr);
        Assertions.assertEquals(1, arr.length);
    }

    @Test
    void testReverseArray() {
        int[] arr = {5, 4, 3, 2, 1};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testRandomArray() {
        int[] arr = new Random().ints(1000, -1000, 1000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

}
