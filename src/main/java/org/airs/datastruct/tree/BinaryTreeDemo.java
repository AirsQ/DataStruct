package org.airs.datastruct.tree;

public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();

        HeroNode heroNode1 = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义");
        HeroNode heroNode3 = new HeroNode(3, "吴用");
        HeroNode heroNode4 = new HeroNode(4, "林冲");
        HeroNode heroNode5 = new HeroNode(5, "5");
        HeroNode heroNode6 = new HeroNode(6, "6");
        HeroNode heroNode7 = new HeroNode(7, "7");
        HeroNode heroNode8 = new HeroNode(8, "8");

        binaryTree.setRoot(heroNode1);

        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);

        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);

//        heroNode4.setLeft(heroNode6);
        heroNode4.setRight(heroNode7);

//        System.out.println("=============");
//        heroNode1.preOrder();
//        System.out.println("=============");
//        heroNode1.infixOrder();
//        System.out.println("=============");
//        heroNode1.postOrder();

//        System.out.println(binaryTree.preOrderSearch(2));

        binaryTree.deleteNode(4);
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (root == null) {
            System.out.print("空");
            return;
        }

        root.preOrder();
    }

    public void infixOrder() {
        if (root == null) {
            System.out.print("空");
            return;
        }

        root.infixOrder();
    }

    public void postOrder() {
        if (root == null) {
            System.out.print("空");
            return;
        }

        root.postOrder();
    }

    public HeroNode preOrderSearch(int no) {
        if (root == null) {
            System.out.print("空");
            return null;
        }

        return root.preOrderSearch(no);
    }

    public void deleteNode(int no) {
        if (root == null) {
            System.out.print("空");
            return;
        }

        if (root.getNo() == no) {
            root = null;
            return;
        }

        if (root.getLeft() != null && root.getLeft().getNo() == no) {
            root.setLeft(null);
            return;
        }

        if (root.getRight() != null && root.getRight().getNo() == no) {
            root.setRight(null);
            return;
        }

        root.deleteNode(no);
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public HeroNode(int no, String name, HeroNode left, HeroNode right) {
        this.no = no;
        this.name = name;
        this.left = left;
        this.right = right;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
//                ", left=" + left +
//                ", right=" + right +
                '}';
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

    public void infixOrder() {
        if (left != null) {
            left.infixOrder();
        }

        System.out.println(this);

        if (right != null) {
            right.infixOrder();
        }
    }

    public void postOrder() {
        if (left != null) {
            left.postOrder();
        }

        if (right != null) {
            right.postOrder();
        }

        System.out.println(this);
    }


    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }

        HeroNode res = null;
        if (left != null) {
            res = left.preOrderSearch(no);
        }
        if (res != null) {
            return res;
        }

        if (right != null) {
            res = right.preOrderSearch(no);
        }

        return res;
    }

    public void deleteNode(int no) {
        /**
         * 1. 如果是叶子节点直接删除
         * 2. 如果目标节点只有一个子节点，删除目标节点，子节点上位
         * 3. 如果目标节点同时有左右两个子节点，左子节点上位
         */
        if (left != null && left.no == no) {
            left = null;
//            if (left.left == null && left.right == null) {
//                left = null;
//                return;
//            }
//
//            if (left.left == null || left.right == null) {
//                this.left = left.left != null ? left.left : left.right;
//                return;
//            }
//
//            // 左节点上位
//            this.leftUp();
            return;
        }

        if (right != null && right.no == no) {
            right = null;
//            if (right.left == null && right.right == null) {
//                right = null;
//                return;
//            }
//
//            if (right.left == null || right.right == null) {
//                this.right = right.left != null ? right.left : right.right;
//                return;
//            }
//
//            // 左节点上位
//            this.leftUp();
            return;
        }

        if (left != null) {
            left.deleteNode(no);
        }

        if (right != null) {
            right.deleteNode(no);
        }

        System.out.println("不存在节点");
    }

    /**
     * 删除左子节点 它的左子节点上位
     * 如果该节点是叶子节点，直接上位
     * 如果有左孩子，继续向下递归
     * 如果只有有孩子，有孩子变左孩子，上位
     */
    public void leftUp() {
        if (left == null || left.left == null) {
            return;
        }

        HeroNode tmp = this.left.left;
        if (tmp.left == null && tmp.right == null) {
            tmp.right = this.left.right;
            this.left = tmp;
            return;
        }

        if (tmp.left == null && tmp.right != null) {
            tmp.left = tmp.right;
            this.left = tmp;
            return;
        }

        this.left.leftUp();
        this.left = this.left.left;
    }

}
