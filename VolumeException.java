
public class VolumeException extends IllegalArgumentException {
    private String message;
    public VolumeException (String message) {
        super(message);
        this.message = message;
    }
    @Override
    public String getMessage() { return message;
    }
}
