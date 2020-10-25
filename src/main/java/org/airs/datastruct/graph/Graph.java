package org.airs.datastruct.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

    private final ArrayList<String> nodeList;

    private final int[][] edges;

    private int edgesNum = 0;

    public Graph(int n) {
        nodeList = new ArrayList<>(n);
        edges = new int[n][n];
        visited = new boolean[n];
    }

    public static void main(String[] args) {
        String[] vertexValue = {"a", "b", "c", "d", "e"};
        int n = vertexValue.length;

        Graph graph = new Graph(n);
        for (String s : vertexValue) {
            graph.addNode(s);
        }

        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);

        graph.showGraph();
//        graph.dfs();
        graph.bfs();
    }

    public void addNode(String node) {
        nodeList.add(node);
    }

    public void addEdge(int n1, int n2, int weight) {
        edges[n1][n2] = weight;
        edges[n2][n1] = weight;

        edgesNum++;
    }

    public String getNode(int idx) {
        return nodeList.get(idx);
    }

    public int getNumOfNodes() {
        return nodeList.size();
    }

    public int getNumOfEdges() {
        return edgesNum;
    }

    public String getByIdx(int idx) {
        return nodeList.get(idx);
    }

    public void setByIdx(int idx, String value) {
        nodeList.set(idx, value);
    }

    public int getWeight(int n1, int n2) {
        return edges[n1][n2];
    }

    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    private final boolean[] visited;

    public void dfs() {
        Arrays.fill(visited, false);

        for (int i = 0; i < nodeList.size(); i++) {
            if (visited[i]) {
                continue;
            }

            dfs(i);
        }
    }

    public void dfs(int idx) {
        /*
         * 1. 输出结点 设置访问过了
         * 2. 获取第一个与该节点相连接的节点
         * 3. 判断新节点是否已经访问过，访问过 寻找当前节点的下一个节点
         * 4. 如果没有， 使用新节点递归查找
         */
        System.out.println(nodeList.get(idx));
        visited[idx] = true;

        int firstLinkNodeIdx = getFirstLinkNodeIdx(idx);
        while (firstLinkNodeIdx != -1) {

            if (!visited[firstLinkNodeIdx]) {
                dfs(firstLinkNodeIdx);
            }

            firstLinkNodeIdx = getNextNodeIdx(idx);
        }
    }

    public int getFirstLinkNodeIdx(int idx) {
        for (int i = 0; i < edges[idx].length; i++) {
            if (edges[idx][i] == 1) {
                return i;
            }
        }

        return -1;
    }

    public int getNextNodeIdx(int idx) {
        for (int nextIdx = idx + 1; nextIdx < edges.length; nextIdx++) {
            if (!visited[nextIdx]) {
                return nextIdx;
            }
        }

        return -1;
    }

    public void bfs() {
        List<Integer> openList = new ArrayList<>();
        List<Integer> closeList = new ArrayList<>();
        openList.add(0);

        int curIdx;
        List<Integer> linkedNodes = new ArrayList<>();
        while (!openList.isEmpty()) {
            curIdx = openList.get(0);
            openList.remove(0);
            closeList.add(curIdx);

            linkedNodes.clear();
            linkedNodes = getLinkedNode(curIdx);
            linkedNodes.forEach((Integer i) -> {
                if (!closeList.contains(i)) {
                    openList.add(i);
                }
            });
        }

        for (Integer integer : closeList) {
            System.out.println(getByIdx(integer));
        }
    }

    public List<Integer> getLinkedNode(int idx) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < edges[idx].length; i++) {
            if (edges[idx][i] == 1) {
                res.add(i);
            }
        }

        return res;
    }

}
