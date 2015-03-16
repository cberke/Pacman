import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.omg.CORBA.portable.InputStream;

//37 = left
//38 = up
//39 = right
//40 = down

public class Manager implements KeyListener {
	Board board;
	private int inBox;
	private int key;
	private boolean started;
	
	public Manager() throws IOException{
		board = new Board();
		FileInputStream imgStream = new FileInputStream("Icon.png");
		BufferedImage myImg = ImageIO.read(imgStream);
		board.panel.getFrame().setIconImage(myImg);
		inBox = board.boxW;
		started = false;
		setUP();
	}
	
	public void setUP(){
		board.panel.addKeyListener(this);
	}
	
	public void playGame() throws IOException{
		Main.board = new int[32][32];
		for(int i = 0; i < 32; i++){
			for(int j = 0; j < 32; j++){
				Main.board[i][j] = Main.originalBoard[i][j];
			}
		}
		started = false;
		board.g.clearRect(0, 0, board.boxW * 32 + 150, board.boxH * 32 + 150);
		board.g.setColor(Color.WHITE);
		board.g.setFont(new Font("Enter_To_Start", Font.PLAIN, 50));
		board.g.drawString("Press Enter to Start", 10, (board.boxH * 32 + 150)/2);
		while(key != 10 && board.panel.getVisible()){
			waiting(1);
		}
		board.g.clearRect(0, 0, board.boxW * 32 + 150, board.boxH * 32 + 150);
		started = true;
		board.display();
		key = 37;
		int direction = key;
		Point current = new Point(board.pacPosition[1] * board.boxW, board.pacPosition[0] * board.boxH);
		Point old = new Point(0, 0);
		//System.out.print(key);
		Ghost one = new Ghost(15, 15);
		while(board.panel.getVisible()){
			direction = inBox==(int)board.boxH? key : direction;
			if(direction == 37) { //left
				if(Main.board[board.pacPosition[0]][board.pacPosition[1]-1] != 1  && Main.board[board.pacPosition[0]][board.pacPosition[1]-1] != 8){
					old = current;
					current = old.addX(-1);
					graphic(old, current, one);
					inBox = (inBox+1) > board.boxH? 0 : ++inBox;
					Main.board[board.pacPosition[0]][board.pacPosition[1]] = 2;
					if(Main.board[board.pacPosition[0]][board.pacPosition[1] - 1] == 0){
						board.addDotsEaten();
					}
					Main.board[board.pacPosition[0]][board.pacPosition[1]-1] = 3;
				}
				else{
					inBox = board.boxH;
				}
			}
			else if(direction == 38) { //up
				if(Main.board[board.pacPosition[0]-1][board.pacPosition[1]] != 1  && Main.board[board.pacPosition[0]-1][board.pacPosition[1]] != 8){
					old = current;
					current = old.addY(-1);
					graphic(old, current, one);
					inBox = (inBox+1) > board.boxH? 0 : ++inBox;
					Main.board[board.pacPosition[0]][board.pacPosition[1]] = 2;
					if(Main.board[board.pacPosition[0]-1][board.pacPosition[1]] == 0){
						board.addDotsEaten();
					}
					Main.board[board.pacPosition[0]-1][board.pacPosition[1]] = 3;
				}
				else{
					inBox = board.boxH;
				}
			}
			else if(direction == 39) { //right
				if(Main.board[board.pacPosition[0]][board.pacPosition[1]+1] != 1  && Main.board[board.pacPosition[0]][board.pacPosition[1]+1] != 8){
					old = current;
					current = old.addX(1);
					graphic(old, current, one);
					inBox = (inBox+1) > board.boxH? 0 : ++inBox;
					Main.board[board.pacPosition[0]][board.pacPosition[1]] = 2;
					if(Main.board[board.pacPosition[0]][board.pacPosition[1] + 1] == 0){
						board.addDotsEaten();
					}
					Main.board[board.pacPosition[0]][board.pacPosition[1]+1] = 3;
				}
				else{
					inBox = board.boxH;
				}
			}
			else if(direction == 40) {//down
				if(Main.board[board.pacPosition[0]+1][board.pacPosition[1]] != 1  && Main.board[board.pacPosition[0]+1][board.pacPosition[1]] != 8){
					old = current;
					current = old.addY(1);
					graphic(old, current, one);
					inBox = (inBox+1) > board.boxH? 0 : ++inBox;
					Main.board[board.pacPosition[0]][board.pacPosition[1]] = 2;
					if(Main.board[board.pacPosition[0]+1][board.pacPosition[1]] == 0){
						board.addDotsEaten();
					}
					Main.board[board.pacPosition[0]+1][board.pacPosition[1]] = 3;
				}
				else{
					inBox = board.boxH;
				}
			}
			//System.out.println(inBox + " " + board.boxH);
			boolean gameOver = false;
			if(inBox==board.boxH){
				gameOver = one.bfs(board);
				board.display2();
				current = new Point(board.pacPosition[1] * board.boxW, board.pacPosition[0] * board.boxH);
				if(board.getDotsEaten() == board.getDotsTotal()){
					board.g.setColor(new Color(3, 9, 128));
					board.g.fillRect(0, 0, board.boxW * 32 + 150, board.boxH * 32 + 150);
					board.g.setColor(Color.BLACK);
					board.g.setFont(new Font("Winning", Font.ITALIC, 100));
					board.g.drawString("Level Complete", 10, (board.boxH * 32 + 150)/2);
					break;
				}
			}
			waiting(13);
			if(gameOver){
				Random rand = new Random();
				int twat = rand.nextInt(20);
				if(twat ==  13){
					board.g.setColor(Color.BLACK);
					board.g.clearRect(0, 0, board.boxW * 32 + 150, board.boxH * 32 + 150);
					JFrame f = new JFrame();
			        ImageIcon reel = new ImageIcon("9e7[1].gif");
			        JLabel label = new JLabel(reel);
			        reel.setImageObserver(label);
			        f.getContentPane().add(label);
			        f.setUndecorated(true);
			        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			        f.setLocation((int)dim.getWidth()/4, (int)dim.getHeight()/4);
			        f.setSize(800, 450);
			        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        f.setVisible(true);
			        break;
				}
				board.g.setColor(Color.CYAN);
				board.g.fillRect(board.pacPosition[1] * board.boxW + 5, board.pacPosition[0] * board.boxH + 5, 10, 10);
				waiting(500);
				if(twat != 13){
					board.g.setColor(Color.BLACK);
					board.g.clearRect(0, 0, board.boxW * 32 + 150, board.boxH * 32 + 150);
					board.g.setColor(new Color(150, 0, 0));
					board.g.setFont(new Font("Game_Over", Font.BOLD, 100));
					board.g.drawString("Game Over", 10, (board.boxH * 32 + 150)/2);
					waiting(5000);
					playGame();
				}
				board.g.setColor(Color.BLACK);
				board.g.clearRect(0, 0, board.boxW * 32 + 150, board.boxH * 32 + 150);
				break;
			}
		}
	}
	
