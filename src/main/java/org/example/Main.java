package org.example;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {

    }

    public static void mergeSort(int[] arr){

        if(arr.length <= 1){   // base case
            return;
        }

        int mid = arr.length / 2;

        int[] left = Arrays.copyOfRange(arr, 0, mid);   // from 0 to mid
        int[] right = Arrays.copyOfRange(arr    , mid, arr.length); // from mid to arr.length


        mergeSort(left);
        mergeSort(right);

        merge(arr,left,right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0,j = 0,k = 0;

        //while there are elements in left and right array we just add to the orig array
        while(i < left.length && j < right.length){
            if(left[i] <= right[j]){
                arr[k++] = left[i++];
            }else {
                arr[k++] = right[j++];
            }
        }
        //add the rest from left array
        while(i < left.length){
            arr[k++] = left[i++];
        }

        //add the rest from right array
        while(j < right.length){
            arr[k++] = right[j++];
        }
    }
}