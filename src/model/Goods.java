package model;

public class Goods {
    public String Gno;
    public String Gname;
    public Double Gprice;
    public Integer Gstorage;

    public Goods() {
    }

    public Goods(String gno, String gname, Double gprice, Integer gstorage) {
        Gno = gno;
        Gname = gname;
        Gprice = gprice;
        Gstorage = gstorage;
    }

    public String getGno() {
        return Gno;
    }

    public void setGno(String gno) {
        Gno = gno;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public Double getGprice() {
        return Gprice;
    }

    public void setGprice(Double gprice) {
        Gprice = gprice;
    }

    public Integer getGstorage() {
        return Gstorage;
    }

    public void setGstorage(Integer gstorage) {
        Gstorage = gstorage;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "Gno='" + Gno + '\'' +
                ", Gname='" + Gname + '\'' +
                ", Gprice=" + Gprice +
                ", Gstorage=" + Gstorage +
                '}';
    }
}
