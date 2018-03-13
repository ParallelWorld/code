package pw.parallelworld.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumerInJava {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("How to user wait and notify method in java");
        System.out.println("Solving Producer Consumer Problem");
        Queue<Integer> buffer = new LinkedList<Integer>();
        int maxSize = 10;
        Thread producer = new Producer(buffer, maxSize, "PRODUCER");
        Thread consumer = new Consumer(buffer, maxSize, "CONSUMER");
        producer.start();
        consumer.start();
    }

    static class Producer extends Thread {
        private Queue<Integer> queue;
        private int maxSize;

        public Producer(Queue<Integer> queue, int maxSize, String name) {
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    // 队列满时停止生产，wait等待
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println("Queue is full, " + "Producer thread waiting for " + "consumer to take something from queue");
                            queue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    // 生产并通知消费线程
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("Producing value : " + i);
                    queue.add(i);
                    queue.notify();
                }
            }
        }
    }

    static class Consumer extends Thread {
        private Queue<Integer> queue;
        private int maxSize;

        public Consumer(Queue queue, int maxSize, String name) {
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    // 队列空时停止消费，wait等待
                    while (queue.isEmpty()) {
                        System.out.println("Queue is empty, " + "Consumer thread is waiting " + "for producer thread to put something in queue");
                        try {
                            queue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    // 消费并通知生产线程
                    System.out.println("Consumer value : " + queue.remove());
                    queue.notify();
                }
            }
        }
    }
}
