package 界面;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.F ont;
//import java.awt.Graphics;
//import java awtMouseInfo;
//import java awt.Point;
//import java awt.event.ActionEvent;
//import java.awt.event ActionListener;
//import java awt.event.MouseAdapter;
//import java.awt.event
//import java.awt.event.ActionEvent;
//import java.awt.event.MouseListener;
//import java.io.File;
//import javax swing BorderF actory;
//import javax swing.ImageIcon;
//import javax.swing
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//import com.sun.glass.events.MouseEvent;
//
//import javax swing JColorChooser;
//import javax swing JF ileChooser,
//import javax swing JFrame;
//import javax swing JPanel1;
//import javax swing JTextArea;
//import javax swing JTextField;
//import javax swing.filechooser.F ileNameExtensionF ilter;import简单工厂模式*;import装饰模式*;import桥接模式*;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import 桥接模式.BigPen;
import 桥接模式.Small;
import 桥接模式.Big;
import 桥接模式.smallpen;
import 桥接模式.size;
import 桥接模式.Pen;
import 简单工厂模式.Draw;
import 简单工厂模式.proFactory;
import 装饰模式.Conpotent;
import 装饰模式.Oval;
import 装饰模式.Oval_Oval;
import 装饰模式.Rec;
import 装饰模式.Rec_Rec;
import 装饰模式.Tri;
import 装饰模式.Tri_Tri;

public class MainFrame extends JFrame {
	private JPanel downPanel = new JPanel();
	private Color c = Color.black;
	int x = 0;
	int y = 0;
	int x1 = 0;
	int y1 = 0;
	String name;
	String name2;
	int flag = 0;
	Conpotent con;
	Pen pen;
	File[] selectedFiles = null;
	int fileIndex = 0;
	int width = 200;
	int height = 200;

