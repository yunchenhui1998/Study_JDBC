package com.xupt.jdbc.part04;

import java.util.ArrayList;
import java.util.List;
/*
向数据库中插入100条数据，比较传统方式和数据库连接池方式的性能差异

1. 使用传统方式创建100个线程，每个线程都会创建新的连接，通过这个连接向数据库插入1条数据，然后关闭这个连接。

2. 使用数据库连接池的方式，创建一个有10条连接的连接池，然后创建100个线程，每个线程都会向连接池借用连接，借用到后，向数据库插入1条数据，然后归还这个连接。

通过时间统计，比较这两种方式的性能表现差异。
 */
public class TestConnectionPoll02 {
    static int threadCount=100;
    public static void main(String[] args) {
        traditionalWay();
        ConnectionPollWay();
    }
    public static void traditionalWay(){
        long startTime=System.currentTimeMillis();
        List<Thread> list=new ArrayList<>();
        for(int i=0;i<threadCount;i++){
            TraditionalWorkingThread t=new TraditionalWorkingThread();
            t.start();
            list.add(t);
        }
        for(Thread th:list){
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime=System.currentTimeMillis();
        System.out.println("传统方式启动100条线程耗时："+(endTime-startTime));
    }
    public static void ConnectionPollWay(){
        long startTime=System.currentTimeMillis();
        ConnectionPoll cp=new ConnectionPoll(10);
        List<Thread> list=new ArrayList<>();
        for(int i=0;i<threadCount;i++){
            ConnectionPoolWorkingThread t=new ConnectionPoolWorkingThread(cp);
            t.start();
            list.add(t);
        }
        for(Thread th:list){
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime=System.currentTimeMillis();
        System.out.println("连接池方式启动100条线程耗时："+(endTime-startTime));
    }
}
