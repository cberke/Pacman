
public class Point {
	
	public int x;
	public int y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point addX(int toAdd){
		Point add = new Point(this.x + toAdd, this.y);
		return add;
	}
	public Point addY(int toAdd){
		Point add = new Point(this.x, this.y + toAdd);
		return add;
	}
	
}
