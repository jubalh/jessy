package jessy;

import jessy.Figures;

public class Bishop extends Figures {

	public Bishop() {
		super();
	}

	public Bishop(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2657';
	}

	public boolean move(Coord coordOld, Coord coordNew) {
		return true; //edit here for issue #1
	}
}
