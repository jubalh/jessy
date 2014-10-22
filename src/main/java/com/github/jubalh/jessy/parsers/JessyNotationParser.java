/**
 * 
 * Notation Parser for jessy format
 * @author Michael Vetter
 *
 */

package com.github.jubalh.jessy.parsers;

import com.github.jubalh.jessy.Coord;
import com.github.jubalh.jessy.Move;
import com.github.jubalh.jessy.NotAField;
import com.github.jubalh.jessy.pieces.Figure;
import com.github.jubalh.jessy.pieces.Pawn;

public class JessyNotationParser extends NotationParser {

	@Override
	public Move parse(String text) throws NotAField {
		int index=0;
		Move m = null;
		FigureCoordContainer fc = new FigureCoordContainer();

		index = parseFigurePos(text, fc);

		// "-"  = move figure
		char c = text.charAt(index);
		if (c == '-') {
			String sub = text.substring(++index);
			FigureCoordContainer fc2 = new FigureCoordContainer();
			// get second figure + position
			index = parseFigurePos(sub, fc2);
			m = new Move(fc.coord, fc2.coord);
		}
		return m;
	}

	/**
	 * Parses a single Figure+Position string.
	 * @param text text in form "Ka1"
	 * @param pa ParseHelper object, to return figure and position
	 * @return number of characters that got passed, length of the string if proper.
	 */
	private int parseFigurePos(final String text, final FigureCoordContainer fc) throws NotAField {
		int index = 0;
		char c = text.charAt(index);

		// figure
		if (isUpperCase(c)) {
			fc.figure = Figure.getFigureByChar(c);
			if (fc.figure == null)
				return index; // TODO Exception?
			index++;
		} else {
			fc.figure = new Pawn();
		}

		c = text.charAt(index);

		if (!((c >= 'a' && c <= 'h') || (c >= '1' && c <= '8')))
			throw new NotAField();

		String sub = text.substring(index, index+2);//TODO: +2..
		fc.coord = new Coord(sub);
		index += 2;

		return index;
	}

}
