package dao;

import model.Tradeinfo;

import java.util.List;

public interface CashdeskDao {
    //往数据库交易信息表添加记录
    public void add(Tradeinfo c);

}