	public MainFrame() {
		initUI();
		showFrame();
	}

//初始化界面显示组件
	private void initUI() {
		final JTextField jtfGrap = new JTextField("图形");
		final JTextField jtfModi = new JTextField("装饰");
		final JTextField jtfStra = new JTextField("桥接 ");
		final JButton jbtColo = new JButton("颜色");
		final JButton jbtTria = new JButton("三角形");
		final JButton jbtRoun = new JButton("圆形");
		final JButton jbtRect = new JButton("矩形");
		final JButton jbtClea = new JButton("清空");

		final JButton jbtMod1 = new JButton("三角形");
		final JButton jbtMod2 = new JButton("圆形");
		final JButton jbtMod3 = new JButton("矩形");
		final JButton jbtMod4 = new JButton("移动");

		final JButton jbtStr1 = new JButton("粗红毛笔");
		final JButton jbtStr2 = new JButton("粗绿毛笔");
		final JButton jbtStr3 = new JButton("细红毛笔");
		final JButton jbtStr4 = new JButton("细绿毛笔");
		final JPanel jpPanel1 = new JPanel();
		final JPanel jpPanel2 = new JPanel();
		final JPanel jpPanel3 = new JPanel();
		jbtColo.setBounds(115, 10, 90, 20);
		jbtColo.setForeground(new Color(153, 217, 234));

		jbtColo.setContentAreaFilled(false);
		jbtColo.setFont(new Font("黑 体", Font.BOLD, 14));
		jtfGrap.setBounds(15, 5, 40, 30);
		jtfGrap.setFont(new Font("黑 体", Font.BOLD, 14));
		jtfGrap.setBorder(null);

		jtfGrap.setEditable(false);
		jtfGrap.setOpaque(false);
		jbtTria.setBounds(15, 35, 90, 40);
		jbtTria.setForeground(new Color(153, 217, 234));

		jbtTria.setContentAreaFilled(false);
		jbtTria.setFont(new Font("黑 体", Font.BOLD, 14));
		jbtRoun.setBounds(115, 35, 90, 40);
		jbtRoun.setForeground(new Color(153, 217, 234));
		jbtRoun.setContentAreaFilled(false);
		jbtRoun.setFont(new Font("黑 体", Font.BOLD, 14));
		jbtRect.setBounds(15, 85, 90, 40);

		jbtRect.setForeground(new Color(153, 217, 234));

		jbtRect.setContentAreaFilled(false);
		jbtRect.setFont(new Font("黑 体", Font.BOLD, 14));
		jtfModi.setBounds(15, 5, 40, 30);
		jtfModi.setFont(new Font("黑 体", Font.BOLD, 14));

		jtfModi.setBorder(null);

		jtfModi.setEditable(false);
		jtfModi.setOpaque(false);
		jbtMod1.setBounds(15, 35, 90, 40);

		jbtMod1.setForeground(new Color(153, 217, 234));

		jbtMod1.setContentAreaFilled(false);

		jbtMod1.setFont(new Font("黑体", Font.BOLD, 14));

		jbtMod2.setBounds(115, 35, 90, 40);

		jbtMod2.setForeground(new Color(153, 217, 234));
		jbtMod2.setContentAreaFilled(false);
		jbtMod2.setFont(new Font("黑体", Font.BOLD, 14));
		jbtMod3.setBounds(15, 85, 90, 40);

		jbtMod3.setForeground(new Color(153, 217, 234));
		jbtMod3.setContentAreaFilled(false);
		jbtMod3.setFont(new Font("黑体", Font.BOLD, 14));
		jbtMod4.setBounds(115, 85, 90, 40);

		jbtMod4.setForeground(new Color(153, 217, 234));

		jbtMod4.setContentAreaFilled(false);
		jbtMod4.setFont(new Font("黑体", Font.BOLD, 14));
		jtfStra.setBounds(15, 5, 40, 30);

		jtfStra.setFont(new Font("黑体", Font.BOLD, 14));
		jtfStra.setBorder(null);
		jtfStra.setEditable(false);
		jtfStra.setOpaque(false);
		jbtStr1.setBounds(15, 35, 90, 40);

		jbtStr1.setForeground(new Color(153, 217, 234));

		jbtStr1.setContentAreaFilled(false);
		jbtStr1.setFont(new Font("黑 体", Font.BOLD, 14));
		jbtStr2.setBounds(115, 35, 90, 40);

		jbtStr2.setForeground(new Color(153, 217, 234));
		jbtStr2.setContentAreaFilled(false);
		jbtStr2.setFont(new Font("黑 体", Font.BOLD, 14));
		jbtStr3.setBounds(115, 85, 90, 40);

		jbtStr3.setForeground(new Color(153, 217, 234));

		jbtStr3.setContentAreaFilled(false);
		jbtStr3.setFont(new Font("黑 体", Font.BOLD, 14));
		jbtStr4.setBounds(15, 85, 90, 40);

		jbtStr4.setForeground(new Color(153, 217, 234));

		jbtStr4.setContentAreaFilled(false);
		jbtStr4.setFont(new Font("黑 体", Font.BOLD, 14));
		jbtClea.setBounds(620, 10, 70, 20);

		jbtClea.setForeground(new Color(153, 217, 234));

		jbtClea.setContentAreaFilled(false);
		jbtClea.setFont(new Font("黑 体", Font.BOLD, 14));
//监听器
		jbtClea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downPanel.removeAll();
				downPanel.add(jbtClea);
				downPanel.update(downPanel.getGraphics());
			}
		});

//画图区域

		downPanel.setBounds(260, 15, 700, 627);
		downPanel.setBackground(Color.WHITE);

		downPanel.setLayout(null);
		downPanel.add(jbtClea);

		jpPanel1.setBorder(BorderFactory.createLineBorder(getForeground()));
		jpPanel1.setBounds(20, 15, 222, 142);
		jpPanel1.setBackground(Color.WHITE);
		jpPanel1.setLayout(null);
		jpPanel1.add(jbtTria);
		jpPanel1.add(jbtRoun);
		jpPanel1.add(jbtRect);
		jpPanel1.add(jbtColo);
		jpPanel1.add(jtfGrap);

		jpPanel2.setBorder(BorderFactory.createLineBorder(getForeground()));
		jpPanel2.setBounds(20, 177, 222, 142);
		jpPanel2.setBackground(Color.WHITE);
		jpPanel2.setLayout(null);
		jpPanel2.add(jbtMod1);
		jpPanel2.add(jbtMod2);
		jpPanel2.add(jbtMod3);
		jpPanel2.add(jbtMod4);
		jpPanel2.add(jtfModi);

		jpPanel3.setBorder(BorderFactory.createLineBorder(getForeground()));
		jpPanel3.setBounds(20, 339, 222, 142);
		jpPanel3.setBackground(Color.WHITE);
		jpPanel3.setLayout(null);
		jpPanel3.add(jbtStr1);
		jpPanel3.add(jbtStr2);
		jpPanel3.add(jbtStr3);
		jpPanel3.add(jbtStr4);
		jpPanel3.add(jtfStra);
		this.add(downPanel);
		this.add(jpPanel1);
		this.add(jpPanel2);
		this.add(jpPanel3);
