package com.leetcode;
import java.util.*;

/**
 * Implement Stack using Queues
 * Implement the following operations of a stack using queues.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 empty() -- Return whether the stack is empty.
 Notes:
 You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

 * Created by wentaod on 12/27/15.
 */
public class ImplementStackUsingQueues {
    static class MyStack {
        int top;
        Queue<Integer> queue1 = new LinkedList<Integer>();
        Queue<Integer> queue2 = new LinkedList<Integer>();

        // Push element x onto stack.
        public void push(int x) {
            if(!queue1.isEmpty())
                queue1.offer(x);
            else
                queue2.offer(x);
            top = x;
        }

        // Removes the element on top of the stack.
        public int pop() {
            if(!queue1.isEmpty()) {
                while(queue1.size() > 1) {
                    top = queue1.poll();
                    queue2.offer(top);
                }
                return queue1.poll();
            }
            else {
                while(queue2.size() > 1) {
                    top = queue2.poll();
                    queue1.offer(top);
                }
                return queue2.poll();
            }
        }

        // Get the top element.
        public int top() {
            return top;
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }

    static public void main(String args[]) {
        MyStack s = new MyStack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.pop();
        System.out.println(s.top());
    }
}
