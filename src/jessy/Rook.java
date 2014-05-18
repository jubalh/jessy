package jessy;

import jessy.Figure;

public final class Rook extends Figure {

	public Rook() {
		super();
	}

	public Rook(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2656';
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

	public boolean canMove(Board board, Coord coordCurrent, Coord coordNew) {
		try {
			//up
			if (coordNew.getX() == coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
				while(coordNew.getY() > coordCurrent.getY()) {
					coordCurrent.increaseY(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
			//right
			else if (coordNew.getX() > coordCurrent.getX() && coordNew.getY() == coordCurrent.getY()) {
				while(coordNew.getX() > coordCurrent.getX()) {
					coordCurrent.increaseX(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
			//down
			else if (coordNew.getX() == coordCurrent.getX() && coordNew.getY() < coordCurrent.getY()) {
				while(coordNew.getY() < coordCurrent.getY()) {
					coordCurrent.decreaseY(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
			//left
			else if (coordNew.getX() < coordCurrent.getX() && coordNew.getY() == coordCurrent.getY()) {
				while(coordNew.getX() < coordCurrent.getX()) {
					coordCurrent.decreaseY(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
	    } catch (NotAField ex) {
			// TODO
		}

		return false;
	}
}
