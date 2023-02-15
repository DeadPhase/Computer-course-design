package 简单工厂模式;

import java.awt.Color;
import java.awt.Graphics;

public interface Draw {
	public abstract void draw(Graphics g, int x, int y, int x1, int y1, Color c);

	public abstract void draw(Graphics g, int x, int y, int x1, int y1, Color c, float size);
}
