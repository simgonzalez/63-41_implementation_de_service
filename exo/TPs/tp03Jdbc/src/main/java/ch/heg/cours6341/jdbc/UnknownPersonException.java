package ch.heg.cours6341.jdbc;

public class UnknownPersonException extends Exception {
    public UnknownPersonException(String message) {
        super(message);
    }

    public UnknownPersonException(String message, Throwable cause) {
        super(message, cause);
    }
}
