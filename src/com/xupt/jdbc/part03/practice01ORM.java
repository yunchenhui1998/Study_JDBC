package com.xupt.jdbc.part03;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
根据ORM的思想，设计其他几个常见的ORM方法：

把一个Hero对象插入到数据库中

public static void add(Hero h)


把这个Hero对象对应的数据删除掉

public static void delete(Hero h)


更新这条Hero对象

public static void update(Hero h)


把所有的Hero数据查询出来，转换为Hero对象后，放在一个集合中返回

public static List<Hero> list();
 */
public class practice01ORM {
    public static void main(String[] args) {
        practice01ORM p=new practice01ORM();
        System.out.println(p.list().size());
        Hero teemo=new Hero(0,"teemo",313.0f,55);
        p.add(teemo);
        System.out.println(p.list().size());
        Hero h1=new Hero(7,"jie",500.0f,50);
        p.update(h1);
        System.out.println(p.list().size());
    }
    public practice01ORM(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
    }
    public void add(Hero h){
        String add="insert into hero values(null,?,?,?)";
        try(Connection c= getConnection();
            PreparedStatement ps=c.prepareStatement(add);
                ){
            ps.setString(1,h.name);
            ps.setFloat(2,h.hp);
            ps.setInt(3,h.damage);
            ps.execute();
            System.out.println("新加了一条数据");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void delete(Hero h){
        String delete="delete from hero where id=?";
        try(Connection c=getConnection();
            PreparedStatement ps=c.prepareStatement(delete);
                ){
            ps.setInt(1,h.id);
            ps.execute();
            System.out.println("成功删除id="+h.id+"的数据!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void update(Hero h){
        String update="update hero set name=?,hp=?,damage=? where id = ?";
        try(Connection c=getConnection();
            PreparedStatement ps=c.prepareStatement(update);
                ){
            ps.setString(1,h.name);
            ps.setFloat(2,h.hp);
            ps.setInt(3,h.damage);
            ps.setInt(4,h.id);
            int i=ps.executeUpdate();
            if(i!=0){
                System.out.println("更新成功");
            }else{
                System.out.println("更新失败");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Hero> list(){
        List<Hero> list=new ArrayList<>();
        String sql="select * from hero";
        try(Connection c= getConnection();
            PreparedStatement ps=c.prepareStatement(sql);
        ){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                float hp=rs.getFloat(3);
                int damage=rs.getInt(4);
                Hero h=new Hero();
                h.id=id;
                h.name=name;
                h.hp=hp;
                h.damage=damage;
                list.add(h);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
