/**
 * 
 * Notation Parser for jessy format
 * @author Michael Vetter
 *
 */

package parsers;

import com.github.jubalh.jessy.Coord;
import com.github.jubalh.jessy.Move;
import com.github.jubalh.jessy.NotAField;
import com.github.jubalh.jessy.ParseHelper;
import com.github.jubalh.jessy.pieces.Figure;
import com.github.jubalh.jessy.pieces.Pawn;

public class JessyNotationParser extends NotationParser {

	@Override
	public Move parse(String text) throws NotAField {
		int index=0;
		Move m = null;
		ParseHelper pa = new ParseHelper();

		index = parseFigurePos(text, pa);

		// "-"  = move figure
		char c = text.charAt(index);
		if (c == '-') {
			String sub = text.substring(++index);
			ParseHelper pa2 = new ParseHelper();
			// get second figure + position
			index = parseFigurePos(sub, pa2);
			m = new Move(pa.coord, pa2.coord);
		}
		return m;
	}

	/**
	 * Parses a single Figure+Position string.
	 * @param text text in form "Ka1"
	 * @param pa ParseHelper object, to return figure and position
	 * @return number of characters that got passed, length of the string if proper.
	 */
	private int parseFigurePos(final String text, final ParseHelper pa) throws NotAField {
		int index = 0;
		char c = text.charAt(index);

		// figure
		if (isUpperCase(c)) {
			pa.figure = Figure.getFigureByChar(c);
			if (pa.figure == null)
				return index; // TODO Exception?
			index++;
		} else {
			pa.figure = new Pawn();
		}

		c = text.charAt(index);

		if (!((c >= 'a' && c <= 'h') || (c >= '1' && c <= '8')))
			throw new NotAField();

		String sub = text.substring(index, index+2);//TODO: +2..
		pa.coord = new Coord(sub);
		index += 2;

		return index;
	}

}
