package jessy;

public final class Board {

	private char [] [] matrix = new char [8][8];

	public boolean setFigure(int x, int y, final char figure) {
		x--; y--;
		if ( x >= 0 && x < matrix.length
				&& y >= 0 && y < matrix[x].length ) {
			matrix[x][y] = figure;
			return true;
		}
		return false;
	}

	public void drawBoard() {
		for ( char[] col : matrix ) {
			for ( char row : col ) {
				System.out.print("[" + row + " ]");
			}
			System.out.println();
		}
	}

}
