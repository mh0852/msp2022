//package com.mh.web.test;
//
//import java.util.Random;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//
////生产着消费者模型
//public class Test2 {
//    public static void main(String[] args) throws InterruptedException {
//        //创建一个阻塞队列
//        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>();
//        Thread customer = new Thread(() -> {//消费者
//            while (true) {
//                try {
//                    int value = blockingQueue.take();
//                    System.out.println("消费元素: " + value);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "消费者");
//        customer.start();
//        Thread producer = new Thread(() -> {//生产者
//            Random random = new Random();
//            while (true) {
//                try {
//                    int num = random.nextInt(1000);
//                    System.out.println("生产元素: " + num);
//                    blockingQueue.put(num);
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "生产者");
//        producer.start();
//        customer.join();
////        producer.join();
//        ThreadGroup thread  Group = Thread.currentThread().getThreadGroup();
//        //activeCount()返回当前正在活动的线程的数量
//        int total = Thread.activeCount();
//        Thread[] threads = new Thread[total];
//        //enumerate(threads)将当前线程组中的active线程全部复制到传入的线程数组threads中
//        // 并且返回数组中元素个数，即线程组中active线程数量
//        threadGroup.enumerate(threads);
//        for (Thread t:threads){
//            System.out.println("线程"+t.getId()+" "+t.getName());
//        }
//    }
//}
