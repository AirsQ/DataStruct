package org.airs.datastruct.search;

public class BinarySearch {
    public static void main(String[] args) {
        //  保证有序数组
        int[] arr = {1, 8, 10, 89, 100, 1042};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 1042));
    }

    public static int binarySearch(int[] arr, int left, int right, int value) {
        /*
         * 1. 找到中间的索引值，比较搜索值和中间索引的元素的大小
         *    两者值相同，返回
         *    搜索值大，向右找
         *    搜索值小，向左找
         * 2. 如果left == right， 说明是最后一个值了，如果不等，说明没找到
         */
        int mid = (left + right) / 2;
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
            return binarySearch(arr, mid + 1, right, value);
        }

        return binarySearch(arr, left, mid - 1, value);
    }

}
