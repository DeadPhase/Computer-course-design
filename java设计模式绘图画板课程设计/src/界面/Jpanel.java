package ����;

import java.awt.Graphics;

public class Jpanel {
	public int n;
	
	/**
	 * ����һ���� x �� y �������鶨��ıպ϶���Ρ�ÿ�� (x, y) ���궨��һ���㡣
	 */
	public int x[] = null;
	public int y[] = null;
//��ͼ�����drawPolygon()����ʹ������������ǰ��������Ϊ������������飬����������Ϊ�����ĸ���
	public void paintComponent(Graphics g) {
		g.drawPolygon(x, y, n);
	}

	public void paintComponent1(Graphics g) {
		g.drawOval(x[0], x[1], x[2], x[3]);
	}
}