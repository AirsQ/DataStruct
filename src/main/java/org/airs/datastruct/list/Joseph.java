package org.airs.datastruct.list;

public class Joseph {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoys(5);

        circleSingleLinkedList.list();
        circleSingleLinkedList.joseph(2, 3);
    }
}

class CircleSingleLinkedList {

    private Boy first;
    private Boy last;

    public void addBoys(int num) {
        if (num < 1) {
            System.out.println("无效参数！");
            return;
        }

        Boy boy;
        for (int i = 1; i <= num; i++) {
            boy = new Boy(i);
            boy.next = first;

            if (first == null) {
                first = boy;
                last = boy;
                continue;
            }

            last.next = boy;
            last = boy;
        }
    }

    public void list() {
        if (first == null) {
            return;
        }

        Boy temp = first;
        do {
            System.out.println(temp);
            temp = temp.next;
        } while (temp != first);
    }

    public void joseph(int startNo, int step) {
        if (first == null) {
            return;
        }
        if (startNo <= 0 || step <= 0) {
            return;
        }
        if (first.next == first) {
            System.out.println(first.no);
            return;
        }

        Boy cur = first;
        Boy pre = this.last;
        for (int i = 1; i < startNo; i++) {
            cur = cur.next;
            pre = pre.next;
        }

        /*
         * cur pre 同时移动 step - 1 步
         * cur 出队
         * 调整链表
         * 直到链表内只剩一个元素
         */
        while (true) {
            if (cur == pre) {
                System.out.println(cur.no);
                break;
            }

            for (int i = 0; i < step - 1; i++) {
                cur = cur.next;
                pre = pre.next;
            }

            System.out.println(cur.no);
            cur = cur.next;
            pre.next = cur;
        }
    }
}

class Boy {

    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
