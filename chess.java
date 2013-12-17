import static java.lang.System.out;

public final class chess {
    private chess() { }

    public static void main(final String[] args) {
		char [] [] ar = new char [8][8];

		ar[1][2] = '\u2654';
		System.out.println(ar.length);
		drawBoard(ar);
    }

	public static void drawBoard(char[][] board) {
		for ( char[] col : board ) {
			for ( char row : col ) {
				System.out.print("[" + row + " ]");
			}
			System.out.println();
		}
	}

}

