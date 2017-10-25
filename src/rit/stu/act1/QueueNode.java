package rit.stu.act1;

import rit.cs.Node;
import rit.cs.Queue;

/**
 * A queue implementation that uses a Node to represent the structure.
 * @param <T> The type of data the queue will hold
 * @author Sean Strout @ RIT CS
 * @author Bri Miskovitz
 */
public class QueueNode<T> implements Queue<T> {
    /**
     * Create an empty queue.
     */

    // (Node<T>) front of the queue
    private Node<T> front;

    // (Node<T>) front of the queue
    private Node<T> back;

    // (int) size of the queue
    private int size;

    public QueueNode() {
        /*
        Create an empty queue.
        */
        this.front = new Node<T>(null, null);
        this.back = new Node<T>(null, null);
        this.size = 0;
    }

    @Override
    public T back() {
        /*
        Description copied from interface: Queue
            Get the last element in the queue.

        Specified by:
            back in interface Queue<T>
        Returns:
            the back element
        */
        assert !empty();
        return this.back.getData();
    }

    @Override
    public T dequeue() {
        /*
        Description copied from interface: Queue
            Remove and return the front element in the queue.

        Specified by:
            dequeue in interface Queue<T>
        Returns:
            the front element
        */
        assert !empty();
        T FrontData = this.front.getData();
        this.front = this.front.getNext();
        if (empty()){
            this.back = new Node<T>(null, null);
        }
        this.size--;
        return FrontData;
    }

    @Override
    public boolean empty() {
        /*
        Description copied from interface: Queue
            Check if the queue is currently empty or not.

        Specified by:
            empty in interface Queue<T>
        Returns:
            true if empty, false otherwise
        */
        return (this.size == 0);

    }

    @Override
    public void enqueue(T element) {
        /*
        Description copied from interface: Queue
            Add a new element to the back of the queue.

        Specified by:
            enqueue in interface Queue<T>
        Parameters:
            element - The new data element
        */
        Node<T> newNode = new Node<T>(element, null);
        if (empty()){
            this.front = newNode;
        } else {
            this.back.setNext(newNode);
        }
        this.back = newNode;
        this.size++;
    }

    @Override
    public T front() {
        /*
        Description copied from interface: Queue
            Get the front element in the queue.

        Specified by:
            front in interface Queue<T>
        Returns:
            the front element
        */
        assert !empty();
        return this.front.getData();
    }
}
