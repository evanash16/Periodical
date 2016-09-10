package graphics.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Materas extends Element{

	public Materas(int x, int y, int width, int height){
		
		super(x, y, width, height);
		
		setType("Materas");
	}
	
	public void createImage(Graphics g){
		
		g.setColor(new Color(242, 240, 161));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(new Color(255, 232, 0));
		
		double[] xPoints = new double[]{0.3773624181460929, 0.027435442101817364, 0.12154143067489043, 0.8022401681609272, 0.1668061126451731, 0.07246418458934945, 0.6261033142251682, 0.9016648010370014, 0.15946212665532322, 0.955686880515285, 0.9318228760576673, 0.32630810533490673, 0.5711068419088472, 0.839460393024971, 0.11508053732002632, 0.8450746858620594, 0.5790358364996364, 0.325017677942242, 0.8314563904659936, 0.1278818788772279};
		double[] yPoints = new double[]{0.6279606142126087, 0.8549099419935072, 0.51181752737645, 0.5060547083326591, 0.5697610978204404, 0.3407000896381779, 0.8528476277393504, 0.4211218087857934, 0.3924953097072613, 0.16120155495955235, 0.8110336641984218, 0.5867015626326488, 0.3341068428655618, 0.3806583302635047, 0.7397883965527361, 0.6078932626950002, 0.24756013457014003, 0.021613310771958716, 0.27130227415281516, 0.3035654054365433};
		
		for(int i = 0; i < xPoints.length; i++){
			g.fillOval((int) (xPoints[i] * getWidth()) - (getWidth() / 40), (int) (yPoints[i] * getWidth()) - (getWidth() / 40), (getWidth() / 20), getWidth() / 20);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("LucidaSansRegular", Font.PLAIN, getHeight()));
		g.drawString("M", 0, getHeight());
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
