package Tests;
import java.util.*;

/**
 * Created by wedeng on 2/8/15.
 */
public class ProducerConsumer {

    int counter = 0;

    class Producer implements Runnable{
        Queue<Integer> queue;
        final int size;

        Producer(Queue<Integer> queue, int size) {
            this.queue = queue;
            this.size = size;
        }

        public void run() {
            try {
                while (true) {
                    synchronized (queue) {
                        if (queue.size() == size) {
                            queue.wait();
                        } else {
                            System.out.println("Producing " + counter);
                            queue.offer(counter++);
                            queue.notifyAll();
                        }
                    }
                    Thread.sleep(1000);
                }

            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getStackTrace());
            }

        }
    }

    class Consumer implements Runnable {
        Queue<Integer> queue;
        final int size;

        Consumer(Queue<Integer> queue, int size) {
            this.queue = queue;
            this.size = size;
        }

        public void run() {
            try {
                while (true) {
                    synchronized (queue) {
                        if (queue.isEmpty()) {
                            queue.wait();
                        } else {
                            System.out.println("Consumed " + queue.poll());
                            queue.notifyAll();
                        }

                    }
                    Thread.sleep(500);
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getStackTrace());
            }

        }
    }

    public static void main(String[] args) {
        final int SIZE = 4;
        Queue<Integer> queue = new LinkedList<Integer>();
        ProducerConsumer test = new ProducerConsumer();

        new Thread(test.new Producer(queue, SIZE)).start();
        new Thread(test.new Producer(queue, SIZE)).start();
        new Thread(test.new Consumer(queue, SIZE)).start();
        new Thread(test.new Producer(queue, SIZE)).start();
        new Thread(test.new Consumer(queue, SIZE)).start();
    }
}
