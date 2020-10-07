package dao.impl;

import dao.QueryCardDao;
import model.Customer;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCardDaoImpl implements QueryCardDao {

    public Customer customer=new Customer();
    public Connection conn =null;
    public PreparedStatement pstmt =null;
    public ResultSet rs =null;

    //查会员卡号有无
    @Override
    public void QueryCard(String custom_cardnum) {
        //调用Customer的model

        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from Customer where Cno=?";
            pstmt = conn.prepareStatement(sql);
            //System.out.println("数据库2连接成功");
            //给?赋值
            //1,2代表？的位置,从1开始
            pstmt.setString(1,custom_cardnum);
            //再执行sql
            rs = pstmt.executeQuery();
            while (rs.next()){
                //从数据库(从1开始)中得到的结果集,保存在成员变量中,
                customer.Cno=rs.getString(1);
                System.out.println("获得顾客会员卡号为:"+customer.Cno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
    }

    //注册会员卡号
    @Override
    public void registerCard(String register_cardnum, String name, String sex) {
        try {
            conn = JDBCUtils.getConnection();
            String sql="INSERT INTO Customer(Cno,Cname,Csex,Cconsumption) VALUES (?,?,?,0)";
            pstmt = conn.prepareStatement(sql);
            //System.out.println("数据库2-注册连接成功");
            //给?赋值
            //1,2代表？的位置,从1开始
            pstmt.setString(1,register_cardnum);
            pstmt.setString(2,name);
            pstmt.setString(3,sex);
            // pstmt.setString(4,0);
            //再执行sql
            int i = pstmt.executeUpdate();
            //System.out.println("执行结果:"+i);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
    }


    //从cashidesk更新会员的消费记录
    @Override
    public void updateCconsumption(double Cconsumption1, String Cno1) {
        try {
            conn = JDBCUtils.getConnection();
            String sql="UPDATE Customer set Cconsumption=Cconsumption+? WHERE Cno=?";
            pstmt = conn.prepareStatement(sql);
            //System.out.println("数据库Goods连接成功");
            //给?赋值
            //1,2代表？的位置,从1开始
            pstmt.setString(1, String.valueOf(Cconsumption1));
            pstmt.setString(2,Cno1);
            //再执行sql
            int i = pstmt.executeUpdate();
            //System.out.println("更改顾客消费记录:"+i);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
    }

}
