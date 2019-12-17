package com.xupt.jdbc.part03;

import java.sql.*;

public class JdbcTest01 {
    public static void main(String[] args) {
        Hero h=get(5);
        System.out.println(h.name);
    }
    public static Hero get(int id){
            Hero hero=null;
            try{
                Class.forName("com.mysql.jdbc.Driver");
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            String sql="select * from hero where id = ?";
            try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
                PreparedStatement ps=c.prepareStatement(sql);
                    ){
                ps.setInt(1,id);
                ResultSet rs=ps.executeQuery();

                if(rs.next()){
                    hero=new Hero();
                    hero.id=rs.getInt(1);
                    hero.name=rs.getString(2);
                    hero.hp=rs.getFloat(3);
                    hero.damage=rs.getInt(4);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return hero;
    }
}
