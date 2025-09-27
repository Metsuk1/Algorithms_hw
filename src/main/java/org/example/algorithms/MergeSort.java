package org.example.algorithms;

import org.example.utils.Metrics;
import org.example.utils.algorithm;

public class MergeSort extends algorithm {
    private static final int CASE_INSERTION_SORT = 16;

    public static void sort(int[] array) {
        if(array.length <= 1){
            return;
        }

        Metrics.reset();
        int[] buffer = new int[array.length];
        Metrics.recordAllocation();
        sort(array,buffer,0,array.length - 1,1);
    }

    private static void sort(int[] arr, int[] buffer, int left, int right, int depth){
        Metrics.recordDepth(depth);

        if(right - left + 1 <= CASE_INSERTION_SORT){
            insertionSort(arr,left,right);
            return;
        }

        int mid = (left + right) >>> 1;
        sort(arr,buffer,left,mid,depth+1);
        sort(arr,buffer,mid+1,right,depth+1);

        merge(arr,buffer,left,mid,right);


    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        System.arraycopy(arr, left, buffer, left, mid - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            Metrics.recordComparison();
            if(buffer[i] <= arr[j]){
                arr[k++] = buffer[i++];
            }else{
                arr[k++] = buffer[j++];
            }
        }

        while (i <= mid){
            arr[k++] = buffer[i++];
        }
    }

}
