import java.util.*;


public class Ghost {
	private Point current;
	public Point oldPos;
	public Point currentOnPoint;
	public Point oldOnPoint;
	public boolean first;
	public int waitToComeOut = 25;

	public Ghost(int x, int y){
		current = new Point(x, y);
		oldPos = new Point(0, 0);
		currentOnPoint = new Point(x, y);
		oldOnPoint = new Point(0, 0);
		first = true;
	}

	public boolean bfs(Board game){
		if((waitToComeOut > 0)){
			waitToComeOut--;
			return false;
		}
		if((current.x == game.pacPosition[0] && current.y == game.pacPosition[1]) || (current.x + 1 == game.pacPosition[0] && current.y == game.pacPosition[1]) || (current.x - 1 == game.pacPosition[0] && current.y == game.pacPosition[1]) || (current.x == game.pacPosition[0] && current.y + 1 == game.pacPosition[1]) || (current.x == game.pacPosition[0] && current.y - 1 == game.pacPosition[1])){
			return true;
		}
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
				if(Main.board[top.x + x[i]][top.y + y[i]] != 1 && pointsExplored[top.x + x[i]][top.y + y[i]] == false){
					pointsExplored[top.x + x[i]][top.y + y[i]] = true;
					path[top.x + x[i]][top.y + y[i]] = top;
					next.add(new Point(top.x + x[i], top.y + y[i]));
				}
			}
		}
		Point cCheck = new Point(Board.pacPosition[0], Board.pacPosition[1]);
		ArrayList<Point> pathToTake = new ArrayList<Point>();
		do{
			//System.out.println(cCheck.x + " " + cCheck.y);
			pathToTake.add(cCheck);
			cCheck = path[cCheck.x][cCheck.y];
		}while(cCheck != current);
			oldPos = new Point(current.x, current.y);
			current = new Point(pathToTake.get(pathToTake.size() - 1).x, pathToTake.get(pathToTake.size() - 1).y);
			oldOnPoint = new Point(current.x * game.boxW, current.y * game.boxH);
			currentOnPoint = new Point(current.x * game.boxW, current.y * game.boxH);
			return false;
		}
	
	public void addX(int x){
		currentOnPoint.x += x;
		oldOnPoint.x += x; 
	}
	
	public void addY(int y){
		currentOnPoint.y += y;
		oldOnPoint.y += y; 
	}
	
	public Point getCurrent(){
		return current;
	}
}