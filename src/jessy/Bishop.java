package jessy;

import jessy.Figure;

public final class Bishop extends Figure {

	public Bishop() {
		super();
	}

	public Bishop(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2657';
	}

	private boolean isAllowedMove(final Board board, final Coord coordCurrent,
			final Coord coordNew) throws NotAField {
		if (!board.isEmptyField(coordCurrent)) {
			if (coordCurrent.equals(coordNew)) {
				if (board.isOpponentField(coordCurrent, this)) {
					return true;//catching opponent
				}
			}
			return false;
		}
		return true;
	}

	public boolean move(final Board board, Coord coordCurrent, Coord coordNew) {
		try {
			// up-right diagonal
			if (coordNew.getX() > coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
				while (coordNew.getX() > coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
					coordCurrent.increase(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
			// up-left diagonal
			else if (coordNew.getX() < coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
				while (coordNew.getX() < coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
					coordCurrent.increaseY(1);
					coordCurrent.decreaseX(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
			// down-left diagonal
			else if (coordNew.getX() < coordCurrent.getX() && coordNew.getY() < coordCurrent.getY()) {
				while (coordNew.getX() < coordCurrent.getX() && coordNew.getY() < coordCurrent.getY()) {
					coordCurrent.decreaseY(1);
					coordCurrent.decreaseX(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
			// down-right diagonal
			else if (coordNew.getX() > coordCurrent.getX() && coordNew.getY() < coordCurrent.getY()) {
				while (coordNew.getX() > coordCurrent.getX() && coordNew.getY() < coordCurrent.getY()) {
					coordCurrent.decreaseY(1);
					coordCurrent.increaseX(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
		} catch (NotAField ex) {
			// TODO
		}

		return false; // edit here for issue #1
	}
}
