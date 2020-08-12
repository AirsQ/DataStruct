package org.airs.datastruct.stack;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);

        arrayStack.push(10);
        arrayStack.push(20);
        arrayStack.push(30);
        arrayStack.list();

        System.out.println(arrayStack.pop());
        arrayStack.list();

        // ==============================
        SingleLinkedStack singleLinkedStack = new SingleLinkedStack();

        singleLinkedStack.push(new Node(1, 10));
        singleLinkedStack.push(new Node(2, 20));
        singleLinkedStack.push(new Node(3, 30));
        singleLinkedStack.list();

        System.out.println("pop:" + singleLinkedStack.pop());
        singleLinkedStack.list();
    }
}

// 用数组模拟栈
class ArrayStack {

    private final int maxSize;
    private final int[] stack;
    private int top = -1;

    public ArrayStack(int size) {
        maxSize = size;
        stack = new int[size];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满！");
            return;
        }

        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空！");
        }

        return stack[top--];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空！");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("[%d]值：%d\n", i, stack[i]);
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈空！");
        }

        return stack[top];
    }
}

class SingleLinkedStack {

    private Node head = new Node(-1, null);

    public boolean isEmpty() {
        return head.next == null;
    }

    public void push(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = node;
    }

    public Node pop() {
        if (isEmpty()) {
            System.out.println("栈空！");
            return null;
        }

        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }

        Node node = new Node(temp.next.no, temp.next.data);
        temp.next = null;
        return node;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空！");
            return;
        }

        // 反转
        Node reverseHead = new Node(-1, null);
        Node cur = head.next;
        Node temp;
        while (cur != null) {
            temp = new Node(cur.no, cur.data);
            cur = cur.next;

            temp.next = reverseHead.next;
            reverseHead.next = temp;
        }

        // 显示
        temp = reverseHead.next;
        while (temp != null) {
            System.out.println("list:" + temp);
            temp = temp.next;
        }
    }
}

class Node {

    public int no;
    public Integer data;
    public Node next;

    public Node(int no, Integer data) {
        this.no = no;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", data=" + data +
                '}';
    }
}

