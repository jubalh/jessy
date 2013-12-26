package jessy;

import jessy.Figures;

public class Pawn extends Figures {

	public Pawn() {
		super();
	}

	public Pawn(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2659';
	}

}
