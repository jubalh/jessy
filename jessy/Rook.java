package jessy;

import jessy.Figures;

public class Rook extends Figures {

	public Rook() {
		super();
	}

	public Rook(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2656';
	}
}
