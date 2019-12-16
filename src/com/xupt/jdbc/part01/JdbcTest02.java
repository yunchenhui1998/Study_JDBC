package com.xupt.jdbc.part01;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest02 {
    public static void main(String[] args) {
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
            String sql="update hero set name='name5' where id=407";
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
