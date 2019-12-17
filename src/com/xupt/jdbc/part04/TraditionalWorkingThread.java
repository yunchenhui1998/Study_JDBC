package com.xupt.jdbc.part04;

import java.sql.*;

public class TraditionalWorkingThread extends Thread{
    public TraditionalWorkingThread(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
    }
    public void run(){
        String sql="insert into hero values(null,?,?,?)";
        try(Connection c=getConnection();
            PreparedStatement s=c.prepareStatement(sql);
                ){
            s.setString(1,"gaiLun");
            s.setFloat(2,515.0f);
            s.setInt(3,50);
            s.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
