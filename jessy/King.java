package jessy;

import jessy.Figures;

public class King extends Figures {

	public King() {
		super();
	}

	public King(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2654';
	}

	public boolean move(Coord coordOld, Coord coordNew) {
		return true; //edit here for issue #1
	}
}
