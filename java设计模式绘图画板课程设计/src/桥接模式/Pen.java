package ге╫сдёй╫;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Pen {
	protected Color color;

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract void draw(String name, Graphics g, int x, int y, int x1, int y1, Color c, float size);
}
