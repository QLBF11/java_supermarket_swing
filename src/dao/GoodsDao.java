package dao;

public interface GoodsDao {

    //查询有没该货号
    public void QueryGno(String Gnonum);

    //根据顾客会员号更新库存
    public void updateGstorage(String buynum,String Gno2);
}
