package org.airs.datastruct.tree;

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲");
        HeroNode2 heroNode5 = new HeroNode2(5, "5");
        HeroNode2 heroNode6 = new HeroNode2(6, "6");
        HeroNode2 heroNode7 = new HeroNode2(7, "7");
        HeroNode2 heroNode8 = new HeroNode2(8, "8");


        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();

        threadedBinaryTree.setRoot(heroNode1);

        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);

        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);

        heroNode4.setLeft(heroNode6);
        heroNode4.setRight(heroNode7);

        threadedBinaryTree.infixThread();
        threadedBinaryTree.infixOrder();

    }
}

class ThreadedBinaryTree {
    private HeroNode2 root;

    private HeroNode2 pre;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    public void infixThread() {
        infixThread(root);
    }

    /**
     * 中序线索化
     * @param node
     */
    private void infixThread(HeroNode2 node) {
        if (node == null) {
            return;
        }

        infixThread(node.getLeft());

        // 中序线索化
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        infixThread(node.getRight());
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("空树");
            return;
        }

        HeroNode2 node = root;
        while (node != null) {

            while (node.getLeftType() != 1) {
                node = node.getLeft();
            }


            System.out.println(node);
            while(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();
        }
    }

}

class HeroNode2 {
    private int no;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;

    private int leftType = 0;   // 0 代表左子树 1 代表右子树
    private int rightType = 0;  // 0 代表左子树 1 代表右子树

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public HeroNode2(int no, String name, HeroNode2 left, HeroNode2 right) {
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

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}

