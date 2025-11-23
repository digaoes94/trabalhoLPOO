package data.exceptions;

public class DBExceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DBExceptions(String msg) {
		super(msg);
	}
}
