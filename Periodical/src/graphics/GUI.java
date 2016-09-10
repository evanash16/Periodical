package graphics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.Timer;

import graphics.elements.Element;
import graphics.elements.ElementManager;

public class GUI extends JFrame implements ActionListener, MouseListener{

	public static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	private Timer timer;
	private int ticks = 0;
	private ElementManager eManager;
	private boolean playing = true;

	public static void main(String[] args){
		
		GUI gui = new GUI();
	}
	
	public GUI(){
		
		super("Periodical");
		
		setSize(screensize);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		addMouseListener(this);
		
		timer = new Timer(10, this);
		timer.start();
		
		eManager = new ElementManager(5, 5, getWidth() / 20, getHeight() / 10);
		
		setVisible(true);
	}
	
	public void paint(Graphics g){
		
		BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g2 = buffer.getGraphics();
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		int x = getWidth() - (getWidth() / 40);
		int y = getHeight() - (getWidth() / 40);
		
		if(playing){
			
			eManager.update();
			
			g2.setColor(Color.BLACK);
			g2.fillRect(x, y, getWidth() / 200, (getWidth() / 40) - (getWidth() / 200));
			g2.fillRect(getWidth() - 2 * (getWidth() / 200), y, getWidth() / 200, (getWidth() / 40) - (getWidth() / 200));
		}
		
		else{
			
			g2.setColor(Color.BLACK);
			g2.fillPolygon(new int[]{x, x + (getWidth() / 50), x}, new int[]{y, y + (getWidth() / 100), y + (getWidth() / 50)}, 3);
			
			if(ticks % 50 <= 25) {
				
				g2.setColor(new Color(242, 240, 161, 50));
				Graphics2D g2D = (Graphics2D) g2;
				g2D.setStroke(new BasicStroke(getWidth() / 10));
				g2.drawRect(0, 0, getWidth(), getHeight());
				g2D.setStroke(new BasicStroke());
				
			}
		}

		eManager.draw(g2);
		
		if(getMousePosition() != null){
			
			Element tooltip = eManager.getTooltip(getMousePosition().x, getMousePosition().y);
			
			if(tooltip != null){
				
				if(Math.abs(tooltip.getColor().getRGB() - Color.BLACK.getRGB()) < Math.abs(Color.WHITE.getRGB() - tooltip.getColor().getRGB())){g2.setColor(tooltip.getColor().brighter().brighter());}
				else {g2.setColor(tooltip.getColor().darker().darker());}
				
				g2.setFont(new Font("LucidaSansRegular", Font.PLAIN, getHeight() / 50));
				g2.drawString(tooltip.getType(), getMousePosition().x, getMousePosition().y);
			}
		}
		
		g.drawImage(buffer, 0, 0, null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == timer){

			ticks++;
			
			if(Math.random() < 0.005 && playing){
				
				eManager.generate();
			}
			
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		if(arg0.getButton() == 1){
			
			if(playing && eManager.click(arg0)){}
			else if(arg0.getX() > getWidth() - (getWidth() / 40) && arg0.getX() > getHeight() - (getHeight() / 40)){
				
				playing = !playing;
			}
		}
		
		if(arg0.getButton() == 3){
			
			eManager.checkRecipe();
		}
	}
}
