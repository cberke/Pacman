import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;


public class Main  implements KeyListener, ActionListener {
	private static int[][] board = {
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
		{1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1}, 
		{1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, 
		{1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1}, 
		{1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1}, 
		{1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 8, 8, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, 
		{1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 2, 2, 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, 
		{1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 2, 2, 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1}, 
		{1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 2, 2, 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, 
		{1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 2, 2, 2, 2, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1}, 
 		{1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1}, 
		{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1}, 
		{1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1}, 
		{1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1}, 
		{1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1}, 
		{1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1}, 
		{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} 

			
	};
	public static DrawingPanel panel;
	public static Graphics g;
	public static double boxH;
	public static double boxW;
	public static boolean isGaming = true; 
	public static int [] packPosition = {0,0};
	public static void main(String args[]){
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		Dimension dim = toolkit.getScreenSize();
		int height = dim.height - 150;
		int width = dim.height - 150;
		panel = new DrawingPanel(width, height);
		g = panel.getGraphics();
		panel.setBackground(Color.BLACK);
		Main main = new Main();
		main.setUP();
		

		// 0 = dot
		// 1 = wall
		// 2 = blank
		// 3 = pacman
		// 4 = ghost1
		// 5 = ghost2
		// 6 = ghost3
		// 7 = ghost4
		// 8 = door
		// 1 space = 25 by 25 block
		boxW = Math.round(width / 32);
		boxH = Math.round(height / 32);
		display(g, boxW, boxH);
		//for(int i = 0; i <800; i++){
		//	g.setColor(Color.YELLOW);
		//	g.fillOval(i, 100, (int)boxW - 10, (int)boxH -10);
		//	try {
		//		Thread.sleep(15);
		//	} catch (InterruptedException e) {
			//	 TODO Auto-generated catch block
		//		e.printStackTrace();
		//	}
		//	g.setColor(Color.BLACK);
		//	g.fillRect(i, 100, (int)boxW - 5, (int)boxH - 5);
		//}
		
		//while(isGaming){
			
		//}
		
	}public void setUP(){
		panel.addKeyListener(this);
	}
	
	public static void display(Graphics g, double width, double height){
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				//Draw dots
				if(board[j][i] ==  0){
					g.setColor(Color.YELLOW);
					g.fillOval((int)Math.round((i * width) + (width/2)), (int)Math.round((j * height) + (height/2)), 5, 5);
				}
				//Draw walls
				else if(board[j][i] == 1){
					g.setColor(Color.BLUE);
					g.drawRect((int)Math.round(i * width), (int)Math.round(j * height), (int)Math.round(width), (int)Math.round(height));
				}
				//Draw doors
				else if(board[j][i] == 8){
					g.setColor(Color.BLUE); 
					g.drawRect((int)Math.round(i * width), (int)Math.round((j * height) + 5), (int)Math.round(width), (int)Math.round(height - ((height/5) * 2))); 
				}if(board[j][i] ==  2){
					g.setColor(Color.BLACK);
					g.fillRect((int)Math.round(i * width)+1, (int)Math.round((j * height) + 5), (int)Math.round(width)-1, (int)Math.round(height - ((height/5) * 2))); 
				}else if (board[j][i] == 3){
					g.setColor(Color.YELLOW);
					g.fillOval((int)Math.round((i * width)) + 5, (int)Math.round((j * height)) + 5, 13, 13);
					packPosition[0] = j;
					packPosition[1] = i;
				}
			}
		}
	}public static void display2(Graphics g, double width, double height){
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				//Draw dots
				if(board[j][i] ==  2){
					g.setColor(Color.BLACK);
					g.fillRect((int)Math.round(i * width)+1, (int)Math.round((j * height) + 5), (int)Math.round(width)-1, (int)Math.round(height - ((height/5) * 2))); 
				}else if (board[j][i] == 3){
					g.setColor(Color.YELLOW);
					g.fillOval((int)Math.round((i * width)) + 6, (int)Math.round((j * height)) + 5, 13, 13);
					packPosition[0] = j;
					packPosition[1] = i;
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0);
		if(arg0.getKeyCode() == 39){
			if(board[packPosition[0]][packPosition[1]+1] != 1){
				board[packPosition[0]][packPosition[1]] = 2;
				board[packPosition[0]][packPosition[1]+1] = 3;
				display2(g,boxH , boxW);
			}
		}
		else if(arg0.getKeyCode() == 40){
			if(board[packPosition[0]+1][packPosition[1]] != 1 && board[packPosition[0]+1][packPosition[1]] != 8){
				board[packPosition[0]][packPosition[1]] = 2;
				board[packPosition[0] +1][packPosition[1]] = 3;
				display2(g,boxH , boxW);	
			}
		}
		else if(arg0.getKeyCode() == 37){
			if(board[packPosition[0]][packPosition[1]-1] != 1){
				board[packPosition[0]][packPosition[1]] = 2;
				board[packPosition[0]][packPosition[1]-1] = 3;
				display2(g,boxH , boxW);	
			}
		}
		else if(arg0.getKeyCode() == 38){
			if(board[packPosition[0]-1][packPosition[1]] != 1){
				board[packPosition[0]][packPosition[1]] = 2;
				board[packPosition[0]-1][packPosition[1]] = 3;
				display2(g,boxH , boxW);	
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(e);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
