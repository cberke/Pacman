import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;


public class Board {
	public static DrawingPanel panel;
	public static Graphics g;
	
	public static double boxH;
	public static double boxW;
	
	public static int [] pacPosition = {0,0};
	
	public Board(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		boxW = Math.round((dim.height - 150) / 32);
		boxH = Math.round((dim.height - 150) / 32);
		panel = new DrawingPanel(dim.height - 150, dim.height - 150);
		g = panel.getGraphics();
		panel.setBackground(Color.BLACK);
		display();
	}
	public void display(){
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < Main.board.length; i++){
			for(int j = 0; j < Main.board[i].length; j++){
				//Draw dots
				if(Main.board[j][i] ==  0){
					g.setColor(Color.YELLOW);
					g.fillOval((int)Math.round((i * boxW) + (boxW/2)), (int)Math.round((j * boxH) + (boxH/2)), 5, 5);
				}
				//Draw walls
				else if(Main.board[j][i] == 1){
					g.setColor(Color.BLUE);
					g.drawRect((int)Math.round(i * boxW), (int)Math.round(j * boxH), (int)Math.round(boxW), (int)Math.round(boxH));
				}
				//Draw doors
				else if(Main.board[j][i] == 8){
					g.setColor(Color.BLUE); 
					g.drawRect((int)Math.round(i * boxW), (int)Math.round((j * boxH) + 5), (int)Math.round(boxW), (int)Math.round(boxH - ((boxH/5) * 2))); 
				}if(Main.board[j][i] ==  2){
					g.setColor(Color.BLACK);
					g.fillRect((int)Math.round(i * boxW)+1, (int)Math.round((j * boxH) + 5), (int)Math.round(boxW)-1, (int)Math.round(boxH - ((boxH/5) * 2))); 
				}else if (Main.board[j][i] == 3){
					g.setColor(Color.YELLOW);
					g.fillOval((int)Math.round((i * boxW)) + 5, (int)Math.round((j * boxH)) + 5, 13, 13);
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
				if(Main.board[j][i] ==  2){
					g.setColor(Color.BLACK);
					g.fillRect((int)Math.round(i * boxW)+1, (int)Math.round((j * boxH) + 5), (int)Math.round(boxW)-1, (int)Math.round(boxH - ((boxH/5) * 2))); 
				}else if (Main.board[j][i] == 3){
					g.setColor(Color.YELLOW);
					g.fillOval((int)Math.round((i * boxW)) + 6, (int)Math.round((j * boxH)) + 5, 13, 13);
					pacPosition[0] = j;
					pacPosition[1] = i;
				}
			}
		}
	}
}
