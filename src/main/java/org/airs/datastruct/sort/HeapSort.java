package org.airs.datastruct.sort;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {

        int[] arr = {4, 6, 8, 5, 9};

        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        // 调整成大根堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustBigRootHeap(arr, i, arr.length);
        }
        System.out.println(Arrays.toString(arr));

        // 交换数组最大元素和最后一个元素
        // 在调整成大根堆，重复
        int tmp = 0;
        for (int j = arr.length - 1; j >= 0; j--) {
            tmp = arr[0];
            arr[0] = arr[j];
            arr[j] = tmp;

            adjustBigRootHeap(arr, 0, j);
            System.out.println("==============");
            System.out.println(Arrays.toString(arr));
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void adjustBigRootHeap(int[] arr, int i, int len) {
        if (i < 0) {
            return;
        }

        int oldValue = arr[i];

        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
            if (k + 1 < len && arr[k] < arr[k + 1]) {
                k = k + 1;
            }

            if (arr[k] <= oldValue) {
                break;
            }

            arr[i] = arr[k];
            i = k;
        }

        arr[i] = oldValue;
    }

}
