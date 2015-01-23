import java.awt.Color;
import java.awt.Graphics;

public class Wall {
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	public Wall(int xPos, int yPos, int width, int height){
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}
	
	public void drawWall(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(xPos, yPos, width, height);
	}
}
