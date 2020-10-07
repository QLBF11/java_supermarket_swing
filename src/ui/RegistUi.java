package ui;

import dao.impl.QueryCardDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistUi extends JFrame implements ActionListener {

    JPanel jp1,jp2,jp3,jp4;//面板
    JLabel jlb1,jlb2,jlb3;//标签
    JButton jb1,jb2;//按钮
    JTextField jtf1,jtf2,jtf3;//文本框
    //RegistUi也是调用对应的dao层的impl(QueryCardDaoImpl)类
    QueryCardDaoImpl q2=new QueryCardDaoImpl();
    public RegistUi(){
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
        jb1=new JButton("提交");
        jb2=new JButton("退出");
        //设置监听
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        //创建面板对象
        JPanel jPanel=new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        //创建标签何文本框
        jlb1=new JLabel("注册会员卡号为:");
        jtf1=new JTextField(10);
        jlb2=new JLabel("姓名:");
        jtf2=new JTextField(10);
        jlb3=new JLabel("性别:");
        jtf3=new JTextField(10);
        jlb1.setFont(new Font("Dialog",1,17));
        jlb2.setFont(new Font("Dialog",1,17));
        jlb3.setFont(new Font("Dialog",1,17));
        //将组件添加到1对应面板中
        jp1.add(jlb1);
        jp1.add(jtf1);
        jp2.add(jlb2);
        jp2.add(jtf2);
        jp3.add(jlb3);
        jp3.add(jtf3);
        jp4.add(jb1);
        jp4.add(jb2);
        //设置面板为透明
        jPanel.setOpaque(false);
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        jp3.setOpaque(false);
        jp4.setOpaque(false);
        //添加面板到顶层容器中
        this.add(jPanel);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        //使用网格布局
        this.setLayout(new GridLayout(6,1));
        //窗口设置标题
        this.setTitle("超市收银系统-注册会员卡");
        //设置窗口大小
        this.setSize(450,450);
        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //显示窗口  不可自由放大
        this.setVisible(true);
        this.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "退出") {
            //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            System.exit(0);
        } else if (e.getActionCommand() == "提交") {
            if (!jtf1.getText().isEmpty() && !jtf2.getText().isEmpty() && !jtf3.getText().isEmpty()){
                //1.先用正则表达式判断先
                if (jtf1.getText().matches("^\\d{1,20}$")
                        && jtf2.getText().matches("^[\u4e00-\u9fa5\\w]{1,20}$")
                        && jtf3.getText().matches("[男女]")){
                    //2.先查Cno有没有该卡号
                    q2.QueryCard(jtf1.getText());
                    regist();
                }else{
                    JOptionPane.showMessageDialog(null,
                            "注册信息非法!卡号必须为数字(20长度内);姓名可以字母、数字、汉字及下划线组成,20长度内;性别只能取男或女!",
                            "警告信息",
                            JOptionPane.WARNING_MESSAGE);
                }
            }else if(jtf1.getText().isEmpty() || jtf2.getText().isEmpty() || jtf3.getText().isEmpty()){
                //试试吧null换成this
                JOptionPane.showMessageDialog(null,
                        "请填好注册信息，缺一不可!",
                        "警告信息",
                        JOptionPane.WARNING_MESSAGE);
            }
            }
        }

    private void regist() {
        if (q2.customer.getCno() != null) {
            //查到有卡号就不注册
            JOptionPane.showMessageDialog(null,
                    "该会员卡号已存在，请另取一个会员卡号",
                    "警告信息",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            //没有卡号就注册
            q2.registerCard(jtf1.getText(),jtf2.getText(),jtf3.getText());
            System.out.println("注册"+jtf1.getText()+" 新会员成功!");
            JOptionPane.showMessageDialog(null,
                    "成功注册:"+jtf1.getText()+"新会员，请收银员查询该会员!",
                    "提示信息",
                    JOptionPane.WARNING_MESSAGE);
            //销毁该页面，返回查询会员号页面
            dispose();
        }
    }
}


