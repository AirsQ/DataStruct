package org.airs.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Kruskal {

    private int edgeNum;
    private final char[] vertexes;
    private final int[][] weight;
    private static final int UNLINKED = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                {0, 12, UNLINKED, UNLINKED, UNLINKED, 16, 14},
                {12, 0, 10, UNLINKED, UNLINKED, 7, UNLINKED},
                {UNLINKED, 10, 0, 3, 5, 6, UNLINKED},
                {UNLINKED, UNLINKED, 3, 0, 4, UNLINKED, UNLINKED},
                {UNLINKED, UNLINKED, 5, 4, 0, 2, 8},
                {16, 7, 6, UNLINKED, 2, 0, 9},
                {14, UNLINKED, UNLINKED, UNLINKED, 8, 9, 0},
        };

        Kruskal kruskal = new Kruskal(data, weight);

        kruskal.show();

        List<Edge> edges = kruskal.getEdges();
        Collections.sort(edges);

        List<Edge> openEdges = new ArrayList<>();
        // 维护每个顶点在最小联通树的终点
        int[] ends = new int[data.length];

        /*
         * 边按照权值从小到达排序
         * 获取第一个权值最小的边
         * 判断改变该边是否会形成回路
         * 形成回来 抛弃 继续循环
         * 没有形成回路，添加到结果数组
         */
        Edge newEdge;
        int startIdx;
        int endIdx;
        int startEndIdx;
        int endEndIdx;
        while (edges.size() > 0) {
            Collections.sort(edges);
            newEdge = edges.remove(0);

            startIdx = kruskal.getIdx(newEdge.start);
            endIdx = kruskal.getIdx(newEdge.end);

            startEndIdx = kruskal.getEnd(ends, startIdx);
            endEndIdx = kruskal.getEnd(ends, endIdx);

            if (startEndIdx == endEndIdx) {
                continue;
            }

            ends[startEndIdx] = endEndIdx;
            openEdges.add(newEdge);
        }

        for (Edge openEdge : openEdges) {
            System.out.println(openEdge);
        }
    }

    public Kruskal(char[] vertexes, int[][] weight) {
        int size = vertexes.length;
        this.vertexes = new char[size];
        System.arraycopy(vertexes, 0, this.vertexes, 0, vertexes.length);

        this.weight = new int[size][size];
        for (int i = 0; i < weight.length; i++) {
            System.arraycopy(weight[i], 0, this.weight[i], 0, weight[0].length);
        }

        edgeNum = 0;
        for (int[] intArr : weight) {
            for (int anInt : intArr) {
                if (anInt != Kruskal.UNLINKED) {
                    edgeNum++;
                }
            }
        }
    }

    public void show() {
        for (int[] intArr : this.weight) {
            System.out.println(Arrays.toString(intArr));
        }
    }

    public int getIdx(char ch) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == ch) {
                return i;
            }
        }

        return -1;
    }

    public List<Edge> getEdges() {
        edgeNum = 0;
        List<Edge> result = new ArrayList<>();

        Edge tmp;
        for (int i = 0; i < weight.length; i++) {
            for (int j = i + 1; j < weight[0].length; j++) {
                if (weight[i][j] == UNLINKED) {
                    continue;
                }

                edgeNum++;
                tmp = new Edge(vertexes[i], vertexes[j], weight[i][j]);
                result.add(tmp);
            }
        }

        return result;
    }

    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

}

class Edge implements Comparable<Edge> {
    char start;
    char end;
    int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}


