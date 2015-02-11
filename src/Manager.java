import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//37 = left
//38 = up
//39 = right
//40 = down

public class Manager implements KeyListener {
	Board board;
	private int key;
	public Manager(){
		board = new Board();
		setUP();
	}
	public void setUP(){
		board.panel.addKeyListener(this);
	}public void playGame(){
		//while();
	}public void Graphic(){
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0.getKeyCode());
		key = arg0.getKeyCode();
	}
	
	public void keyReleased(KeyEvent e) { }
	
	public void keyTyped(KeyEvent e) { } 

}
