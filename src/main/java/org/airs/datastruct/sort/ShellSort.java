package org.airs.datastruct.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        shellSort2(arr);
    }

    public static void shellSort1(int[] arr) {
        int stepLen = arr.length / 2;

        int temp = 0;
        while (stepLen > 0) {
            // 第一层for循环控制着那些数需要比较
            // 个人认为非常不好理解 请忽视这种实现方法
            for (int i = 0; i < arr.length - stepLen; i++) {
                // 第二次for循环控制着具体的比较
                // 注意这其中有当前和后来的数值比较之后，一次向前的回溯
                for (int j = i; j >= 0; j -= stepLen) {
                    if (arr[j] > arr[j + stepLen]) {
                        temp = arr[j];
                        arr[j] = arr[j + stepLen];
                        arr[j + stepLen] = temp;
                    }
                }
            }
            System.out.println(Arrays.toString(arr));
            stepLen = stepLen / 2;
        }
    }

    public static void shellSort2(int[] arr) {
        int stepLen = arr.length / 2;

        int insertIdx = 0;
        int insertValue = 0;
        while (stepLen > 0) {
            for (int i = stepLen; i < arr.length; i++) {
                insertIdx = i;
                insertValue = arr[insertIdx];

                while (insertIdx - stepLen >= 0 && insertValue < arr[insertIdx - stepLen]) {
                    arr[insertIdx] = arr[insertIdx - stepLen];
                    insertIdx -= stepLen;
                }
                arr[insertIdx] = insertValue;
            }

            System.out.println(Arrays.toString(arr));
            stepLen = stepLen / 2;
        }
    }

}
