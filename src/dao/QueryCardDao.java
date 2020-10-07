package dao;

public interface QueryCardDao {
    //查会员卡号有无
    public void QueryCard(String custom_cardnum);

    //注册会员卡号
    public void registerCard(String register_cardnum,String name,String sex);

    //从cashidesk更新会员的消费记录
    public void updateCconsumption(double Cconsumption1,String Cno1);

}
