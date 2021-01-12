package ruz;

public class RuzApiException extends Exception {
    public RuzApiException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
