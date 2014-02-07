package jessy;

public final class Coord {
	int x;
	int y;

	public Coord() {
	}

	public Coord(int x, int y) {
		setX(x);
		setY(y);
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
