package org.airs.datastruct.tree;

public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);

        arrBinaryTree.preOrder(0);
    }

}

/**
 * 1. 左节点 2*n+1
 * 2. 右节点 2*n+2
 * 3. 父节点 (n-1)/2
 */
class ArrBinaryTree {

    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int idx) {
        if (idx < 0) {
            System.out.println("错误参数");
            return;
        }

        if (arr == null || arr.length <= 0) {
            System.out.println("数据为空");
            return;
        }

        System.out.println(arr[idx]);
        if ((2 * idx) + 1 < arr.length) {
            preOrder(2 * idx + 1);
        }
        if ((2 * idx) + 2 < arr.length) {
            preOrder(2 * idx + 2);
        }
    }

}

