package org.airs.datastruct.list;


public class SingleLinkedListDemo {

    public static void main(String[] args) {

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode5 = new HeroNode(5, "5", "及时雨");
        HeroNode heroNode6 = new HeroNode(6, "6", "玉麒麟");
        HeroNode heroNode7 = new HeroNode(7, "7", "智多星");
        HeroNode heroNode8 = new HeroNode(8, "8", "豹子头");

        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode5);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode6);
//        singleLinkedList.list();
//        System.out.println(singleLinkedList.getLength());
//        singleLinkedList.addByOrder(heroNode4);


//        HeroNode tempNode = new HeroNode(100, "test", "test");
//        singleLinkedList.update(tempNode);
////        singleLinkedList.list();
//
//        singleLinkedList.delete(10);
//        singleLinkedList.delete(1);
//        System.out.println("delete:");
//        singleLinkedList.list();

//        singleLinkedList.list();
//        System.out.println(singleLinkedList.getBackwards(4));

//        singleLinkedList.reverse();
//        singleLinkedList.list();

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(heroNode1);
        singleLinkedList2.addByOrder(heroNode3);
        singleLinkedList2.addByOrder(heroNode7);
        singleLinkedList2.addByOrder(heroNode8);

        merge(singleLinkedList, singleLinkedList2);
    }

    public static void merge(SingleLinkedList listA, SingleLinkedList listB) {
        /*
         * 分别获取两个列表的指针
         * 比较两个指针对应值的大小
         */
        HeroNode head = new HeroNode(-1, "", ""); // 指向链表的最后一个节点
        HeroNode temp = head;

        HeroNode a = listA.getHead().next;
        HeroNode b = listB.getHead().next;
        while (!(a == null || b == null)) {
            temp.next = a.no <= b.no ? a : b;
            temp = temp.next;

            if (a.no <= b.no) {
                a = a.next;
            } else {
                b = b.next;
            }
        }
        temp.next = a == null ? b : a;

        // show
        temp = head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp);
        }
    }
}
