package pl.teo.azuredemoapp.exception;

public class ApiValidationException extends RuntimeException {
    public ApiValidationException(String message) {
        super(message);
    }
}
