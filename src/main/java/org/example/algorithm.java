package org.example;

import java.util.Random;

public class algorithm {
    private static Random rand = new Random();


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void insertionSort(int[] arr, int left, int right) {
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

    public static int partition(int[] arr, int left, int right) {
        int randomPivot = left + rand.nextInt(right - left + 1);
        swap(arr, randomPivot, right);

        int pivot = arr[right];
        int i = left - 1;

        for(int j = left; j < right; j++){
            if(arr[j] <= pivot){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);

        return i + 1;
    }
}
