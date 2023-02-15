package в╟йндёй╫;

import java.awt.Color;
import java.awt.Graphics;

public class Oval_Oval extends Decorator {

	public Oval_Oval(Conpotent conpotent) {
		super(conpotent);
	}

	public void paint(Graphics g, int x1, int y1, int x2, int y2, Color c) {
		Oval ova = new Oval();
		ova.draw(g, 1 + (x2 - x1) / 4, y1 + (y2 - y1) / 4, x2 - (x2 - x1) / 4, y2 - (y2 - y1) / 4, c);
	}
}