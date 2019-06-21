public class AdNotFoundException extends Exception {
    String message;
    public AdNotFoundException(String message) {
        super(message);
        this.message=message;
    }
}
