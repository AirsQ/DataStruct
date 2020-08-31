package org.airs.datastruct.search;

public class SequenceSearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 155, 746, 4513, 55, 3, 9, 46};
        System.out.println(sequenceSearch(arr, 1));
    }

    public static int sequenceSearch(int[] arr, int value) {
        if (arr == null || arr.length <= 0) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (value == arr[i]) {
                return i;
            }
        }

        return -1;
    }

}
