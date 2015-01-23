import java.awt.Color;
import java.awt.Graphics;


public class Pacman {
	private boolean isTriVis;
	private boolean isDead;
	private int xLoc;
	private int yLoc;
	private int xSize;
	private int ySize;
	
	public Pacman(){
		this.xSize = 25;
		this.ySize = 25;
		this.xLoc = 380;
		this.yLoc = 660;
		this.isTriVis = false;
		this.isDead = false;
	}
	
	public void drawPac(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillOval(xLoc, yLoc, xSize, ySize);
	}
}
