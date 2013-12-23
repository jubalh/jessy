package jessy;

public class NotAField extends Exception {
	public NotAField() {
	}

	public NotAField(int x, int y) {
		System.err.println("Not a field: x" + x + " y" +y);
	}
}
