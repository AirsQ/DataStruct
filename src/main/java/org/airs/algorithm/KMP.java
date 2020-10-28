package org.airs.algorithm;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String ori = "ABCDABABCDABCDABD";
        String tar = "ABCDABD";

        System.out.println(kmpSearch(ori, tar));
    }

    public static int kmpSearch(String ori, String tar) {
        int[] next = generateKmpNext(tar);
        System.out.println(Arrays.toString(next));

        for (int i = 0, j = 0; i < ori.length(); i++) {

            while (j > 0 && ori.charAt(i) != tar.charAt(j)) {
                j = next[j - 1];
            }

            if (ori.charAt(i) == tar.charAt(j)) {
                j++;
            }

            if (j == tar.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }

    public static int[] generateKmpNext(String str) {
        int[] next = new int[str.length()];
        next[0] = 0;

        for (int i = 1, j = 0; i < str.length(); i++) {

            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }

            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }

            next[i] = j;
        }

        return next;
    }

}
