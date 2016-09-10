package graphics.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Reydas extends Element {

	public Reydas(int x, int y, int width, int height) {
		
		super(x, y, width, height);
		
		setType("Reydas");
	}

public void createImage(Graphics g){
		
		g.setColor(new Color(219, 72, 126));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(new Color(252, 174, 187));
		
		double[] xPoints = new double[]{0.7272811427607963, 0.26121694864718814, 0.4824466065980135, 0.6563379439334216, 0.28908285070692996, 0.19535382958487124, 0.9527437962850859, 0.6601417222415098, 0.7554081206973645, 0.9478647533977979, 0.4437233147434225, 0.36424386576768547, 0.21837260237627432, 0.3784440811529466, 0.14436988159926456, 0.11939750579958464, 0.20947928395830906, 0.5933866586504399, 0.7599897415647846, 0.5212791327291469};
		double[] yPoints = new double[]{0.025881853880076133, 0.8748843410128205, 0.02526306745126672, 0.13256565702492085, 0.0032012477134273354, 0.17468146255490224, 0.23326172860140737, 0.7529669250568932, 0.08978443854703298, 0.9510500184455117, 0.711981722732488, 0.8154913524181171, 0.8321768763616516, 0.8907413022677285, 0.7629469502615381, 0.20304267260294007, 0.5427136372859813, 0.9765157381685304, 0.5656991405119894, 0.15151570989975294};
		
		for(int i = 0; i < xPoints.length; i++){
			g.fillOval((int) (xPoints[i] * getWidth()) - (getWidth() / 40), (int) (yPoints[i] * getWidth()) - (getWidth() / 40), (getWidth() / 20), getWidth() / 20);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("LucidaSansRegular", Font.PLAIN, getHeight()));
		g.drawString("R", 0, getHeight());
	}
	
	public void createStars(){
		
		int starCount = 20;
		
		System.out.print("double[] xPoints = new double[]{");
		
		for(int i = 0; i < starCount; i++){
			
			double rand = (double) (Math.random() * getWidth()) / (double) getWidth();
			
			if(i + 1 < starCount){
				
				System.out.print(rand + ", ");
			}
			
			else{
				
				System.out.print(rand);
			}
		}
		
		System.out.print("};");
		System.out.println();
		
		System.out.print("double[] yPoints = new double[]{");
		
		for(int i = 0; i < starCount; i++){
			
			double rand = (double) (Math.random() * getHeight()) / (double) getHeight();
			
			if(i + 1 < starCount){
				
				System.out.print(rand + ", ");
			}
			
			else{
				
				System.out.print(rand);
			}
		}
		
		System.out.print("};");
	}
}
