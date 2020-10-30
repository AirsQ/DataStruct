package org.airs.algorithm;

import java.util.Arrays;

public class Prim {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexes = data.length;
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 2},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},
        };

        Graph graph = new Graph(vertexes);
        graph.setData(data);
        graph.setWeight(weight);

        graph.show();
        prim(graph, 2);

    }

    public static void prim(Graph graph, int startIdx) {
        int vertexes = graph.getVertexes();
        int[] visited = new int[vertexes];
        visited[startIdx] = 1;

        int minWeight = 10000;
        int targetIdx = -1;
        for (int totalCount = 0; totalCount < vertexes - 1; totalCount++) {
            minWeight = 10000;

            for (int i = 0; i < vertexes; i++) {
                for (int j = 0; j < vertexes; j++) {
                    if (i == j) {
                        continue;
                    }

                    /*
                     * i 访问过
                     * j 没被访问过
                     * 如果i j联通 且小于最小权重
                     */
                    if (visited[i] == 1 && visited[j] == 0
                            && graph.getWeight()[i][j] < minWeight) {
                        minWeight = graph.getWeight()[i][j];
                        startIdx = i;
                        targetIdx = j;
                    }
                }
            }

            visited[targetIdx] = 1;
            System.out.printf("边<%s, %s> 权值:%d \n", startIdx, targetIdx, minWeight);
        }
    }
}

class Graph {
    private int vertexes;
    private char[] data;
    private int[][] weight;

    public void setData(char[] data) {
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    public void setWeight(int[][] weight) {
        for (int i = 0; i < weight.length; i++) {
            System.arraycopy(weight[i], 0, this.weight[i], 0, weight[0].length);
        }
    }

    public Graph(int vertexes) {
        this.vertexes = vertexes;
        data = new char[vertexes];
        weight = new int[vertexes][vertexes];
    }

    public void show() {
        for (int[] intArr : weight) {
            System.out.println(Arrays.toString(intArr));
        }
    }


    public int getVertexes() {
        return vertexes;
    }

    public char[] getData() {
        return data;
    }

    public int[][] getWeight() {
        return weight;
    }
}
