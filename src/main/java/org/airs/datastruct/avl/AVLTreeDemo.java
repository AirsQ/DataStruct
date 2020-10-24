package org.airs.datastruct.avl;


public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//         int[] arr = {10, 11, 7, 6, 8, 9};
        int[] arr = {2, 1, 6, 5, 7, 3};

        AVLTree avlTree = new AVLTree(null);
        for (int i : arr) {
            avlTree.add(new Node(i));
        }

        avlTree.add(new Node(1));
        avlTree.add(new Node(5));
        avlTree.add(new Node(10));
        System.out.println(avlTree.height());
        System.out.println(avlTree.leftHeight());
        System.out.println(avlTree.rightHeight());
    }
}

class AVLTree {
    private Node root;

    public AVLTree(Node root) {
        this.root = root;
    }

    public void add(Node node) {
        addIn(node);
        balanceTree();
    }

    private void balanceTree() {
        /*
         * 1. 如果左高度大于右高度 > 1
         * 2. 如果右高度大于左高度 > 1
         */
        int delta = root.leftHeight() - root.rightHeight();
        if (delta <= 1 && delta >= -1) {
            return;
        }

        // 左比右高 右旋转
        if (delta > 1) {
            // 右旋判断左子树的右子树高度是否大于左子树的左子树
            delta = root.left.leftHeight() - root.left.rightHeight();
            if (delta < 0) {
                root.left.leftRotate();
            }

            root.rightRotate();
            return;
        }

        // 右比左高 左旋
        // 左旋判断右子树的左子树高度是否大于右子树的右子树
        delta = root.right.leftHeight() - root.right.rightHeight();
        if (delta > 0) {
            root.right.rightRotate();
        }
        root.leftRotate();
    }

    private void addIn(Node node) {
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

    public int height() {
        return root == null ? 0 : root.height();
    }

    public int leftHeight() {
        return root == null ? 0 : root.leftHeight();
    }

    public int rightHeight() {
        return root == null ? 0 : root.rightHeight();
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

    /**
     * 获取子树的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(),
                right == null ? 0 : right.height()) + 1;
    }

    public int leftHeight() {
        return left == null ? 0 : left.height();
    }

    public int rightHeight() {
        return right == null ? 0 : right.height();
    }

    public void leftRotate() {
        Node newNode = new Node(value);
        newNode.left = left;
        newNode.right = this.right.left;
        this.left = newNode;

        this.value = right.value;
        this.right = this.right.right;
    }

    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = this.right;
        newNode.left = this.left.right;

        this.value = this.left.value;
        this.left = this.left.left;
    }
}