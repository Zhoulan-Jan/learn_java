package internal;

public class CircularQueue {
    int[] queue; //队列
    int front; //队首
    int rear; //队尾
    int capacity;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public CircularQueue(int k) {
        capacity = k+1;
        queue = new int[capacity];
        front = 0;
        rear = 0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        //考虑队列满
        if (isFull()) {
            return false;
        }
        queue[rear] = value;
        rear = (rear+1)%capacity;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front+1)%capacity;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[front];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return queue[(rear+capacity-1)%capacity];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return front == rear;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}
