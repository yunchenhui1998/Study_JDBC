package com.xupt.jdbc.part01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest01 {
    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection c=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
        Statement s=c.createStatement()){
            String sql="delete from hero ";
            s.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
