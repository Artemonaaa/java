package hw10.models.exceptions;

public class FamilyOverflowException extends RuntimeException {
    public FamilyOverflowException(String message) {
        super(message);
    }
}