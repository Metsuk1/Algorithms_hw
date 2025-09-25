package org.example.algorithms;

import org.example.Metrics;
import org.example.algorithm;

import java.util.Random;

public class QuickSort extends algorithm {
    private static final int CASE_INSERTION_SORT = 7;


    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        Metrics.reset();
        quickSort(arr,0,arr.length - 1,1);
    }

    private static void quickSort(int[] arr, int left, int right, int depth) {
        while(left < right) {
            Metrics.recordDepth(depth);

            if(right - left + 1 < CASE_INSERTION_SORT ) {
                insertionSort(arr,left,right);
                break;
            }

            int pivot = partition(arr,left,right);

            if(pivot - left  < right - pivot) {
                quickSort(arr,left,pivot - 1,depth+1);
                left = pivot + 1;
            } else {
                quickSort(arr,pivot + 1,right,depth+1);
                right = pivot - 1;
            }
            Metrics.recordDepth(depth);
        }
    }
}