	public void graphic(Point old, Point current, Ghost one){
		int boxH = (int) board.boxH;
		int boxW = (int) board.boxW;
		board.g.setColor(Color.BLACK);
		board.g.fillRect((old.x)+1, (old.y) + 1, boxW-1, boxH - ((boxH/(int)Math.round(boxH * 0.2173913)) * (int)Math.round(boxW * 0.08695652)) + (int)Math.round(boxH * 0.17391304));
		board.g.setColor(Color.YELLOW);
		board.g.fillOval((current.x) + (int)Math.round(boxW * 0.26086957), (current.y) + (int)Math.round(boxH * 0.2173913), (int) Math.round(boxW * 0.56521739), (int) Math.round(boxH * 0.56521739));
		if(one.getCurrent().x == one.oldPos.x){
			if(one.getCurrent().y < one.oldPos.y){
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + (int)Math.round(boxH * 0.17391304) + board.boxW, one.oldOnPoint.x + (int)Math.round(boxH * 0.17391304), (int) Math.round(boxW * 0.56521739), (int) Math.round(boxH * 0.56521739));
				one.addY(-1);
				board.g.setColor(Color.CYAN);
				board.g.fillRect(one.currentOnPoint.y + (int)Math.round(boxH * 0.2173913) + board.boxW, one.currentOnPoint.x + (int)Math.round(boxH * 0.2173913), (int) Math.round(boxW * 0.43478261), (int) Math.round(boxH * 0.43478261));
			}
			else{
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + (int)Math.round(boxH * 0.17391304) - board.boxW, one.oldOnPoint.x + (int)Math.round(boxH * 0.17391304), (int) Math.round(boxW * 0.56521739), (int) Math.round(boxH * 0.56521739));
				one.addY(1);
				board.g.setColor(Color.CYAN);
				board.g.fillRect(one.currentOnPoint.y + (int)Math.round(boxH * 0.2173913) - board.boxW, one.currentOnPoint.x + (int)Math.round(boxH * 0.2173913), (int) Math.round(boxW * 0.43478261), (int) Math.round(boxH * 0.43478261));
			}
		}
		else if(one.getCurrent().y == one.oldPos.y){
			if(one.getCurrent().x < one.oldPos.x){
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + (int)Math.round(boxH * 0.17391304), one.oldOnPoint.x + (int)Math.round(boxH * 0.17391304) + board.boxH, (int) Math.round(boxW * 0.56521739), (int) Math.round(boxH * 0.56521739));
				one.addX(-1);
				board.g.setColor(Color.CYAN);
				board.g.fillRect(one.currentOnPoint.y + (int)Math.round(boxH * 0.2173913), one.currentOnPoint.x + (int)Math.round(boxH * 0.2173913) + board.boxH, (int) Math.round(boxW * 0.43478261), (int) Math.round(boxH * 0.43478261));
			}
			else{
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + (int)Math.round(boxH * 0.17391304), one.oldOnPoint.x + (int)Math.round(boxH * 0.17391304) - board.boxH, (int) Math.round(boxW * 0.56521739), (int) Math.round(boxH * 0.56521739));
				one.addX(1);
				board.g.setColor(Color.CYAN);
				board.g.fillRect(one.currentOnPoint.y + (int)Math.round(boxH * 0.2173913), one.currentOnPoint.x + (int)Math.round(boxH * 0.2173913) - board.boxH, (int) Math.round(boxW * 0.43478261), (int) Math.round(boxH * 0.43478261));
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println(arg0.getKeyCode());
		key = ((arg0.getKeyCode() < 41 && arg0.getKeyCode() > 36) || (arg0.getKeyCode() == 10 && !started)) ? arg0.getKeyCode() : key;
	}
	
	public void keyReleased(KeyEvent e) { }
	
	public void keyTyped(KeyEvent e) { } 

	public void waiting(int waitTime){
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
