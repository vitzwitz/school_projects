package rit.stu.act1;

import rit.cs.Stack;
import rit.cs.Node;

/**
 * A stack implementation that uses a Node to represent the structure.
 * @param <T> The type of data the stack will hold
 * @author Sean Strout @ RIT CS
 * @author Bri Miskovitz
 */
public class StackNode<T> implements Stack<T> {

    // (Node<T>) Top of the stack
    private Node<T> top;

    // (int) Size of stack
    private int size;


    public StackNode() {
        /**
         * Create an empty stack.
         */
        this.top = new Node<T>(null, null);
        this.size = 0;
    }

    @Override
    public boolean empty() {
        /*
        Check if the stack is currently empty or not.

        Specified by:
            empty in interface Stack<T>
        Returns:
            true if empty, false otherwise
        */
        return (this.size == 0);
    }

    @Override
    public T pop() {
        /*
        Description copied from interface: Stack
            Remove and return the top element in the stack.

        Specified by:
            pop in interface Stack<T>
        Returns:
            the front element
        */
        assert !empty();
        T oldTop = this.top.getData();
        this.top = this.top.getNext();
        this.size--;
        return oldTop;
    }

    @Override
    public void push(T element) {
        /*
        Description copied from interface: Stack
            Add a new element to the top of the stack.

        Specified by:
            push in interface Stack<T>
        Parameters:
            element - The new data element
        */
        this.top = new Node<T>(element, this.top);
        this.size++;

    }

    @Override
    public T top() {
        /*
        Description copied from interface: Stack
            Get the top element on the stack.
        Specified by:
            top in interface Stack<T>
        Returns:
            The top element
        */
        assert !empty();
        return this.top.getData();
    }
}
