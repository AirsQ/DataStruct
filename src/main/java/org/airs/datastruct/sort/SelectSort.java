package org.airs.datastruct.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {

        int[] arr = {3, -1, -2, 9, 10};

        int min = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            min = arr[i];
            for (int j = i + 1; j < arr.length ; j++) {
                if(arr[j] < min){
                    min = arr[j];
                    arr[j] = arr[i];
                    arr[i] = min;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
