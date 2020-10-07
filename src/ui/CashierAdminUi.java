package ui;
import dao.impl.CashierAdminDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashierAdminUi extends JFrame implements ActionListener {
    JPanel jp1,jp2,jp3;//面板
    JLabel jlb1,jlb2;//标签
    JButton jb1,jb2,jb3;//按钮
    JTextField jtf;//文本框
    JPasswordField jpf;//密码框
    //调用对应的dao层的impl方法
    CashierAdminDaoImpl s1=new CashierAdminDaoImpl();
    public static void main(String[] args) {
        CashierAdminUi cashierAdminUi=new CashierAdminUi();
    }
    public CashierAdminUi(){
        //背景图
        ImageIcon image1= null;
        try {
            image1 = new ImageIcon("D:\\IdeaProjects\\swing-supermarket\\src\\img2\\3.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JLabel label=new JLabel(image1);
        label.setBounds(0,0,image1.getIconWidth(),image1.getIconHeight());
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        //必须加
        JPanel j=(JPanel)this.getContentPane();
        j.setOpaque(false);
        //创建按钮组件
        jb1=new JButton("登录");
        jb2=new JButton("重置");
        jb3=new JButton("退出");
        //设置监听
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        //创建面板对象
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        JPanel jPanel=new JPanel();
        //创建标签何文本框
        jlb1=new JLabel("收银员登录名:");
        jlb2=new JLabel("登录密码:");
        //给标签设置大小
        jlb1.setFont(new Font("Dialog",1,17));
        jlb2.setFont(new Font("Dialog",1,17));
        jtf=new JTextField(10);
        jpf=new JPasswordField(10);
        //将组件添加到对应面板中
        jp1.add(jlb1);
        jp1.add(jtf);
        jp2.add(jlb2);
        jp2.add(jpf);
        jp3.add(jb1);
        jp3.add(jb2);
        jp3.add(jb3);
        //把面板设为透明，才能让背景图显示
        jPanel.setOpaque(false);
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        jp3.setOpaque(false);
        this.add(jPanel);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        //使用网格布局
        this.setLayout(new GridLayout(5,1));
        //窗口设置标题
        this.setTitle("超市收银系统");
        //设置窗口大小
        this.setSize(450,450);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //显示窗口  不要自由放大
        this.setVisible(true);
        this.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="退出"){
            //如果不行用 this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            System.exit(0);
        }
        else if(e.getActionCommand()=="重置"){
            this.clear();
        }
        else if (e.getActionCommand()=="登录"){
            if (!jtf.getText().isEmpty() && !String.valueOf(jpf.getPassword()).isEmpty()){
                s1.LoginQuery(jtf.getText(), String.valueOf(jpf.getPassword()));
                login();
            }
            else if(jtf.getText().isEmpty()){
                //试试吧null换成this
                JOptionPane.showMessageDialog(null,
                        "请输入收银员登录名!",
                        "警告信息",
                        JOptionPane.WARNING_MESSAGE);
                this.clear();
            }
            else if(String.valueOf(jpf.getPassword()).isEmpty()){
                //试试吧null换成this
                JOptionPane.showMessageDialog(null,
                        "请输入收银员密码!",
                        "警告信息",
                        JOptionPane.WARNING_MESSAGE);
                this.clear();
            }
        }
    }
    private void login() {
        if (s1.admin.getClogpassword()!=null){
            System.out.println("登录成功!CashierAdminUi");
            JOptionPane.showMessageDialog(null,
                    "登陆成功!",
                    "提示信息",
                    JOptionPane.WARNING_MESSAGE);
            this.clear();
            //销毁当前页面
            dispose();
            QueryCardUi querycard1=new QueryCardUi();
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "用户名或者密码错误!",
                    "警告信息",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    private void clear() {
        //清除输入框
        jtf.setText("");
        jpf.setText("");
    }
}
