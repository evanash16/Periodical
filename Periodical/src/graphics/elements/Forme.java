package graphics.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Forme extends Element{

	public Forme(int x, int y, int width, int height){
		
		super(x, y, width, height);
		
		setType("Forme");
	}
	
	public void createImage(Graphics g){
		
		g.setColor(new Color(246, 80, 88));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("LucidaSansRegular", Font.PLAIN, getHeight()));
		g.drawString("F", 0, getHeight());
	}
}
