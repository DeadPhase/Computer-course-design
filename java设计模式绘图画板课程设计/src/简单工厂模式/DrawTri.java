package �򵥹���ģʽ;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ����.Jpanel;

public class DrawTri implements Draw {
	public void draw(Graphics g, int x, int y, int x1, int y1, Color C){
		Jpanel jp = new Jpanel(); 
		//�滭������
		jp.n = 3;
		jp.x = new int[]{x, x1, x};
		jp.y = new int[]{y, y1, y1};
		g.setColor(C);
		jp.paintComponent(g);
		}


	@Override
	public void draw(Graphics g, int x, int y, int x1,int y1, Color c,
			float size) {
			float lineWidth = size;
			((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
			Jpanel jp = new Jpanel();
			jp.n = 3;
			jp.x = new int[]{x, x1, x};
			jp.y = new int[]{y, y1, y1};
			g.setColor(c); 
			jp.paintComponent(g);
	}

}