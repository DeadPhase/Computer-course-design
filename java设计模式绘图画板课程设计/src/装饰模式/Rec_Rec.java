package װ��ģʽ;

import java.awt.Color;
import java.awt.Graphics;
import �򵥹���ģʽ.DrawRec;

public class Rec_Rec extends Decorator {
	public Rec_Rec(Conpotent conpotent) {
		super(conpotent);
	}

	public void paint(Graphics g, int x1, int y1, int x2, int y2, Color c) {
		DrawRec rec = new DrawRec();
		rec.draw(g, 1 + (x2 - x1) / 4, y1 + (y2 - y1) / 4, x2 - (x2 - x1) / 4, y2 - (y2 - y1) / 4, c);
	}
}