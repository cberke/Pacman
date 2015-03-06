import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

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
	
	public void playGame(){
		key = 37;
		int direction = key;
		Point current = new Point(board.pacPosition[1] * board.boxW, board.pacPosition[0] * board.boxH);
		Point old = new Point(0, 0);
		//System.out.print(key);
		Ghost one = new Ghost(1, 1);
		while(board.panel.getVisible()){
			direction = inBox==(int)board.boxH? key : direction;
			if(direction == 37) { //left
				if(Main.board[board.pacPosition[0]][board.pacPosition[1]-1] != 1  && Main.board[board.pacPosition[0]][board.pacPosition[1]-1] != 8){
					old = current;
					current = old.addX(-1);
					graphic(old, current, one);
					inBox = (inBox+1) > board.boxH? 0 : ++inBox;
					Main.board[board.pacPosition[0]][board.pacPosition[1]] = 2;
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
			}
			waiting();
			if(one.getCurrent().x == board.pacPosition[0] && one.getCurrent().y == board.pacPosition[1]){
				Random rand = new Random();
				int twat = rand.nextInt(20);
				if(twat ==  13){
					
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
		System.out.println("OLD: " + one.oldOnPoint.x + " " + one.oldOnPoint.y);
		System.out.println("CURRENT: " + one.currentOnPoint.x + " " + one.currentOnPoint.y);
		if(one.getCurrent().x == one.oldPos.x){
			if(one.getCurrent().y < one.oldPos.y){
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + 5 + board.boxW, one.oldOnPoint.x + 5, 13, 13);
				one.addY(-1);
				board.g.setColor(Color.CYAN);
				board.g.fillRect(one.currentOnPoint.y + 5 + board.boxW, one.currentOnPoint.x + 5, 10, 10);
			}
			else{
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + 5 - board.boxW, one.oldOnPoint.x + 5, 13, 13);
				one.addY(1);
				board.g.setColor(Color.CYAN);
				board.g.fillRect(one.currentOnPoint.y + 5 - board.boxW, one.currentOnPoint.x + 5, 10, 10);
			}
		}
		else if(one.getCurrent().y == one.oldPos.y){
			if(one.getCurrent().x < one.oldPos.x){
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + 5, one.oldOnPoint.x + 5 + board.boxH, 13, 13);
				one.addX(-1);
				board.g.setColor(Color.CYAN);
				board.g.fillRect(one.currentOnPoint.y + 5, one.currentOnPoint.x + 5 + board.boxH, 10, 10);
			}
			else{
				board.g.setColor(Color.BLACK);
				board.g.fillRect(one.oldOnPoint.y + 5, one.oldOnPoint.x + 5 - board.boxH, 13, 13);
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
