package dao.impl;

import dao.CashdeskDao;
import model.Tradeinfo;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashdeskDaoImpl implements CashdeskDao{
    public Connection conn =null;
    public PreparedStatement pstmt =null;
    public ResultSet rs =null;

    @Override
    public void add(Tradeinfo c) {
        try {
            conn = JDBCUtils.getConnection();
            String sql="INSERT into Tradeinfo VALUES (?,?,?)";
            pstmt = conn.prepareStatement(sql);
            //给?赋值
            //1,2代表？的位置,从1开始
            pstmt.setString(1,c.Cno);
            pstmt.setString(2,c.Gno);
            pstmt.setString(3, String.valueOf(c.Tquantity));
            //再执行sql
            int count = pstmt.executeUpdate();
            //System.out.println("数据库cashdesk准备添加数据到数据库成功"+count);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
    }
}
