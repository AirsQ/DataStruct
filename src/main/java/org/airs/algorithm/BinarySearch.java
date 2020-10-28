package org.airs.algorithm;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 10, 20, 56};

        int idx = bs(arr, 56);

        System.out.println(idx);
    }

    public static int bs(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] == value) {
                return mid;
            }

            if (arr[mid] > value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }


}
