import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//37 = left
//38 = up
//39 = right
//40 = down

public class Manager implements KeyListener {
	Board board;
	public static boolean isGaming;
	//private boolean inBox;
	private int key;
	
	public Manager(){
		board = new Board();
		//inBox = true;
		setUP();
		//isGaming = true;
	}
	
	public void setUP(){
		board.panel.addKeyListener(this);
	}
	
	public void playGame(){
		key = 37;
		System.out.print(key);
		while(board.panel.getVisible()){
			graphic(key);
		}
	}
	
	public void graphic(int direction){
		int boxH = (int) board.boxH;
		int boxW = (int) board.boxW;
		
		if(direction == 37) { //left
			//System.out.print("Here");
			if(Main.board[board.pacPosition[0]][board.pacPosition[1]-1] != 1  && Main.board[board.pacPosition[0]][board.pacPosition[1]-1] != 8){
				//System.out.print("Here");
				Main.board[board.pacPosition[0]][board.pacPosition[1]-1] = 3;
				Main.board[board.pacPosition[0]][board.pacPosition[1]] = 2;
				for(int i = 0; i < boxW; i++){
					//System.out.println(direction);
					//blank space
					board.g.setColor(Color.YELLOW);
					board.g.fillOval((int)Math.round((board.pacPosition[1] * boxW)) + 6 - i, (int)Math.round((board.pacPosition[0] * boxH)) + 5, 13, 13);
					waiting();
					board.g.setColor(Color.BLACK);
					board.g.fillRect((int)Math.round((board.pacPosition[1] * boxW)+1) - i, (int)Math.round((board.pacPosition[0] * boxH) + 5), (int)Math.round(boxW)-1, (int)Math.round(boxH - ((boxH/5) * 2)));
					
				}board.display2();
			}
		} else if(direction == 38) { //up
			if(Main.board[board.pacPosition[0]-1][board.pacPosition[1]] != 1  && Main.board[board.pacPosition[0]-1][board.pacPosition[1]] != 8){
				//System.out.print("Here");
				Main.board[board.pacPosition[0]-1][board.pacPosition[1]] = 3;
				Main.board[board.pacPosition[0]][board.pacPosition[1]] = 2;
				for(int i = 0; i < boxH; i++){
					//System.out.println(direction);
					//blank space
					board.g.setColor(Color.YELLOW);
					board.g.fillOval((int)Math.round((board.pacPosition[1] * boxW)) + 6, (int)Math.round((board.pacPosition[0] * boxH)) + 5 - i, 13, 13);
					waiting();
					board.g.setColor(Color.BLACK);
					board.g.fillRect((int)Math.round((board.pacPosition[1] * boxW)+1), (int)Math.round((board.pacPosition[0] * boxH) + 5 - i), (int)Math.round(boxW)-1, (int)Math.round(boxH - ((boxH/5) * 2)));
					
				}board.display2();
			}
		} else if(direction == 39) { //right
			if(Main.board[board.pacPosition[0]][board.pacPosition[1]+1] != 1  && Main.board[board.pacPosition[0]][board.pacPosition[1]-1] != 8){
				//System.out.print("Here");
				Main.board[board.pacPosition[0]][board.pacPosition[1]+1] = 3;
				Main.board[board.pacPosition[0]][board.pacPosition[1]] = 2;
				for(int i = 0; i <boxW; i++){
					//System.out.println(direction);
					//blank space
					board.g.setColor(Color.YELLOW);
					board.g.fillOval((int)Math.round((board.pacPosition[1] * boxW)) + 6 + i, (int)Math.round((board.pacPosition[0] * boxH)) + 5, 13, 13);
					waiting();
					board.g.setColor(Color.BLACK);
					board.g.fillRect((int)Math.round((board.pacPosition[1] * boxW)+1 + i), (int)Math.round((board.pacPosition[0] * boxH) + 5), (int)Math.round(boxW)-1, (int)Math.round(boxH - ((boxH/5) * 2)));
					
				}board.display2();
			}
		} else if(direction == 40) {//down
			if(Main.board[board.pacPosition[0]+1][board.pacPosition[1]] != 1  && Main.board[board.pacPosition[0]+1][board.pacPosition[1]] != 8){
				//System.out.print("Here");
				Main.board[board.pacPosition[0]+1][board.pacPosition[1]] = 3;
				Main.board[board.pacPosition[0]][board.pacPosition[1]] = 2;
				for(int i = 0; i <boxH; i++){
					//System.out.println(direction);
					//blank space
					board.g.setColor(Color.YELLOW);
					board.g.fillOval((int)Math.round((board.pacPosition[1] * boxW)) + 6, (int)Math.round((board.pacPosition[0] * boxH)) + 5 + i, 13, 13);
					waiting();
					board.g.setColor(Color.BLACK);
					board.g.fillRect((int)Math.round((board.pacPosition[1] * boxW)+1), (int)Math.round((board.pacPosition[0] * boxH) + 5 + i), (int)Math.round(boxW)-1, (int)Math.round(boxH - ((boxH/5) * 2)));
					
				}board.display2();
			}
		}  
	}
	
	public void DrawGhost(Point gCoord){
		//Rewrite code to be better
		board.g.setColor(Color.CYAN);
		board.g.fillRect((int)gCoord.y * (int)board.boxW, (int)gCoord.x * (int)board.boxH, 10, 10);
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
