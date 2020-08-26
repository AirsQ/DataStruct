package org.airs.datastruct;

import java.util.Arrays;

public class Queen8 {


    public static void main(String[] args) {
        int[] arr = new int[8];

        check(arr, 0);
    }

    public static void check(int[] arr,  int n){
        if(n == 8){
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[n] = i;
            if(judge(arr, n)){
                check(arr, n + 1);
            }
        }
    }

    public static boolean judge(int[] arr, int n){
        for (int i = 0; i < n; i++) {
            if(arr[i] == arr[n] || Math.abs(i - n) == Math.abs(arr[i] - arr[n])){
                return false;
            }
        }

        return true;
    }


}
