package װ��ģʽ;

import java.awt.Color;
import java.awt.Graphics;
import �򵥹���ģʽ.DrawOval;
import �򵥹���ģʽ.DrawRec;

public class Oval extends DrawOval implements Conpotent {
	public void paint(Graphics g, int x, int y, int x1, int y1, Color c) {
		DrawOval ova = new DrawOval();
		ova.draw(g, x, y, x1, y1, c);
	}

}
