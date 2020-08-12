package org.airs.datastruct.list;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 heroNode5 = new HeroNode2(5, "5", "及时雨");
        HeroNode2 heroNode6 = new HeroNode2(6, "6", "玉麒麟");
        HeroNode2 heroNode7 = new HeroNode2(7, "7", "智多星");
        HeroNode2 heroNode8 = new HeroNode2(8, "8", "豹子头");

        doubleLinkedList.addToLast(heroNode4);
        doubleLinkedList.addToLast(heroNode5);
        doubleLinkedList.addToLast(heroNode2);
        doubleLinkedList.addToLast(heroNode6);

        doubleLinkedList.list();
    }

}

class DoubleLinkedList {

    // 头节点
    private HeroNode2 head = new HeroNode2(-1, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    // 遍历
    public void list() {
        if (head.next == null) {
            System.out.println("列表为空！");
            return;
        }

        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp);
        }
    }

    // 添加
    public void addToLast(HeroNode2 node) {
        if (node == null) {
            System.out.println("无效参数！");
            return;
        }

        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = node;
        node.pre = temp;
    }

    // 修改
    public void update(HeroNode2 node) {
        if (head.next == null) {
            System.out.println("列表为空！");
            return;
        }

        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
            if (temp.no == node.no) {
                temp.name = node.name;
                temp.nickName = node.nickName;
                return;
            }
        }

        System.err.println("不存在的值！");
    }

    // 删除
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("列表为空！");
            return;
        }

        HeroNode2 temp = head.next;
        while (temp != null) {
            if (temp.no == no) {
                temp.pre.next = temp.next;
                temp.next.pre = temp.pre;
                return;
            }

            temp = temp.next;
        }

        System.err.println("不存在的值！");
    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}



