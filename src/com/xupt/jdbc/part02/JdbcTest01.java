package com.xupt.jdbc.part02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//测试PreparedStatement的使用
public class JdbcTest01 {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql="insert into hero values(null,?,?,?)";
        try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
            PreparedStatement ps=c.prepareStatement(sql);
        ){
            ps.setString(1,"提莫");
            ps.setFloat(2,313.0f);
            ps.setInt(3,55);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
