package jessy;

import jessy.Figures;

public class Knight extends Figures {

	public Knight() {
		super();
	}

	public Knight(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2658';
	}

	public boolean move(Coord coordOld, Coord coordNew) {
		return true; //edit here for issue #1
	}
}
