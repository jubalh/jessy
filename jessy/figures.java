package jessy;

public final class Figures {
	public static final char PAWN = '\u2659';
	public static final char KNIGHT  = '\u2658';
	public static final char BISHOP  = '\u2657';
	public static final char ROOK  = '\u2656';
	public static final char QUEEN  = '\u2655';
	public static final char KING  = '\u2654';

	public static char getFigureByChar( final char c ) {
		switch (c) {
		case 'P':
		case '\0':
			return PAWN;
		case 'N':
			return KNIGHT;
		case 'B':
			return BISHOP;
		case 'R':
			return ROOK;
		case 'Q':
			return QUEEN;
		case 'K':
			return KING;
		}
		return ' ';
	}
}
