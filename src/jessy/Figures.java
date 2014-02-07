package jessy;

import jessy.Color;

public abstract class Figures {
	protected final int BLACK_SUMMAND = 6;
	private Color color;

	public Figures() {
		setColor(Color.WHITE);
	}

	public Figures(Color color) {
		setColor(color);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public boolean isBlack() {
		return this.color == Color.BLACK;
	}

	public String toString() {
		char c = getBasicUnicode();
		if ( this.isBlack() ) {
			c += BLACK_SUMMAND;
		}
		return c+"";
	}

	// force to implement this
	protected abstract char getBasicUnicode();
	public abstract boolean move(Coord coordOld, Coord coordNew);
}
