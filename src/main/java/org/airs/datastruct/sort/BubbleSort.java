package org.airs.datastruct.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {

        int[] arr = {3, -1, 2, 9, 10};

        int first = 0;
        int second = 0;
        int max = arr.length;
        boolean isChange = false;
        while (max > 0) {
            isChange = false;

            for (int i = 0; i < max - 1; i++) {
                first = arr[i];
                second = arr[i + 1];
                if (second < first) {
                    isChange = true;
                    arr[i] = second;
                    arr[i + 1] = first;
                }
            }

            if(!isChange){
                break;
            }
            max--;
        }

        System.out.println(Arrays.toString(arr));
    }

}
