package model;

public class Tradeinfo {
    //交易信息表的model
    public String Cno;
    public String Gno;
    public Integer Tquantity;
    public Tradeinfo() {
    }

    public Tradeinfo(String cno, String gno, Integer tquantity) {
        Cno = cno;
        Gno = gno;
        Tquantity = tquantity;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getGno() {
        return Gno;
    }

    public void setGno(String gno) {
        Gno = gno;
    }

    public Integer getTquantity() {
        return Tquantity;
    }

    public void setTquantity(Integer tquantity) {
        Tquantity = tquantity;
    }
}
