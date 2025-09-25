package org.example.adapters;

import org.example.algorithms.QuickSort;
import org.example.interfaces.SortAlgorithm;

public class QuickSortAdapter implements SortAlgorithm {
    @Override
    public void sort(int[] arr) {
        QuickSort.sort(arr);
    }

    @Override
    public String name() {
        return QuickSort.class.getSimpleName();
    }
}
