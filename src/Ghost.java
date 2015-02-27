import java.util.ArrayList;
import java.util.*;


public class Ghost {
	private boolean[][] pointsExplored;
	private Point current;

	public Ghost(int x, int y){
		pointsExplored = new boolean[32][32];
		current = new Point(x, y);
	}

	public void bfs(){
		int[] x = {1, -1, 0, 0 };
		int[] y = {0, 0, 1, -1 };
		Queue<Point> next = new LinkedList<Point>();
		next.add(current);
		pointsExplored[current.x][current.y] = true;
		while(next.size() != 0){
			Point top = next.element();
			next.remove();
			System.out.println(top.x + " " + top.y);
			for(int i = 0; i < 4; i++){
				if(Main.board[top.x + x[i]][top.y + y[i]] != 1 && pointsExplored[top.x + x[i]][top.y + y[i]] == false){
					pointsExplored[top.x + x[i]][top.y + y[i]] = true;
					next.add(new Point(top.x + x[i], top.y + y[i]));
				}
			}
		}
	}
}