////

		jbtRoun.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				name = "圆形";
			}
		});
//监听器
		jbtRect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				name = "矩形";
			}
		});

		jbtTria.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				name = "三角 形";
			}
		});

		jbtColo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				c = JColorChooser.showDialog(null, "Choose a Color", jbtColo.getForeground());
			}
		});

		jbtMod1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name2 = "三角形";
				if (name != null && name.equals("矩形")) {
					flag = 1;
				} else if (name != null && name.equals("圆形")) {
					flag = 2;
				} else if (name != null && name.equals("三角形")) {
					flag = 3;
				}
			}

		});

		jbtMod2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name2 = "圆形";
				if (name != null && name.equals("矩形")) {
					flag = 4;
				} else if (name != null && name.equals("三角形")) {
					flag = 5;
				} else if (name != null && name.equals("圆形")) {
					flag = 6;
				}
			}
		});

		jbtMod3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name2 = "矩形";
				if (name != null && name.equals("矩形")) {
					flag = 7;
				} else if (name != null && name.equals("圆形")) {
					flag = 8;
				} else if (name != null && name.equals("三角形")) {
					flag = 9;
				}
			}
		});

		jbtMod4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				flag = -1;
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "ipg", "gif");
				// 设置文件类型
				chooser.setFileFilter(filter);

				// 打开选择器面板
				int returmVal = chooser.showOpenDialog(downPanel);
				if (returmVal == JFileChooser.APPROVE_OPTION) {
					selectedFiles = chooser.getSelectedFiles();
					repaint();
				}

			}
		});

		jbtStr1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				c = Color.red;
				flag = 10;
			}

		});
		jbtStr2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				c = Color.green;
				flag = 10;
			}

		});
		jbtStr3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				c = Color.red;
				flag = 11;
			}

		});
		jbtStr4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				c = Color.green;
				flag = 11;
			}

		});

		downPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				x1 = e.getX();
				y1 = e.getY();
				Graphics g = downPanel.getGraphics();
				// System.out.println( "name="+name);
				switch (flag) {
				case 0:
					Draw d = proFactory.produce(name);
					d.draw(g, x, y, x1, y1, c);
					break;
				case 1:
					con = new Rec();
					con.paint(g, x, y, x1, y1, c);
					break;
				case 2:
					con = new Oval();
					con.paint(g, x, y, x1, y1, c);
					break;
				case 3:
					con = new Tri();
					con.paint(g, x, y, x1, y1, c);
					break;
				case 4:
					con = new Rec();
					con.paint(g, x, y, x1, y1, c);
					break;
				case 5:
					con = new Tri();
					con.paint(g, x, y, x1, y1, c);
					break;
				case 6:
					con = new Oval();
					con.paint(g, x, y, x1, y1, c);
					break;
				case 7:
					con = new Rec();
					con.paint(g, x, y, x1, y1, c);
					break;
				case 8:
					con = new Oval();
					con.paint(g, x, y, x1, y1, c);
					break;
				case 9:
					con = new Tri();
					con.paint(g, x, y, x1, y1, c);
					break;
				}

				if (flag == 1 || flag == 2 || flag == 3) {
					Tri_Tri tr = new Tri_Tri(con);
					tr.paint(g, x, y, x1, y1, c);
					flag = 0;
				}
				if (flag == 4 || flag == 5 || flag == 6) {
					Oval_Oval tr = new Oval_Oval(con);
					tr.paint(g, x, y, x1, y1, c);
					flag = 0;
				}

				if (flag == 7 || flag == 8 || flag == 9) {
					Rec_Rec tr = new Rec_Rec(con);
					tr.paint(g, x, y, x1, y1, c);
					flag = 0;
				}
				
				if (flag == 10) {
					//桥接，注入新的属性尺寸大小
					size s = new Big();
					float size;
					size = s.bepaint();
					Pen p = new BigPen();
					p.draw(name, g, x, y, x1, y1, c, size);
				}

				if (flag == 11) {
					size s = new Small();
					float size;
					size = s.bepaint();
					Pen p = new BigPen();
					p.draw(name, g, x, y, x1, y1, c, size);
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
			}

		});
	}

	public void showFrame() {
		this.setTitle("DrawSomething");
		this.setLayout(null);
		this.setSize(1000, 700);
		this.setLocation(600, 300);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
