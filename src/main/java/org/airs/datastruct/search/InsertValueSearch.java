package org.airs.datastruct.search;

public class InsertValueSearch {
    public static void main(String[] args) {
        //  保证有序数组
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 7));
    }

    public static int insertValueSearch(int[] arr, int left, int right, int value) {
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        if (mid < left || mid > right) {
            return -1;
        }

        int midEle = arr[mid];
        if (value == midEle) {
            // TODO
            // 向右扫描 找到 value
            // 向左扫描 找到 value

            return mid;
        }

        if (left == right) {
            return -1;
        }

        if (value > midEle) {
            return insertValueSearch(arr, mid + 1, right, value);
        }

        return insertValueSearch(arr, left, mid - 1, value);
    }

}
