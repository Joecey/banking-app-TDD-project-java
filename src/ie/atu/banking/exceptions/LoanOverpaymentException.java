package ie.atu.banking.exceptions;

public class LoanOverpaymentException extends RuntimeException {
    public LoanOverpaymentException(String message) {
        super(message);
    }
}
