package 简单工厂模式;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import 界面.Jpanel;

public class DrawOval implements Draw {
	public void draw(Graphics g, int x1, int y1, int x2, int y2, Color c) {
		Jpanel jp = new Jpanel();
		jp.n = 3;
		jp.x = new int[] { x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, (x2 - x1) > 0 ? (x2 - x1) : (x1 - x2),
				(y2 - y1) > 0 ? (y2 - y1) : (y1 - y2) };
		g.setColor(c);
		jp.paintComponent1(g);
	}

	@Override
	public void draw(Graphics g, int x1, int y1, int x2, int y2, Color c, float size) {
		// TODO 自动生成的方法存根
		float lineWidth = size;
		((Graphics2D) g).setStroke(new BasicStroke(lineWidth));
		Jpanel jp = new Jpanel();
		jp.n = 3;
		jp.x = new int[] { x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, (x2 - x1) > 0 ? (x2 - x1) : (x1 - x2),
				(y2 - y1) > 0 ? (y2 - y1) : (y1 - y2) };
		g.setColor(c);
		jp.paintComponent1(g);

	}
}
