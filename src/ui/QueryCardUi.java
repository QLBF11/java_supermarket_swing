package ui;

import dao.impl.QueryCardDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class QueryCardUi extends JFrame implements ActionListener {
    JPanel jp1,jp2;//面板
    JLabel jlb1;//标签
    JButton jb1,jb2,jb3;//按钮
    JTextField jtf;//文本框
    //querycardui调用对应的dao层的impl方法
    QueryCardDaoImpl q1=new QueryCardDaoImpl();
    public QueryCardUi(){
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
        jb1=new JButton("查询");
        jb2=new JButton("注册");
        jb3=new JButton("退出");
        //设置监听
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        //创建面板对象
        JPanel jPanel=new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        //创建标签何文本框
        jlb1=new JLabel("请输入会员卡号:");
        jtf=new JTextField(10);
        jlb1.setFont(new Font("Dialog",1,17));
        //将组件添加到1对应面板中
        jp1.add(jlb1);
        jp1.add(jtf);
        jp2.add(jb1);
        jp2.add(jb2);
        jp2.add(jb3);
        //把面板设为透明
        jPanel.setOpaque(false);
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        this.add(jPanel);
        this.add(jp1);
        this.add(jp2);
        //使用网格布局
        this.setLayout(new GridLayout(3,1));
        //窗口设置标题
        this.setTitle("超市收银系统-查询会员卡");
        //设置窗口大小
        this.setSize(450,450);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //显示窗口  不可自由放大
        this.setVisible(true);
        this.setResizable(false);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "退出") {
            System.exit(0);
        } else if (e.getActionCommand() == "注册") {
            RegistUi registui2=new RegistUi();

            } else if (e.getActionCommand() == "查询") {
                if (!jtf.getText().isEmpty()) {
                    q1.QueryCard(jtf.getText());
                    querynum();

                } else{
                    //试试吧null换成this
                    // if (jtf.getText().isEmpty())
                    JOptionPane.showMessageDialog(null,
                            "请输入顾客会员卡号!",
                            "警告信息",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        private void querynum ()
        {
            if (q1.customer.getCno() != null) {
                System.out.println("查询顾客会员卡成功!QueryCardUi");
                JOptionPane.showMessageDialog(null,
                        "欢迎: " + q1.customer.getCno() + "会员!",
                        "提示信息",
                        JOptionPane.WARNING_MESSAGE);
                //销毁当前页面
                //dispose();
                jtf.setText("");
                CashdeskUi cashdeskUi = new CashdeskUi(q1);
            } else {
                JOptionPane.showMessageDialog(null,
                        "没有该会员，请注册会员!",
                        "警告信息",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

}
