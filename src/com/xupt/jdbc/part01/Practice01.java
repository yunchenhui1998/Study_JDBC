package com.xupt.jdbc.part01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
借助循环，和上面学习到的JDBC的知识，向数据库中插入100条数据，并在mysql-front中观察查询结果
 */
public class Practice01 {
    public static void main(String[] args) {
        String[] strs=new String[100];
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
        Statement s=c.createStatement()){
            for(int i=1;i<=20;i++){
                strs[i-1]="insert into hero values(null,"+"'英雄"+i+"'"+","+313.0f+","+50+")";
            }
            for(int i=0;i<20;i++){
                s.execute(strs[i]);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
