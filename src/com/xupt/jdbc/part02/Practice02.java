package com.xupt.jdbc.part02;

import java.sql.*;
import java.util.Scanner;

/*
当c.setAutoCommit(false);时，事务是不会提交的
只有执行使用 c.commit(); 才会提交进行

设计一个代码，删除表中前10条数据，但是删除前会在控制台弹出一个提示：
是否要删除数据(Y/N)
如果用户输入Y，则删除
如果输入N则不删除。
如果输入的既不是Y也不是N，则重复提示
 */
public class Practice02 {
    public static void main(String[] args) {
        deleteData(0,10);
    }
    public static void deleteData(int start,int number){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sqlSelect="select * from hero limit "+start+","+number;
        String sqlDelete="delete from hero where id=?";
        try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
            PreparedStatement ps=c.prepareStatement(sqlSelect);
            PreparedStatement ps2=c.prepareStatement(sqlDelete)
        ){
            c.setAutoCommit(false);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt(1);
                System.out.println("试图删除id="+id+"的数据");
                ps2.setInt(1,id);
                ps2.execute();
            }
            inputJudge(c);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void inputJudge(Connection c){
        Scanner in=new Scanner(System.in);
        try{
            System.out.println("是否要删除数据(Y/N)?");
            String str=in.nextLine();
            if(str.equals("Y")){
                System.out.println("删除成功!");
                c.commit();
            }else if(str.equals("N")){
                System.out.println("拒绝删除!");
                return;
            }else{
                System.out.println("非法输入,请重新输入:");
                inputJudge(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
