package dao.impl;

import dao.GoodsDao;
import model.Goods;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsDaoImpl implements GoodsDao {
    //创建Goods实体类来保存查询到的结果
    public Goods goods=new Goods();

    public Connection conn =null;
    public PreparedStatement pstmt =null;
    public ResultSet rs =null;
    @Override
    public void QueryGno(String Gnonum) {
        try {
            conn = JDBCUtils.getConnection();
            String sql="select * from Goods where Gno=?";
            pstmt = conn.prepareStatement(sql);
            //System.out.println("数据库查询Goods号成功");
            //给?赋值
            //1,2代表？的位置,从1开始
            pstmt.setString(1,Gnonum);
            //再执行sql
            rs = pstmt.executeQuery();

            while (rs.next()){
                //从数据库(从1开始)中得到的结果集,保存在成员变量中,
                goods.Gno=rs.getString(1);
                goods.Gname=rs.getString(2);
                goods.Gprice=rs.getDouble(3);
                goods.Gstorage=rs.getInt(4);
                System.out.println("获得商品货号为:"+goods.Gno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
    }


    //根据顾客会员号更新库存
    @Override
    public void updateGstorage(String buynum, String Gno2) {
        try {
            conn = JDBCUtils.getConnection();
            String sql="UPDATE Goods set Gstorage=Gstorage-? WHERE Gno=?";
            pstmt = conn.prepareStatement(sql);
//            System.out.println("数据库Goods连接成功");
            //给?赋值
            //1,2代表？的位置,从1开始
            pstmt.setString(1,buynum);
            pstmt.setString(2,Gno2);
            //再执行sql
            int i = pstmt.executeUpdate();
           // System.out.println("更改库存"+i);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
    }
}
