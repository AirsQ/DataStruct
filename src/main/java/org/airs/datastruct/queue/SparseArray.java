package org.airs.datastruct.queue;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparseArray {

    public static void main(String[] args) throws Exception {
        /*
         *  简单的二维数组
         */
        int row = 11;
        int col = 11;
        int[][] originalArr = new int[row][col];
        originalArr[0][1] = 1;
        originalArr[1][2] = 2;
//        printArr(originalArr);

        /*
         * 二位数组转换为稀疏数组
         *
         * 稀疏数组是一个 n行3列的数组
         * 第一行记录原始数组的行数 列数 总共多少个值
         * 11   11  2
         * 下面的开始记录每个值所在的行数 列数 和 值本身
         * row  col value
         * 0    1   1
         * 1    2   2
         */
        int valueCount = arrValueCount(originalArr);
        int[][] sparseArr = new int[valueCount + 1][3];
        sparseArr[0][0] = row;
        sparseArr[0][1] = col;
        sparseArr[0][2] = valueCount;

        int sparseIndex = 1;
        int value = 0;
        for (int i = 0; i < originalArr.length; i++) {
            for (int j = 0; j < originalArr[i].length; j++) {
                value = originalArr[i][j];
                if (value != 0) {
                    sparseArr[sparseIndex][0] = i;
                    sparseArr[sparseIndex][1] = j;
                    sparseArr[sparseIndex][2] = value;
                    sparseIndex++;
                }
            }
        }
//        printArr(sparseArr);

        /*
         * 通过稀疏数组转换成原始数组
         */
        row = sparseArr[0][0];
        col = sparseArr[0][1];
        int[][] targetArr = new int[row][col];
        for (int i = 1; i < sparseArr.length; i++) {
            row = sparseArr[i][0];
            col = sparseArr[i][1];
            value = sparseArr[i][2];

            targetArr[row][col] = value;
        }
        printArr(targetArr);

        saveFile(sparseArr);
        loadFile();
    }

    private static void printArr(int[][] arr) {
        if (arr == null) {
            return;
        }

        for (int[] one : arr) {
            for (int two : one) {
                System.out.printf("%d,", two);
            }
            System.out.println();
        }
    }

    private static int arrValueCount(int[][] arr) {
        if (arr == null) {
            return 0;
        }

        int sum = 0;
        for (int[] one : arr) {
            for (int two : one) {
                if (two != 0) {
                    sum++;
                }
            }
        }

        return sum;
    }

    private static void saveFile(int[][] sparseArr) throws IOException {
        // 找到文件，没有则新建
        File file = new File("");

        String filePath = file.getCanonicalPath() + "\\save\\save.data";
        file = new File(filePath);
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().createNewFile()) {
                System.err.println("创建目录失败!");
                return;
            }
        }

        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.err.println("创建文件失败!");
                return;
            }
        }

        // 将数组一行行地追加到文件
        try (FileWriter fileWriter = new FileWriter(file)) {
            StringBuilder str = new StringBuilder();
            for (int[] one : sparseArr) {
                str.setLength(0);
                str.append(one[0]).append(",").append(one[1]).append(",").append(one[2]).append("\n");
                fileWriter.append(str.toString());
            }

            fileWriter.flush();
        }
    }

    private static Object[] loadFile() throws IOException {
        String filePath = new File("").getCanonicalPath() + "\\save\\save.data";
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("存储文件不存在!");
            return null;
        }

        List<int[]> resultArr = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line = "";
            String[] strArr = null;
            int[] intArr = null;

            while ((line = bufferedReader.readLine()) != null) {
                strArr = line.split(",");

                intArr = new int[3];
                intArr[0] = Integer.parseInt(strArr[0]);
                intArr[1] = Integer.parseInt(strArr[1]);
                intArr[2] = Integer.parseInt(strArr[2]);

                System.out.println(Arrays.toString(intArr));
                resultArr.add(intArr);
            }
        }

        return resultArr.toArray();
    }

}
