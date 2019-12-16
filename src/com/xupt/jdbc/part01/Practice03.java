package com.xupt.jdbc.part01;

import java.sql.*;

public class Practice03 {
    public static void main(String[] args) {
        list(5,10);
    }
    public static void list(int start,int count){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
            Statement s=c.createStatement();
        ){
            String sql="select * from hero limit "+start+","+count;
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                float hp=rs.getFloat(3);
                int damage=rs.getInt(4);
                System.out.println(id+"\t"+name+"\t"+hp+"\t"+damage+"\n");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
