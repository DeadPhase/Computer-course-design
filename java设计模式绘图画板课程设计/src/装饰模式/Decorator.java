package װ��ģʽ;

import java.awt.Color;
import java.awt.Graphics;

public class Decorator implements Conpotent {

	private Conpotent conpotent;

	public Decorator(Conpotent conpotent) {
		this.conpotent = conpotent;
	}

	@Override
	public void paint(Graphics g, int x, int y, int x1, int y1, Color c) {
		// TODO �Զ����ɵķ������

	}
}