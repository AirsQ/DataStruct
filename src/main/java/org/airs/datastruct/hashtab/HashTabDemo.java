package org.airs.datastruct.hashtab;

import org.airs.datastruct.list.HeroNode;
import org.airs.datastruct.list.SingleLinkedList;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode5 = new HeroNode(5, "5", "及时雨");
        HeroNode heroNode6 = new HeroNode(6, "6", "玉麒麟");
        HeroNode heroNode7 = new HeroNode(7, "7", "智多星");
        HeroNode heroNode8 = new HeroNode(8, "8", "豹子头");

        hashTab.add(heroNode1);
        hashTab.add(heroNode2);
        hashTab.add(heroNode3);
        hashTab.add(heroNode4);
        hashTab.add(heroNode5);
        hashTab.add(heroNode6);
        hashTab.add(heroNode7);
        hashTab.add(heroNode8);

//        hashTab.list();

        System.out.println(hashTab.getById(10));
    }
}

class HashTab {

    private final SingleLinkedList[] singleLinkedLists;
    private final int size;

    public HashTab(int size) {
        singleLinkedLists = new SingleLinkedList[size];
        for (int i = 0; i < singleLinkedLists.length; i++) {
            singleLinkedLists[i] = new SingleLinkedList();
        }
        this.size = size;
    }

    public void add(HeroNode heroNode) {
        int no = hashFun(heroNode.no);
        singleLinkedLists[no].add(heroNode);
    }

    public int hashFun(int no) {
        return no % size;
    }

    public void list() {
        for (SingleLinkedList singleLinkedList : singleLinkedLists) {
            singleLinkedList.list();
            System.out.println("==================");
        }
    }

    public HeroNode getById(int no) {
        int no1 = no % size;
        return singleLinkedLists[no1].getById(no);
    }

}