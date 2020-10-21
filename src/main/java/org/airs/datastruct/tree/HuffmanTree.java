package org.airs.datastruct.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {

    }

    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }

        Node left;
        Node right;
        Node parent = null;
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            left = nodes.get(0);
            right = nodes.get(1);

            parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }

        return parent;
    }
}

class Node implements Comparable<Node> {

    public int value;
    public Node left;
    public Node right;

    public Node(int v) {
        value = v;
    }

    @Override
    public int compareTo(Node o) {
        return value - o.value;
    }
}