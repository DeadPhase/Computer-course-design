package 简单工厂模式;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import 界面.Jpanel;

public class DrawRec implements Draw {
	public void draw(Graphics g, int x, int y, int x1, int y1, Color c) {
		Jpanel jp = new Jpanel();
		//绘画矩形
		jp.n=4;
//		 int X[] = {10,180,90,110,130,50};   //顶点X坐标
//		   int Y[] = {15,55,95,115,135,180};   //顶点Y坐标
		
		jp.x = new int[]{x, x1, x1, x};
		jp.y = new int[]{y, y, y1, y1};
		g.setColor(c); 
		jp.paintComponent(g);
	}

	@Override
	public void draw(Graphics g, int x, int y, int x1, int y1, Color C,
			float size) {
			float lineWidth = size;
			((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
			Jpanel jp = new Jpanel( );
			jp.n = 4;
			jp.x = new int[]{x, x1, x1, x};
			jp.y = new int[]{y, y, y1, y1};
			g.setColor(C);
			jp.paintComponent(g);

}
}