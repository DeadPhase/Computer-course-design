package װ��ģʽ;

import java.awt.Color;
import java.awt.Graphics;
import �򵥹���ģʽ.DrawRec;
import �򵥹���ģʽ.DrawTri;

public class Tri extends DrawTri implements Conpotent {
	public void paint(Graphics g, int x, int y, int x1, int y1, Color c) {
		DrawTri tri = new DrawTri();
		tri.draw(g, x, y, x1, y1, c);
	}
}
