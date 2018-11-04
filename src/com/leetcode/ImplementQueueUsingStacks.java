package com.leetcode;
import java.util.*;

/**
 * Implement Queue using Stacks
 * Implement the following operations of a queue using stacks.
 push(x) -- Push element x to the back of queue.
 pop() -- Removes the element from in front of queue.
 peek() -- Get the front element.
 empty() -- Return whether the queue is empty.
 * Created by wd186013 on 8/10/18.
 */
public class ImplementQueueUsingStacks {
    static class MyQueue {
        Stack<Integer> pushStack = new Stack<>();
        Stack<Integer> popStack = new Stack<>();
        Integer front;

        // Push element x to the back of queue.
        public void push(int x) {
            if (pushStack.isEmpty())
                front = x;
            pushStack.push(x);
        }

        // Removes the element from in front of queue.
        public int pop() {
            if (popStack.isEmpty()) {
                while(!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
            return popStack.pop();
        }

        // Get the front element.
        public int peek() {
            if (!popStack.isEmpty()) {
                return popStack.peek();
            }

            return front;
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return pushStack.isEmpty() && popStack.isEmpty();
        }
    }

    static public void main(String args[]) {
        MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        q.push(3);
        System.out.println(q.pop());
        q.push(4);
        System.out.println(q.pop());
        System.out.println(q.peek());
        System.out.println(q.pop());
        System.out.println(q.pop());
    }
}

