import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		inBox = 32;
		setUP();
	}
	
	public void setUP(){
		board.panel.addKeyListener(this);
	}
	
	public void playGame(){
		key = 37;
		int direction = key;
		Point current = new Point(board.pacPosition[0], board.pacPosition[1]);
		Point old;
		System.out.print(key);
		Ghost one = new Ghost(1, 1);
		while(board.panel.getVisible()){
			direction = inBox==32? key : direction;
			if(direction == 37) { //left
				if(Main.board[board.pacPosition[0]][board.pacPosition[1]-1] != 1  && Main.board[board.pacPosition[0]][board.pacPosition[1]-1] != 8){
					old = current;
					current = old.addY(-1);
					graphic(old, current);
				}
				else{
					inBox = 32;
				}
			}
			else if(direction == 38) { //up
				if(Main.board[board.pacPosition[0]-1][board.pacPosition[1]] != 1  && Main.board[board.pacPosition[0]-1][board.pacPosition[1]] != 8){
					old = current;
					current = old.addX(-1);
					graphic(old, current);
				}
			}
			else if(direction == 39) { //right
				if(Main.board[board.pacPosition[0]][board.pacPosition[1]+1] != 1  && Main.board[board.pacPosition[0]][board.pacPosition[1]-1] != 8){
					old = current;
					current = old.addY(1);
					graphic(old, current);
				}
			}
			else if(direction == 40) {//down
				if(Main.board[board.pacPosition[0]+1][board.pacPosition[1]] != 1  && Main.board[board.pacPosition[0]+1][board.pacPosition[1]] != 8){
					old = current;
					current = old.addX(1);
					graphic(old, current);
				}
			}
			waiting();
			one.bfs(board);
		}
	}
	
	public void graphic(Point old, Point current){
		int boxH = (int) board.boxH;
		int boxW = (int) board.boxW;
		board.g.setColor(Color.YELLOW);
		board.g.fillOval((int)Math.round((current.x * boxW)) + 6, (int)Math.round((current.y * boxH)) + 5, 13, 13);
		board.g.setColor(Color.BLACK);
		board.g.fillRect((int)Math.round((old.x * boxW)+1), (int)Math.round((old.y * boxH) + 5), (int)Math.round(boxW)-1, (int)Math.round(boxH - ((boxH/5) * 2))); 
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0.getKeyCode());
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
