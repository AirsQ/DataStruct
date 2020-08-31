package org.airs.datastruct.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr) {
        // 找到数组中最大的值
        int max = arr[0];
        for (int j : arr) {
            if (j > max) {
                max = j;
            }
        }
        int turns = String.valueOf(max).length();

        int divisor = 1;
        int[][] buckets = new int[10][arr.length];  // 10个桶
        int[] digitOfEle = new int[10]; // 记录每个桶内存储了多少个数据

        int tmp = 0;
        for (int i = 0; i < turns; i++) {
            divisor = (int) (Math.pow(10, i));

            /*
             * 1. 获取每个数据的个位，将数字放置再正确的位置
             * 2. 依次取出桶内的数据，放入到原来的数组
             */
            for (int arrEle : arr) {
                tmp = arrEle / divisor % 10;

                buckets[tmp][digitOfEle[tmp]] = arrEle;
                digitOfEle[tmp]++;
            }

            int idx = 0;
            int digit = 0;
            for (int digitIdx = 0; digitIdx < digitOfEle.length; digitIdx++) {
                digit = digitOfEle[digitIdx];
                if (digit != 0) {
                    for (int j = 0; j < digit; j++) {
                        arr[idx] = buckets[digitIdx][j];
                        idx++;
                    }
                }
                digitOfEle[digitIdx] = 0;
            }

            System.out.println(Arrays.toString(arr));
        }
    }

}
