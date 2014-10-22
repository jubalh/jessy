package com.github.jubalh.jessy;

public class TempHelpClass {
	StringBuilder s;

	public TempHelpClass() {
		s = new StringBuilder();
	}

	public void addText(String text) {
		s.append(text);
	}

	public String getText() {
		return s.toString();
	}
}
