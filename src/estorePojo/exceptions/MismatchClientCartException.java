package estorePojo.exceptions;

public class MismatchClientCartException extends Exception {
    private static final long serialVersionUID = -5678524790558683191L;

    public MismatchClientCartException(String message) {
        super(message);
    }
}
