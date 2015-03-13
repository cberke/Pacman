import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//37 = left
//38 = up
//39 = right
//40 = down

public class Manager implements KeyListener {
	Board board;
	private int inBox;
	private int key;
	
	public Manager(){
		board = new Board();
		inBox = board.boxW;
		setUP();
	}
	
	public void setUP(){
		board.panel.addKeyListener(this);
	}
	
	public void playGame() throws IOException{
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
			if(inBox==board.boxH){
				one.bfs(board);
				board.display2();
				current = new Point(board.pacPosition[1] * board.boxW, board.pacPosition[0] * board.boxH);
				if(board.getDotsEaten() == board.getDotsTotal()){
					board.g.setColor(Color.CYAN);
					board.g.fillRect(0, 0, board.boxW * 32 + 150, board.boxH * 32 + 150);
					board.g.setColor(Color.BLACK);
					board.g.drawString("Congratulations! You win!", (board.boxW * 32 + 150)/2, (board.boxH * 32 + 150)/2);
					break;
				}
			}
			waiting();
			if(one.getCurrent().x == board.pacPosition[0] && one.getCurrent().y == board.pacPosition[1]){
				Random rand = new Random();
				int twat = rand.nextInt(20);
				System.out.println(twat);
				if(twat ==  13){
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
				}
				else{
					board.g.clearRect(0, 0, board.boxW * 32 + 150, board.boxH * 32 + 150);
					board.g.setColor(Color.RED);
					board.g.drawString("Game Over", (board.boxW * 32 + 150)/2, (board.boxH * 32 + 150)/2);
					break;
				}
				board.g.clearRect(0, 0, board.boxW * 32 + 150, board.boxH * 32 + 150);
				break;
			}
		}
	}
	
	public void graphic(Point old, Point current, Ghost one){
		int boxH = (int) board.boxH;
		int boxW = (int) board.boxW;
		board.g.setColor(Color.BLACK);
		board.g.fillRect((old.x)+1, (old.y) + 1, boxW-1, boxH - ((boxH/5) * 2) + 4);
		board.g.setColor(Color.YELLOW);
		board.g.fillOval((current.x) + 6, (current.y) + 5, 13, 13);
		if(one.getCurrent().x == one.oldPos.x){
			if(one.getCurrent().y < one.oldPos.y){
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + 4 + board.boxW, one.oldOnPoint.x + 4, 13, 13);
				one.addY(-1);
				board.g.setColor(Color.CYAN);
				board.g.fillRect(one.currentOnPoint.y + 5 + board.boxW, one.currentOnPoint.x + 5, 10, 10);
			}
			else{
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + 4 - board.boxW, one.oldOnPoint.x + 4, 13, 13);
				one.addY(1);
				board.g.setColor(Color.CYAN);
				board.g.fillRect(one.currentOnPoint.y + 5 - board.boxW, one.currentOnPoint.x + 5, 10, 10);
			}
		}
		else if(one.getCurrent().y == one.oldPos.y){
			if(one.getCurrent().x < one.oldPos.x){
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + 4, one.oldOnPoint.x + 4 + board.boxH, 13, 13);
				one.addX(-1);
				board.g.setColor(Color.CYAN);
				board.g.fillRect(one.currentOnPoint.y + 5, one.currentOnPoint.x + 5 + board.boxH, 10, 10);
			}
			else{
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + 4, one.oldOnPoint.x + 4 - board.boxH, 13, 13);
				one.addX(1);
				board.g.setColor(Color.CYAN);
				board.g.fillRect(one.currentOnPoint.y + 5, one.currentOnPoint.x + 5 - board.boxH, 10, 10);
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println(arg0.getKeyCode());
		key = arg0.getKeyCode();
	}
	
	public void keyReleased(KeyEvent e) { }
	
	public void keyTyped(KeyEvent e) { } 

	public void waiting(){
		try {
			Thread.sleep(14);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
