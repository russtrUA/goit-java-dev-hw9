package ua.goit.exception;

public class HttpWrongStatusException extends RuntimeException{
    public HttpWrongStatusException(String message) {
        super(message);
    }
}
