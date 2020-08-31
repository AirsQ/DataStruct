package org.airs.datastruct.list;

public class SingleLinkedList {

    // 头节点 仅作带头用，没有存储功能
    private final HeroNode head = new HeroNode(-1, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode heroNode) {
        /*
         * 直接在链表尾部添加节点
         * 通过一个临时的节点当作指针，遍历到最后一个节点
         * 将节点插入到尾部
         */
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        /*
         * 根据排名按顺序添加
         * 遍历比较排名的大小
         * 如果下一个节点编号相等，直接报错
         * 如果下一个节点大 那么直接插入
         */
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                temp.next = heroNode;
                break;
            }

            if (temp.next.no == heroNode.no) {
                System.err.println("存在重复编号的值！no:" + heroNode.no);
                break;
            }

            if (temp.next.no > heroNode.no) {
                heroNode.next = temp.next;
                temp.next = heroNode;
                break;
            }

            temp = temp.next;
        }
    }

    public void list() {
        if (head.next == null) {
            System.out.println("列表为空！");
            return;
        }

        /*
         * 遍历展示列表中所有的内容
         */
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp);
        }
    }

    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("列表为空！");
            return;
        }

        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            if (temp.no == heroNode.no) {
                temp.name = heroNode.name;
                temp.nickName = heroNode.nickName;
                return;
            }
        }

        System.err.println("不存在的值！");
    }

    public void delete(int no) {
        if (head.next == null) {
            System.out.println("列表为空！");
            return;
        }

        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                return;
            }

            temp = temp.next;
        }

        System.err.println("不存在的值！");
    }

    // 注意头节点
    public int getLength() {
        if (head.next == null) {
            return 0;
        }

        HeroNode temp = head;
        int length = 0;
        while (temp.next != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }

    public HeroNode getBackwards(int backwards) {
        if (head.next == null) {
            System.out.println("[getBackwards]数组为空！");
            return null;
        }

        int len = getLength();
        if (backwards <= 0 || backwards > len) {
            System.err.println("无效参数0！");
            return null;
        }

        int tarIdx = len - backwards + 1;
        int idx = 0;
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            idx++;
            if (idx == tarIdx) {
                return temp;
            }
        }

        System.err.println("无效参数2!");
        return null;
    }

    public void reverse() {
        if (head.next == null || head.next.next == null) {
            return;
        }

        /*
         * 1. temp 记录当前节点
         * 2. next 记录下一个节点
         * 从当前列表中顺序取出节点，用reversehead记录
         */
        HeroNode reverseHead = new HeroNode(-1, "", "");
        HeroNode next = head.next;
        HeroNode temp;
        while (next != null) {
            temp = next;
            next = next.next;

            temp.next = reverseHead.next;
            reverseHead.next = temp;
        }

        head.next = reverseHead.next;
        reverseHead = null;
    }

    public void headAdd(HeroNode heroNode) {
        heroNode.next = head.next;
        head.next = heroNode;
    }

    public HeroNode getById(int no) {
        if (head.next == null) {
            System.out.println("列表为空！");
            return null;
        }

        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                return temp.next;
            }

            temp = temp.next;
        }

        System.err.println("不存在的值！");
        return null;
    }
}
