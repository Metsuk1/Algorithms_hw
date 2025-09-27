package org.example.adapters;

import org.example.algorithms.DeterministicSelect;
import org.example.interfaces.SelectAlgorithm;

public class DeterministicSelectAdapter implements SelectAlgorithm {
    @Override
    public int select(int[] arr, int k) {
        return DeterministicSelect.select(arr, k);
    }

    @Override
    public String name() {
        return DeterministicSelect.class.getSimpleName();
    }
}
