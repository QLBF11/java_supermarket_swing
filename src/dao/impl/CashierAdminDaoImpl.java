package dao.impl;

import dao.CashierAdminDao;
import model.CashierAdmin;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierAdminDaoImpl implements CashierAdminDao{
    //调用CashierAdmin的model,要设为public，要不然其他包看不到
    public CashierAdmin admin=new CashierAdmin();
    //收银员登录验证
    @Override
    public void LoginQuery(String logname, String logpassword) {
        Connection conn =null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from cashier where Clogname=? and Clogpassword=?";
            pstmt = conn.prepareStatement(sql);
            //System.out.println("数据库连接成功");
            //给?赋值
            //1,2代表？的位置,从1开始
            pstmt.setString(1,logname);
            pstmt.setString(2,logpassword);
            //再执行sql
            rs = pstmt.executeQuery();

            while (rs.next()){
                //从数据库(从1开始)中得到的结果集,保存在成员变量中,
                admin.Cname=rs.getString(4);
                admin.Clogpassword=rs.getString(5);
                //System.out.println("获得的收银员登录名为:"+admin.Cname+"  登录密码为:"+admin.Clogpassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }

    }


}
