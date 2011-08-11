package nebula.persistor;

public class PersistorException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 5570001498508497037L;

    public PersistorException() {
    }

    public PersistorException(String message) {
        super(message);
    }

    public PersistorException(Throwable cause) {
        super(cause);
    }

    public PersistorException(String message, Throwable cause) {
        super(message, cause);
    }

}
