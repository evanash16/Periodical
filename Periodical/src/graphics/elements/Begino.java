package graphics.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Begino extends Element{

	public Begino(int x, int y, int width, int height){
		
		super(x, y, width, height);
		
		setType("Begino");
	}
	
	public void createImage(Graphics g){
		
		g.setColor(new Color(152, 158, 161));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("LucidaSansRegular", Font.PLAIN, getHeight()));
		g.drawString("B", 0, getHeight());
	}
}
