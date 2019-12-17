package com.xupt.jdbc.part04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPoll {
    List<Connection> cs=new ArrayList<>();
    int size;
    public ConnectionPoll(int size){
        this.size=size;
        init();
    }
    public void init(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(int i=0;i<size;i++){
            try {
                Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
                cs.add(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized Connection getConnection(){
        while(cs.isEmpty()){
            try{
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return cs.remove(0);
    }
    public synchronized void returnConnection(Connection c){
        cs.add(c);
        this.notifyAll();
    }
}
