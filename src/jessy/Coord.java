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
	
	public int increaseX(int amount) {
		return this.x += amount;
	}

	public int increaseY(int amount) {
		return this.y += amount;
	}
	
	public void increase(int amount) {
		increaseX(amount);
		increaseY(amount);
	}

	public int decreaseX(int amount) {
		return this.x -= amount;
	}

	public int decreaseY(int amount) {
		return this.y -= amount;
	}
	
	public void decrease(int amount) {
		decreaseX(amount);
		decreaseY(amount);
	}

	public boolean equals(final Coord coord) {
		if (coord.x == this.x && coord.y == this.y) {
			return true;
		}
		return false;
		
	}
}
