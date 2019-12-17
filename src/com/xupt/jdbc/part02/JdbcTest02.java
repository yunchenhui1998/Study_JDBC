package com.xupt.jdbc.part02;

import java.sql.*;

//向数据库中插入10000条数据，分别使用Statement和PreparedStatement，比较各自花的时间差异
public class JdbcTest02 {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
            Statement s=c.createStatement()
        ){
            long start1=System.currentTimeMillis();
            for(int i=1;i<=10;i++){
                String str="insert into hero values(null,"+"'name"+i+"',"+313.0f+","+55+")";
                s.execute(str);
            }
            long end1=System.currentTimeMillis();
            System.out.println(end1-start1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        String sql="insert into hero values(null,?,?,?)";
        try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
            PreparedStatement ps=c.prepareStatement(sql)
        ){
            long start2=System.currentTimeMillis();
            for(int i=1;i<=10;i++){
                ps.setString(1,"Hero"+i);
                ps.setFloat(2,313.0f);
                ps.setInt(3,55);
                ps.execute();
            }
            long ends=System.currentTimeMillis();
            System.out.println(ends-start2);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
