package 装饰模式;

import java.awt.Color;
import java.awt.Graphics;
import 简单工厂模式.DrawRec;

public class Rec extends DrawRec implements Conpotent {
	public void paint(Graphics g, int x, int y, int x1, int y1, Color c) {
		DrawRec rec = new DrawRec();
		rec.draw(g, x, y, x1, y1, c);
	}
}
