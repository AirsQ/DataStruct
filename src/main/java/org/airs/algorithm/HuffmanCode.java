package org.airs.algorithm;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like java";

        byte[] bytes = zip(str);
//        System.out.println(Arrays.toString(bytes));

//        unzip(bytes);

        System.out.println(Integer.toBinaryString(-2));
    }

    // huffman ===============================================
    public static Node createHuffmanTree(String str) {
        List<Node> nodes = getNodes(str);

        Node left;
        Node right;
        Node parent = null;
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            left = nodes.get(0);
            right = nodes.get(1);

            parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }

        return parent;
    }

    public static List<Node> getNodes(String str) {
        byte[] bytes = str.getBytes();
        Map<Byte, Integer> counts = new HashMap<>();
        Integer tmpInt;
        for (byte value : bytes) {
            tmpInt = counts.get(value);
            if (tmpInt == null) {
                counts.put(value, 1);
                continue;
            }

            counts.put(value, tmpInt + 1);
        }

        List<Node> nodes = new ArrayList<>();
        counts.forEach((key, value) -> {
            nodes.add(new Node(key, value));
        });

        return nodes;
    }

    private static final Map<Byte, String> huffmanCodes = new HashMap<>();

    public static Map<Byte, String> generateHuffmanCode(Node root) {
        if (root == null) {
            return null;
        }

        generateHuffmanCode(root.left, "0", new StringBuilder());
        generateHuffmanCode(root.right, "1", new StringBuilder());

        return huffmanCodes;
    }

    public static void generateHuffmanCode(Node node, String code, StringBuilder sb) {
        if (node == null) {
            return;
        }

        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);

        // 非叶子节点
        if (node.value == null) {
            generateHuffmanCode(node.left, "0", sb2);
            generateHuffmanCode(node.right, "1", sb2);
            return;
        }

        huffmanCodes.put(node.value, sb2.toString());
    }

    // zip ================================================
    public static byte[] zip(String str) {
        Node root = createHuffmanTree(str);
        generateHuffmanCode(root);

        return zip(str.getBytes(), huffmanCodes);
    }

    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        StringBuilder sb = new StringBuilder();

        for (byte tmpByte : bytes) {
            sb.append(huffmanCodes.get(tmpByte));
        }

//        System.out.println(sb.toString());

        int len = sb.length() % 8 == 0 ? sb.length() / 8 : sb.length() / 8 + 1;
        byte[] result = new byte[len];
        String tmpStr;
        int idx = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            tmpStr = (i + 8) < sb.length() ? sb.substring(i, i + 8) : sb.substring(i);
            result[idx++] = (byte) Integer.parseInt(tmpStr, 2);
        }

//        System.out.println(Arrays.toString(result));

        return result;
    }

    public static String unzip(byte[] zipBytes) {
        /**
         * 将数组二进制字符串
         * 根据字符串和赫夫曼编码转成源字符串
         */
        String result = "";
        StringBuilder sb = new StringBuilder();
        for (byte zipByte : zipBytes) {
            System.out.println("==============");
            System.out.println(Integer.toBinaryString(zipByte));
        }


        return result;
    }

}

class Node implements Comparable<Node> {

    public Byte value;
    public Integer weight;
    public Node left;
    public Node right;

    public Node(Byte value, Integer weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);

        if (left != null) {
            left.preOrder();
        }

        if (right != null) {
            right.preOrder();
        }
    }
}
