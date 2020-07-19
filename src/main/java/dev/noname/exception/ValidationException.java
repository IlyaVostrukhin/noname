package dev.noname.exception;

public class ValidationException extends IllegalArgumentException {
    private static final long serialVersionUID = 5007865938185869867L;

    public ValidationException(String s) {
        super(s);
    }
}
