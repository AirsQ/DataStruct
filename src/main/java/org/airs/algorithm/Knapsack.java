package org.airs.algorithm;

import java.util.Arrays;

public class Knapsack {
    public static void main(String[] args) {

        int[] prices = {1000, 3000, 2500};
        int[] weight = {1, 4, 3};
        int bagWeight = 4;
        int[][] totalPrices = new int[prices.length + 1][bagWeight + 1];

        /*
         * 一行一行来
         * 第一行 第一列设置为0
         * 一行一行的开始计算和设置值
         */
        for (int i = 0; i < totalPrices.length; i++) {
            totalPrices[i][0] = 0;
        }
        Arrays.fill(totalPrices[0], 0);

        // i 代表了是什么物品
        // j 代表了重量限制
        for (int itemIdx = 1; itemIdx < totalPrices.length; itemIdx++) {  // 不处理第一行
            for (int totalWeight = 1; totalWeight < totalPrices[0].length; totalWeight++) {   // 不处理第一列
                // 如果新物品重量超过了限制，不添加新物品，获取上一行的最佳数据
                if (weight[itemIdx - 1] > totalWeight) {
                    totalPrices[itemIdx][totalWeight] = totalPrices[itemIdx - 1][totalWeight];
                    continue;
                }

                // 如果没有 计算出添加新物品后和剩余重量的最佳结果之和 和 上一行的最佳数据比较 获取本单元的最佳结果
                // 每一个单元都是最佳结果
                int m1 = prices[itemIdx - 1] + totalPrices[itemIdx - 1][totalWeight - weight[itemIdx - 1]];
                int m2 = totalPrices[itemIdx - 1][totalWeight];
                totalPrices[itemIdx][totalWeight] = Math.max(m1, m2);
            }
        }

        System.out.println(Arrays.deepToString(totalPrices));
    }

}
