package com.xupt.jdbc.part04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPoolWorkingThread  extends Thread{
    ConnectionPoll cp;
    public ConnectionPoolWorkingThread(ConnectionPoll cp){
        this.cp=cp;
    }
    public void run(){
        Connection c=cp.getConnection();
        String sql="insert into hero values(null,?,?,?)";
        try(PreparedStatement s=c.prepareStatement(sql)){
            s.setString(1,"teemo");
            s.setFloat(2,313.0f);
            s.setInt(3,55);
            s.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        cp.returnConnection(c);
    }
}
