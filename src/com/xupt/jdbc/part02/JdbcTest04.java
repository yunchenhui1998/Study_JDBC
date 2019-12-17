package com.xupt.jdbc.part02;

import java.sql.*;

//测试Statement的getGeneratedKeys()方法
public class JdbcTest04 {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql="insert into hero values(null,?,?,?)";
        try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
            PreparedStatement ps=c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
                ){
            ps.setString(1,"Teemo");
            ps.setFloat(2,313.0f);
            ps.setInt(3,55);
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                int id=rs.getInt(1);
                System.out.println(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
