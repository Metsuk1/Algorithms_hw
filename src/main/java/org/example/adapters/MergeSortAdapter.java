package org.example.adapters;

import org.example.algorithms.MergeSort;
import org.example.interfaces.SortAlgorithm;

public class MergeSortAdapter implements SortAlgorithm {

    @Override
    public void sort(int[] arr) {
        MergeSort.sort(arr);
    }

    @Override
    public String name() {
        return MergeSort.class.getSimpleName();
    }
}
