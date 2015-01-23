import java.awt.Color;
import java.awt.Graphics;


public class Dot {
	private boolean isVisible;
	private int xPos;
	private int yPos;
	
	public Dot(int xPos, int yPos){
		isVisible = true;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void drawDot(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillOval(xPos, yPos, 7, 7);
	}
}
