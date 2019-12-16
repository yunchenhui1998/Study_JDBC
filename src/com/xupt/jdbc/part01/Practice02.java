package com.xupt.jdbc.part01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
设计一个方法

public static void execute(String sql)


方法接受的参数是SQL语句，无论是增加，删除，还是修改，都调用这个方法，每次传不同的SQL语句作为参数
 */
public class Practice02 {
    public static void main(String[] args) {

    }
    public static void execute(String sql){
        Connection c=null;
        Statement s=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
            s=c.createStatement();
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(c!=null){
                try {
                    c.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(s!=null){
                try {
                    s.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
