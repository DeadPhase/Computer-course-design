package 界面;

import java.awt.Graphics;

public class Jpanel {
	public int n;
	
	/**
	 * 绘制一个由 x 和 y 坐标数组定义的闭合多边形。每对 (x, y) 坐标定义一个点。
	 */
	public int x[] = null;
	public int y[] = null;
//画图多边形drawPolygon()方法使用三个参数，前两个参数为两个坐标点数组，第三个参数为坐标点的个数
	public void paintComponent(Graphics g) {
		g.drawPolygon(x, y, n);
	}

	public void paintComponent1(Graphics g) {
		g.drawOval(x[0], x[1], x[2], x[3]);
	}
}