package �Ž�ģʽ;

import java.awt.Color;
import java.awt.Graphics;

import �򵥹���ģʽ.Draw;
import �򵥹���ģʽ.proFactory;

public class MiddlePen extends Pen {

	@Override
	public void draw(String name, Graphics g, int x, int y, int x1, int y1, Color c, float size) {
		Draw d = proFactory.produce(name);
		d.draw(g, x, y, x1, y1, c, size);
		
	}

}
