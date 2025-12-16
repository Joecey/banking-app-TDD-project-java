package ie.atu.banking.exceptions;

public class IncorrectFundParameters extends RuntimeException {
    public IncorrectFundParameters(String message) {
        super(message);
    }
}
