package 装饰模式;

import java.awt.Color;
import java.awt.Graphics;
import 界面.Jpanel;

public class Tri_Tri extends Decorator {
	public Tri_Tri(Conpotent conpotent) {
		super(conpotent);
	}

public void paint(Graphics g, int x1, int y1, int x2, int y2, Color c) {
Tri tr = new Tri(); 
tr.draw(g,x1+(x2-x1)/4, y1+(y2-y1)/4, x2-(x2-x1)/4, y2-(y2-y1)/4,c );
}
}
