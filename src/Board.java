import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;


public class Board {
	public DrawingPanel panel;
	public Graphics g;
	
	public int boxH;
	public int boxW;
	
	private int numDotsEaten;
	private int numDotsTotal;
	
	public static int [] pacPosition = {0,0};
	
	public Board(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		boxW = ((dim.height - 150) / 32);
		boxH = ((dim.height - 150) / 32);
		panel = new DrawingPanel(dim.height - 150, dim.height - 150);
		g = panel.getGraphics();
		panel.setBackground(Color.BLACK);
		numDotsEaten = 0;
		numDotsTotal = 0;
		//display();
	}
	public void display(){
		numDotsTotal = 0;
		numDotsEaten = 0;
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < Main.board.length; i++){
			for(int j = 0; j < Main.board[i].length; j++){
				//Draw dots
				if(Main.board[j][i] ==  0){
					numDotsTotal++;
					g.setColor(Color.YELLOW);
					g.fillOval((i * boxW) + (boxW/(int)Math.round(boxW * 0.08695652)), (j * boxH) + (boxH/(int)Math.round(boxW * 0.08695652)), (int)Math.round(boxH * 0.2173913), (int)Math.round(boxH * 0.2173913));
				}
				//Draw walls
				else if(Main.board[j][i] == 1){
					g.setColor(Color.BLUE);
					g.drawRect(i * boxW, j * boxH, boxW, boxH);
				}
				//Draw doors
				else if(Main.board[j][i] == 8){
					g.setColor(Color.BLUE); 
					g.drawRect(i * boxW, (j * boxH) + (int)Math.round(boxH * 0.2173913), boxW, boxH - ((boxH/(int)Math.round(boxH * 0.2173913)) * (int)Math.round(boxW * 0.08695652))); 
				}if(Main.board[j][i] ==  2){
					//g.setColor(Color.BLACK);
					//g.fillRect(i * boxW+1, (j * boxH) + 5, boxW-1, boxH - ((boxH/5) * 2)); 
				}else if (Main.board[j][i] == 3){
					//g.setColor(Color.YELLOW);
					//g.fillOval((i * boxW) + 5, (j * boxH) + 5, 13, 13);
					pacPosition[0] = j;
					pacPosition[1] = i;
				}
			}
		}
	}
	public void display2(){
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < Main.board.length; i++){
			for(int j = 0; j < Main.board[i].length; j++){
				//Draw dots
				if(Main.board[j][i] ==  0){
					g.setColor(Color.BLACK);
					g.fillRect((i * boxW)+ (int)Math.round(boxH * .04347826), (j * boxH) + (int)Math.round(boxH * 0.2173913), (boxW)-(int)Math.round(boxW * 0.04347826), boxH - ((boxH/(int)Math.round(boxH * 0.2173913)) * (int)Math.round(boxW * 0.08695652))); 
					g.setColor(Color.YELLOW);
					g.fillOval((i * boxW) + (boxW/(int)Math.round(boxW * 0.08695652)), (j * boxH) + (boxH/(int)Math.round(boxW * 0.08695652)), (int)Math.round(boxH * 0.2173913), (int)Math.round(boxH * 0.2173913));
				}
				if(Main.board[j][i] ==  2){
					g.setColor(Color.BLACK);
					g.fillRect((i * boxW)+(int)Math.round(boxW * 0.04347826), (j * boxH) + (int)Math.round(boxH * 0.2173913), (boxW)-(int)Math.round(boxW * 0.04347826), boxH - ((boxH/(int)Math.round(boxH * 0.2173913)) * (int)Math.round(boxW * 0.08695652))); 
				}else if (Main.board[j][i] == 3){
					//g.setColor(Color.YELLOW);
					//g.fillOval((int)Math.round((i * boxW)) + 5, (int)Math.round((j * boxH)) + 5, 13, 13);
					pacPosition[0] = j;
					pacPosition[1] = i;
				}
			}
		}
	}
	
	public int getDotsEaten(){
		return numDotsEaten;
	}
	
	public int getDotsTotal(){
		return numDotsTotal;
	}
	
	public void addDotsEaten(){
		numDotsEaten++;
	}
}
