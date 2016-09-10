package graphics.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Element {

	private int x, y, width, height, addition = 100;
	private boolean animating = true, closing = false, visible = true, selected = false;
	private String type = "Element";
	
	private BufferedImage image;
	private Color primaryColor;
	
	public Element(int x, int y, int width, int height){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		createImage(image.getGraphics());
		
		primaryColor = new Color(image.getRGB(0, 0));
	}
	
	public void createImage(Graphics g){
		
		g.setColor(new Color(140, 150, 157));
		g.fillRect(0, 0, width, height);
	}
	
	public void update(){
		
		if(animating){
			
			if(!closing){
				
				if(addition >= 0){
					
					addition -= (addition / 4);
				}
				
				else{
					
					animating = false;
				}
			}
			
			else{
				
				if(Math.abs(addition + (2 * addition)) < width && Math.abs(addition + (2 * addition)) < height){
					
					addition += 2 * addition;
				}
				
				else{
					
					animating = false;
					visible = false;
				}
			}
		}
	}
	
	public void draw(Graphics g){
		
		g.drawImage(image, x - (addition / 2), y - (addition / 2), width + addition, height + addition, null);
		
		if(selected){
			
			Graphics2D g2D = (Graphics2D) g;
			g2D.setStroke(new BasicStroke(10));
			
			g.setColor(Color.BLACK);
			
			g.drawRect(x - (addition / 2), y - (addition / 2), width + addition, height + addition);
		}
	}
	
	public void click(){if(!animating && !selected) {select();} else if(animating) {select();} else if(selected) {animate();}}
	public void close() {animating = true; closing = true; selected = false; addition =- 1;}
	private void select() {animating = false; selected = true; addition = 20;}
	private void animate() {selected = false; animating = true;}
	
	public boolean isSelected() {return selected;}
	public boolean isAnimating() {return animating;}
	public boolean isVisible() {return visible;}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	public void setType(String type) {this.type = type;}
	public String getType() {return type;}
	
	public Color getColor() {return primaryColor;}
}
