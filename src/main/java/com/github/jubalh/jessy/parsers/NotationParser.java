/**
 * 
 * Notation Parser
 * @author Michael Vetter
 *
 */

package com.github.jubalh.jessy.parsers;

import com.github.jubalh.jessy.Move;
import com.github.jubalh.jessy.NotAField;

public abstract class NotationParser {

	abstract public Move parse(String text) throws NotAField;

	/**
	 * Checks whether character is in upper case.
	 * @param c character
	 * @return true if upper case.
	 */
	protected static boolean isUpperCase(char c) {
		int v = (int) c;
		if (v >= (int) 'A' && v <= (int) 'Z') {
			return true;
		}
		return false;
	}
}
