package org.example;

import org.example.algorithms.DeterministicSelect;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 9, 1, 5};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        for (int k = 0; k < arr.length; k++) {
            int result = DeterministicSelect.select(arr, k);
            assertEquals(sorted[k], result);
        }
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        assertEquals(42, DeterministicSelect.select(arr, 0));
    }

    @Test
    void testDuplicates() {
        int[] arr = {5, 5, 5, 5, 5};
        for (int k = 0; k < arr.length; k++) {
            int result = DeterministicSelect.select(arr, k);
            assertEquals(5, result);
        }
    }

    @Test
    void testLargeArrayMedian() {
        int n = 1001;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        int median = DeterministicSelect.select(arr, n / 2);
        assertEquals(n / 2, median);
    }

}
