package graphics.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Choleo extends Element{

	public Choleo(int x, int y, int width, int height){
		
		super(x, y, width, height);
		
		setType("Choleo");
	}
	
	public void createImage(Graphics g){
		
		g.setColor(new Color(16, 24, 32));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.WHITE);
		
		double[] xPoints = new double[]{0.8145178878573298, 0.9666831746704262, 0.7053533690969314, 0.014555035681660278, 0.7152794619124095, 0.6136875339331828, 0.5051191677562694, 0.8465467221869271, 0.1984947985618244, 0.5483821784008962, 0.9827458787815987, 0.5636473633414102, 0.576242833643361, 0.4165759480155285, 0.6406007175616565, 0.10866689272426588, 0.1234201662159985, 0.240133453948142, 0.18340091026762728, 0.12281858566664272};
		double[] yPoints = new double[]{0.09236763851063425, 0.8338673330813337, 0.4734286176701717, 0.030895287015186512, 0.6447834547401681, 0.7919465494337965, 0.24637073336014426, 0.19176548863047915, 0.8614764112396327, 0.9175087990904315, 0.965621621009761, 0.05281873178113517, 0.27664466459068593, 0.513109728921679, 0.8071448980950414, 0.24405948403645136, 0.5757487309190803, 0.6498375703580265, 0.7874979287370317, 0.2008694700083361};
		
		for(int i = 0; i < xPoints.length; i++){
			
			g.setColor(Color.WHITE);
			g.fillOval((int) (xPoints[i] * getWidth()) - (getWidth() / 40), (int) (yPoints[i] * getWidth()) - (getWidth() / 40), (getWidth() / 20), getWidth() / 20);
		}
		
		g.setFont(new Font("LucidaSansRegular", Font.PLAIN, getHeight()));
		g.drawString("C", 0, getHeight());
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
