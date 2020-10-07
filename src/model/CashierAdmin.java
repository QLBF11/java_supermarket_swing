package model;

public class CashierAdmin {
    public String Cno;
    public String Cname;
    public String Csex;
    public String Clogname;
    public String Clogpassword;

    public CashierAdmin() {
    }

    public CashierAdmin(String cno, String cname, String csex, String clogname, String clogpassword) {
        Cno = cno;
        Cname = cname;
        Csex = csex;
        Clogname = clogname;
        Clogpassword = clogpassword;
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

    public String getClogname() {
        return Clogname;
    }

    public void setClogname(String clogname) {
        Clogname = clogname;
    }

    public String getClogpassword() {
        return Clogpassword;
    }

    public void setClogpassword(String clogpassword) {
        Clogpassword = clogpassword;
    }

    @Override
    public String toString() {
        return "CashierAdmin{" +
                "Cno='" + Cno + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Csex='" + Csex + '\'' +
                ", Clogname='" + Clogname + '\'' +
                ", Clogpassword='" + Clogpassword + '\'' +
                '}';
    }
}
