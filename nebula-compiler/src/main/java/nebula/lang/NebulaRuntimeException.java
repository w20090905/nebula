package nebula.lang;

public class NebulaRuntimeException  extends RuntimeException {

	private static final long serialVersionUID = -958514539252191505L;

	public NebulaRuntimeException() {
		super();
	}

	public NebulaRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NebulaRuntimeException(String message) {
		super(message);
	}

	public NebulaRuntimeException(Throwable cause) {
		super(cause);
	}

}
