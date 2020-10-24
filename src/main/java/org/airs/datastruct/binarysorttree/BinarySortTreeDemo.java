package org.airs.datastruct.binarysorttree;

public class BinarySortTreeDemo {

    public static void main(String[] args) {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 0};

        BinarySortTree binarySortTree = new BinarySortTree(null);
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }

        binarySortTree.delNode(0);
        binarySortTree.delNode(5);
        binarySortTree.delNode(12);
        binarySortTree.delNode(9);
        binarySortTree.delNode(3);
        binarySortTree.delNode(7);
        binarySortTree.delNode(10);
//        binarySortTree.delNode(1);
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private Node root;

    public BinarySortTree(Node root) {
        this.root = root;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (root == null) {
            root = node;
            return;
        }

        root.add(node);
    }

    public void infixOrder() {
        if (root == null) {
            return;
        }

        root.infixOrder();
    }

    public Node search(int value) {
        return root == null ? null : root.search(value);
    }

    public Node searchParent(int value) {
        return root == null ? null : root.searchParent(value);
    }

    public void delNode(int value) {
        if (root == null) {
            return;
        }

        // 获取目标节点
        Node tarNode = search(value);
        if (tarNode == null) {
            return;
        }

        // 获取目标父节点
        Node parentNode = searchParent(value);

        // 目标节点是叶子节点
        if (tarNode.left == null && tarNode.right == null) {
            // 当前树只有根节点，且根节点就是目标节点
            if (parentNode == null) {
                root = null;
                return;
            }

            if (parentNode.left != null && tarNode.value < parentNode.value) {
                parentNode.left = null;
                return;
            }

            parentNode.right = null;
            return;
        }

        // 目标节点有左右子树
        if (tarNode.left != null && tarNode.right != null) {
            tarNode.value = delMinNode(tarNode.right);
            return;
        }

        // 目标节点只有左或右子树
        // 父节点为空 根节点是目标节点
        if (parentNode == null) {
            root = (tarNode.left != null ? tarNode.left : tarNode.right);
            return;
        }

        // 是父节点的左节点
        if (parentNode.left != null && tarNode.value < parentNode.value) {
            parentNode.left = (tarNode.left != null ? tarNode.left : tarNode.right);
            return;
        }

        // 是父节点的右节点
        parentNode.right = (tarNode.left != null ? tarNode.left : tarNode.right);
    }

    public int delMinNode(Node root) {
        Node minNode = root;
        while (minNode.left != null) {
            minNode = minNode.left;
        }

        delNode(minNode.value);
        return minNode.value;
    }
}

class Node {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 二叉排序树添加新节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (left == null) {
                left = node;
                return;
            }

            left.add(node);
            return;
        }

        if (right == null) {
            right = node;
            return;
        }

        right.add(node);
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (left != null) {
            left.infixOrder();
        }

        System.out.println(this.value);

        if (right != null) {
            right.infixOrder();
        }
    }

    /**
     * 查找要删除的节点
     */
    public Node search(int value) {
        /**
         * 判断当前节点是否为目标节点
         * 判断值是否小于当前节点的值
         */
        if (value == this.value) {
            return this;
        }

        if (value < this.value) {
            return left == null ? null : left.search(value);
        }

        return right == null ? null : right.search(value);
    }

    /**
     * 查找要删除的父节点
     */
    public Node searchParent(int value) {
        if ((left != null && left.value == value)
                || (right != null && right.value == value)) {
            return this;
        }

        if (value < this.value) {
            return left == null ? null : left.searchParent(value);
        }

        return right == null ? null : right.searchParent(value);
    }
}