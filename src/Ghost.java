import java.util.*;


public class Ghost {
	private Point current;

	public Ghost(int x, int y){
		current = new Point(x, y);
	}

	public void bfs(Board game){
		boolean[][] pointsExplored = new boolean[32][32];
		Point[][] path = new Point[32][32];
		path[current.x][current.y] = new Point(current.x, current.y);
		int[] x = {1, -1, 0, 0 };
		int[] y = {0, 0, 1, -1 };
		Queue<Point> next = new LinkedList<Point>();
		next.add(current);
		pointsExplored[current.x][current.y] = true;
		while(next.size() != 0){
			Point top = next.element();
			next.remove();
			for(int i = 0; i < 4; i++){
				if(Main.board[top.x + x[i]][top.y + y[i]] != 1 && Main.board[top.x + x[i]][top.y + y[i]] != 8 && pointsExplored[top.x + x[i]][top.y + y[i]] == false){
					pointsExplored[top.x + x[i]][top.y + y[i]] = true;
					path[top.x + x[i]][top.y + y[i]] = top;
					next.add(new Point(top.x + x[i], top.y + y[i]));
				}
			}
		}
		Point cCheck = new Point(Board.pacPosition[0], Board.pacPosition[1]);
		ArrayList<Point> pathToTake = new ArrayList<Point>();
		do{
			System.out.println(cCheck.x + " " + cCheck.y);
			pathToTake.add(cCheck);
				cCheck = path[cCheck.x][cCheck.y];
		}while(cCheck != current);
			game.DrawGhost(pathToTake.get(pathToTake.size() - 1));
			current = pathToTake.get(pathToTake.size() - 1);
	}
}