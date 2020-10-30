package org.airs.algorithm;

import java.util.Arrays;

public class Floyd {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        FGraph graph = new FGraph(vertex.length, vertex, matrix);
        graph.floyd();
        graph.show();
    }
}

class FGraph {
    private final char[] vertex;
    private final int[][] distance;
    private final int[][] pre;

    public FGraph(int length, char[] vertex, int[][] distance) {
        this.vertex = vertex;
        this.distance = distance;
        this.pre = new int[length][length];

        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();

            for (int j = 0; j < distance[0].length; j++) {
                System.out.printf("(%s->%s最小距离:%s) ", vertex[i], vertex[j], distance[i][j]);
            }
            System.out.println();
        }
    }

    public void floyd() {
        for (int k = 0; k < vertex.length; k++) {
            for (int i = 0; i < vertex.length; i++) {
                for (int j = 0; j < vertex.length; j++) {
                    if (distance[i][j] <= distance[i][k] + distance[j][k]) {
                        continue;
                    }

                    distance[i][j] = distance[i][k] + distance[j][k];
                    pre[i][j] = pre[k][j];
                }
            }
        }
    }
}


