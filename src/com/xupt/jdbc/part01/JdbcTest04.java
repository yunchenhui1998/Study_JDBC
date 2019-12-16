package com.xupt.jdbc.part01;

import java.sql.*;

public class JdbcTest04 {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
            Statement s=c.createStatement();
        ){
            String name="dashen";
            String password="thispassword";
            String sql="select * from user where name='"+name+"' and password='"+password+"'";
            ResultSet rs=s.executeQuery(sql);
            if(rs.next()){
                System.out.println("账号密码正确");
            }else{
                System.out.println("账号密码错误");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
