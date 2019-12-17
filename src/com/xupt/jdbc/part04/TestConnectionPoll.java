package com.xupt.jdbc.part04;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnectionPoll {
    public static void main(String[] args) {
        ConnectionPoll cp=new ConnectionPoll(3);
        for(int i=0;i<100;i++){
            Thread t=new WorkingThread("线程"+i,cp);
            t.start();
        }
    }
}
class WorkingThread extends Thread{
    private ConnectionPoll cp;
    public WorkingThread(String name,ConnectionPoll cp){
        super(name);
        this.cp=cp;
    }
    @Override
    public void run(){
        Connection c=cp.getConnection();
        System.out.println(this.getName()+"获得一个连接并开始工作");
        try(Statement s=c.createStatement()){
            Thread.sleep(1000);
            s.execute("select * from hero");
        }catch (SQLException | InterruptedException e){
            e.printStackTrace();
        }
        cp.returnConnection(c);
    }
}
