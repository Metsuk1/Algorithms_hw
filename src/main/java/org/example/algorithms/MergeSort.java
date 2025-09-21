package org.example.algorithms;

import org.example.Metrics;

public class MergeSort {
    private static final int BASE_CASE_SIZE = 16;

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

        if(right - left + 1 <= BASE_CASE_SIZE){
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


    private static void insertionSort(int[] arr, int left, int right) {
        if(left >= right){
            return;
        }

        for(int i = left + 1; i <= right; i++){
            int key = arr[i];
            int j = i - 1;

            while(j >= left){
                Metrics.recordComparison();
                if(arr[j] > key){
                    arr[j+1] = arr[j];
                    j--;
                }else{
                    break;
                }
            }
            arr[j+1] = key;
        }
    }
}
