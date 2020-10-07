package ui;

import dao.impl.CashdeskDaoImpl;
import dao.impl.GoodsDaoImpl;
import dao.impl.QueryCardDaoImpl;
import model.Tradeinfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class CashdeskUi extends JFrame{
        JLabel label1,label2;
        JTextField jtf1,jtf2;
        DefaultTableModel model;
        JTable table;
        JScrollPane scrollPane;
        JButton submitButton,cancelButton,queryButton;
        //保存在交易信息表的数据库
        CashdeskDaoImpl c1=new CashdeskDaoImpl();

        //连接Goods的数据库
        //querycard_sql q1=new querycard_sql();

//    public static void main(String[] args) {
//        cashdesk c=new cashdesk();
//    }
        public CashdeskUi(QueryCardDaoImpl qq){

            //背景图
            ImageIcon image1= null;
            try {
                image1 = new ImageIcon("D:\\IdeaProjects\\swing-supermarket\\src\\img2\\8.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
            JLabel label=new JLabel(image1);
            label.setBounds(0,0,image1.getIconWidth(),image1.getIconHeight());
            this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
            //必须加
            JPanel j=(JPanel)this.getContentPane();
            j.setOpaque(false);
            //添加组件
            label1=new JLabel("商品货号:");
            label1.setBounds(25,250,80,30);
            label1.setFont(new Font("Dialog",1,17));
            jtf1=new JTextField(10);
            jtf1.setBounds(105, 250, 100, 25);

            label2=new JLabel("购买数量:");
            label2.setBounds(25,300,80,30);
            label2.setFont(new Font("Dialog",1,17));
            jtf2 = new JTextField(10);
            jtf2.setBounds(105, 300, 100, 25);


            submitButton=new JButton("提交");
            submitButton.setBounds(335,300,100,30);

            String xiaoji=String.valueOf(0);
            JLabel label3=new JLabel("小计:");
            label3.setBounds(340,245,80,30);
            label3.setFont(new Font("Dialog",1,16));

            //设置小计
            JLabel label4=new JLabel(xiaoji);
            label4.setBounds(380,245,120,30);
            label4.setFont(new Font("Dialog",1,16));

            //表格中的数据
            Vector<Vector<Object>> rowDatas=new Vector<>();
            Vector<Object> columnNames=new Vector<>();
            columnNames.add("商品货号");
            columnNames.add("购买数量");
            columnNames.add("小计");

            model=new DefaultTableModel(rowDatas,columnNames);
            table=new JTable(model);
            scrollPane=new JScrollPane(table);
            scrollPane.setBounds(20,30,450,200);

            //把面板设为透明
            scrollPane.setOpaque(false);
            table.setOpaque(false);
            label1.setOpaque(false);
            label2.setOpaque(false);
            this.add(scrollPane);
            this.add(label1);
            this.add(jtf1);
            this.add(label2);
            this.add(jtf2);
            this.add(submitButton);
            this.add(label3);
            this.add(label4);
            final double[] xiaoji2 = {0};
            //把输入的数据往数据库添加,同时忘表格添加
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(jtf1.getText().isEmpty() || jtf2.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "请输入商品货号和数量!",
                                "警告信息",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        //非空
                        //把g1设为成员变量，要不然换一个错的货号在后面打的话会报错
                        GoodsDaoImpl g1=new GoodsDaoImpl();
                        g1.QueryGno(jtf1.getText());
                        if (g1.goods.getGno() != null) {
                            //如果有该商品，则查询库存量，有再卖
                            if (Integer.parseInt(String.valueOf(g1.goods.getGstorage())) >= Integer.parseInt(jtf2.getText()))
                            {
                                System.out.println("商品货号:"+g1.goods.getGno()+g1.goods.getGname()+",库存充足!");
                                JOptionPane.showMessageDialog(null,
                                        "成功购买:"+g1.goods.getGno()+"号"+Integer.parseInt(jtf2.getText())+"件"+
                                                g1.goods.getGname()+"商品",
                                        "提示信息",
                                        JOptionPane.WARNING_MESSAGE);

                                //购买并显示在表格上;
                                String Gno1=jtf1.getText();
                                Integer Tquantity1= Integer.valueOf(jtf2.getText());
                                Tradeinfo c2=new Tradeinfo();
                                //传入消费记录
                                c2.setCno(qq.customer.getCno());
                                c2.setGno(Gno1);
                                c2.setTquantity(Tquantity1);
                                c1.add(c2);
                                //应该把Goods 对象为g1的库存减去Tquantity
                                g1.updateGstorage(Tquantity1.toString(),g1.goods.getGno());

                                Vector<Object> vec=new Vector<>();
                                vec.addElement(Gno1);
                                vec.addElement(Tquantity1);
                                //小计为Tquantity1*g1.getPrice,更新到表格中
                                double buynum=Double.parseDouble(Tquantity1.toString());
                                double price=Double.parseDouble(g1.goods.getGprice().toString());
                                double trip=buynum*price;
                                vec.addElement(trip);
                                //把小计的金额加入到顾客表(对象为qq)的消费金额里面
                                qq.updateCconsumption(trip,qq.customer.getCno());
                                model.addRow(vec);

                                //更新小计
                                xiaoji2[0] +=trip;
                                label4.setText(String.valueOf(xiaoji2[0]));
                                jtf1.setText("");
                                jtf2.setText("");



                            }else {
                                JOptionPane.showMessageDialog(null,
                                        "购买失败!!! "+g1.goods.getGno()+"号商品,库存不足!紧剩余"+g1.goods
                                        .getGstorage()+"件",
                                        "警告信息",
                                        JOptionPane.WARNING_MESSAGE);
                                jtf1.setText("");
                                jtf2.setText("");
                            }
                        }
                         else {
                        JOptionPane.showMessageDialog(null,
                                "没有该商品，请输入正确的商品货号!",
                                "警告信息",
                                JOptionPane.WARNING_MESSAGE);
                        }


                    }


                }
            });

            this.setLayout(null);
            this.setTitle("收银界面");
            this.setSize(500, 500);
            this.setLocationRelativeTo(null);// 设置居中显示
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.setResizable(false);

        }

}
