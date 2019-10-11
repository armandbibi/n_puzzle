package webEngineering.application.project.taquin;

public class Position {
	
	private int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Boolean equals(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public Boolean equals(Position p) {
		if (p == null)
			return false;
		if (this == p)
			return true;
		
		return x == p.x && y == p.y;
	}

}
