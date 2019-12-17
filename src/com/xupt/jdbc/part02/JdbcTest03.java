package com.xupt.jdbc.part02;

import java.sql.*;
import java.util.Scanner;
//测试Statement的getResultSet()方法
public class JdbcTest03 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner in=new Scanner(System.in);
        System.out.println("从第几页开始查询？");
        int start=in.nextInt();
        System.out.println("查询几页？");
        int number=in.nextInt();
        String sql="select * from hero limit "+start+","+number;
        try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
            Statement s=c.createStatement()
        ){
            boolean flag=s.execute(sql);
            if(flag){
                ResultSet rs=s.getResultSet();
                while(rs.next()){
                    int id=rs.getInt(1);
                    String name=rs.getString(2);
                    float hp=rs.getFloat(3);
                    int damage=rs.getInt(4);
                    System.out.println(id+"\t"+name+"\t"+hp+"\t"+damage+"\n");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
