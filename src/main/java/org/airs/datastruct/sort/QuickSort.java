package org.airs.datastruct.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 2, 7, 2, 2, 5, 4, 4, 0};     // 只移动自身时bug
////        int[] arr = {8, 9, 5, 2, 2, 7, 2, 4, 4, 0};
////        int[] arr = {0, 5, 7, 6, 7, 3, 7, 2, 1};
////        int[] arr = {1, 9, 4, 4, 5};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));

        int size = 20;
        int[] arr = new int[size];

        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) (Math.random() * size);
            }
            int[] old = Arrays.copyOf(arr, arr.length);

            quickSort(arr, 0, arr.length - 1);

            if (!check(arr)) {
                System.out.println(Arrays.toString(old));
                System.out.println(Arrays.toString(arr));
                System.out.println("=============");
            }
        }
    }

    public static boolean check(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static void quickSort(int[] arr, int leftIdx, int rightIdx) {
        int l = leftIdx;
        int r = rightIdx;
        int pivot = arr[(leftIdx + rightIdx) / 2];

        int temp = 0;
        while (l < r) {

            // 从左边开始找，找到一个比中间值大或等于的值
            while (arr[l] < pivot) {
                l++;
            }

            // 从右边开始找，找到一个比中间值小或等于的值
            while (arr[r] > pivot) {
                r--;
            }

            // 如果左索引和右索引开始交错了，说明此时，两者指向了同一个元素
            if (l >= r) {
                break;
            }

            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            // 如果对面在交换前等于中间值，直接向中间靠拢
            // 为了解决如果此时l r 同时等于 pivot死循环的情况
            // 为什么是对面向中间靠拢，而不是自己向中间靠拢
            // 会有bug 模拟的时候发现，l r 最后不指向pivot
            // 如果只移动自己，导致最后指向的值不等于pivot，既不参与左边接下来的递归，也不参与右边接下来的递归，排序出错
            // 如果只移动对面，虽然不指向原本的元素，但是值是一样的，排序没有问题
            // 原因是什么？
            // 为什么靠拢一个后，还要再判断另一个，只判断一次行不行
            // 经个人测试，可以只移动对面
            // 但是两边同时检测移动的话，l r 最后会指向pivot元素
            if (arr[l] == pivot) {
                r--;
//                l++;
            }
//            if (arr[r] == pivot) {
//                l++;
//            }
        }
//        System.out.println(l);
//        System.out.println(r);

        if (l == r) {
            l++;
            r--;
        }

        if (leftIdx < r) {
            quickSort(arr, leftIdx, r);
        }

        if (l < rightIdx) {
            quickSort(arr, l, rightIdx);
        }
    }

}
