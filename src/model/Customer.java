package model;

public class Customer {
    public String Cno;
    public String Cname;
    public String Csex;
    public Double Cconsumption;


    public Customer() {
    }

    public Customer(String cno, String cname, String csex, Double cconsumption) {
        Cno = cno;
        Cname = cname;
        Csex = csex;
        Cconsumption = cconsumption;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCsex() {
        return Csex;
    }

    public void setCsex(String csex) {
        Csex = csex;
    }

    public Double getCconsumption() {
        return Cconsumption;
    }

    public void setCconsumption(Double cconsumption) {
        Cconsumption = cconsumption;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Cno='" + Cno + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Csex='" + Csex + '\'' +
                ", Cconsumption=" + Cconsumption +
                '}';
    }
}
