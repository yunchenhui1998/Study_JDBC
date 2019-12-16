package com.xupt.jdbc.part01;

import java.sql.*;

public class JdbcTest03 {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
            Statement s=c.createStatement()){
            String sql="select * from hero";
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString(2);
                float hp=rs.getFloat("hp");
                int damage=rs.getInt(4);
                System.out.println(id+"\t"+name+"\t"+hp+"\t"+damage+"\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
