package org.airs.datastruct.queue;

public class ArrayQueueDemo {

    public static void main(String[] args) {

        ArrayQueue queue = new ArrayQueue(3);

        queue.showQueue();

        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.showQueue();

        System.out.println("peek:" + queue.peek());
        queue.showQueue();

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.showQueue();

        System.out.println("peek:" + queue.peek());
        queue.push(4);
        queue.push(5);
        queue.showQueue();
    }

}

/**
 * 数组队列
 */
class ArrayQueue {

    /**
     * 前指针
     * 后指针
     * 队列大小
     * 队列数组
     */
    private int front = 0;
    private int rear = 0;
    private int maxSize = 0;
    private final int[] queue;

    public ArrayQueue(int size) {
        maxSize = size + 1;
        queue = new int[maxSize];
    }

    /**
     * 向队列中添加元素
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("队列已满！");
            return;
        }

        front++;
        front = front % maxSize;
        queue[front] = value;
    }

    /**
     * 返回队列值
     *
     * @return 队列返回的值
     */
    public Integer pop() {
        if (isEmpty()) {
            System.out.println("队列为空！");
            return null;
        }

        rear++;
        rear = rear % maxSize;
        return queue[rear];
    }

    /**
     * 查看队头的值，注意，并不出队
     *
     * @return 队头的值
     */
    public Integer peek() {
        if (isEmpty()) {
            System.out.println("队列为空！");
            return null;
        }

        return queue[(rear + 1) % maxSize];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空！");
            return;
        }

        int i = (rear + 1);
        int viewIdx = 0;
        while (i != front + 1) {
            i = i % maxSize;
            System.out.printf("queue[%d]=%d\n", viewIdx, queue[i]);
            i++;
            viewIdx ++;
        }
    }

    public boolean isFull() {
        return (front + 1) % maxSize == rear;
    }

    public boolean isEmpty() {
        return front == rear;
    }

}