package ie.atu.banking.exceptions;

public class InsufficientFundsException extends Exception {
    // Constructor with a custom message
    public InsufficientFundsException(String message) {
        super(message);
    }
}