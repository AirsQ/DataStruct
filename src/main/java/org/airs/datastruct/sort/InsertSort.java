package org.airs.datastruct.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 109, 1};

//        insertSort1(arr);
        insertSort2(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort1(int[] arr) {
        int cur = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            cur = arr[i];
            for (int j = 0; j < i; j++) {
                if (cur < arr[j]) {
                    temp = arr[j];
                    arr[j] = cur;

                    for (int k = i; k > j + 1; k--) {
                        arr[k] = arr[k - 1];
                    }
                    arr[j + 1] = temp;
                    break;
                }
            }
        }
    }

    public static void insertSort2(int[] arr) {
        int insertIdx = 0;
        int insertValue = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            insertIdx = i;
            insertValue = arr[insertIdx + 1];

            while (insertIdx >= 0 && insertValue < arr[insertIdx]) {
                arr[insertIdx + 1] = arr[insertIdx];
                insertIdx--;
            }
            arr[insertIdx + 1] = insertValue;
        }
    }

}
