package com.xupt.jdbc.part02;

import java.sql.*;

/*
当插入一条数据之后，通过获取自增长id，得到这条数据的id，比如说是55.

删除这条数据的前一条，54.

如果54不存在，则删除53，以此类推直到删除上一条数据。
 */
public class Practice01 {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        int key=-1;
        String sql="insert into hero values(null,?,?,?)";
        String sql2="delete from hero where id=?";
        try(Connection c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
            PreparedStatement ps=c.prepareStatement(sql);
            PreparedStatement ps2=c.prepareStatement(sql2)
                ){
            ps.setString(1,"GaiLun");
            ps.setFloat(2,616.0f);
            ps.setInt(3,50);
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                key=rs.getInt(1);
            }
            if(key!=-1){
                do {
                    ps2.setInt(1, --key);
                }while(ps2.executeUpdate()==0);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
